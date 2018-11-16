#ifndef SAVINGSACCOUNT_H
#define SAVINGSACCOUNT_H
#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include "bankAccount.h"

class savingsAccount: public bankAccount
{
    double interestRate;

    public:
        savingsAccount(int,double);
        void setInterestRate(double);
        double getInterestRate();
        //POST INTEREST?
        void withdraw(double);
        void outprintInfo();
};

#endif // SAVINGSACCOUNT_H
