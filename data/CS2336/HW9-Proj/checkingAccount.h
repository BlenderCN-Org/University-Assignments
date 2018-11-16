#ifndef CHECKINGACCOUNT_H
#define CHECKINGACCOUNT_H
#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
//#include "bankAccount.h"



class checkingAccount: public bankAccount
{
    double interestRate;
    int minBalance;
    double serviceCharge;

    public:
        checkingAccount(int,double);
        void setInterestRate(double);
        double getInterestRate();
        void setMinBalance(int);
        int getMinBalance();
        void setServiceCharge(double);
        //POST INTEREST?
        double getServiceCharge();
        int isMin();
        void withdraw(double);
};

#endif // CHECKINGACCOUNT_H
