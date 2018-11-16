//Matthew McMillian && Chris Wang

#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>


using namespace std;
//x = 2 × π × R (1 - √(2/3)).

const double pi = 4*atan(1);
int main()
{
    float radius;
    float x;
    float nRadius = (radius*(pi-1))/pi;
    float n;

    cout << "Please enter the radius of your circular waxed paper" << "\n";
    cin >> radius;
    //cout << radius << "\n";

    x = 2*pi*radius*(1-sqrt(2/3));


    cout << setprecision(2) << fixed << x << "\n";



}




