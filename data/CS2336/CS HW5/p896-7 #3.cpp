#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>




using namespace std;

int main()
{

    int numCan = 0;

    cout << "How many candidates are running in the election?" << "\n";
    cin >> numCan;

    cout << "Please enter the candidates last names and votes received in this fashion" << "\n";\
    cout << "EX:: 'LastName'-'Votes Received' " << "\n";

    string* names = new string[numCan];
    int* votes = new int[numCan];

    int totVotes = 0;
    double per = 0.0;

    for(int i = 0; i < numCan; i++)
    {
        string line;

        cout << "Please enter candidate #" << i+1 << "'s information:: " << "\n";
        cin >> line;

        names[i] = line.substr(0,line.find("-"));
        votes[i] = atoi(line.substr(line.find("-")+1).c_str());
    }

    int maxi = 0;

    for(int i = 0; i < numCan; i++)
    {
        totVotes = totVotes + votes[i];

        if(votes[i] > votes[maxi])
        {
            maxi = i;
        }
    }

    cout << "Candidate, Votes Received, % of Total Votes" << "\n";

    for(int i = 0; i < numCan; i++)
    {
        per = ((double)votes[i]/(double)totVotes)*100;
        cout << setprecision(2) << fixed << names[i] << " " << votes[i] << " " << per << "\n";
    }

        cout << "Total " << totVotes << "\n";
        cout << "The Winner is " << names[maxi] << "\n";
    delete[] names;
    delete[] votes;
}




