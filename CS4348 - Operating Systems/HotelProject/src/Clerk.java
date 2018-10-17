import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class Clerk extends Thread {

    private HashMap<String, Semaphore> semaphoreHashMap;
    private HashMap<String, Semaphore> clerkGuestHashMap;

    private String id;
    public int threadId;
    private int guestNo;
    public boolean beingUsed;

    Clerk(HashMap<String, Semaphore> semaphoreHashMap, Integer id) throws InterruptedException {
        this.semaphoreHashMap = semaphoreHashMap;
        this.id = "Clerk " + id;
        this.threadId = id;
        this.guestNo = -1;
        beingUsed = false;
    }

    public void setClerkGuestHashMap(HashMap<String, Semaphore> clerkGuestHashMap) {
        this.clerkGuestHashMap = clerkGuestHashMap;
    }

    public void setGuestNo(int threadId) {
        this.guestNo = threadId - 1;
    }

    @Override
    public void run() {
        while (this.isAlive()) {
            try {
                System.out.println(id + ": is waiting for a [Guest]");
                semaphoreHashMap.get("guests").acquire();

                semaphoreHashMap.get("clerks").release();

                semaphoreHashMap.get("acquiredGuest").acquire();
                System.out.println(id + ": is assisting [Guest " + (guestNo + 1) + "]");

                System.out.println(id + ": is finding a room for [Guest " + (guestNo + 1) + "]");
                System.out.println(id + ": has found a room for [Guest " + (guestNo + 1) + "]");
                try {
                    // Right here, we have a case of about 1 guest messing up
                    clerkGuestHashMap.get("forRoom").release();
                } catch (Exception e) {
                    System.out.println("Error at Clerk/Guest: " + threadId + ", " + (guestNo + 1));
                }

                clerkGuestHashMap.get("hasRoom").acquire();
                System.out.println(id + ": is finding a key for [Guest " + (guestNo + 1) + "]");
                System.out.println(id + ": has found a key for [Guest " + (guestNo + 1) + "]");
                clerkGuestHashMap.get("forKey").release();


                clerkGuestHashMap.get("hasKey").acquire();
                this.clerkGuestHashMap = null;
                this.beingUsed = false;
                System.out.println(id + ": is now available for another [Guest]");


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
