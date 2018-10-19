package Classes;

import interfaces.HotelHelper;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class Guest extends Thread {

    private HashMap<String, Semaphore> semaphoreHashMap;
    private HashMap<String, Semaphore> clerkGuestHashMap;
    private HashMap<String, Semaphore> bellhopGuestHashMap;

    private String toStringId;
    private int guestNo;
    private int clerkNo;
    private int bellhopNo;
    private int bags;

    private HotelHelper helper;

    public Guest(Integer guestNo, HotelHelper helper) throws InterruptedException {

        this.helper = helper;
        this.semaphoreHashMap = helper.initializeSemaphoreHashMap();

        this.guestNo = guestNo;
        this.clerkNo = -1;

        this.bags = 3;

        this.toStringId = "Guest " + guestNo + ": ";

    }

    public void setClerkGuestHashMap(HashMap<String, Semaphore> clerkGuestHashMap) {
        this.clerkGuestHashMap = clerkGuestHashMap;
    }

    public void setBellhopGuestHashMap(HashMap<String, Semaphore> bellhopGuestHashMap) {
        this.bellhopGuestHashMap = bellhopGuestHashMap;
    }

    @Override
    public void run() {
        try {
            printStringToConsole(toStringId, "has entered the hotel");

            this.semaphoreHashMap.get("guests").release();

            this.semaphoreHashMap.get("guestClerkMutex").acquire();

            this.semaphoreHashMap.get("clerks").acquire();

            this.semaphoreHashMap.get("guestSync").acquire();
            this.clerkNo = helper.newClerkGuestHashMap(guestNo);
            this.semaphoreHashMap.get("guestSync").release();

            this.semaphoreHashMap.get("guestClerkMutex").release();

            printStringToConsole(toStringId, "is approaching [Clerk ", clerkNo + "", "]");

            this.semaphoreHashMap.get("sync").release();

            this.clerkGuestHashMap.get("forRoom").acquire();
            printStringToConsole(toStringId, "has gotten the room number from [Clerk ", clerkNo + "", "]");

            this.clerkGuestHashMap.get("hasRoom").release();

            this.clerkGuestHashMap.get("forKey").acquire();
            printStringToConsole(toStringId, "has gotten the room key from [Clerk ", clerkNo + "", "]");

            this.clerkGuestHashMap.get("hasKey").release();

            this.clerkGuestHashMap.get("completed").acquire();

            if (this.bags > 2) {

                this.semaphoreHashMap.get("guestBellhopMutex").acquire();
                this.semaphoreHashMap.get("bellhops").acquire();

                this.semaphoreHashMap.get("guestBellhopSync").acquire();
                bellhopNo = helper.newBellhopGuestHashMap(guestNo);
                this.semaphoreHashMap.get("guestBellhopSync").release();

                this.semaphoreHashMap.get("guestBellhopMutex").release();

                printStringToConsole(toStringId, "is approaching [Bellhop ", bellhopNo + "", "]");

                this.semaphoreHashMap.get("bellhopSync").release();

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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private void printStringToConsole(String ... strings) {
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }

        System.out.println(sb.toString());

    }
}
