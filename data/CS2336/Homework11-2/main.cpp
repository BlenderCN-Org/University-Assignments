//Matthew McMillian P.1263 #15
#include <iostream>
#include <string>
#include <stack>
#include <queue>

using namespace std;

int main()
{

        cout << "Enter a line of text with fluxuating Upper/Lowercase characters" << endl;
        string s;
        getline(cin,s);

        for(int i = 0; i < s.length(); i++)
        {
            if(isalpha(s[i]) && s[i] <= 90)
            {
                s[i] = s[i] + 32; // To_Lower
            }
            else if(isalpha(s[i]) && s[i] <= 122)
            {
                s[i] = s[i] - 32; // To_Upper
            }
        }



       queue <char> q;
       stack <char> x;
       for(int i = 0; i < s.length(); i++)
       {
           q.push(s[i]);
           x.push(s[i]);
       }

       bool isPala = true;

       for(int i = 0; i < s.length(); i++)
       {
           if(q.front() != x.top())
           {
               isPala = false;
               break;
           }
           else
           {
                q.pop();
                x.pop();
           }
       }

       if(isPala == true)
       {
           cout << "Is A Pala:: " << endl;
       }
       else
       {
           cout << "Not A Pala:: " << endl;
       }





}
