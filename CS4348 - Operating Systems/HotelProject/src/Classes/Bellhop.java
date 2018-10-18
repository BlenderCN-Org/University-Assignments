package Classes;

import interfaces.HotelHelper;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class Bellhop extends Thread {
    private HashMap<String, Semaphore> semaphoreHashMap;
    private HashMap<String, Semaphore> bellhopGuestHashMap;

    private String toStringId;
    private int guestNo;

    private boolean beingUsed = false;

    private HotelHelper helper;

    public Bellhop(Integer bellhopNo, HotelHelper helper) {
        this.helper = helper;

        this.semaphoreHashMap = helper.initializeSemaphoreHashMap();
        this.toStringId = "Bellhop " + bellhopNo + ": ";
        this.guestNo = -1;
    }

    public boolean isUsed() {
        return beingUsed;
    }

    public void setUsed(boolean beingUsed) {
        this.beingUsed = beingUsed;
    }

    public void setBellhopGuestHashMap(HashMap<String, Semaphore> bellhopGuestHashMap) {
        this.bellhopGuestHashMap = bellhopGuestHashMap;
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

                this.semaphoreHashMap.get("bellhops").release();

                this.semaphoreHashMap.get("bellhopSync").acquire();

                System.out.println(toStringId + "has obtained the bags of [Guest " + guestNo + "]");
                this.bellhopGuestHashMap.get("hasBags").release();

                this.bellhopGuestHashMap.get("guestNeedsBags").acquire();

                this.bellhopGuestHashMap.get("inRoom").acquire();
                System.out.println(toStringId + "has entered the room of [Guest " + guestNo + "]");

                System.out.println(toStringId + "has given the bags to [Guest " + guestNo + "]");
                this.bellhopGuestHashMap.get("giveBags").release();

                this.bellhopGuestHashMap.get("giveTip").acquire();
                System.out.println(toStringId + "has gotten their tip and leaves from [Guest " + guestNo + "]");

                this.bellhopGuestHashMap.get("gotTip").release();

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
