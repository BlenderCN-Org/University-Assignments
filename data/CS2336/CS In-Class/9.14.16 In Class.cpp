#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>




using namespace std;

struct fPlayer
{
    string name;
    string pos;
    int tDowns;
    int catches;
    double pYards;
    double rYards;
    double ruYards;
};

void inputData(fPlayer* players, int length);
void outprintData(fPlayer* players, int length);
void updatePlayer(fPlayer* players,string n,string p, int t,int c,double pY,double r,double ru, int length);
void menu(fPlayer* players, int length);
void save(fPlayer* players,string filename,int length);
void addPlayer(fPlayer* players, int length);

int main()
{
    int length = 10;
    fPlayer* players = new fPlayer[10];

    inputData(players,length);

    delete [] players;
}

void menu(fPlayer* players, int length)
{
    string input = "";

    cout << "What would you like to do?" << "\n";
    cout << "Add a player? Type addplayer" << "\n";
    cout << "Update a Player? Type updateplayer <name> <pos> <touchdowns> <passingyds> <returnyds> <rushingyrds>" << "\n";
    cout << "See all players? Type outputplayer" << "\n";
    cout << "Save? Type save <filename>" << "\n";
    getline(cin,input);

    if(input.substr(0,input.find(" ")) == "addplayer")
    {
        addPlayer(players,length);
    }
    else if(input.substr(0,input.find(" ")) == "updateplayer")
    {
        string n;
        string p;
        int t;
        int c;
        double pY;
        double r;
        double ru;


        //updatePlayer()
    }
    else if(input.substr(0,input.find(" ")) == "outputplayer")
    {
        outprintData(players,length);
    }
    else if(input.substr(0,input.find(" ")) == "save")
    {
        //save(players,filename,length);
    }
    else
    {
        cout << "Entered Invalid Input, returning to menu" << "\n";
        cout << "---------------------------------------------------------------------------------------------------" << "\n";
        menu(players,length);
    }

}

void save(fPlayer* players,string filename,int length)
{
    ofstream myFile(filename);

    for(int i = 0; i < length; i++)
    {
        myFile << players[i].name << " " << players[i].pos << " " << players[i].tDowns << " " << players[i].catches << " " << players[i].pYards << " " << players[i].rYards << " " << players[i].ruYards << "\n";
    }

    cout << "---------------------------------------------------------------------------------------------------" << "\n";
    menu(players,length);
}

void inputData(fPlayer* players, int length)
{
    for(int i = 0; i < length; i++)
    {
        string n;
        string p;
        int t;
        int c;
        double pY;
        double r;
        double ru;

        cout << "Input the following information followed by spaces:: " << "\n";
        cout << "Name, Position, Touchdowns, Catches, Passing Yards, Received Yards, Rushing Yards" << "\n";
        cin >> n,p,t,c,pY,r,ru;

        players[i].name = n;
        players[i].pos = p;
        players[i].tDowns = t;
        players[i].catches = c;
        players[i].pYards = pY;
        players[i].rYards = r;
        players[i].ruYards = ru;
    }

    cout << "---------------------------------------------------------------------------------------------------" << "\n";
    menu(players,length);
}

void addPlayer(fPlayer* players, int length)
{
    string n;
    string p;
    int t;
    int c;
    double pY;
    double r;
    double ru;

    fPlayer* playas = new fPlayer[length+1];

    cout << "Input the following information followed by spaces:: " << "\n";
    cout << "Name, Position, Touchdowns, Catches, Passing Yards, Received Yards, Rushing Yards" << "\n";

    for(int i = 0; i < length; i++)
    {
        playas[i].name = players[i].name;
        playas[i].pos = players[i].pos;
        playas[i].tDowns = players[i].tDowns;
        playas[i].catches = players[i].catches;
        playas[i].pYards = players[i].pYards;
        playas[i].rYards = players[i].rYards;
        playas[i].ruYards = players[i].ruYards;
    }

    players[length+1].name = n;
    players[length+1].pos = p;
    players[length+1].tDowns = t;
    players[length+1].catches = c;
    players[length+1].pYards = pY;
    players[length+1].rYards = r;
    players[length+1].ruYards = ru;

    length++;

    cout << "---------------------------------------------------------------------------------------------------" << "\n";
    menu(playas,length);
}

void outprintData(fPlayer* players, int length)
{
    for(int i = 0; i < length; i++)
    {
        cout << players[i].name << " ";
        cout << players[i].pos << " ";
        cout << players[i].tDowns << " ";
        cout << players[i].catches << " ";
        cout << players[i].pYards << " ";
        cout << players[i].rYards << " ";
        cout << players[i].ruYards << " " << "\n";

        cout << "---------------------------------------------------------------------------------------------------" << "\n";
        menu(players,length);
    }
}

void updatePlayer(fPlayer* players,string n,string p, int t,int c,double pY,double r,double ru, int length)
{
    int posit;

    for(int i = 0; i < length; i++)
    {
        if(players[i].name == n)
        {
            posit = i;
        }
    }

    players[posit].pos = p;
    players[posit].tDowns = t;
    players[posit].catches = c;
    players[posit].pYards = pY;
    players[posit].rYards = r;
    players[posit].ruYards = ru;

    cout << "---------------------------------------------------------------------------------------------------" << "\n";
    menu(players,length);
}
