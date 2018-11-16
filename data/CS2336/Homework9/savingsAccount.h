#ifndef SAVINGSACCOUNT_H
#define SAVINGSACCOUNT_H

#include <bankAccount.h>
#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>


class savingsAccount : public bankAccount
{
    public:
        savingsAccount(int,double);
        virtual ~savingsAccount();
        void setInterestRate(double);
        double getInterestRate();
        //POST INTEREST?
        void withdraw(double);
        void outprintInfo();
        double interestRate;

    protected:

    private:
};

#endif // SAVINGSACCOUNT_H
