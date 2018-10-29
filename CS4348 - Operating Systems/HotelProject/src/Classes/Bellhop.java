package Classes;

import Globals.HotelHelper;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

/**
 * @semaphoreHashMap - Global HashMap shared by all classes
 * @bellhopGuestHashMap - Used to handle specific interactions between guests and bellhops
 * @mutex - Used to make sure guests obtain the correct bellhop
 * @toStringId, @guestNo, @beingUsed - Defining variables of the class
 */
public class Bellhop extends Thread {
    private HashMap<String, Semaphore> semaphoreHashMap;
    private HashMap<String, Semaphore> bellhopGuestHashMap;

    private Semaphore mutex;

    private String toStringId;
    private int guestNo;
    private int bellhopNo;

    private HotelHelper helper;

    /**
     * @param bellhopNo - The 'relative' bellhop number used in output
     * @param helper    - Global helper function shared among all classes
     * @throws InterruptedException - For semaphore.acquire()
     */
    public Bellhop(Integer bellhopNo, HotelHelper helper) throws InterruptedException {
        this.helper = helper;
        this.mutex = new Semaphore(1, true);
        this.mutex.acquire();

        this.semaphoreHashMap = helper.initializeSemaphoreHashMap();
        this.toStringId = "Bellhop " + bellhopNo + ": ";
        this.bellhopNo = bellhopNo;
        this.guestNo = -1;
    }

    public int getBellhopNo() {
        return bellhopNo;
    }

    /**
     * @param bellhopGuestHashMap - Sets the bellhopGuestHashMap of this thread to that
     *                            of whatever is passed into it
     */
    public void setBellhopGuestHashMap(HashMap<String, Semaphore> bellhopGuestHashMap) {
        this.bellhopGuestHashMap = bellhopGuestHashMap;
    }

    /**
     * @param guestNo - A guest number used to give a guest number to output
     */
    public void setGuestNo(Integer guestNo) {
        this.guestNo = guestNo;
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
     */
    @Override
    public void run() {
        while (this.isAlive()) {
            try {
                this.semaphoreHashMap.get("lock").acquire();
                this.guestNo = -1;
                this.bellhopGuestHashMap = null;
                this.semaphoreHashMap.get("bellhops").release();
                this.helper.addToBellhopQueue(this);
                this.semaphoreHashMap.get("lock").release();

                printStringToConsole(toStringId, "is ready to help a guest");

                this.mutex.acquire();

                printStringToConsole(toStringId, "has obtained the bags of [Guest ", guestNo + "", "]");
                this.bellhopGuestHashMap.get("hasBags").release();

                this.bellhopGuestHashMap.get("guestNeedsBags").acquire();

                this.bellhopGuestHashMap.get("inRoom").acquire();
                printStringToConsole(toStringId, "has entered the room of [Guest ", guestNo + "", "]");

                printStringToConsole(toStringId, "has given the bags to [Guest ", guestNo + "", "]");
                this.bellhopGuestHashMap.get("giveBags").release();

                this.bellhopGuestHashMap.get("giveTip").acquire();
                printStringToConsole(toStringId, "has gotten their tip and leaves from [Guest ", guestNo + "", "]");

                this.bellhopGuestHashMap.get("gotTip").release();

                printStringToConsole(toStringId, "is ready to help a guest");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return toStringId;
    }

    private void printStringToConsole(String ... strings) throws InterruptedException {
        this.semaphoreHashMap.get("lock").acquire();
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }

        System.out.println(sb.toString());
        this.semaphoreHashMap.get("lock").release();
    }
}
