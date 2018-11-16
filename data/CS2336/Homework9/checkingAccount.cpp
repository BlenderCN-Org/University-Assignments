#include "checkingAccount.h"
#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>

using namespace std;

checkingAccount::checkingAccount(int a, double b)
{

}

checkingAccount::~checkingAccount()
{
    //dtor
}
void checkingAccount::setInterestRate(double rate)
{
    interestRate = rate;
}
double checkingAccount::getInterestRate()
{
    return interestRate;
}
void checkingAccount::setMinBalance(int bal)
{
    minBalance = bal;
}
int checkingAccount::getMinBalance()
{
    return minBalance;
}
void checkingAccount::setServiceCharge(double rate)
{
    serviceCharge = rate;
}
double checkingAccount::getServiceCharge()
{
    return serviceCharge;
}
int checkingAccount::isMin()
{
    if(Balance < minBalance)
    {
        return -1;
    }
    else if(Balance > minBalance)
    {
        return 1;
    }
    else
    {
        return 0;
    }
}
void checkingAccount::withdraw(double money)
{
    if(Balance - money >= minBalance)
    {
        Balance = Balance - money;
        cout << "You have taken out $" << money << " from your account, and are left with $" << Balance << "\n";
    }
    else
    {
        cout << "You cannot withdraw that amount of money, it would make your balance negative or put it below the min balance" << "\n";
    }
}
