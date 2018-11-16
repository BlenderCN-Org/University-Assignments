#include <iostream>
#include <fstream>
#include <cmath>
#include <string>
#include <iomanip>
#include <cstdlib>
#include <cstdio>
#include <sstream>


using namespace std;

//Ch3_Ex6Output.dat

//GROUP MEMBERS:: SEONG WANG, MATTHEW MCMILLIAN, DANIAL GARCIA

int main()
{
    string fileName;
    string line;
    string fName;
    string lName;
    float sPay; // Starting Pay
    float per; // Percent Increase
    float nPay; // New Pay


    cout << "Enter the entire name of the file you wish to use for this program:: ";
    cin >> fileName;

    ifstream fileReader(fileName);
    ofstream fOutput;
    fOutput.open("Ch3_Ex6Output.dat");


    while(!(fileReader.eof()))
    {
        //getline(cin,line)
        fileReader >> lName >> fName >> sPay >> per;
        nPay = sPay + (sPay * (per/100));
        fOutput << "" << fName << " " << lName <<  " " << setprecision(2) << fixed << nPay << "\n";
    }

    fOutput.close();
    return 0;

}

