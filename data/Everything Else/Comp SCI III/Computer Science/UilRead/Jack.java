/**
 * @(#)Jack.java
 *
 *
 * @author
 * @version 1.00 2015/11/5
 */
import java.util.*;
import java.io.*;


public class Jack {

    public static void main(String args [])throws IOException
    {
		Scanner kb = new Scanner(new File("jack.dat"));
		kb.nextLine();
		do{
			System.out.println (kb.nextLine());
			if(kb.hasNextLine())
				kb.nextLine();
		}while(kb.hasNextLine());

    }


}