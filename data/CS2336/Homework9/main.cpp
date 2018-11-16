#include <iostream>
#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>

#include "bankAccount.h"
#include "checkingAccount.h"
#include "savingsAccount.h"

using namespace std;

int main()
{
    int bNum = 25567;
    double bBal = 500.00;

    int cNum = 95320;
    double cBal = 3660.24;

    int sNum = 21743;
    double sBal = 93456.241;

    /////////////////////////////////////////////////////

    bankAccount x(bNum,bBal);
        x.setAccountNumber(35019);
        cout << "Get Account Number:: " << x.getAccountNumber() << "\n";
        cout << "Get Balance:: " << x.getBalance() << "\n";
        x.deposit(500.00);
        cout << "Get Balance:: " << x.getBalance() << "\n";
        x.withdraw(500.00);
        cout << "Get Balance:: " << x.getBalance() << "\n";
        x.outprintInfo();

    cout << "\n"; /////////////////////////////////////////////////////

    checkingAccount y(cNum,cBal);
        y.setAccountNumber(25134);
        cout << "Get Account Number:: " << y.getAccountNumber() << "\n";
        cout << "Get Balance:: " << y.getBalance() << "\n";
        y.deposit(500.00);
        cout << "Get Balance:: " << y.getBalance() << "\n";
        y.withdraw(500.00);
        cout << "Get Balance:: " << y.getBalance() << "\n";
        y.setInterestRate(0.24);
        cout << "Get IR:: " << y.getInterestRate() << "\n";
        y.setMinBalance(400);
        cout << "Get Min:: " << y.getMinBalance() << "\n";
        y.withdraw(5000);



    cout << "\n"; /////////////////////////////////////////////////////

    savingsAccount z(sNum,sBal);
        z.setInterestRate(.45);
        cout << "Get IR:: " << z.getInterestRate() << "\n";
        z.withdraw(5000);
        z.outprintInfo();

}
