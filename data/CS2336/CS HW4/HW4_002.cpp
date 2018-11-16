#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>




using namespace std;

void Display(string Seats[13][6]);
void Acc(string Input,string Seats[13][6]);
void Start(string Seats[13][6]);

int main()
{
    string Seats [13][6];

    for(int i = 0; i < 13; i++) // Fills the Matrix with *'s to show empty Seats
    {
        for(int p = 0; p < 6; p++)
        {
            Seats[i][p] = "*";
        }
    }

    Start(Seats);
}

void Start(string Seats[13][6])
{
    string Input = "";
    cout << "::Current Seating Arrangement::" << "\n";

    Display(Seats);

    cout << "\n";
    cout << "PROGRAM:: Enter Your Ticket Type(First, Business, or Economy) & Seat (Row Seat)" << "\n";
    cout << "PROGRAM:: EX) First-2-6 " << "\n";
    //cout << "\n";
    cin >> Input;
    //cout << "Input:: " << Input << "\n";

    Acc(Input,Seats);
}

void Display(string Seats[13][6])
{
    cout << "       A   B   C   D   E   F" << "\n"; //6 Spaces in Front. 3 Spaces after A-B-C-D-E-F

    for(int i = 0; i < 13; i++)
    {
        if(i<9)
        {
            cout << "Row" << i+1 << ":  ";
        }
        else
        {
            cout << "Row" << i+1 << ": ";
        }

        for(int p = 0; p < 6; p++)
        {
            cout << Seats[i][p] << "   ";
        }

        if(i <= 1)
        {
            cout << "(First)";
        }
        else if(i <= 6)
        {
            cout << "(Business)";
        }
        else if(i <= 12)
        {
            cout << "(Economy)";
        }

        cout << "\n";
    }
}

void Acc(string Input,string Seats[13][6]) //First 1 5
{
    string aClass = Input.substr(0,Input.find("-"));

    Input = Input.substr(Input.find("-")+1);

    string rNum = Input.substr(0,Input.find("-"));

    Input = Input.substr((Input.find("-")+1));

    string sNum = Input;


    int rowNum = atoi(rNum.c_str());
    int seatNum = atoi(sNum.c_str());;

    if(aClass == "First")
    {
        if(rowNum <= 2)
        {
            if(Seats[rowNum-1][seatNum-1] != "X")
            {
                Seats[rowNum-1][seatNum-1] = "X";
                cout << "PROGRAM:: You are sitting at Row " << rowNum << ", Col " << seatNum << "\n";
            }
            else
            {
                cout << "PROGRAM:: That seat is already taken, sorry" << "\n";
            }
        }
        else
        {
            cout << "PROGRAM:: You Entered An Invalid Command" << "\n";
        }
    }
    else if(aClass == "Business")
    {
        if(rowNum >= 3 && seatNum <= 7)
        {
            if(Seats[rowNum-1][seatNum-1] != "X")
            {
                Seats[rowNum-1][seatNum-1] = "X";
                cout << "PROGRAM:: You are sitting at Row " << rowNum << ", Col " << seatNum << "\n";
            }
            else
            {
                cout << "PROGRAM:: That seat is already taken, sorry" << "\n";
            }
        }
        else
        {
            cout << "PROGRAM:: You Entered An Invalid Command" << "\n";
        }
    }
    else if(aClass == "Economy")
    {
        if(rowNum >= 8 && seatNum <= 13)
        {
            if(Seats[rowNum-1][seatNum-1] != "X")
            {
                Seats[rowNum-1][seatNum-1] = "X";
                cout << "PROGRAM:: You are sitting at Row " << rowNum << ", Col " << seatNum << "\n";
            }
            else
            {
                cout << "PROGRAM:: That seat is already taken, sorry" << "\n";
            }
        }
        else
        {
            cout << "PROGRAM:: You Entered An Invalid Command" << "\n";
        }
    }
    else
    {
        cout << "PROGRAM:: You Entered An Invalid Command" << "\n";
    }

    cout << "---------------------------------------------------------" << "\n";

    Start(Seats);
}


