#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>


//Buy Coffee
//# of Cups Sold
//# of Coffee Sold
//Total Money Made

using namespace std;

void mainMenu(double cSmall, double cMed, double cLarge, int sSold, int mSold, int lSold);
void buyCoffee(double cSmall, double cMed, double cLarge, int sSold, int mSold, int lSold, string siz, int quant);
void numCups(double cSmall, double cMed, double cLarge, int sSold, int mSold, int lSold); //CupsSold
void numCoffee(double cSmall, double cMed, double cLarge, int sSold, int mSold, int lSold);//CoffeeSold
void totMoney(double cSmall, double cMed, double cLarge, int sSold, int mSold, int lSold);//MoneySold
void Help(double cSmall, double cMed, double cLarge, int sSold, int mSold, int lSold);





int main()
{
    //int test = 5;
    //double tests = 2.5;
    //cout << tests*test;

    const double cSmall = 1.75;
    const double cMed = 1.90;
    const double cLarge = 2.00;

    int sSold = 0;
    int mSold = 0;
    int lSold = 0;


    mainMenu(cSmall,cMed,cLarge,sSold,mSold,lSold);
}

void mainMenu(double cSmall, double cMed, double cLarge, int sSold, int mSold, int lSold)
{

   string com;
   string siz;
   int quant;

   cout << "SYSTEM:: Welcome to Jason's Coffee Shop!!" << "\n";
   cout << "SYSTEM:: What would you like to do? Type 'Help - 0' for Help" << "\n";
   cout << "USER:: ";
   cin >> com >> siz >> quant;
   //cout << com << siz << quant;

   if(com == "BuyCoffee" && (siz == "Small" || siz == "Medium" || siz == "Large"))
   {
        buyCoffee(cSmall,cMed,cLarge,sSold,mSold,lSold,siz,quant);
   }
   else if(com == "CupsSold")
   {
        numCups(cSmall,cMed,cLarge,sSold,mSold,lSold);
   }
   else if(com == "CoffeeSold")
   {
        numCoffee(cSmall,cMed,cLarge,sSold,mSold,lSold);
   }
   else if(com == "MoneySold")
   {
        totMoney(cSmall,cMed,cLarge,sSold,mSold,lSold);
   }
   else if(com == "Help")
   {
       Help(cSmall,cMed,cLarge,sSold,mSold,lSold);
   }
   else
   {
        cout << "SYSTEM:: You Entered an Invalid Command, Type Help for Help if you need further Instructions" << "\n";
        cout << "SYSTEM:: Returning to the Menu..." << "\n";
        cout << "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||" << "\n";
        mainMenu(cSmall,cMed,cLarge,sSold,mSold,lSold);
   }

    //cout << "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||" << "\n";

}

void buyCoffee(double cSmall, double cMed, double cLarge, int sSold, int mSold, int lSold, string siz, int quant) //BuyCoffee
{
    if(siz == "Small")
    {
        sSold+=quant;
        cout << "SYSTEM:: You bought " << quant << " Small Cups!" << "\n";
    }
    else if(siz == "Medium")
    {
        mSold+=quant;
        cout << "SYSTEM:: You bought " << quant << " Medium Cups!" << "\n";
    }
    else if(siz == "Large")
    {
        lSold+=quant;
        cout << "SYSTEM:: You bought " << quant << " Large Cups!" << "\n";
    }

    cout << "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||" << "\n";
    mainMenu(cSmall,cMed,cLarge,sSold,mSold,lSold);
}

void numCups(double cSmall, double cMed, double cLarge, int sSold, int mSold, int lSold) //CupsSold
{
    cout << "SYSTEM:: Small Cups Sold:: " << sSold << "\n";
    cout << "SYSTEM:: Medium Cups Sold:: " << mSold << "\n";
    cout << "SYSTEM:: Large Cups Sold:: " << lSold << "\n";
    cout << "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||" << "\n";
    mainMenu(cSmall,cMed,cLarge,sSold,mSold,lSold);
}

void numCoffee(double cSmall, double cMed, double cLarge, int sSold, int mSold, int lSold)//CoffeeSold
{
    cout << "SYSTEM:: Total Coffee Sold:: " <<sSold+mSold+lSold << "\n";
    cout << "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||" << "\n";
    mainMenu(cSmall,cMed,cLarge,sSold,mSold,lSold);
}

void totMoney(double cSmall, double cMed, double cLarge, int sSold, int mSold, int lSold)//MoneySold
{
    cout << "SYSTEM:: Total Revenue:: $" <<(cSmall*sSold) + (cMed * mSold) + (cLarge * lSold) << "\n";
    cout << "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||" << "\n";
    mainMenu(cSmall,cMed,cLarge,sSold,mSold,lSold);
}

void Help(double cSmall, double cMed, double cLarge, int sSold, int mSold, int lSold)
{
    cout << "SYSTEM:: To CHOOSE AN ITEM, you must type in a COMMAND" << "\n";
    cout << "SYSTEM:: To BUY COFFEE, type this command:: BuyCoffee 'SIZE' 'QUANTITIY'" << "\n";
    cout << "SYSTEM:: To SEE THE NUMBER OF CUPS SOLD, type this command:: CupsSold - 0" << "\n";
    cout << "SYSTEM:: To SEE THE AMOUNT OF COFFEE SOLD, type this command:: CoffeeSold - 0" << "\n";
    cout << "SYSTEM:: To SEE THE TOTAL REVENUE, type this command:: MoneySold - 0" << "\n";
    cout << "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||" << "\n";
    mainMenu(cSmall,cMed,cLarge,sSold,mSold,lSold);
}




