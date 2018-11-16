#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>

using namespace std;

    const float Oz9 = 1.75;
    const float Oz12 = 1.90;
    const float Oz15 = 2.00;

    string Choice(string resp,int tot9oz,int tot12oz,int tot15oz,int totMoney,int totCoffee);
    int main();

int main()
{
    int tot9oz;
    int tot12oz;
    int tot15oz;
    int totMoney;
    int totCoffee;


    string resp;

    cout << "Welcome to Jason's Coffee Shop!" << "\n";
    cout << ":::COMMANDS:::" << "\n";
    cout << "  BUY COFFEE  " << "\n";
    cout << "  CUPS SOLD   " << "\n";
    cout << "  MONEY MADE  " << "\n";
    cout << "  COFFEE SOLD " << "\n";
    cout << "     HELP     " << "\n";
    cout << "     EXIT     " << "\n";
    cout << "-------------------------------------------------" << "\n";

    cin >> resp;

    Choice(resp,tot9oz,tot12oz,tot15oz,totMoney,totCoffee);

   // cout << "-------------------------------------------------" << "\n";
}

string Choice(string resp,int tot9oz,int tot12oz,int tot15oz,int totMoney,int totCoffee)
{
    string buycoffee = "BUY COFFEE";

    if((resp.compare(buycoffee))
    {
        string resp1;
        string resp2;

        cout << "Which Coffee Cup Size Would You Like to Buy?" << "\n";
        cout << "9oz :: $1.75 :: SMALL" << "\n";
        cout << "12oz :: $1.90 :: MEDIUM" << "\n";
        cout << "15oz :: $2.00 :: LARGE" << "\n";

        cin >> resp1;

        cout << "-------------------------------------------------" << "\n";



        /*
        if(resp1.compare("9oz"))
        {
            cout << "How many cups would you like to buy?" << "\n";
            cout << "-------------------------------------------------" << "\n";
            cin >> resp2;
            cout << "-------------------------------------------------" << "\n";

            totCoffee += atoi(resp2.c_str());
            tot9oz += atoi(resp2.c_str());
        }
        else if(resp1.compare("12oz") == 0)
        {
            cout << "How many cups would you like to buy?" << "\n";
            cout << "-------------------------------------------------" << "\n";
            cin >> resp2;
            cout << "-------------------------------------------------" << "\n";

            totCoffee += atoi(resp2.c_str());
            tot12oz += atoi(resp2.c_str());
        }
        else if(resp1.compare("15oz") == 0)
        {
            cout << "How many cups would you like to buy?" << "\n";
            cout << "-------------------------------------------------" << "\n";
            cin >> resp2;
            cout << "-------------------------------------------------" << "\n";

            totCoffee += atoi(resp2.c_str());
            tot15oz += atoi(resp2.c_str());
        }

        */


    }

    /*
    else if(resp.compare("CUPS SOLD") == 0)
    {
        cout << "9oz Sold:: " << tot9oz << "\n";
        cout << "12oz Sold:: " << tot12oz << "\n";
        cout << "15oz Sold:: " << tot15oz << "\n";
        cout << "-------------------------------------------------" << "\n";
    }
    else if(resp.compare("MONEY MADE") == 0)
    {
        cout << "Total Gross Sales:: " << totMoney << "\n";
        cout << "-------------------------------------------------" << "\n";
    }

    else if(resp.compare("COFFEE SOLD") == 0)
    {
        cout << "Total Coffee Sold:: " << totCoffee << "\n";
        cout << "-------------------------------------------------" << "\n";
    }

    else if(resp.compare("HELP") == 0)
    {

    }
    else if(resp.compare("EXIT") == 0)
    {

    }
    else
    {

    }

*/
}






