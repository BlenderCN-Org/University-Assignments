#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include "savingsAccount.h"




using namespace std;

savingsAccount::savingsAccount(int a, double b)
{
    AccountNumber = a;
    Balance = b;
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
    if(Balance - money != 0)
    {
        Balance = Balance - money;
        cout << "You have taken out $" << money << "from your account, and are left with $" << Balance << "\n";
    }
    else
    {
        cout << "You cannot withdraw that amount of money, it would make your balance negative" << "\n";
    }
}
void savingsAccount::outprintInfo()
{
    cout << "Account Info-----------------------------------------------------------------------------" << "\n";
    cout << "\n";
    cout << "Account Number:: " << SavingsNumber << "\n";
    cout << "Balance:: " << Balance << "\n";
    cout << "-----------------------------------------------------------------------------------------" << "\n";
}
