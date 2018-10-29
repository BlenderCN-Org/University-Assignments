package Classes;

import Globals.HotelHelper;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Semaphore;


/**
 * @semaphoreHashMap - Global HashMap shared by all classes
 * @clerkGuestHashMap - Used to handle specific interactions between guests and clerks
 * @bellhopGuestHashMap - Used to handle specific interactions between guests and bellhops
 * @toStringId, @guestNo, @clerkNo, @bellhopNo, @bags - Defining variables of the class
 * @helper - Global helper function used among all classes
 */
public class Guest extends Thread {

    private HashMap<String, Semaphore> semaphoreHashMap;
    private HashMap<String, Semaphore> clerkGuestHashMap;
    private HashMap<String, Semaphore> bellhopGuestHashMap;

    private Semaphore mutex;

    private String toStringId;
    private int guestNo;
    private int clerkNo;
    private int bellhopNo;
    private int bags;

    int roomNum;

    private HotelHelper helper;


    /**
     * @param guestNo - The 'relative' guest number used in output
     * @param helper  - Global helper function shared among all classes
     *                <p>
     *                Initializes the guest object
     */
    public Guest(Integer guestNo, HotelHelper helper) throws InterruptedException {

        this.helper = helper;
        this.semaphoreHashMap = helper.initializeSemaphoreHashMap();

        this.mutex = new Semaphore(1, true);
        this.mutex.acquire();

        Random random = new Random();
        random.setSeed(100);

        this.guestNo = guestNo;
        this.clerkNo = -1;

        this.bags = new Random().nextInt(5);

        this.toStringId = "Guest " + guestNo + ": ";

    }

    /**
     * @param clerkGuestHashMap - Sets the clerkGuestHashMap of this thread to that
     *                          of whatever is passed into it
     */
    public void setClerkGuestHashMap(HashMap<String, Semaphore> clerkGuestHashMap) {
        this.clerkGuestHashMap = clerkGuestHashMap;
    }

    /**
     * @param bellhopGuestHashMap - Sets the bellhopGuestHashMap of this thread to that
     *                            of whatever is passed into it
     */
    public void setBellhopGuestHashMap(HashMap<String, Semaphore> bellhopGuestHashMap) {
        this.bellhopGuestHashMap = bellhopGuestHashMap;
    }

    /**
     *
     * @return - Returns the guest number
     */
    public int getGuestNo() {
        return guestNo;
    }

    /**
     *
     * @param roomNum - Room number to set
     */
    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    /**
     *
     * @param clerkNo - ClerkNo to set
     */
    public void setClerkNo(int clerkNo) {
        this.clerkNo = clerkNo;
    }

    /**
     * Acts as a mutex, handles interaction between classes so that the guests and
     * clerks get assigned correctly. Used outside of this class.
     */
    public void triggerMutex() {
        if (mutex.availablePermits() == 0) {
            mutex.release();
        } else {
            try {
                mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Runs the thread, contains the main code for handling interactions between the objects.
     * In particular, each thread waits on *thread - 3* threads to complete. This simulates the
     * queueing system. Each guest enters the hotel, but cannot go out of line. Thread is killed
     * at the end of the run method.
     */
    @Override
    public void run() {
        try {
            printStringToConsole(toStringId, "has entered the hotel with " + bags + " bags");

            this.semaphoreHashMap.get("clerks").acquire();

            this.mutex.acquire();

            printStringToConsole(toStringId, "is approaching [Clerk ", clerkNo + "", "]");

            this.clerkGuestHashMap.get("forRoom").acquire();
            printStringToConsole(toStringId, "has gotten the room number " + roomNum + " from [Clerk ", clerkNo + "", "]");

            this.clerkGuestHashMap.get("hasRoom").release();

            this.clerkGuestHashMap.get("forKey").acquire();
            printStringToConsole(toStringId, "has gotten the room key from [Clerk ", clerkNo + "", "]");

            this.clerkGuestHashMap.get("hasKey").release();

            this.clerkGuestHashMap.get("completed").acquire();

            if (this.bags > 2) {

                this.semaphoreHashMap.get("bellhops").acquire();

                bellhopNo = helper.newBellhopGuestHashMap(guestNo);

                printStringToConsole(toStringId, "is approaching [Bellhop ", bellhopNo + "", "]");

                printStringToConsole(toStringId, "has given their bags to [Bellhop ", bellhopNo + "", "]");

                this.bellhopGuestHashMap.get("hasBags").acquire();

                this.bellhopGuestHashMap.get("guestNeedsBags").release();
            }

            printStringToConsole(toStringId, "has gone to their room");

            if (this.bags > 2) {
                this.bellhopGuestHashMap.get("inRoom").release();

                this.bellhopGuestHashMap.get("giveBags").acquire();
                printStringToConsole(toStringId, "has gotten their bags from [Bellhop ", bellhopNo + "", "]");

                printStringToConsole(toStringId, "has given a tip to [Bellhop ", bellhopNo + "", "]");
                this.bellhopGuestHashMap.get("giveTip").release();

                this.bellhopGuestHashMap.get("gotTip").acquire();
            }

            printStringToConsole(toStringId, "has retired for the night");
            this.helper.guestRetired(guestNo);
            this.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param strings - Variable Strings as input, allows for multiple input
     *                <p>
     *                This function concats the strings in an output message and prints at the
     *                same time using StringBuilder so that the async nature of String concat is
     *                held.
     */
    private void printStringToConsole(String... strings) throws InterruptedException {
        this.semaphoreHashMap.get("lock").acquire();
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }

        System.out.println(sb.toString());
        this.semaphoreHashMap.get("lock").release();

    }
}
