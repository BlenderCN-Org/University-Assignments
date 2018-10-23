package Classes;

import interfaces.HotelHelper;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class Clerk extends Thread {

    private HashMap<String, Semaphore> semaphoreHashMap;
    private HashMap<String, Semaphore> clerkGuestHashMap;

    private Semaphore mutex;

    private String toStringId;
    private int guestNo;

    private boolean beingUsed = false;

    private HotelHelper helper;

    public Clerk(Integer clerkNo, HotelHelper helper) throws InterruptedException {
        this.helper = helper;
        this.mutex = new Semaphore(1, true);
        this.mutex.acquire();

        this.semaphoreHashMap = helper.initializeSemaphoreHashMap();
        this.toStringId = "Clerk " + clerkNo + ": ";
        this.guestNo = -1;
    }

    public boolean isUsed() {
        return beingUsed;
    }

    public void setUsed(boolean beingUsed) {
        this.beingUsed = beingUsed;
    }

    public void setClerkGuestHashMap(HashMap<String, Semaphore> clerkGuestHashMap) {
        this.clerkGuestHashMap = clerkGuestHashMap;
    }

    public void setGuestNo(Integer guestNo) {
        this.guestNo = guestNo;
    }

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

    public int getGuestNo() {
        return guestNo;
    }

    @Override
    public void run() {
        printStringToConsole(toStringId + "is ready to help a guest");

        while (this.isAlive()) {
            try {
                this.semaphoreHashMap.get("lock").acquire();
                this.beingUsed = false;
                this.guestNo = -1;
                this.clerkGuestHashMap = null;
                this.semaphoreHashMap.get("clerks").release();
                this.semaphoreHashMap.get("lock").release();

                this.semaphoreHashMap.get("guests").acquire();

                this.mutex.acquire();
                printStringToConsole(toStringId, "is helping [Guest ", guestNo + "", "]");

                printStringToConsole(toStringId, "has found a room for [Guest ", guestNo + "", "]");
                try {
                    this.clerkGuestHashMap.get("forRoom").release();
                } catch (Exception e) {
                    System.out.println("Error at Guest: " + guestNo + ", Clerk: " + toStringId);
                    System.exit(0);
                }

                this.clerkGuestHashMap.get("hasRoom").acquire();

                printStringToConsole(toStringId, "has found a key for [Guest ", guestNo + "", "]");
                this.clerkGuestHashMap.get("forKey").release();

                this.clerkGuestHashMap.get("hasKey").acquire();

                this.clerkGuestHashMap.get("completed").release();

                printStringToConsole(toStringId, "is ready to help a guest");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printStringToConsole(String... strings) {
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }

        System.out.println(sb.toString());

    }

    @Override
    public String toString() {
        return toStringId + ", " + beingUsed;
    }
}
