import Classes.Bellhop;
import Classes.Clerk;
import Classes.Guest;
import Globals.HotelHelper;

public class Main {

    /**
     * @param args - Input arguments to the program, none in this case
     * @return - Nothing to return
     * @throws InterruptedException - In case of semaphore error
     *                              <p>
     *                              Contains 4 variables:
     * @guestCount - Integer variable that controls the number of guests that enter the hotel
     * @clerkCount - Integer variable that controls the number of clerks at the hotel
     * @bellhopCount - Integer variable that controls the number of bellhops at the hotel
     * {@link HotelHelper} - A class that contains global functions and variables for the program
     * <p>
     * The program starts by generating new clerks and bellhops that are immediately started. The
     * program then generates new guests but waits to start them. The clerks, guests, and bellhops
     * all get added to a ArrayList of their respective types for handling inside the HotelHelper.
     * Since all the objects are passed by reference in this case, all the subclasses
     * (clerk, guest, bellhop) have access to HotelHelper. The guests then get started in a
     * queue-like fashion.
     */
    public static void main(String[] args) throws InterruptedException {

        int guestCount = 25;
        int clerkCount = 2;
        int bellhopCount = 2;

        HotelHelper helper = new HotelHelper();
        helper.generateGlobalHashMap();

        for (int i = 0; i < clerkCount; i++) {
            helper.addToClerks(new Clerk(i + 1, helper));
        }

        for (int i = 0; i < bellhopCount; i++) {
            helper.addToBellhops(new Bellhop(i + 1, helper));
        }

        for (int i = 0; i < guestCount; i++) {
            Guest g = new Guest(i + 1, helper);
            helper.addToGuests(g);
            helper.addToGuestQueue(g);
            helper.getGuest(i).start();
        }

        for (int i = 0; i < clerkCount; i++) {
            helper.getClerk(i).start();
        }

        for (int i = 0; i < bellhopCount; i++) {
            helper.getBellhop(i).start();
        }


        for (int i = 0; i < guestCount; i++) {
            helper.getGuest(i).join();
        }
        System.out.println();
        System.out.println(helper.getTotalRetired());
        System.out.println(helper.getRetiredArrayList());
        System.exit(0);
    }


}
