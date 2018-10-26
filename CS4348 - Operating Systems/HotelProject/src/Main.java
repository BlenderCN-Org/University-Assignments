import Classes.Bellhop;
import Classes.Clerk;
import Classes.Guest;
import interfaces.HotelHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class Main {

    private static HashMap<String, Semaphore> semaphoreHashMap;

    private static HotelHelper helper;

    public static void main(String[] args) throws InterruptedException {

        int guestCount = 25;
        int clerkCount = 2;
        int bellhopCount = 2;

        helper = new HotelHelper();
        helper.generateGlobalHashMap();

        for (int i = 0; i < clerkCount; i++) {
            helper.addToClerks(new Clerk(i + 1, helper));
        }

        for (int i = 0; i < bellhopCount; i++) {
            helper.addToBellhops(new Bellhop(i + 1, helper));
        }

        for (int i = 0; i < guestCount; i++) {
            helper.addToGuests(new Guest(i + 1, helper));
        }

        for (int i = 0; i < clerkCount; i++) {
            helper.getClerk(i).start();
        }

        for (int i = 0; i < bellhopCount; i++) {
            helper.getBellhop(i).start();
        }

        for (int i = 0; i < guestCount; i++) {
            helper.getGuest((i)).start();
        }

        Thread.sleep(1000);
        System.out.println();
        System.out.println(helper.getTotalRetired());
        System.out.println(helper.getRetiredArrayList());
        System.exit(0);
    }


}
