package interfaces;

import Classes.Bellhop;
import Classes.Clerk;
import Classes.Guest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class HotelHelper {

    private ArrayList<Clerk> clerkArrayList = new ArrayList<>();
    private ArrayList<Bellhop> bellhopArrayList = new ArrayList<>();
    private ArrayList<Guest> guestArrayList = new ArrayList<>();
    private HashMap<String, Semaphore> globalHashMap = new HashMap<>();

    private ArrayList<Integer> retiredArrayList = new ArrayList<>();

    public int newClerkGuestHashMap(int guestNo) throws InterruptedException {
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

        int clerkLoc = getFreeClerk();

        clerkArrayList.get(clerkLoc).setClerkGuestHashMap(hashMap);
        clerkArrayList.get(clerkLoc).setGuestNo(guestNo);
        clerkArrayList.get(clerkLoc).triggerMutex();

        guestArrayList.get(guestNo - 1).setClerkGuestHashMap(hashMap);

        return clerkLoc + 1;
    }

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

    private int getFreeClerk() {
        for (int i = 0; i < clerkArrayList.size(); i++) {
            if (!clerkArrayList.get(i).isUsed()) {
                clerkArrayList.get(i).setUsed(true);
                return i;
            }
        }
        return -1;
    }

    private int getFreeBellhop() {
        for (int i = 0; i < bellhopArrayList.size(); i++) {
            if (!bellhopArrayList.get(i).isUsed()) {
                bellhopArrayList.get(i).setUsed(true);
                return i;
            }
        }
        return -1;
    }

    public void addToClerks(Clerk c) {
        clerkArrayList.add(c);
    }

    public void addToBellhops(Bellhop b) {
        bellhopArrayList.add(b);
    }

    public void addToGuests(Guest g) {
        guestArrayList.add(g);
    }

    public Clerk getClerk(Integer loc) {
        return clerkArrayList.get(loc);
    }

    public Bellhop getBellhop(Integer loc) {
        return bellhopArrayList.get(loc);
    }

    public Guest getGuest(Integer loc) {
        return guestArrayList.get(loc);
    }

    public void generateGlobalHashMap() throws InterruptedException {
        HashMap<String, Semaphore> hashMap = new HashMap<>();

        hashMap.put("guests", new Semaphore(25, true));
        hashMap.put("guestSync", new Semaphore(1, true));
        hashMap.put("clerks", new Semaphore(2, true));
        hashMap.put("clerkSync", new Semaphore(1, true));
        hashMap.put("bellhops", new Semaphore(2, true));
        hashMap.put("guestBellhopSync", new Semaphore(1, true));
        hashMap.put("lock", new Semaphore(1, true));
        hashMap.put("bellhopSync", new Semaphore(1, true));

        hashMap.put("clerkVars", new Semaphore(1, true));
        hashMap.put("bellhopVars", new Semaphore(1, true));

        hashMap.put("guestClerkMutex", new Semaphore(1, true));
        hashMap.put("guestBellhopMutex", new Semaphore(1, true));

        hashMap.get("guests").acquire(25);
        hashMap.get("clerks").acquire(2);
        hashMap.get("bellhops").acquire(2);
        hashMap.get("clerkSync").acquire();
        hashMap.get("bellhopSync").acquire();

        globalHashMap = hashMap;
    }

    public HashMap<String, Semaphore> initializeSemaphoreHashMap() {
        return globalHashMap;
    }

    public void guestRetired(Integer guestNo) {
        retiredArrayList.add(guestNo);
    }

    public int getTotalRetired() {
        return retiredArrayList.size();
    }

    public ArrayList<Integer> getRetiredArrayList() {
        Collections.sort(retiredArrayList);
        return retiredArrayList;
    }
}
