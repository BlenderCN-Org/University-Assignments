package Classes;

import Globals.HotelHelper;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

/**
 * @semaphoreHashMap - Global HashMap shared by all classes
 * @clerkGuestHashMap - Used to handle specific interactions between guests and bellhops
 * @mutex - Used to make sure guests obtain the correct bellhop
 * @toStringId, @guestNo, @beingUsed - Defining variables of the class
 */
public class Clerk extends Thread {

    private HashMap<String, Semaphore> semaphoreHashMap;
    private HashMap<String, Semaphore> clerkGuestHashMap;

    private Semaphore mutex;

    private String toStringId;
    private int guestNo;
    private int clerkNo;

    private HotelHelper helper;

    /**
     * @param clerkNo - The 'relative' clerk number used in output
     * @param helper  - Global helper function shared among all classes
     * @throws InterruptedException - For semaphore.acquire()
     */
    public Clerk(Integer clerkNo, HotelHelper helper) throws InterruptedException {
        this.helper = helper;
        this.mutex = new Semaphore(1, true);
        this.mutex.acquire();

        this.semaphoreHashMap = helper.initializeSemaphoreHashMap();
        this.toStringId = "Clerk " + clerkNo + ": ";
        this.clerkNo = clerkNo;
        this.guestNo = -1;
    }
    /**
     * @param clerkGuestHashMap - Sets the clerkGuestHashMap of this thread to that
     *                          of whatever is passed into it
     */
    public void setClerkGuestHashMap(HashMap<String, Semaphore> clerkGuestHashMap) {
        this.clerkGuestHashMap = clerkGuestHashMap;
    }

    /**
     * @param guestNo - A guest number used to give a guest number to output
     */
    public void setGuestNo(Integer guestNo) {
        this.guestNo = guestNo;
    }

    /**
     * Runs the thread, contains the main code for handling interactions between the objects.
     */
    @Override
    public void run() {
         while (this.isAlive()) {
            try {
                this.semaphoreHashMap.get("lock").acquire();
                this.guestNo = -1;
                this.clerkGuestHashMap = null;
                this.semaphoreHashMap.get("clerks").release();
                this.helper.addToClerkQueue(this);
                this.semaphoreHashMap.get("lock").release();

                printStringToConsole(toStringId, "is ready to help a guest");

                this.semaphoreHashMap.get("guests").acquire();

                this.semaphoreHashMap.get("lock").acquire();
                this.guestNo = helper.newClerkGuestHashMap(clerkNo);
                this.semaphoreHashMap.get("lock").release();

//                this.mutex.acquire();
                printStringToConsole(toStringId, "is helping [Guest ", guestNo + "", "]");

                printStringToConsole(toStringId, "has found a room for [Guest ", guestNo + "", "]");
                this.clerkGuestHashMap.get("forRoom").release();

                this.clerkGuestHashMap.get("hasRoom").acquire();

                printStringToConsole(toStringId, "has found a key for [Guest ", guestNo + "", "]");
                this.clerkGuestHashMap.get("forKey").release();

                this.clerkGuestHashMap.get("hasKey").acquire();

                this.clerkGuestHashMap.get("completed").release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getClerkNo() {
        return clerkNo;
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

    /**
     * @return - toString for debug use only
     */
    @Override
    public String toString() {
        return toStringId;
    }
}
