#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>




using namespace std;
float Solve();

//(z-2x)(y-2x)x = V

float z;
float y;
float x;
float maxVolume;

bool maxi = false;

int main()
{
    Solve();
    cout << setprecision(3) << fixed <<"The CUTOUT VALUE is " << x << " and the MAX VOLUME is " << maxVolume;

}

float Solve()
{
    cout << "Please Enter the 'Z' Width and 'Y' Length of the Cardboard:: 'Z' 'Y' " << "\n";
    cin >> z >> y;

    x = (z+y - sqrt(pow(z,2) + pow(y,2) - (z*y)))/6;

    maxVolume = x*y*z;
}




