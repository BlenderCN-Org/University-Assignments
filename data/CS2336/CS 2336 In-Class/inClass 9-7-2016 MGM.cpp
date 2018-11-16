#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>


//EX) float * num = new float[n]; **CAN BE ANYTHING; **num.close();

//EX) float num [10] **MUST BE A CONSTANT VAR

//Array is passed by reference, ONLY OPTION
//"RANGE BASED FOR LOOP:: " = for(auto varName; arrayName) <- C++ 11 <- Lazy Programming :(
//^^^ For every value in the array, do stuff.
//^^^ Once you start passing the array, you cannot use the Ranged Based For Loop
//strlen(array); <- String Literal (Char Array) ".length()"
//"\o" must be the last character in the array
//strcpy(array1,array2) <- Array 1 must be able to hold array2. Copies Array2 into Array1 ***Copies Null Terminator Automatically
//strncpy(array1,array2,n) <- The n represents the number of chars to copy over ***Very Problematic:: If it does not copy a null terminator,
//I must make sure to add a null terminator in the char array
//strcat(array1,array2) <- Puts both arrays inside array1, must make sure that array1 is big enough to hold it
//strncat(array1,array2,n) ***Unlike strncopy, this will add the null terminator
//strcmp(array1,array2) <- Gives an int value. If 0,strings are same. ***CASE SENSENTIVE; If return -1, Array 1 < Array2; If return +1, Array1 > Array2

using namespace std;

void strToarray(string int1,string int2,string integer1[],string integer2[]);
void addArray(string int1,string int2,string integer1[],string integer2[],string totalSum[]);

int main()
{

    string int1;
    string int2;



    cout << "Please Enter Two Integers that are 20 characters or less each" << "\n";
    cin >> int1,int2;


    string integer1 [20];
    string integer2 [20];
    string totalSum [41];
    strToarray(int1,int2,integer1,integer2);

    //addArray(int1,int2,integer1,integer2,totalSum);




}

void strToarray(string int1,string int2,string integer1[],string integer2[])
{

    if(int1 > int2)
    {
        for(int i = int1.length(); i > 0; i--)
        {
            integer1[i] = int1.substr(i,i+1);
            cout << integer1[i];
        }

        cout << "\n";

        for(int i = int2.length(); i > 0; i--)
        {
            integer2[i] = int2.substr(i,i+1);
            cout << integer2[i];
        }
    }
    else
    {
        for(int i = int2.length(); i > 0; i--)
        {
            integer1[i] = int2.substr(i,i+1);
            cout << integer1[i];
        }

         cout << "\n";

        for(int i = int1.length(); i > 0; i--)
        {
            integer2[i] = int1.substr(i,i+1);
            cout << integer2[i];
        }
    }


}

void addArray(string int1,string int2,string integer1[],string integer2[],string totalSum[])
{
    int diff = 0;

    int i1;
    int i2;for(int i = 0; i < int1.length(); i++)
    {
        integer1[i] = int1.substr(i,i+1);
    }

    for(int i = 0; i < int2.length(); i++)
    {
        integer2[i] = int2.substr(i,i+1);
    }
    int sum;
    bool isOver = false;

    if(int1.length() > int2.length())
    {
        diff = int1.length();
    }
    else if(int1.length() < int2.length())
    {
        diff = int2.length();
    }
    else
    {
        diff = int1.length();
    }

    for(int i = diff-1; i > 0; i--)
    {
       i1 = atoi(integer1[diff].c_str());
       i2 = atoi(integer2[diff].c_str());
       sum = i1+i2;

       cout << "Sum:: " << i1 << "+" << i2 << "=" << sum;

       if(isOver = true)
       {
           sum = atoi(integer1[diff-1].c_str())+1;
       }

        isOver = false;


       if(sum > 9)
       {
            isOver = true;
            sum = sum - 10;
       }

       totalSum[diff] = sum;


    }

    cout << "Sum:: ";

    for(int i = 0; i < 40; i++)
    {
        cout << totalSum[i];
    }
}




