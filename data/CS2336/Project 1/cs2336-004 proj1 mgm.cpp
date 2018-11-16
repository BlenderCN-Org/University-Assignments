#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>

//Things Changed::
//Line 84: if(line.find(" ") != string::npos)
//
// toString: myfile << setprecision(2) << fixed << name << "   " << total << "\n"; //Outprints the name follow by the total area.
//
//myfile.open("pilot_areas.txt");
//
//    for(int i = 0; i < lines; i++) // I is an arbitrary value used only to loop through the number of lines
//    {
//        getline(fileReader,line); //Gets the current line
//        getCoor(line,name);
//    }
//    myfile.close();

using namespace std;

void getCoor(string line,string name);
float getArea(float xCoor[], float yCoor[], string name, int counter);
void toString(string name, float total);

ofstream myfile;


int main()
{
    string line; //This will grab the current line of data that the file has, allowing me to chop it up into pieces
    int lines = 0; //Will represent the number of lines in the file

    string name; // The Pilots Name, used to easily regurgitate it later

    string tmp;
    //cout << "Please Enter The File Name You Wish To Read:: " << "\n";
    //getline(cin,tmp);
    //cout << "\n";

    ifstream fileReader("pilot_routes3.txt"); // Allows me to access the file. fileReader is my name that allows me to access the file

    //This next bit of code allows me to check the number of lines in the file. Now I can create a for loop to easily cycle through the data
    if(fileReader.is_open()) // Checks to see if the document is open
    {
        while(getline(fileReader,line)) //Checks for a line, accepts the fileReader and a line, which in this case is a string var "Line"
        {
            lines++; // Increases per line
        }
        fileReader.close(); //Closes the file so I can reset my positioning
    }

    fileReader.open("pilot_routes3.txt"); // Reopening the file so I can start back at the top

    myfile.open("pilot_areas.txt");
    for(int i = 0; i < lines; i++) // I is an arbitrary value used only to loop through the number of lines
    {
        getline(fileReader,line); //Gets the current line
        getCoor(line,name);
    }
    myfile.close();

}

void getCoor(string line,string name) //Grabs the coordinates read from the file
{
        string temp; // Temporary string that allows me to cycle through the line
        int counter = 0; // Allows us to cycle through array positions easily

        name = line.substr(0,line.find(" ")); //Takes the characters from 0, to wherever the next space is and adds it as the "name"
        line = line.substr(line.find(" ")+1); //Takes out the name from the entire string, allows for easier substrings

        //cout << "Line::" << line;

        float* xCoor = new float[15]; //Array full of X Coordinates that we can pull for later use
        float* yCoor = new float[15]; //Array full of Y Coordinates that we can pull for later use

        while(line != "") // While the line is not empty
        {
            //**************PROBLEM HERE**************************

            temp = line.substr(0,line.find(" ")); // Grabs the first coordinate pair
            //cout << "TEMP:: " << temp << "\n";
            //cout << "TEMP:: " << temp << "\n";
            //cout << "X-COOR:: " << temp.substr(0,temp.find(","));
            xCoor[counter] = atof((temp.substr(0,temp.find(","))).c_str()); // Separates the string at the comma, grabbing the X-number, and converting it to a float
            //cout << "ARRAY xCoor:: " << xCoor[0] << "\n";
            yCoor[counter] = atof((temp.substr(temp.find(",")+1,temp.find(" ")-1)).c_str()); //Separates the string at the comma, grabbing the Y-number, and converting it to a float

            if(line.find(" ") != string::npos)
            {
                line = line.substr(line.find(" ")+1); //Removes the coordinate pair from the line entirely.
            }
            else
            {
                line = "";
            }
            counter++; // Increase the counter var so that we can access different positions in the array
        }

        getArea(xCoor,yCoor,name,counter); //Solves for the Area
}

float getArea(float xCoor[], float yCoor[], string name, int counter) //Uses Coordinates from getCoor to calculate the area using the given formula
{
    float total = 0; //Total is the end result of the function

    for(int i = 0; i < counter-1; i++)
    {
        total += (xCoor[i+1] + xCoor[i])*(yCoor[i+1] - yCoor[i]); //This Cycles through the series
    }

    if(total < 0) // If the total is negative, you cannot have a negative area
    {
        total = total * -1; //Absolute Value
    }

    total = total/2; //To complete the formula, we device the total by 2

    toString(name,total); //Calls the function to outprint the results
}

void toString(string name, float total) //toString is a custom method that outprints the results in fashion to the rubric
{
    //cout << setprecision(2) << fixed << name << "   " << total << "\n"; //Outprints the name follow by the total area.
    myfile << setprecision(2) << fixed << name << "   " << total << "\n"; //Outprints the name follow by the total area.

}



