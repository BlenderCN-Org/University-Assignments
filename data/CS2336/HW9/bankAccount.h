#ifndef BANKACCOUNT_H
#define BANKACCOUNT_H
#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>

using namespace std;

class bankAccount
{
    int AccountNumber;
    double Balance;

    public:
        vbankAccount(int,double); //Account Number (INT) and Balance (DOUBLE)
        void setAccountNumber(int);
        int getAccountNumber();
        double getBalance();
        void deposit(double);
        void withdraw(double);
        void outprintInfo();
};

#endif // BANKACCOUNT_H
