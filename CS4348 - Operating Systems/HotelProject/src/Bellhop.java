import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class Bellhop extends Thread {
    private static HashMap<String, Semaphore> semaphoreHashMap;

    Bellhop(HashMap<String, Semaphore> semaphoreHashMap) {
        Bellhop.semaphoreHashMap = semaphoreHashMap;
    }
}
