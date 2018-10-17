import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class Main {

    private static HashMap<String, Semaphore> semaphoreHashMap;
    protected static ArrayList<Clerk> clerkArrayList = new ArrayList<>();
    protected static ArrayList<Guest> guestArrayList = new ArrayList<>();

    public static int retired = 0;

    public static void main(String[] args) throws InterruptedException {
        semaphoreHashMap = initializeSemaphoreHashMap();
        getSemaphoreStatus();

        for (int i = 0; i < 2; i++) {
            clerkArrayList.add(new Clerk(semaphoreHashMap, i + 1));
            clerkArrayList.get(i).start();
        }

        for (int i = 0; i < 25; i++) {
            guestArrayList.add(new Guest(semaphoreHashMap, i + 1));
            guestArrayList.get(i).start();
        }

        Thread.sleep(1000);
        System.out.println("Retired Guests: " + retired);

        System.exit(0);
    }

    static HashMap<String, Semaphore> newClerkGuestHashMap() throws InterruptedException {
        HashMap<String, Semaphore> hashMap = new HashMap<>();

        hashMap.put("forRoom", new Semaphore(1, true));
        hashMap.put("hasRoom", new Semaphore(1, true));
        hashMap.put("forKey", new Semaphore(1, true));
        hashMap.put("hasKey", new Semaphore(1, true));

        hashMap.get("forRoom").acquire();
        hashMap.get("hasRoom").acquire();

        hashMap.get("forKey").acquire();
        hashMap.get("hasKey").acquire();

        return hashMap;
    }

    private static HashMap<String, Semaphore> initializeSemaphoreHashMap() throws InterruptedException {
        HashMap<String, Semaphore> hashMap = new HashMap<>();
        hashMap.put("guests", new Semaphore(25));
        hashMap.put("clerks", new Semaphore(2));
        hashMap.put("bellhops", new Semaphore(2));

        hashMap.put("sync", new Semaphore(1, true));
        hashMap.put("acquiredGuest", new Semaphore(1, true));

        hashMap.put("getBags", new Semaphore(1, true));
        hashMap.put("enterRoom", new Semaphore(1, true));
        hashMap.put("doTip", new Semaphore(1, true));
        hashMap.put("acquireBags", new Semaphore(1, true));


        hashMap.get("guests").acquire(25);
        hashMap.get("clerks").acquire(2);
        hashMap.get("getBags").acquire();
        hashMap.get("enterRoom").acquire();
        hashMap.get("doTip").acquire();

        hashMap.get("acquiredGuest").acquire();

        return hashMap;
    }

    public static void getSemaphoreStatus() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : semaphoreHashMap.keySet()) {
            stringBuilder.append(key + ": " + semaphoreHashMap.get(key).availablePermits() + " ");
        }
        System.out.println(stringBuilder.toString());
    }
}
