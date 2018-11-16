#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>



using namespace std;

//OBJECTIVES::
//1.Read # Of Lines in File
//2.Cut "Chopper" the lines up into 2 different arrays
//  based on $$ and ## for easy multiplication
//3.Multiply together and assign summation of all the values to a
//  "Total" int, outprint.
// Total should be " $6,493,750 "

int main()
{
    int num = 0;
    int pos;
    int counter;
    float total = 0;


    string line;
    string fileName;

    cout << "Enter the entire name of the file you wish to use for this program:: ";
    cin >> fileName;
    ifstream fileReader(fileName);

    if(fileReader.is_open()) // Checks to see if the document is open
    {
        while(getline(fileReader,line)) //Checks for a line, accepts the fileReader and a line, which in this case is a string var "Line"
        {
            num++;
        }
        fileReader.close();
    }
    else cout << "Unable to Open File" << "\n";


    float* tPrice = new float[num];
    float* tSold  = new float[num];
    //float tSold [5];
    counter = 0;
    string partOne;
    string partTwo;

    ///////////////////////////////////////////// Pre-Req for Assigning Lengths, Prob an easier way

    fileReader.open(fileName);


    if(fileReader.is_open()) // Checks to see if the document is open
    {
        while(getline(fileReader,line)) //Checks for a line, accepts the fileReader and a line, which in this case is a string var "Line"
        {
            pos = line.find(" ");
            partOne = line.substr(0,pos);
            partTwo = line.substr(pos+1);
            tPrice [counter] = atof(partOne.c_str());
            tSold [counter] = atof(partTwo.c_str());

            //cout << tPrice[counter] << " | " ;
            //cout << tSold[counter] << "\n";
            //cout << counter << "\n";

            counter++;

        }
        fileReader.close();
    }
    else cout << "Unable to Open File" << "\n";


    for(int i = 0; i < num; i++)
    {
        //cout << tPrice[i] << "\n";
        //cout << tSold[i] << "\n";

        total +=  tPrice[i]*tSold[i];
    }

    cout << setprecision(2) << fixed << "The Total Sales Amount Was $" << total;

    delete [] tPrice;
    delete [] tSold;









}
