#include "invalidMonth.h"
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

invalidMonth::invalidMonth()
{
    message = "You entered an invalid month";
}
string invalidMonth::what()
{
    return message;
}
