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


class bankAccount
{
    public:
        bankAccount(int,double);
        virtual ~bankAccount();
        void setAccountNumber(int);
        int getAccountNumber();
        double getBalance();
        void deposit(double);
        void withdraw(double);
        void outprintInfo();
        int AccountNumber;
        double Balance;


    protected:

    private:
};

#endif // BANKACCOUNT_H
