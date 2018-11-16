#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include "Pilot.h"

Pilot::Pilot()
{
    //ctor
}

Pilot::~Pilot()
{
    //dtor
}

std::string Pilot::toString()
{
  std::stringstream ss;
  ss << name << "\t";

  if(valid){
        ss << area << "\t";
        ss << "Valid" << std::endl;
  }
  else{
        ss << "" << "\t";
        ss << "InValid" << std::endl;
  }

  return ss.str();
}
