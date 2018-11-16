#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include "bankAccount.h"

using namespace std;

//QUESTIONS::
//
//Post Interest = Simple Interest (Look Up)
//Write a check:: Subtracting Amount, are you overdrawn or over min
//Account Numbers:: Separate or Input??
//

bankAccount::bankAccount(int a, double b)
{
    AccountNumber = a;
    Balance = b;
}

void bankAccount::setAccountNumber(int num)
{
    AccountNumber = num;
    cout << "Your new account number is:: " << AccountNumber << "\n";
}

int bankAccount::getAccountNumber()
{
    return AccountNumber;
}

double bankAccount::getBalance()
{
    return Balance;
}
void bankAccount::deposit(double money)
{
    Balance = Balance + money;
    cout << "You have put in $" << money << "toi your account, and now have $" << Balance << "\n";
}
void bankAccount::withdraw(double money)
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
void bankAccount::outprintInfo()
{
    cout << "Account Info-----------------------------------------------------------------------------" << "\n";
    cout << "\n";
    cout << "Account Number:: " << AccountNumber << "\n";
    cout << "Balance:: " << Balance << "\n";
    cout << "-----------------------------------------------------------------------------------------" << "\n";
}
