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




 cout << "What is the name of your file?" << "\n";
 string fileName;
 cin >> fileName;

 ifstream fileReader(fileName);
 string line;

 string StudentID;
 string sAnswers;

 getline(fileReader,line);

 //cout << line << "\n";

 int lngth = line.length();

 string* correctAnswers = new string[lngth];
 string* studentAnswers = new string[lngth];

 for(int i = 0; i < lngth; i++)
 {
    correctAnswers[i] = line.substr(0,1);
    line = line.substr(1);
 }



 while(getline(fileReader,line))
 {
    string StudentGrade = "";
    sAnswers = "";
    float StudentPercentage = 0.0;
    int StudentScore = 0;
    StudentID = line.substr(0,line.find(" "));
    //cout << StudentID;
    line = line.substr(line.find(" ")+1);
    //cout << line;

    for(int i = 0; i < lngth; i++)
    {
        studentAnswers[i] = line.substr(0,1);
        line = line.substr(1);
        sAnswers = sAnswers + studentAnswers[i];
        //cout << studentAnswers[i];
    }

    //cout << sAnswers;

    for(int p = 0; p < lngth; p++)
    {
        if(correctAnswers[p] == studentAnswers[p])
        {
            StudentScore += 2;
        }
        else if(studentAnswers[p] == " ")
        {
            StudentScore += 0;
        }
        else if(correctAnswers[p] != studentAnswers[p])
        {
            StudentScore -= 1;
        }
    }

    //cout << StudentScore;

    StudentPercentage = ((float)StudentScore/40)*100;

    //cout << StudentPercentage;

    if(StudentPercentage > 90.0)
    {
        StudentGrade = "A";
    }
    else if(StudentPercentage > 80.0)
    {
        StudentGrade = "B";
    }
    else if(StudentPercentage > 70.0)
    {
        StudentGrade = "C";
    }
    else if(StudentPercentage > 60.0)
    {
        StudentGrade = "D";
    }
    else
    {
        StudentGrade = "F";
    }

    cout << StudentID << " " << sAnswers << " " << StudentScore << "/40 " << StudentGrade << "\n";

 }



}




