#ifndef CHECKINGACCOUNT_H
#define CHECKINGACCOUNT_H

#include <bankAccount.h>
#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>


class checkingAccount : public bankAccount
{
    public:
        checkingAccount(int,double);
        virtual ~checkingAccount();
        void setInterestRate(double);
        double getInterestRate();
        void setMinBalance(int);
        int getMinBalance();
        void setServiceCharge(double);
        //POST INTEREST?
        double getServiceCharge();
        int isMin();
        void withdraw(double);
        double interestRate;
        int minBalance;
        double serviceCharge;

    protected:

    private:
};

#endif // CHECKINGACCOUNT_H
