#include <iostream>
#include <iostream>

#include <fstream>

#include <string>

#include <cmath>

#include <vector>

#include <cstdlib>

#include <sstream>

#include <iomanip>

#include "fractionType.h"



using namespace std;

int main()
{
	string tmp;
	int upper;
	int lower;
	
	cout << "Please Enter Fraction #1" << "\n";
	getline(cin,tmp);
	upper = atoi(tmp.substr(0,tmp.find("/")).c_str());
	lower = atoi(tmp.substr(tmp.find("/")+1).c_str());
	
	fractionType x(upper,lower);
	
	cout << "Please Enter Fraction #2" << "\n";
	getline(cin,tmp);
	upper = atoi(tmp.substr(0,tmp.find("/")).c_str());
	lower = atoi(tmp.substr(tmp.find("/")+1).c_str());
	
	fractionType y(upper,lower);
	
	fractionType z();
	
	z = x+y;
	
	cout << x+y << "\n";
	cout << x-y << "\n";
	cout << x*y << "\n";
	cout << x/y << "\n";
	cout << z;
}
