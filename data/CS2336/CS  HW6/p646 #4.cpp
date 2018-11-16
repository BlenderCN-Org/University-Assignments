#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>




using namespace std;

struct menuItemType
{
    string menuItem[8];
    double menuPrice[8];
    string menuList[8];
    double menuSum[8];

};

void getData(menuItemType jR,double tot);
void showMenu(menuItemType jR,double tot);
void printCheck(menuItemType jR,double tot);

int main()
{
    menuItemType jR;

    double tot = 0.0;
    getData(jR,tot);


}

void getData(menuItemType jR,double tot)
{
    //DEFINING STRUCT NAMES
    jR.menuItem[0] = "Plain Egg";
    jR.menuItem[1] = "Bacon and Egg";
    jR.menuItem[2] = "Muffin";
    jR.menuItem[3] = "French Toast";
    jR.menuItem[4] = "Fruit Basket";
    jR.menuItem[5] = "Cereal";
    jR.menuItem[6] = "Coffee";
    jR.menuItem[7] = "Tea";

    //DEFINING STRUCT PRICES
    jR.menuPrice[0] = 1.45;
    jR.menuPrice[1] = 2.45;
    jR.menuPrice[2] = 0.99;
    jR.menuPrice[3] = 1.99;
    jR.menuPrice[4] = 2.49;
    jR.menuPrice[5] = 0.69;
    jR.menuPrice[6] = 0.50;
    jR.menuPrice[7] = 0.75;

    //DEFINING MENU LIST
    jR.menuList[0] = "1 Plain Egg      :   1.45";
    jR.menuList[1] = "2 Bacon and Egg  :   2.45";
    jR.menuList[2] = "3 Muffin         :   0.99";
    jR.menuList[3] = "4 French Toast   :   1.99";
    jR.menuList[4] = "5 Fruit Basket   :   2.49";
    jR.menuList[5] = "6 Cereal         :   0.69";
    jR.menuList[6] = "7 Coffee         :   0.50";
    jR.menuList[7] = "8 Tea            :   0.75";

    jR.menuSum[0] = 0;
    jR.menuSum[1] = 0;
    jR.menuSum[2] = 0;
    jR.menuSum[3] = 0;
    jR.menuSum[4] = 0;
    jR.menuSum[5] = 0;
    jR.menuSum[6] = 0;
    jR.menuSum[7] = 0;


    showMenu(jR,tot);
}

void showMenu(menuItemType jR,double tot)
{
    string input;

    cout << "::Welcome to Johnny's Restaurant::" << "\n";
    for(int i = 0; i < 8; i++)
    {
        cout << jR.menuList[i] << "\n";
    }

    cout << "To Buy an Item, type in the name as it appears followed by a dash its quantity" << "\n";
    cout << "EX) 1-10 = Ordering 10 Plain Eggs" << "\n";
    cout << "To Display the Menu, type Menu" << "\n";
    cout << "EX) Menu = Open Menu" << "\n";
    cout << "To Display your Check, type Check" << "\n";
    cout << "EX) Check = Display Check" << "\n";
    cout << "Input:: ";
    cin >> input;

    if(input == "Menu")
    {
        cout << "//////////////////////////////////////////////" << "\n";
        showMenu(jR,tot);
    }
    else if (input == "Check")
    {
        cout << "//////////////////////////////////////////////" << "\n";
        printCheck(jR,tot);
    }
    else
    {
        int num = atoi(input.substr(0,input.find("-")).c_str())-1;
        int quant = atoi(input.substr(input.find("-")+1).c_str());
        tot = tot + jR.menuPrice[num]*quant;
        jR.menuSum[num] = jR.menuSum[num] + jR.menuPrice[num]*quant;
        cout << "//////////////////////////////////////////////" << "\n";
        showMenu(jR,tot);
    }

}

void printCheck(menuItemType jR,double tot)
{
    for(int i = 0; i < 8; i++)
    {
        if(jR.menuSum[i] != 0.0)
        {
            cout << jR.menuItem[i] << " " << jR.menuSum[i] << "\n";
        }
    }

    cout << setprecision(2) << fixed << "Tax:: "<< (tot*.05) << "\n";
    cout << setprecision(2) << fixed << "Amount Due:: " << (tot*.05 + tot) << "\n";
}

void mgm()
{

}

