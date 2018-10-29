package Globals;

import Classes.Bellhop;
import Classes.Clerk;
import Classes.Guest;

import java.util.*;
import java.util.concurrent.Semaphore;

/**
 * @clerkArrayList - ArrayList to hold the all of the clerks for easy access
 * @bellhopArrayList - ArrayList to hold the all of the bellhops for easy access
 * @guestArrayList - ArrayList to hold the all of the guests for easy access
 * @globalHashMap - HashMap of semaphores that contain the semaphores needed
 * to coordinate the different classes. [Key] is the name of
 * the semaphore for easy reading.
 * @availableClerks - Queue to order the clerks correctly
 * @availableBellhops - Queue to order bellhops properly
 * @retiredArrayList - ArrayList of all the retired guests for debugging
 */
public class HotelHelper {

    private ArrayList<Clerk> clerkArrayList = new ArrayList<>();
    private ArrayList<Bellhop> bellhopArrayList = new ArrayList<>();
    private ArrayList<Guest> guestArrayList = new ArrayList<>();

    private HashMap<String, Semaphore> globalHashMap = new HashMap<>();

    private Queue<Guest> availableGuests = new LinkedList<>();
    private Queue<Clerk> availableClerks = new LinkedList<>();
    private Queue<Bellhop> availableBellhops = new LinkedList<>();
    private ArrayList<Integer> retiredArrayList = new ArrayList<>();

    private int room = 1;

    /**
     * @param clerkNo - The clerk Number that we want to generate this for
     *                <p>
     *                Creates a HashMap of the semaphores needed for each unique clerk/guest
     *                interaction to properly schedule their text interactions. Also searches
     *                through the clerkArrayList to find the proper free guest and assign it
     *                to the clerk.
     * @return - Returns the guests's number to the clerk who called this function
     * so that it can reference it inside its own class
     * @throws InterruptedException - When acquiring from a semaphore
     */
    public int newClerkGuestHashMap(int clerkNo) throws InterruptedException {
        HashMap<String, Semaphore> hashMap = new HashMap<>();

        hashMap.put("forRoom", new Semaphore(1, true));
        hashMap.put("hasRoom", new Semaphore(1, true));
        hashMap.put("forKey", new Semaphore(1, true));
        hashMap.put("hasKey", new Semaphore(1, true));
        hashMap.put("completed", new Semaphore(1, true));

        hashMap.get("forRoom").acquire();
        hashMap.get("hasRoom").acquire();
        hashMap.get("forKey").acquire();
        hashMap.get("hasKey").acquire();
        hashMap.get("completed").acquire();

        int guestNo = getNextGuest().getGuestNo() - 1;
        guestArrayList.get(guestNo).setClerkGuestHashMap(hashMap);
        guestArrayList.get(guestNo).setClerkNo(clerkNo);
        guestArrayList.get(guestNo).triggerMutex();
        guestArrayList.get(guestNo).setRoomNum(getRoom());
        incRoom();

        clerkArrayList.get(clerkNo - 1).setClerkGuestHashMap(hashMap);

        return guestNo + 1;
    }

    /**
     * @param guestNo - The Guest Number that we want to generate this for
     *                <p>
     *                Creates a HashMap of the semaphores needed for each unique bellhop/guest
     *                interaction to properly schedule their text interactions. Also searches
     *                through the bellhopArrayList to find the proper free bellhop and assign it
     *                to the guest.
     * @return - Returns the clerk's number to the guest who called this function
     * so that it can reference it inside its own class
     * @throws InterruptedException - When acquiring from a semaphore
     */
    public int newBellhopGuestHashMap(int guestNo) throws InterruptedException {
        HashMap<String, Semaphore> hashMap = new HashMap<>();

        hashMap.put("guestNeedsBags", new Semaphore(1, true));
        hashMap.put("hasBags", new Semaphore(1, true));
        hashMap.put("inRoom", new Semaphore(1, true));
        hashMap.put("giveBags", new Semaphore(1, true));
        hashMap.put("giveTip", new Semaphore(1, true));
        hashMap.put("gotTip", new Semaphore(1, true));

        hashMap.get("guestNeedsBags").acquire();
        hashMap.get("hasBags").acquire();
        hashMap.get("inRoom").acquire();
        hashMap.get("giveBags").acquire();
        hashMap.get("giveTip").acquire();
        hashMap.get("gotTip").acquire();

        int bellhopLoc = getFreeBellhop();
        bellhopArrayList.get(bellhopLoc).setBellhopGuestHashMap(hashMap);
        bellhopArrayList.get(bellhopLoc).setGuestNo(guestNo);
        bellhopArrayList.get(bellhopLoc).triggerMutex();

        guestArrayList.get(guestNo - 1).setBellhopGuestHashMap(hashMap);

        return bellhopLoc + 1;
    }

    /**
     * Pops from the clerkQueue for the next free clerk and sets it
     * to being used.
     *
     * @return - Returns the clerk who is being used by the guest who called this
     * function, else returns -1 if there is an error.
     */
    private int getFreeClerk() {
        if (availableClerks.size() > 0)
            return availableClerks.remove().getClerkNo() - 1;
        else
            return -1;
    }

    /**
     * Pops form the bellhopQueue for the next free bellhop and sets it
     * to being used.
     *
     * @return - Returns the bellhop who is being used by the guest who called this
     * function, else returns -1 if there is an error.
     */
    private int getFreeBellhop() {
        if (availableBellhops.size() > 0)
            return availableBellhops.remove().getBellhopNo() - 1;
        else
            return -1;
    }

    /**
     * Pops from the guest queue for the next free guest and sets it
     * to being used.
     *
     * @return - Returns the guest who is next in line
     */
    private Guest getNextGuest() {
        if (availableGuests.size() > 0)
            return availableGuests.remove();
        else
            return null;
    }

    /**
     * @return - Returns the size of the availableGuests, used outside of this class
     */
    public int getAvailableGuestsSize() {
        return availableGuests.size();
    }

    /**
     * @param c - Clerk to add to the queue
     */
    public void addToClerkQueue(Clerk c) {
        availableClerks.add(c);
    }

    /**
     * @param b - Bellhop to add to the queue
     */
    public void addToBellhopQueue(Bellhop b) {
        availableBellhops.add(b);
    }

    /**
     * @param g - Guest to add to the queue
     */
    public void addToGuestQueue(Guest g) {
        availableGuests.add(g);
    }

    /**
     * @param c - Some clerk as input, adds to the clerkArrayList
     */
    public void addToClerks(Clerk c) {
        clerkArrayList.add(c);
    }

    /**
     * @param b - Some bellhop as input, adds to the bellhopArrayList
     */
    public void addToBellhops(Bellhop b) {
        bellhopArrayList.add(b);
    }

    /**
     * @param g - Some guest as input, adds to the guestArrayList
     */
    public void addToGuests(Guest g) {
        guestArrayList.add(g);
    }

    /**
     * @param loc - Some the clerk at location loc from clerkArrayList
     */
    public Clerk getClerk(Integer loc) {
        return clerkArrayList.get(loc);
    }

    /**
     * @param loc - Some the bellhop at location loc from bellhopArrayList
     */
    public Bellhop getBellhop(Integer loc) {
        return bellhopArrayList.get(loc);
    }

    /**
     * @param loc - Some the guest at location loc from guestArrayList
     */
    public Guest getGuest(Integer loc) {
        return guestArrayList.get(loc);
    }

    /**
     * @return - returns the room number
     */
    private int getRoom() {
        return room;
    }

    private void incRoom() {
        room++;
    }

    /**
     * Creates a HashMap of the semaphores needed for the global interaction
     * of all the subclasses to properly schedule their text interactions.
     *
     * @throws InterruptedException - For semaphore.acquire()
     */
    public void generateGlobalHashMap() throws InterruptedException {
        HashMap<String, Semaphore> hashMap = new HashMap<>();

        hashMap.put("guests", new Semaphore(25, true));
        hashMap.put("clerks", new Semaphore(2, true));
        hashMap.put("bellhops", new Semaphore(2, true));
        hashMap.put("lock", new Semaphore(1, true));

//        hashMap.get("guests").acquire(25);
        hashMap.get("clerks").acquire(2);
        hashMap.get("bellhops").acquire(2);

        globalHashMap = hashMap;
    }

    /**
     * @return - Returns the globalHashMap, used outside of this class
     */
    public HashMap<String, Semaphore> initializeSemaphoreHashMap() {
        return globalHashMap;
    }

    /**
     * Adds the guest to the retired guests, used for debug
     */
    public void guestRetired(Integer guestNo) {
        retiredArrayList.add(guestNo);
    }

    /**
     * @return - Returns the total number of retired guests, used for debug
     */
    public int getTotalRetired() {
        return retiredArrayList.size();
    }

    /**
     * @return - Returns the retiredGuestArrayList, used for debug
     */
    public ArrayList<Integer> getRetiredArrayList() {
        Collections.sort(retiredArrayList);
        return retiredArrayList;
    }
}
