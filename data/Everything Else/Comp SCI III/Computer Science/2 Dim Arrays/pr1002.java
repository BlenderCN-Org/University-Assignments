


public class pr1002 {

    public static void main (String [] args)
    {
    	double sumRow1 = 0;
    	double sumRow2	= 0;
    	int numChange = 0;
    	int Changer = -1;


    		double [][]RanNum = {{Math.random(),Math.random(),Math.random()},
    							{Math.random(),Math.random(),Math.random()}

   							};

			System.out.println("The Array is:: ");
			System.out.println("////////////////////////////////////////////////////////////////////////");
   			for(int y = 0; y<6; y++)
   			{

   				if(y<3)
   				{
   					System.out.println(RanNum[numChange][y]);
   				}
   				else if(y>=3)
   				{	numChange = 1;
   					Changer++;
   					System.out.println(RanNum[numChange][Changer]);


   				}
   				else
   				{
   					System.out.println("BBBBBBBBBAAAAAAAAAAAAAAAAKKKKKKKKKKKKKKKKKAAAAAAAAAAAAAAAAAA");
   				}
   			}

				System.out.println("////////////////////////////////////////////////////////////////////////");

   				for(int i = 0; i < 2; i ++)
   			{

   				sumRow1 = RanNum[0][i] + sumRow1;
   				if(i==1)
   				{
   					sumRow1 = RanNum[0][i+1] + sumRow1;
   					System.out.println("The Sum of Row 1 is:: " + sumRow1 );
   				}
   			}
   				for(int i = 0; i < 2; i ++)
   			{

   				sumRow2 = RanNum[1][i] + sumRow2;
   				if(i==1)
   				{
   					sumRow2 = RanNum[1][i+1] + sumRow2;
   					System.out.println("The Sum of Row 2 is:: " + sumRow2 );
   				}
   			}
   			System.out.println("////////////////////////////////////////////////////////////////////////");









    }


}