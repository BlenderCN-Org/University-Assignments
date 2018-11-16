#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include "largeIntegers.h"




using namespace std;


largeIntegers::largeIntegers(string arra)
{
    cNumber = arra;
    length = cNumber.length();
}

void largeIntegers::setArr(string arra)
{
    for(int i = length; i > 0; i--)
    {
        arr[i] = atoi(arra.substr(0,1).c_str());
    }
}

largeIntegers largeIntegers:: operator+(largeIntegers x)
{
    int tmp;
    string fnl = "";

    for(int i = length; i > 0; i--)
    {
       tmp = arr[i] + x.arr[i];

       if(isOver == true)
       {
           tmp = tmp+1;
       }

       if(tmp > 9)
       {
           tmp = tmp-10;
           isOver = true;
       }
       else
       {
           isOver = false;
       }

       comp[i] = tmp;
    }

    for(int u = 0; u < length; u++)
    {
        string String = static_cast<ostringstream*>( &(ostringstream() << arr[u]) )->str();

        fnl = fnl + String;
    }

     largeIntegers temp(fnl);

    return temp;
}

largeIntegers largeIntegers:: operator-(largeIntegers x)
{
    int tmp;
    string fnl = "";

    for(int i = length; i > 0; i--)
    {
        if(isLess == true)
        {
            arr[i] = arr[i]-1;
        }

       if(arr[i] < x.arr[i])
       {
            arr[i] = arr[i] + 10;
            isLess = true;
       }
       else
       {
           isLess = false;
       }

       tmp = arr[i] - x.arr[i];
       comp[i] = tmp;
    }

    for(int u = 0; u < length; u++)
    {
        string String = static_cast<ostringstream*>( &(ostringstream() << arr[u]) )->str();

        fnl = fnl + String;
    }

    largeIntegers temp(fnl);

    return temp;
}

largeIntegers largeIntegers:: operator<(largeIntegers x)
{
    if(length < x.length)
    {
        return 1;
    }
    else if (length = x.length)
    {
        for(int i = 0; i < length; i++)
        {
            if(arr[i] < x.arr[i])
            {
                return 1;
            }
            else if(arr[i] > x.arr[i])
            {
                return -1;
            }
            else
            {
                //DO NOTHING
            }
        }
    }
}

largeIntegers largeIntegers:: operator>(largeIntegers x)
{
    if(length > x.length)
    {
        return 1;
    }
    else if (length = x.length)
    {
        for(int i = 0; i < length; i++)
        {
            if(arr[i] > x.arr[i])
            {
                return 1;
            }
            else if(arr[i] < x.arr[i])
            {
                return -1;
            }
            else
            {
                //DO NOTHING
            }
        }
    }
}

largeIntegers largeIntegers:: operator=(largeIntegers x)
{
    bool same = true;

  if(length = x.length)
  {
      for(int i = 0; i < length; x++)
      {
          if(arr[i] == x.arr[i])
          {
            same = true;
          }
          else
          {
              same = false;
              break;
          }
      }
  }

  return same;
}
