

//Add Purse + Add Coins + Add to the Array List(3times) + totalValue


import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

public class CoinRunner {

    public static void main(String [] args)
    {
    	///////////////////////////////////////////////////////////////////////////

    	ArrayList<Purse> MultiPurse = new ArrayList<Purse>();

    	///////////////////////////////////////////////////////////////////////////

    	Purse kWallet = new Purse();

    	kWallet.addCoin(new Coin (5));
    	kWallet.addCoin(new Coin (10));
    	kWallet.addCoin(new Coin (25));

    	MultiPurse.add(kWallet);

    	///////////////////////////////////////////////////////////////////////////

    	Purse sWallet = new Purse();

    	sWallet.addCoin(new Coin (25));
    	sWallet.addCoin(new Coin (50));
    	sWallet.addCoin(new Coin (25));

    	MultiPurse.add(sWallet);

    	///////////////////////////////////////////////////////////////////////////

    	Purse MattsWallet = new Purse();

    	MattsWallet.addCoin(new Coin (1));
    	MattsWallet.addCoin(new Coin (1));
    	MattsWallet.addCoin(new Coin (1));

    	MultiPurse.add(MattsWallet);

    	///////////////////////////////////////////////////////////////////////////

    	System.out.println("The total amount of purses is " + MultiPurse.size());
    	System.out.println("");
    	System.out.println("The value of Purse 1  is equal to " + kWallet.totalValue());
    	System.out.println("The value of Purse 2  is equal to " + sWallet.totalValue());
    	System.out.println("The value of Purse 3  is equal to " + MattsWallet.totalValue());
    	System.out.println("");



    }



}