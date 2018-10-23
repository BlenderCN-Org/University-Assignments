package Classes;

import interfaces.HotelHelper;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class Bellhop extends Thread {
    private HashMap<String, Semaphore> semaphoreHashMap;
    private HashMap<String, Semaphore> bellhopGuestHashMap;

    private Semaphore mutex;

    private String toStringId;
    private int guestNo;

    private boolean beingUsed = false;

    private HotelHelper helper;

    public Bellhop(Integer bellhopNo, HotelHelper helper) throws InterruptedException {
        this.helper = helper;
        this.mutex = new Semaphore(1, true);
        this.mutex.acquire();

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

    @Override
    public void run() {
        printStringToConsole(toStringId, "is ready to help a guest");
        while (this.isAlive()) {
            try {
                this.semaphoreHashMap.get("lock").acquire();
                this.beingUsed = false;
                this.guestNo = -1;
                this.bellhopGuestHashMap = null;
                this.semaphoreHashMap.get("bellhops").release();
                this.semaphoreHashMap.get("lock").release();

                this.mutex.acquire();

                printStringToConsole(toStringId, "has obtained the bags of [Guest ", guestNo + "", "]");
                try{
                    this.bellhopGuestHashMap.get("hasBags").release();
                } catch (Exception e){
                    printStringToConsole(toStringId, ", error at ", guestNo+"");
                }

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
