import java.util.*;

public class Purse
{



   private ArrayList<Coin> purse;



   public Purse()
   {

   	  purse = new ArrayList<Coin>();
   }


   public int numberOfCoins()
   {



   	return purse.size();
   }
   public int totalValue()
   {
	int total=0;
	int x = 0;
	while(x<purse.size())
	{
		total= purse.get(x).getValue() + total;
		x++;
	}


   	return total;
   }
   public boolean isEmpty()
   {


	if(purse.size() == 0)
	{
		return true;
	}
   	else
   	{
   		return false;
   	}

   }

   public boolean addCoin( Coin c )
   {

	purse.add(c);

	Collections.sort(purse);

   	return true;
   }

   public boolean removeCoin( Coin c )
   {

	purse.remove(c);

   	return true;
   }

   public String toString()
   {
   	return "The number of coins  x  and total value of the purse is x";
   }




}