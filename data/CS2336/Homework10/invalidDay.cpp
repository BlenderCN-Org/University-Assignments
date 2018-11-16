#include "invalidDay.h"
#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include <exception>

using namespace std;

invalidDay:: invalidDay()
{
    message = "You entered an invalid date";
}
string invalidDay::what()
{
    return message;
}



