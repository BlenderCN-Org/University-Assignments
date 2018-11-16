#include "savingsAccount.h"
#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>

using namespace std;

savingsAccount::savingsAccount(int a, double b) : bankAccount(a,b)
{

}

savingsAccount::~savingsAccount()
{
    //dtor
}

void savingsAccount::setInterestRate(double rate)
{
    interestRate = rate;
}
double savingsAccount::getInterestRate()
{
    return interestRate;
}
void savingsAccount::withdraw(double money)
{
    if(Balance - money >= 0)
    {
        Balance = Balance - money;
        cout << "You have taken out $" << money << " from your account, and are left with $" << Balance << "\n";
    }
    else
    {
        cout << "You cannot withdraw that amount of money, it would make your balance negative" << "\n";
    }
}
void savingsAccount::outprintInfo()
{
    cout << "Account Info------------------------------------------------------------------" << "\n";
    cout << "Account Number:: " << AccountNumber << "\n";
    cout << "Balance:: $" << fixed << setprecision(2) << Balance << "\n";
    cout << "------------------------------------------------------------------------------" << "\n";
}
