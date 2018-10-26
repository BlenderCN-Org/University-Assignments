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
        printStringToConsole(toStringId, "is ready to help a guest");
        while (this.isAlive()) {
            try {
                this.semaphoreHashMap.get("bellhopVars").acquire();
                this.beingUsed = false;
                this.guestNo = -1;
                this.bellhopGuestHashMap = null;
                this.semaphoreHashMap.get("bellhopVars").release();

                this.semaphoreHashMap.get("bellhops").release();

                this.semaphoreHashMap.get("bellhopSync").acquire();

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
        return toStringId + ", " + beingUsed;
    }

    private void printStringToConsole(String ... strings) {
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }

        System.out.println(sb.toString());

    }
}
