#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>

//Things Changed::
//


using namespace std;

void getCoor(string line,string name);

int main()
{
 string line; //This will grab the current line of data that the file has, allowing me to chop it up into pieces
    int lines = 0; //Will represent the number of lines in the file

    string name; // The Pilots Name, used to easily regurgitate it later

    ifstream fileReader ("pilot_routes.txt"); // Allows me to access the file. fileReader is my name that allows me to access the file

    //This next bit of code allows me to check the number of lines in the file. Now I can create a for loop to easily cycle through the data
    if(fileReader.is_open()) // Checks to see if the document is open
    {
        while(getline(fileReader,line)) //Checks for a line, accepts the fileReader and a line, which in this case is a string var "Line"
        {
            lines++; // Increases per line
        }
        fileReader.close(); //Closes the file so I can reset my positioning
    }

    //cout << "Lines:: " << lines;

    fileReader.open("pilot_routes.txt"); // Reopening the file so I can start back at the top


    for(int i = 0; i < lines; i++) // I is an arbitrary value used only to loop through the number of lines
    {
        getline(fileReader,line); //Gets the current line
        getCoor(line,name);
    }
}

void getCoor(string line,string name) //Grabs the coordinates read from the file
{
    string temp; // Temporary string that allows me to cycle through the line
        int counter = 0; // Allows us to cycle through array positions easily

        name = line.substr(0,line.find(" ")); //Takes the characters from 0, to wherever the next space is and adds it as the "name"
        line = line.substr(line.find(" ")+1); //Takes out the name from the entire string, allows for easier substrings

        float* xCoor = new float[15]; //Array full of X Coordinates that we can pull for later use
        float* yCoor = new float[15]; //Array full of Y Coordinates that we can pull for later use

        while(line != "") // While the line is not empty
        {
            temp = line.substr(0,line.find(" ")); // Grabs the first coordinate pair
            xCoor[counter] = atof((temp.substr(0,temp.find(","))).c_str()); // Separates the string at the comma, grabbing the X-number, and converting it to a float
            cout << xCoor[counter] << "\n";
            yCoor[counter] = atof((temp.substr(temp.find(",")+1,temp.find(" "))).c_str()); //Separates the string at the comma, grabbing the Y-number, and converting it to a float
            cout << yCoor[counter] << "\n";
            line = line.substr(line.find(" ")+1); //Removes the coordinate pair from the line entirely.
            counter++; // Increase the counter var so that we can access different positions in the array
        }



        //getArea(xCoor,yCoor,name,counter);
}




