#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>




using namespace std;

const float e = 2.718281827;

//

int main()
{

    float num = 0.0f;
    float diff;
    float n = 4;
    float counter = n;

   cout << "Original 'e':: 2.718281827" << "\n";
   cout << "'e' at 4    :: 2.44140625" << "\n";
   cin >> num;
   float junk = 1.0f;

   while(num>0)
    {
        junk = (num) + (num)/junk;
        num-1;
    }
   cout << 2+1/junk;







   /*
   cout << "Values of 'e'from 100 to 10000 in increments of 100 in terms of 'n'::" << "\n";

   for(float i = 100; i <= 10000; i+= 100)
   {
        num = pow(1+(1/i),i);
        diff = num-e;
        cout << setprecision(9) << fixed << "n:: " << i << " | e at n:: " << num << " | diff:: " << diff << "\n";
   }

    cout << "Values of 'e'from 1000 to 100000 in increments of 1000 in terms of 'n'::" << "\n";

    for(float i = 1000; i <= 100000; i+= 1000)
   {
        num = pow(1+(1/i),i);
        diff = num-e;
        cout << setprecision(9) << fixed << "n:: " << i << " | e at n:: " << num << " | diff:: " << diff << "\n";
   }

   */

}


//2 + 1 / [(n-1) + (n-1)/(n+n)]

//Plug into division




