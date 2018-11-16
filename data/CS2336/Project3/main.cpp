//Matthew McMillian
#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>

#include <BaseNode.h>
#include <DoubleLinkNode.h>
#include <LinkedList.h>

//Notes::   Not Adding Correctly to Linked List
//          Linked List Class Does Not Sort Yet
//          Input Validation!!!
//          Check for Multiple Periods and Commas

using namespace std;

//Pre-Defining Functions
float getArea(string);
void sortList(string);
bool inputValidationFile1(string);
bool inputValidationFile2(string);

//Main Method
int main()
{
    fstream file("pilot_routes2.txt",ios::binary);
    ofstream output("results.txt");


    string line;
    int pilots = 0;

    LinkedList list_pilots;

    while(getline(file,line)) // Gets Number of Pilots
    {
        pilots++;
    }

    //cout << "PILOTS:: ";

    file.close();

    file.open("pilot_routes2.txt");
    //cout << "FAILED TEST CASES::" << endl;

    while(getline(file,line)) // Starts the Process of creating a linked list, gets Area
    {
        if(inputValidationFile1(line) == false)
        {
            //cout << "FAILED:: " << line << endl;
        }

        if(inputValidationFile1(line) == true)
        {
            //cout << "CORRECT:: " << line << endl;
            int n;
            string coor;
            DoubleLinkNode *p = new DoubleLinkNode();

            for(int i = 0; i < line.length()-1; i++) // For the size of the line...
            {
                if(isdigit(line[i])) // ... it finds the first digit
                {
                    n = i; // ... sets n to the position of the first var
                    break;
                }
            }

            p->setName(line.substr(0,n-1)); // The name is of pilot is assigned to the node
            //cout << "NAME:: " << line.substr(0,n-1) << "    ";
            coor = line.substr(n); // The Coordinates of the Line
            //cout << coor << endl;
            int area = getArea(coor); //Must be this because memory will set precision every time it runs, and it will literally break the program.
            p->setArea(area); // Gets the area
            //cout << "AREA:: " << area << endl;


            //Adds to LinkedList
            if(!(list_pilots.getHead() && list_pilots.getTail())) // List is Empty
            {
                list_pilots.setHead(p);
                list_pilots.setTail(p);
            }
            else if(list_pilots.getHead() == list_pilots.getTail()) // Only 1 element in List
            {
                list_pilots.setTail(p);
                list_pilots.getHead()->setNext(list_pilots.getTail());
                list_pilots.getTail()->setPrev(list_pilots.getHead());
            }
            else // Head & Tail, for every other element
            {
                p->setPrev(list_pilots.getTail());
                list_pilots.getTail()->setNext(p);
                list_pilots.setTail(p);
            }
        }
    }

    //cout << "Finished First TXT" << endl;

    //cout << list_pilots << endl;

    /*
    if(list_pilots.getHead() != nullptr){
        cout << "head node exist :D" << endl;
    }
    */


    file.close();

    file.open("commands2.txt");

    //cout << endl;
    //cout << "FAILED COMMAND CASES::" << endl;
    while(getline(file,line)) //  File 2, Commands
    {
        if(inputValidationFile2(line) == false)
        {
            //cout << "FAILED:: " << line << endl;
        }
        if(inputValidationFile2(line) == true)
        {
            //cout << "CORRECT:: " << line << endl;

            if(line.find("sort") != string::npos) // Find the sort
            {
                //cout << "Called Sort " << line.substr(line.find(" ")+1) << endl;
                list_pilots.sortList(line);
            }
            else if(atof(line.c_str())) //Find an Area
            {
                if(list_pilots.find_area_in_list(atof(line.c_str())) == true)
                {
                    output << atof(line.c_str()) << "   " << "Found" << endl;
                }
                else
                {
                    output << atof(line.c_str()) << "   " << "Not Found" << endl;
                }
                //***Write to file results::
            }
            else // Find a name
            {
                if(list_pilots.find_name_in_list(line) == true)
                {
                    output << line << "    " << "Found" << endl;
                }
                else
                {
                    output << line << "    " << "Not Found" << endl;
                }
                //***Write to file results::
            }
        }

        //cout << list_pilots;
    }

    //cout << list_pilots;

    output.close();
    list_pilots.write("pilot_areas.txt");
    //cout << "Finished 2nd TXT" << endl;




}

//Function that gets the coordinates then gets the area
float getArea(string coor)
{
    string tmp = coor; //Temporary Coor Var
    int sp = 0; // Spaces Variable

    for(int i = 0; i < coor.length()-1; i++) // Counts the number of spaces after the first coordinate
    {
        //cout << coor.substr(i,i+1) << endl;

        if(tmp.substr(0,1) == " ") // If the char @ i is a space
        {
            sp = sp+1; // Spaces is incremented
        }

        tmp = tmp.substr(1);
    }

    float* xCoor = new float[sp+1]; // X Coordinates
    float* yCoor = new float[sp+1]; // Y Coordinates

    for(int i = 0; i < sp+1; i++) // Assigns coordinates to arrays based on space parameter
    {
        string c = coor.substr(0,coor.find(" ")); // C is a temporary string used to get a single coordinate pair
        xCoor[i] = atof(c.substr(0,c.find(",")).c_str()); // Assigns xCoor
        yCoor[i] = atof(c.substr(c.find(",")+1).c_str()); // Assigns yCoor
        //cout << c.substr(0,c.find(",")) << "," << c.substr(c.find(",")+1) << "\n";

        if(coor.find(" ") != string::npos) // If there isnt a space
            coor = coor.substr(coor.find(" ")+1); // Coor is reassigned
    }

    float sum = 0.0; // Area

    for(int i = 0; i < sp ; i++) // For Each Coordinate...
    {
        sum = sum + ((xCoor[i+1] + xCoor[i])*(yCoor[i+1] - yCoor[i])); //... Add the value from the function into the sum;
        //cout << sum << endl;
    }

    delete [] xCoor;
    delete [] yCoor;
    return (sum/2);


}


bool inputValidationFile1(string s) // Input Validation File 1
{
    //cout << "LINE:: " << s << endl;
    int n;
    string shtName;
    string coorName;
    for(int i = 0; i < s.length()-1; i++) // For the size of the line...
    {
        if(isdigit(s[i])) // ... it finds the first digit
        {
            n = i; // ... sets n to the position of the first var
            break;
        }
    }

    bool isNum = false; // For the Following Loop to Determine if there are any digits

    for(int i = 0; i < s.length(); i++) // CHECKS IF THERE ARE ANY DIGITS IN THE LINE ENTIRELY
    {
        if(isdigit(s[i]))
        {
            isNum = true;
        }
    }

    //cout << "isNUM:: " << isNum << endl;

    if(isNum == false)
    {
        return false;
    }

    if(s[n-1] == 45) // Checks to make sure space is between name and coors
    {
        if(s[n-2] != 32)
        {
            //cout << "False VIA:: No space between coor and name" << endl;
             return false;
        }
    }
    else if(s[n-1] != 32)
    {
        //cout << "False VIA:: No space between coor and name" << endl;
        return false;
    }

    try
    {
        if(s[n-1] == 45)
        {
            shtName = s.substr(0,n-1);
            //cout << "CHECKED NAME:: " << shtName << endl;
            coorName = s.substr(n-1);
            //cout << "CHECKED COOR:: " << coorName << endl;
        }
        else
        {
             shtName = s.substr(0,n-1);
            //cout << "CHECKED NAME:: " << shtName << endl;
            coorName = s.substr(n);
            //cout << "CHECKED COOR:: " << coorName << endl;
        }
    }
    catch(...)
    {
        //cout << "False VIA:: SUBSTR ERROR" << endl;
        return false;
    }

    for(int i = 0; i < shtName.length(); i++) // Checks to see if the name has any digits, and if the correct characters are in the name
    {
        char c = shtName[i];
        //cout << "READ:: " << c << endl;
        //cout << "CHAR READ:: " << (int)c << endl;
        if((isalpha(c) == false)) // IF a digit or a Char
        {
            if((int)c == 32)
            {

            }
            else
            {
                //cout << "False VIA:: Not a Valid Character in Name @ " << s[i] << "<" << endl;
                return false;
                break;
            }
        }
    }

    while(coorName != "") // Finds errors in  coor pair
    {
        string c = coorName.substr(0,coorName.find(" ")); // C is a temporary string used to get a single coordinate pair

        if(coorName.find(",") == string::npos)
        {
            //cout << "False VIA:: no ,'s in CoorName" << endl;
            return false;
            break;
        }

        if(c.find(",") == string::npos)
        {
            //cout << "False VIA:: no , in single coor pair" << endl;
            return false;
            break;
        }

        string xCoor = c.substr(0,c.find(",")); // Assigns xCoor
        string yCoor = c.substr(c.find(",")+1); // Assigns yCoor
        //cout << "xCoor,yCoor:: " << xCoor << "," << yCoor << endl;

        if(xCoor.find_last_of(".") != xCoor.find_first_of("."))
        {
            return false;
        }
        if(yCoor.find_last_of(".") != yCoor.find_first_of("."))
        {
            return false;
        }
        if(xCoor.find_last_of("-") != xCoor.find_first_of("-"))
        {
            return false;
        }
        if(yCoor.find_last_of("-") != yCoor.find_first_of("-"))
        {
            return false;
        }

        for(int i = 0; i < xCoor.length(); i++)
        {
            if(!(isdigit(xCoor[i]))) // Checks X Coordinate for invalid input
            {
                if((int)xCoor[i] != 46)
                {
                    if((int)xCoor[i] != 45)
                    {
                        //cout << "ERROR:: X" << xCoor[i] << endl;
                        //cout << "False VIA:: In Coor Pair, invalid Input" << endl;
                        return false;
                        break;
                    }
                }

            }
        }

        for(int i = 0; i < yCoor.length(); i++) // Checks Y Coordinate for invalid Input
        {
            if(!(isdigit(yCoor[i])))// if not a digit a '.'
            {
                if((int)yCoor[i] != 46)
                {
                    if((int)yCoor[i] != 45)
                    {
                        //cout << "ERROR:: Y " << yCoor[i] << endl;
                        //cout << "False VIA:: In Coor Pair, invalid Input" << endl;
                        return false;
                        break;
                    }
                }
            }
        }
        //cout << c.substr(0,c.find(",")) << "," << c.substr(c.find(",")+1) << "\n";



        if(coorName.find(" ") != string::npos) // If there isnt a space
            coorName = coorName.substr(coorName.find(" ")+1); // Coor is reassigned
        else
            coorName = "";

        //cout << "COORNAME AFTER RUN:: " << coorName << endl;
    }

    return true;

}

bool inputValidationFile2(string s) // Input Validation File 2
{
    bool isCheck = false;

    if(s.find_last_of(".") != s.find_first_of("."))
    {
        return false;
    }

    for(int i = 0; i < s.length(); i++) //Checks for any Invalid characters in the entire line
    {
        if(!(isalnum(s[i]))) //Checks for a num or char
        {
            if(s[i] != 32)
            {
                 if(s[i] != '.')
                 {
                     if(s[i] != '-')
                     {
                         //cout << "Failed From Initial Check" << endl;
                        return false;
                     }
                 }
            }
        }
    }

    if(s.find("sort") != string::npos) //If it is a sort call
    {
        if(s.find(" ") != string::npos) //If there isnt a space
        {
            string criteria = s.substr(s.find(" ")+1); //The criteria
            if(criteria == "area" || criteria == "name"){ //Checks for area or name
                return true;
            }
            else
            {
                //cout << "Failed from wrong sort crit" << endl;
                return false;
            }
        }
        else
        {
            //cout << "failed from no space in sort" << endl;
            return false;
        }

    }

    bool isNum = true;
    bool isName = true;

    for(int i = 0; i < s.length(); i++) // Find if its all nums or all alpha
    {
        if(isdigit(s[i]))
        {
            if(s[i] != '.')
                isName = false;
        }
        else if (isalpha(s[i]))
        {
            if(s[i] != 32)
                isNum = false;
        }
    }

    if(isName==false && isNum == false)
    {
        //cout << "Failed From Area/Name Test" << endl;
        return false;
    }

    return true;
}


