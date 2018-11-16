#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include <queue>

#include "node.h"
#include "binarysearchtree.h"

using namespace std;

void definite(string,ostream&); //Definite Integral Funct
void indefinite(string,ostream&); //Indefinite Integral Funct
void createNodes(queue<string>,binarysearchtree&); //Creates a node based on the queue, need to change
string removeSpaces(string); //Removes the Spaces
queue<string> addTerms(queue<string>); //Combines Like Terms
queue<string> likeLis(queue<string>,string); //Corrects the Negatives
void solveIntegral(binarysearchtree&,string,ostream&); //Solves the Integral

int main()
{
    ifstream file("integrals4.txt",ios::binary); //Input File
    string s; //String to get the line

    ofstream fileOut; //Output File
    fileOut.open("answers.txt"); //Opens the answer.txt file

    /*
    string line = "";
    while(getline(file,line))
    {
        cout << line << endl;
    }
    */

    while(getline(file,s)) //While the file isn't empty
    {
        if(s.substr(0,1) == "|") //If the pip is at the beginning
        {
            //cout << s << endl;
            indefinite(s,fileOut); //Its an Indefinite Integral
        }
        else
        {
            definite(s,fileOut); //Its a Definite Integral
        }
    }

    file.close(); //Closes the File
}

void indefinite(string s,ostream& fileOut) //Indefinite
{
    binarysearchtree tree; //BST Object
    int counter = 0; //Counter Var
    string part = ""; //Part to substr spaces

    s = s.substr(s.find("|")+2);

    s = s.substr(0,s.find("d")-1);
    // S is now the function, minus the pipe and dx

    s = removeSpaces(s); //Removes the spaces from the expression
    string var = s; //Temporary Var for S

    queue<string> lis; //Queue lis to perform functions

    int defLength = s.length(); //The Static Length of the Queue

    //cout << "S:: " << s << endl;

    if(isdigit(s[0]) && s.length() == 1) //If the val of S is only 1 var, its a digit, and its 1 length
    {
        lis.push(s); //Push it in
    }
    else if(s.find("+") == string::npos && s.length() < 3) //If there isn't a +
    {
        lis.push(s);//Push it in
    }
    else //For any other value
    {
         for(int i = 0; i < defLength; i++) //Loop to search through and remove the - & +
            {
                //cout << s[i] << endl;

                if((s[i] == '-' || s[i] == '+') && s[i-1] != '^') //Checks for - & +
                {
                    lis.push(part); //Pushes into the Queue
                    counter++; //Counter Increases
                    part = ""; //Resets Part

                    string tmp = s.substr(i+1); //The string substrings up a space

                    if(tmp.find("+") == string::npos && tmp.find("-") == string::npos) //If there isn't a + or - in the remaining statement
                    {
                        lis.push(tmp); //Push it in
                    }
                }
                else
                {
                    part = part + s[i]; //Part increases based on parameters
                }
            }
    }

    queue<string> out = lis;

    while(!out.empty())
    {
        string tmp = out.front();
        //cout << tmp << endl;
        out.pop();
    }

    if(lis.front() == "") //If the first val is empty in the queue
    {
        lis.pop(); //Pops it out
    }

    lis = likeLis(lis,var); //Corrects the Negatives
    lis = addTerms(lis); //Combines Like Terms
    createNodes(lis,tree); //Creates Nodes and Inserts into the Tree
    solveIntegral(tree,"indf",fileOut); //Solves the Integral
}

void solveIntegral(binarysearchtree& tree,string s,ostream& fileOut) //Solves the Integral
{
    stringstream ss;

    if(s == "indf") //If its an Indefinite Integral
    {
        queue<node> fromTree = tree.printTree(); //Gets The Values of the Tree's queue in G-L order

        int s = fromTree.size(); //Constant Queue Size

        for(int i = 0; i < s; i++)
        {
            node tmp = fromTree.front(); //tmp becomes the queue's front

            if(tmp.toString().find("x") == string::npos) //If there isn't an X
            {
                if(tmp.getCoef() == 1) //If the coefficient is one
                {
                    ss << "x ";
                }
                else //If the coefficient isn't one
                {
                    ss << "+ " << tmp.getCoef() << "x ";
                }
            }
            else if(tmp.toString().find("x") != string::npos && tmp.toString().find("^") == string::npos) //The string contains an X ONLY
            {
                tmp.setExp(tmp.getExp()+1); //Exponent Increases
                if(tmp.getCoef() < 0) //Negative Coefficient
                    ss << "- (" << (tmp.getCoef()*-1) << "/" << tmp.getExp() << ")" << "x^" << tmp.getExp() << " ";
                else if(tmp.getExp() < 0) //Negative Exponent
                    ss << "- (" << tmp.getCoef() << "/" << (tmp.getExp()*-1) << ")" << "x^" << tmp.getExp() << " ";
                else if(tmp.getExp() < 0 && tmp.getCoef() < 0) //Negative Coefficient and Exponent
                    ss << "+ (" << tmp.getCoef()*-1 << "/" << (tmp.getExp()*-1) << ")" << "x^" << tmp.getExp() << " ";
                else //None are Negative
                    ss << "+ (" << tmp.getCoef() << "/" << tmp.getExp() << ")" << "x^" << tmp.getExp() << " ";
            }
            else if(tmp.toString().find("x") != string::npos && tmp.toString().find("^") != string::npos) //The string contains an X and ^
            {
                tmp.setExp(tmp.getExp()+1); //Exponent Increases
                if(tmp.getCoef() < 0) //Negative Coefficient
                    ss << "- (" << (tmp.getCoef()*-1) << "/" << tmp.getExp() << ")" << "x^" << tmp.getExp() << " ";
                else if(tmp.getExp() < 0) //Negative Exponent
                    ss << "- (" << tmp.getCoef() << "/" << (tmp.getExp()*-1) << ")" << "x^" << tmp.getExp() << " ";
                else if(tmp.getExp() < 0 && tmp.getCoef() < 0) //Negative Coefficient and Exponent
                    ss << "+ (" << tmp.getCoef()*-1 << "/" << (tmp.getExp()*-1) << ")" << "x^" << tmp.getExp() << " ";
                else //None are Negative
                    ss << "+ (" << tmp.getCoef() << "/" << tmp.getExp() << ")" << "x^" << tmp.getExp() << " ";
            }

            fromTree.pop(); //Pops from the Tree
        }
        ss << "+ C"; //Adds the constant to the solved integral
        fileOut << ss.str() << endl; //Outprints to the File
    }
    else
    {
        queue<node> fromTree = tree.printTree(); //Temporary Queue
        queue<node> defTree; //Temporary Queue

        int sr = fromTree.size(); //Constant Queue Size

        int s2 = atoi(s.substr(0,s.find(",")).c_str()); //Lower Bound
        int s1 = atoi(s.substr(s.find(",")+1).c_str()); //Upper Bound

        float x = 0; //Sum Lower
        float y = 0; //Sum Upper

        for(int i = 0; i < sr; i++)
        {
            node tmp = fromTree.front(); //Becomes the Front

            if(tmp.toString().find("x") == string::npos) //There is no X
            {
                if(tmp.getCoef() == 1)  //If the coefficient is one
                {
                    ss << "x ";
                    tmp.setExp(1);
                    tmp.hasX = true;

                    //Adds to the Sum
                    x += (tmp.getCoef()/tmp.getExp()) * pow(s2,tmp.getExp());
                    y += (tmp.getCoef()/tmp.getExp()) * pow(s1,tmp.getExp());
                }
                else
                {
                    ss << tmp.getCoef() << "x ";
                    tmp.setExp(1);
                    tmp.hasX = true;

                    //Adds to the Sum
                    x += (tmp.getCoef()/tmp.getExp()) * pow(s2,tmp.getExp());
                    y += (tmp.getCoef()/tmp.getExp()) * pow(s1,tmp.getExp());
                }
            }
            else if(tmp.toString().find("x") != string::npos && tmp.toString().find("^") == string::npos) //X and Exponent
            {
                tmp.setExp(tmp.getExp()+1); //Exponent Increases
                if(tmp.getCoef() < 0) //Negative Coefficient
                    ss << "- (" << (tmp.getCoef()*-1) << "/" << tmp.getExp() << ")" << "x^" << tmp.getExp() << " ";
                else if(tmp.getExp() < 0) //Negative Exponent
                    ss << "- (" << tmp.getCoef() << "/" << (tmp.getExp()*-1) << ")" << "x^" << tmp.getExp() << " ";
                else if(tmp.getExp() < 0 && tmp.getCoef() < 0) //Negative Coefficient and Exponent
                    ss << "+ (" << tmp.getCoef()*-1 << "/" << (tmp.getExp()*-1) << ")" << "x^" << tmp.getExp() << " ";
                else //None are Negative
                    ss << "+ (" << tmp.getCoef() << "/" << tmp.getExp() << ")" << "x^" << tmp.getExp() << " ";

                //Adds to the Sum
                x += (tmp.getCoef()/tmp.getExp()) * pow(s2,tmp.getExp());
                y += (tmp.getCoef()/tmp.getExp()) * pow(s1,tmp.getExp());

                defTree.push(tmp);
            }
            else if(tmp.toString().find("x") != string::npos && tmp.toString().find("^") != string::npos)
            {
                tmp.setExp(tmp.getExp()+1); //Exponent Increases
                if(tmp.getCoef() < 0) //Negative Coefficient
                    ss << "- (" << (tmp.getCoef()*-1) << "/" << tmp.getExp() << ")" << "x^" << tmp.getExp() << " ";
                else if(tmp.getExp() < 0) //Negative Exponent
                    ss << "- (" << tmp.getCoef() << "/" << (tmp.getExp()*-1) << ")" << "x^" << tmp.getExp() << " ";
                else if(tmp.getExp() < 0 && tmp.getCoef() < 0) //Negative Coefficient and Exponent
                    ss << "+ (" << tmp.getCoef()*-1 << "/" << (tmp.getExp()*-1) << ")" << "x^" << tmp.getExp() << " ";
                else //None are Negative
                    ss << "+ (" << tmp.getCoef() << "/" << tmp.getExp() << ")" << "x^" << tmp.getExp() << " ";

                //Adds to the Sum
                x += (tmp.getCoef()/tmp.getExp()) * pow(s2,tmp.getExp());
                y += (tmp.getCoef()/tmp.getExp()) * pow(s1,tmp.getExp());
                defTree.push(tmp); //Pushes into the 2nd Tree
            }
            fromTree.pop(); //Pops from the tree
        }

        ss << setprecision(3) << fixed << ", " << s2 << "|" << s1 << " = " << y-x; //The final function string stream

        fileOut << ss.str() << endl; //Outputs to the file
    }
}

queue<string> likeLis(queue<string> lis,string orig) //Fixes the Negatives
{
    queue<string> newLis; //Temporary Queue

    int constSiz = lis.size(); //Constant Queue Size

    for(int i = 0; i < constSiz; i++)
    {
        int pos;
        string f = lis.front();
        pos = orig.find(f);

        if(orig[pos-1] == '-' || orig[pos-2] == '-') //If the Position in the Original String has a - next to it
        {
            stringstream ss;
            ss << "-" << f;
            newLis.push(ss.str()); //Pushes the Value to the new Queue
        }
        else //If the Position in the Original String does not have a - next to it
        {
            newLis.push(lis.front()); //Pushes the Value to the new Queue
        }

        lis.pop(); //Pops from the old queue
        orig = orig.substr(pos+f.length()); //Substrings the list
    }

    return newLis;
}

queue<string> addTerms(queue<string> lis) // Combines the like terms
{
    string* nGroup = new string[lis.size()]; //New String Array
    queue<string> n; //Temporary Queue

    int si = lis.size(); //Constant Queue Size

    for(int i = 0; i < si; i++)
    {
        nGroup[i] = lis.front(); //Front of the Queue
        lis.pop(); //Pops from the Queue
    }

    for(int i = 0; i < si; i++)
    {
        bool change = false;
        string tmp = nGroup[i];

        if(tmp.find("x") == string::npos && tmp != "") //Constant
        {
            for(int j = i+1; j < si; j++)
            {
                string gmp = nGroup[j];

                if(gmp.find("x") == string::npos) //Constant #2
                {
                    //Combines the Values
                    int newVal = atoi(tmp.c_str()) + atoi(gmp.c_str());
                    nGroup[j] = "";
                    stringstream ss;
                    ss << newVal;
                    n.push(ss.str());
                    change = true;
                }
            }
            if(change == false)
            {
                n.push(tmp);
            }
        }
        else if(tmp.find("x") != string::npos && tmp.find("^") == string::npos) //Just X
        {
            for(int j = i+1; j < si; j++)
            {
                string gmp = nGroup[j];

                if(gmp.find("x") != string::npos && gmp.find("^") == string::npos) //Just X #2
                {
                    //Combines the Values
                    int newVal = atoi(tmp.c_str()) + atoi(gmp.c_str());
                    nGroup[j] = "";
                    stringstream ss;
                    ss << newVal << "x";
                    n.push(ss.str());
                    change = true;
                }
            }
            if(change == false)
            {
                n.push(tmp);
            }
        }
        else if(tmp.find("x") != string::npos && tmp.find("^") != string::npos) //X and EXP
        {
            for(int j = i+1; j < si; j++)
            {
                string gmp = nGroup[j];

                if(gmp.find("x") != string::npos && gmp.find("^") != string::npos) //X and EXP 2
                {
                    //Combines the Values
                    string tmpC = tmp.substr(0,tmp.find("x"));
                    string tmpE = tmp.substr(tmp.find("^")+1);
                    string gmpC = gmp.substr(0,gmp.find("x"));
                    string gmpE = gmp.substr(gmp.find("^")+1);

                    if(atoi(tmpE.c_str()) == atoi(gmpE.c_str()))
                    {
                        int newVal = atoi(tmpC.c_str()) + atoi(gmpC.c_str());
                        nGroup[j] = "";
                        stringstream ss;
                        ss << newVal << "x^" << tmpE;
                        n.push(ss.str());
                        change = true;
                    }
                }
            }
            if(change == false)
            {
                n.push(tmp);
            }
        }
    }

    return n;
}

void definite(string s,ostream& fileOut) //Definite
{
    binarysearchtree tree; //Tree
    int counter = 0; //Counter
    string part = ""; //Part

    string upper = s.substr(0,s.find("|")); //LowerBound
    s = s.substr(s.find("|")+1);
    string lower = s.substr(0,s.find(" ")); //UpperBound

    //S becomes the Function
    s = s.substr(s.find(" ")+1);
    s = s.substr(0,s.find("d")-1);

    //cout << "S:: " << s << endl;


    //Removes the Spaces
    s = removeSpaces(s);
    string var = s;

    queue<string> lis; //Queue

    //Removes all the Operators
    int defLength = s.length();
    if(isdigit(s[0]) && s.length() ==1)
    {
        lis.push(s);
    }
    else if(s.find("+") == string::npos)
    {
        lis.push(s);
    }
    for(int i = 0; i < defLength; i++)
    {
        if((s[i] == '-' || s[i] == '+') && s[i-1] != '^')
        {
            lis.push(part);
            counter ++;
            part = "";

            string tmp = s.substr(i+1);

            if(tmp.find("+") == string::npos && tmp.find("-") == string::npos)
            {
                lis.push(tmp);
            }
        }
        else
        {
            part = part + s[i];
        }
    }
    if(lis.front() == "")
    {
        lis.pop();
    }

    lis = likeLis(lis,var); //Fixes the Operators
    lis = addTerms(lis); //Combines the Terms
    createNodes(lis,tree); //Creates the Nodes for the Tree

    stringstream sp;
    sp << upper << "," << lower;
    solveIntegral(tree,sp.str(),fileOut); //Solves the Integral
}

void createNodes(queue<string> lis,binarysearchtree& tree) // CHANGE TO LIST PARAMETERS
{
    int s = lis.size(); //Constant Queue Size

    for(int i = 0; i < s; i++)
    {
        string tmp = lis.front();

        if(tmp.find("x") == string::npos) //There is just a constant
        {
            node* cur = new node(atof(tmp.c_str()),0);
            tree.insert(cur);
        }
        else if(tmp.find("x") != string::npos && tmp.find("^") == string::npos) // A coefficient but no exp
        {
            node* cur;

            if(tmp.substr(0,tmp.find("x")) == ""){ //Node is just an X, sets correct Values
                cur = new node(1,1);
                cur->setX(true);
            }
            else { //Sets Correct Values of the Node
                cur = new node(atof(tmp.substr(0,tmp.find("x")).c_str()),1);
                cur->setX(true);
            }

            tree.insert(cur);
        }
        else if(tmp.find("x") != string::npos && tmp.find("^") != string::npos) //A coefficient with an Exp
        {
            node* cur;

            if(tmp.substr(0,tmp.find("x")) == ""){ //Node is just an X, sets correct Values
                cur = new node(1,atof(tmp.substr(tmp.find("^")+1).c_str()));
                cur->setX(true);
            }
            else //Sets Correct Values of the Node
            {
                cur = new node(atof(tmp.substr(0,tmp.find("x")).c_str()),atof(tmp.substr(tmp.find("^")+1).c_str()));
                cur->setX(true);
            }
            tree.insert(cur);
        }

        lis.pop();
    }
}

string removeSpaces(string s)
{
    int i = 0;

    while(true)
    {
        if(s.find(" ") == string::npos) //If there are no spaces...
        {
            break;
        }
        else
        {
            if(s[i] == ' ') //If the val is a space...
            {
                s.erase(i,1); //Erase the Space
                i=0;
            }
        }
        i++;
    }

    return s; //Returns the string with no spaces
}
