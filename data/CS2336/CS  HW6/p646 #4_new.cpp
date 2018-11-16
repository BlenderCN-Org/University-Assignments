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
    string menuItem;
    double menuPrice;
    double total = 0.0;
};

void getData(menuItemType*);
void showMenu(menuItemType*);
void printCheck(menuItemType*);

int main()
{
    menuItemType* menuList = new menuItemType[8];
    getData(menuList);

    delete [] menuList;
}

void getData(menuItemType* menuList)
{
    menuList[0].menuItem = "Plain Egg";
    menuList[0].menuPrice = 1.45;

    //menuList++;

    menuList[1].menuItem = "Bacon and Egg";
    menuList[1].menuPrice = 2.45;

    //menuList++;

    menuList[2].menuItem = "Muffin";
    menuList[2].menuPrice = 0.99;

    //menuList++;

    menuList[3].menuItem = "French Toast";
    menuList[3].menuPrice = 1.99;

    //menuList++;

    menuList[4].menuItem = "Fruit Basket";
    menuList[4].menuPrice = 2.49;

    //menuList++;

    menuList[5].menuItem = "Cereal";
    menuList[5].menuPrice = 0.69;

    //menuList++;

    menuList[6].menuItem = "Coffee";
    menuList[6].menuPrice = 0.50;

    //menuList++;

    menuList[7].menuItem = "Tea";
    menuList[7].menuPrice = 0.75;


    showMenu(menuList);
}

void showMenu(menuItemType* menuList)
{
    cout << "Welcome to Jason's Restaurant" << "\n";
    string input;

    for(int i = 0; i < 8; i++)
    {
        cout << setprecision(2) << fixed << i+1 << ".   " << menuList[i].menuItem << setw(25-menuList[i].menuItem.length()) << "   $" << menuList[i].menuPrice << "\n";
    }

    cout << "To Buy an Item, type in the name as it appears followed by a dash its quantity" << "\n";
    cout << "EX) 1-10 = Ordering 10 Plain Eggs" << "\n";
    cout << "To Display the Menu, type Menu" << "\n";
    cout << "To Display your Check, type Check" << "\n";
    cout << "Input:: ";
    cin >> input;

    if(input == "Menu")
    {
        cout << "//////////////////////////////////////////////" << "\n";
        showMenu(menuList);

    }
    else if (input == "Check")
    {
        cout << "//////////////////////////////////////////////" << "\n";
        printCheck(menuList);

    }
    else
    {
        int num = atoi(input.substr(0,input.find("-")).c_str())-1;
        int quant = atoi(input.substr(input.find("-")+1).c_str());
        //tot = tot + jR.menuPrice[num]*quant
        menuList[num].total = menuList[num].total + (menuList[num].menuPrice * quant);

        cout << "//////////////////////////////////////////////" << "\n";

        showMenu(menuList);


    }
}

void printCheck(menuItemType* menuList)
{
    double sum = 0.0;

    for(int i = 0; i < 8; i++)
    {
        if(menuList[i].total != 0.0)
        {
            cout << setprecision(2) << fixed << (menuList[i].total)/(menuList[i].menuPrice) << " " << menuList[i].menuItem << setw(20-menuList[i].menuItem.length()) << "   $" << menuList[i].total << "\n";
            sum += menuList[i].total;
        }
    }

    cout << "Tax:: $" <<  setprecision(2) << fixed << sum * 0.05 << "\n";
    cout << setprecision(2) << fixed << "Amount Due:: $" << sum + (sum*0.05) << "\n";
}


