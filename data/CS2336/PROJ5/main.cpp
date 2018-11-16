#include <iostream>
#include <fstream>
#include <string>

#include <graph.h>
#include <linkedlist.h>
#include <node.h>
#include <Pilot.h>

using namespace std;
bool validateCoor(string coor,Graph g);
double calculatePath(string coor,Graph g);
int countTerms(string l);
vector<Pilot>  sort(vector<Pilot> o);

int main()
{
    //Pre-Requisites, fstreams and ofstreams, opening the files
    fstream file_coor;
    fstream file_pilots;
    ofstream out;
    out.open("patrols.txt");
    file_coor.open("galaxy.txt");

    //Vector of Pilots for Output
    vector<Pilot> output;

    //The Graph itself
    Graph g;

    //Line variable to manipulate file  I/O
    string line;

    //Creates the graph from file1
    while(!file_coor.eof())
    {
        getline(file_coor,line);
        g.insert(line);
    }
    file_coor.close();

    //Opens the routes file
    file_pilots.open("pilots_routes.txt");

    //While the file isn't done
    while(!file_pilots.eof())
    {
        string pilot_name;
        string pilot_coor;
        getline(file_pilots,line);

        //Splits the Pilot's name from their coordinates
        int s = line.length();
        for(int i = 0; i < s; i++)
        {
            if(isdigit(line[i]))
            {
                pilot_name = line.substr(0,i-1);
                pilot_coor = line.substr(i);
                break;
            }
        }

        //Checks to make sure the path is valid
        if(validateCoor(pilot_coor,g))
        {
            Pilot tmp;
            tmp.name = pilot_name;
            tmp.area = calculatePath(pilot_coor,g);
            tmp.valid = true;
            output.push_back(tmp);
        }
        else
        {
            Pilot tmp;
            tmp.name = pilot_name;
            tmp.area = -1;
            tmp.valid = false;
            output.push_back(tmp);
        }
    }

    file_pilots.close();

    //Sorts the pilots from least to greatest
    output = sort(output);

    //Loops through the predefined vector and outprints the data to the file specified
    int s = output.size();
    for(int i = 0; i < s; i++)
    {
        out << output.at(i).toString();
    }

    //Closes the Files
    out.close();
    file_pilots.close();
    file_coor.close();

    //Traverses the Graph
    g.traversal(g.Keys.at(0).getHead());
}

vector<Pilot> sort(vector<Pilot> o)
{
    //Vector 'n' is for pilots
    vector<Pilot> n;

    //Vector 'nan' is for incorrect path pilots
    vector<Pilot> nan;

    //Boolean 't' keeps track of the array, making sure its sorted
    bool t;

    //Puts all the values that have incorrect paths into 'nan'
    while(t == true)
    {
        t = true;
        int s = o.size();
        for(int i = 0; i < s; i++)
        {
            if(o.at(i).area == -1)
            {
                nan.push_back(o.at(i));
                o.erase(o.begin()+i);
            }
        }
        for(int i = 0; i < s; i++)
        {
          if(o.at(i).area==-1)
                t = false;
        }
    }

    //Via BubbleSort, Sort the Vector
    while(o.size() != 0)
    {
        int min = INT_MAX;
        int minpos;
        int s = o.size();
        for(int i = 0; i < s;i++)
        {
            if(o.at(i).area < min)
            {
                min = o.at(i).area;
                minpos = i;
            }
        }
        n.push_back(o.at(minpos));
        o.erase(o.begin()+minpos);
    }

    //Adds on the values of 'nan' onto 'n'
    int s = nan.size();
    for(int i = 0; i < s; i++)
    {
        n.push_back(nan.at(i));
    }

    return n;
}

bool validateCoor(string coor,Graph g)
{

    string* arr = new string[countTerms(coor)];

    //Number of terms in the coordinate
    int terms = countTerms(coor);

    //If there is no such key with the first key, it returns false as the coordinate isnt valid
    if(!g.findKey(coor.substr(0,coor.find(" "))))
    {
        return false;
    }
    //If not...
    else
    {
        for(int i = 0; i < terms; i++)
        {
            string t="";

            //If there is no space, then t is equal to coor
            if(coor.find(" ")==string::npos){
                t = coor;
            }
            //If there is a space, t substrings coor down to get each value
            else{
                t = coor.substr(0,coor.find(" "));
                coor = coor.substr(coor.find(" ")+1);
            }
            arr[i] = t;
        }

        //Checks each key row to see if it contains the next term in the path
        for(int i = 0; i < terms-1; i++)
        {
            if(g.findKey(arr[i])){

            }
            else{
                return false;
            }
            if(!g.Keys.at(g.findKeyPos(arr[i])).find(arr[i+1])){
                return false;
            }

        }
    }
    return true;
}

double calculatePath(string coor,Graph g)
{
    double sum = 0;
    string* arr = new string[countTerms(coor)];

    int terms = countTerms(coor);

        //Substring T down and inputs the values into arr
        for(int i = 0; i < terms; i++)
        {
            string t="";

            if(coor.find(" ")==string::npos){
                t = coor;
            }
            else{
                t = coor.substr(0,coor.find(" "));
                coor = coor.substr(coor.find(" ")+1);
            }
            arr[i] = t;
        }

        //Goes through the array and sums up the node weights
        for(int i = 0; i < terms-1; i++)
        {
            sum = sum + g.Keys.at(g.findKeyPos(arr[i])).findNode(arr[i+1])->getWeight();
        }

        return sum;
}

int countTerms(string l)
{
    //Counts the number of white spaces in a string + 1 which equals the number of terms
    int num=0;
    int s = l.length();
    for(int i = 0; i < s;i++)
    {
        if(l[i]==' ')
            num++;
    }
    num++;

    return num;
}


