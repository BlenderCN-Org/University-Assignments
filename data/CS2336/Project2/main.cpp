#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include <algorithm>
#include "complexNumber.h"

using namespace std;

bool valGet(string);
void solve(complexNumber x,complexNumber y);

int main()
{
    ifstream file ("complex3.txt"); // Opens File
    string line; //Line used for File
    //int lines;



    ofstream outFile;
    outFile.open("results.txt");

    complexNumber a("4+2i");
    complexNumber b("-2-1i");




    while(getline(file,line)) // Loops through the file to gather each line
    {
        if(valGet(line)==true)
        {
            string n1; //The first Statement
            string n2; //The second Statement
            stringstream s1;
            stringstream s2;
            string AWPerator; //The operator
            string org = line;

            n1 = line.substr(0,line.find(" "));// Defining the Vars
            line = line.substr(line.find(" ")+1);// Defining the Vars
            AWPerator = line.substr(0,line.find(" "));// Defining the Vars
            line = line.substr((line.find(" ")+1));// Defining the Vars
            n2 = line;// Defining the Vars



    if(n1 == ("+") || n1 == ("-") || n1 == ("*") || n1 == ("/") //Checking to see if any operators are in the initial statements
     || n1.find("<")!= string::npos || n1.find(">")!= string::npos || n1.find("=") != string::npos || n1.find("/=")!= string::npos)
    {
        AWPerator = "";
    }

    if(n2 == ("+") || n2 == ("-") || n2 == ("*") || n2 == ("/") //Checking to see if any operators are in the initial statements
     || n2.find("<")!= string::npos || n2.find(">")!= string::npos || n2.find("=")!= string::npos || n2.find("/=")!= string::npos)
    {
       AWPerator = "";
    }

    if(n1.find_last_of("i") != n1.find_first_of("i")) // IF there is more than 1 I in the function
    {
        AWPerator = "";
    }

    if(n2.find_last_of("i") != n2.find_first_of("i")) // IF there is more than 1 I in the function
    {
        AWPerator = "";
    }

    if(n2.find_last_of(" ") != n2.find_first_of(" "))// IF there is more than 1 space in the function
    {
        AWPerator = "";
    }

           //cout << n1 << "\n";
           //cout << AWPerator << "\n";
           //cout << n2 << "\n";

            complexNumber x; // First Complex Number
            s1 << n1;
            s1 >> x;


            complexNumber y; // Second Complex Number
            s2 << n2;
            s2 >> y;

            //cout << x.real << "\n";
            //cout << x.imag << "\n";
            //cout << y.real << "\n";
            //cout << y.imag << "\n";

            //Checks for Operations

            if(AWPerator == "+")
            {
                //cout << x+y << "\n";
                outFile << org << "    " << x + y << "\n";
            }
            else if(AWPerator == "-")
            {
                outFile << org << "    " << x - y << "\n";
            }
            else if(AWPerator == "*")
            {
               outFile << org << "    " << x * y << "\n";
            }
            else if(AWPerator == "/")
            {
                outFile << org << "    " << x / y << "\n";
            }
            else if(AWPerator == "=")
            {
                if((x == y) == true)
                {
                    outFile <<  org << "    " << "True" << "\n";
                }
                else
                {
                    outFile <<  org << "    " << "False" << "\n";
                }
            }
            else if(AWPerator == ">")
            {
                if((x > y) == true)
                {
                    outFile << org << "    " << "True" << "\n";
                }
                else
                {
                    outFile << org << "    " << "False" << "\n";
                }
            }
            else if(AWPerator == "<")
            {
                if((x < y) == true)
                {
                    outFile <<  org << "    " << "True" << "\n";
                }
                else
                {
                    outFile <<  org << "    " << "False" << "\n";
                }
            }
            else if(AWPerator == "/=")
            {
                if((x != y) == true)
                {
                    outFile << org << "    " << "True" << "\n";
                }
                else
                {
                    outFile << org << "    " << "False" << "\n";
                }
            }
        }
        else
        {
            //outFile << line << "    " << "BAD INPUT" << "\n";
        }
        line = "";
    }

    outFile.close();




//ifile >> x;
//cout << x.real << "\n";
//cout << x.imag << "\n";

}

bool valGet(string l) // Gets the info, puts in array, then validates user input
{
    string n1; //First Var
    string n2; // Second Var
    string AWPerator;
    int numspaces = 0;
    int numi = 0;
    //int iCount;
    bool isCorrect = true;

    for(int i = 0; i < l.length(); i++)
    {
        if(l.substr(i,i+1) == " ")
        {
            numspaces++;
        }
         if(l.substr(i,i+1) == "i")
         {
             numi++;
         }
    }

    if(numspaces != 2 || numi > 2)
    {
        isCorrect = false;
    }

    ///////////////////////////////////////////////// N1


    //Checks each statement to find any invalid characters
    if(isCorrect != false)
    {
        if((n1.find("+") != string::npos || n1.find("-")) != string::npos && n1.find("i") != string::npos) // Real + Imag
    {
        string tmp;

        if(n1.find("+") != string::npos) //If no +
        {
            tmp = n1.substr(0,n1.find("+"));
        }
        else
        {
            tmp = n1.substr(0,n1.find("-")-1);
        }

        if(atoi(tmp.c_str())%1 == 0) //Checks to see if there is a digit
        {
            isCorrect = true;
        }
        else if(atof(tmp.c_str()) > 0) // checks to see if the string length is greather than 0
        {
            isCorrect = true;
        }
        else
        {
            isCorrect = false;
        }

        if(isCorrect != false)
        {
            if(n1.find("+") != string::npos) // Finding any +'s in the statement
            {
                tmp = n1.substr(n1.find("+")+2,n1.find("i")-1);

                if(atoi(tmp.c_str())%1 == 0 || atof(tmp.c_str()) > 0) // CHecking for digits or anything with a size > 0
                {
                    isCorrect = true;
                }
                else
                {
                    isCorrect = false;
                }
            }
            else
            {
                tmp = n1.substr(n1.find("-")+2,n1.find("i")-1); // |1|2|+|4|i|

                if(atoi(tmp.c_str())%1 == 0 || atof(tmp.c_str()) > 0)
                {
                    isCorrect = true;
                }
                else
                {
                    isCorrect = false;
                }
            }
        }
    }
    }
    else if(atoi(n1.c_str())%1 == 0 || atof(n1.c_str()) > 0) // Real numbers
    {
        isCorrect = true;
    }
    else // Imag numbers
    {
        string tmp = n1.substr(0,n1.find("i"));

        if(atoi(tmp.c_str())%1 == 0)
        {
            isCorrect = true;
        }
        else if(atof(tmp.c_str()) > 0)
        {
            isCorrect = true;
        }
        else
        {
            isCorrect = false;
        }
    }

    /////////////////////////////////////////////////////////////

    if(isCorrect != false)
    {
        if((n2.find("+") != string::npos || n2.find("-")) != string::npos && n2.find("i") != string::npos) // Real + Imag number checking
        {
        string tmp;

        if(n2.find("+") != string::npos) //If there isnt a + in the statement
        {
            tmp = n2.substr(0,n2.find("+"));
        }
        else
        {
            tmp = n2.substr(0,n2.find("-")-1);
        }

        if(atoi(tmp.c_str())%1 == 0) //if it is ia digit
        {
            isCorrect = true;
        }
        else if(atof(tmp.c_str()) > 0) // if its a value > 0;
        {
            isCorrect = true;
        }
        else
        {
            isCorrect = false;
        }

        if(isCorrect != false)
        {
            if(n2.find("+"))
            {
                tmp = n2.substr(n2.find("+")+2,n2.find("i")-1); //finding a +

                if(atoi(tmp.c_str())%1 == 0 || atof(tmp.c_str()) > 0) // checking for a real number
                {
                    isCorrect = true;
                }
                else
                {
                    isCorrect = false;
                }
            }
            else
            {
                tmp = n2.substr(n2.find("-")+2,n2.find("i")-1); // |1|2|+|4|i|

                if(atoi(tmp.c_str())%1 == 0 || atof(tmp.c_str()) > 0)
                {
                    isCorrect = true;
                }
                else
                {
                    isCorrect = false;
                }
            }
        }
    }
    else if(atoi(n2.c_str())%1 == 0 || atof(n2.c_str()) > 0) // Real numbers checking
    {
        isCorrect = true;
    }
    else // Imag
    {
        string tmp = n2.substr(0,n2.find("i"));

        if(atoi(tmp.c_str())%1 == 0)
        {
            isCorrect = true;
        }
        else if(atof(tmp.c_str()) > 0)
        {
            isCorrect = true;
        }
        else
        {
            isCorrect = false;
        }
    }
}

    return isCorrect;
}


void solve(complexNumber x,complexNumber y)
{

}

/*
string copie = l;
    int siz = copie.length();
    int spaces;

    for(int i = 0; i < siz; i++)
    {
        if(copie.find(" "))
        {
            spaces++;
            copie.substr(0,copie.find(" "));
        }
    }

    string* arr = new string[spaces+1];

    //////////////////////////////////////////////////////

    for(int i = 0; i < spaces; i++)
    {
        arr[i] = l.substr(0,l.find(" "));

        if(l.find(" ") != string::npos)
        {
            l = l.substr(0,l.find(" "));
        }
    }

    int opc = 0;
    int cpm = 0;

    for(int i = 0; i < spaces; i++)
    {
        if(i%2 == 0)    // Complex Number
        {
            complexNumber temp (arr[i]);
            compArr[cpm] = temp;
            cpm++;
        }
        else    //Operator
        {
            if(arr[i] == ("+") || arr[i] == ("-") || arr[i] == ("*") || arr[i] == ("/")
               || arr[i] == ("<") || arr[i] == (">") || arr[i] == ("=") || arr[i] == ("/="))
            {
                operators[opc] = arr[i];
                opc++;
            }
        }
    }

    /////////////////////////////////////////////////////////////////

*/
