package Classes;

import interfaces.HotelHelper;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class Clerk extends Thread {

    private HashMap<String, Semaphore> semaphoreHashMap;
    private HashMap<String, Semaphore> clerkGuestHashMap;

    private String toStringId;
    private int guestNo;

    private boolean beingUsed = false;

    private HotelHelper helper;

    public Clerk(Integer clerkNo, HotelHelper helper) {
        this.helper = helper;

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

    @Override
    public void run() {
        System.out.println(toStringId + "is ready to help a guest");

        while (this.isAlive()) {
            try {
                this.beingUsed = false;

                this.semaphoreHashMap.get("clerks").release();
                this.semaphoreHashMap.get("guests").acquire();


                this.semaphoreHashMap.get("sync").acquire();
                System.out.println(toStringId + "is helping [Guest " + guestNo + "]");

                System.out.println(toStringId + "has found a room for [Guest " + guestNo + "]");
                this.clerkGuestHashMap.get("forRoom").release();

                this.clerkGuestHashMap.get("hasRoom").acquire();

                System.out.println(toStringId + "has found a key for [Guest " + guestNo + "]");
                this.clerkGuestHashMap.get("forKey").release();

                this.clerkGuestHashMap.get("hasKey").acquire();

                this.clerkGuestHashMap.get("completed").release();

                System.out.println(toStringId + "is ready to help a guest");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return toStringId + ", " + beingUsed;
    }
}
