#include <iostream>
#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include <exception>
#include "invalidDay.h"
#include "invalidMonth.h"

using namespace std;

bool checkDay(bool isShort,bool isLong, bool isSpecial, int Day, int Month, int Year);
bool checkMonth(int Month);

const int shortMonths[4] {4,6,9,11}; //April,June,September,November
const int longMonths[7] {1,3,5,7,8,10,12}; //January,March,May,July,August,October,December
const int specialMonths[1] {2}; //February
const int shortDays[30] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
const int longDays[31] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
const int specialDays[29] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29}; //28 Normally, 29 for Leap Year
const string globalMonths[12] {"January","February","March","April","May","June","July","August","September","October","November","December"};

int main()
{
    bool isShort = false;
    bool isLong = false;
    bool isSpecial = false;


   /////////////////////////////////////

    try
    {
        string tmp;
        cout << "Enter a date:: " << "\n";
        cin >> tmp;
        string m = tmp.substr(0,tmp.find("-"));
        tmp = tmp.substr(tmp.find("-")+1);
        string d = tmp.substr(0,tmp.find("-"));
        tmp = tmp.substr(tmp.find("-")+1);
        string yr = tmp.substr(tmp.find_last_of("-")+1);

        //cout << m << "\n";
        //cout << d << "\n";
        //cout << yr << "\n";

        int Day = atoi(d.c_str());
        int Month = atoi(m.c_str());
        int Year = atoi(yr.c_str());

        for(int i = 0; i < 4; i++) //Short Months
        {
            if(Month == shortMonths[i])
            {
                isShort = true;
            }
        }

        for(int i = 0; i < 7; i++) //Long Months
        {
            if(Month == longMonths[i])
            {
                isLong = true;
            }
        }
        for(int i = 0; i < 1; i++) //Special Months
        {
            if(Month == specialMonths[i])
            {
                isSpecial = true;
            }
        }

        ////////////////////////////////////////////////////////

        if(checkMonth(Month) == true)
        {
            throw invalidMonth();
        }
        if(checkDay(isShort,isLong,isSpecial,Day,Month,Year) == true)
        {
            throw invalidDay();
        }

        if(checkMonth(Month) != true && checkDay(isShort,isLong,isSpecial,Day,Month,Year) != true)
        {
            string mo = "";

            mo = globalMonths[Month-1];

            cout << mo << " " << d << ", " << yr << "\n";
        }

        ////////////////////////////////////////////////////////
    }
    catch(invalidMonth e)
    {
        cout << "In Date::" "\n";
        cout << "Catch Block: "  << "\n";
        cout << e.what() << "\n";
    }
    catch(invalidDay e)
    {
        cout << "In Date::" "\n";
        cout << "Catch Block: "  << "\n";
        cout << e.what() << "\n";
    }

}

bool checkDay(bool isShort,bool isLong, bool isSpecial, int Day, int Month,int Year)
{
    bool error = true;

    if(isShort == true)
    {
        for(int i = 0; i < 30; i++)
        {
            if(Day == shortDays[i])
            {
                error = false;
                break;
            }
        }
    }

    if(isLong == true)
    {
        for(int i = 0; i < 31; i++)
        {
            if(Day == longDays[i])
            {
                error = false;
                break;
            }
        }
    }

    if(isSpecial == true)
    {
        if(Year%4 != 0)
        {
            for(int i = 0; i < 28; i++)
            {
                if(Day == specialDays[i])
                {
                    error = false;
                    break;
                }
            }
        }
        else
        {
            for(int i = 0; i < 29; i++)
            {
                if(Day == specialDays[i])
                {
                    error = false;
                    break;
                }
            }
        }
    }

    return error;
}

bool checkMonth(int Month)
{
    bool error = true;

    for(int i = 1; i < 13; i++)
    {
        if(Month == i)
        {
            error = false;
            break;
        }
    }

    return error;
}
