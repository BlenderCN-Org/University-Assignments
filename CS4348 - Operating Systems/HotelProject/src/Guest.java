import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Guest extends Thread {

    private HashMap<String, Semaphore> semaphoreHashMap;
    private HashMap<String, Semaphore> clerkGuestHashMap;
    private String id;
    private int threadId;
    private int clerkNo;
    private int bags;


    Guest(HashMap<String, Semaphore> semaphoreHashMap, Integer id) {
//        this.bags = new Random().nextInt(5);
        this.bags = 0;
        this.clerkNo = -1;
        this.semaphoreHashMap = semaphoreHashMap;
        this.id = "Guest " + id;
        this.threadId = id;
    }

    public int getBags() {
        return bags;
    }

    @Override
    public void run() {
        try {
            System.out.println(id + ": is waiting to enter the hotel");
            semaphoreHashMap.get("guests").release();
            System.out.println(id + ": has entered the hotel");

            System.out.println(id + ": is waiting for a [Clerk]");
            semaphoreHashMap.get("clerks").acquire();

            semaphoreHashMap.get("sync").acquire();
            System.out.println(id + ": in Sync");

            for (Clerk c : Main.clerkArrayList) {
                if (!c.beingUsed) {
                    c.beingUsed = true;
                    clerkNo = c.threadId - 1;
                    break;
                }
            }

            System.out.println(id + ": size " + Main.clerkArrayList.size() + ", " + clerkNo);
            Main.clerkArrayList.get(clerkNo).setGuestNo(threadId);
            System.out.println(id + ": is approaching [Clerk " + (clerkNo + 1) + "]");

            clerkGuestHashMap = Main.newClerkGuestHashMap();
            System.out.println(id + ": " + clerkGuestHashMap);
            Main.clerkArrayList.get(clerkNo).setClerkGuestHashMap(clerkGuestHashMap);
            System.out.println(id + ": gave to " + (clerkNo + 1) + ", " + clerkNo);

            System.out.println(id + ": out Sync");
            semaphoreHashMap.get("sync").release();

            semaphoreHashMap.get("acquiredGuest").release();

            clerkGuestHashMap.get("forRoom").acquire();
            System.out.println(id + ": has gotten his room from [Clerk " + (clerkNo + 1) + "]");
            clerkGuestHashMap.get("hasRoom").release();

            clerkGuestHashMap.get("forKey").acquire();
            System.out.println(id + ": has gotten his room key from [Clerk " + (clerkNo + 1) + "]");
            clerkGuestHashMap.get("hasKey").release();

            if (bags > 2) {
                semaphoreHashMap.get("bellhops").acquire();
                semaphoreHashMap.get("getBags").acquire();
                semaphoreHashMap.get("enterRoom").release();
                semaphoreHashMap.get("acquireBags").acquire();
                semaphoreHashMap.get("doTop").release();
            }

            Main.retired++;
            System.out.println(id + ": has retired");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
