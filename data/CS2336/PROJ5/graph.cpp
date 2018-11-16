#include "graph.h"
#include <stdlib.h>
#include <vector>
#include <iostream>
#include <sstream>
#include <stack>

void Graph::insert(std::string vertex)
{
    //Parses the input w/ strings
    std::string key = vertex.substr(0,vertex.find(" "));
    vertex = vertex.substr(vertex.find(" ")+1);

    //Creates a new node w/ the Key input
    Node* cur = new Node(key);

    //Creates a new linked list with the new node, puts it at the head
    LinkedList var(cur);

    //C keeps track of the spaces within the line
    int c = 1;
    std::string s = vertex;

    //Calculates the number of terms in the line
    while(true)
    {
        if(s.find(" ") == std::string::npos)
            break;
        else{
            c++;
            s=s.substr(s.find(" ")+1);
        }
    }

    //Breaks up the input, and inserts it into the linked lists
    for(int i = 0; i < c; i++)
    {
        std::string tmp;
        if(vertex.find(" ")!=std::string::npos) {
            tmp = vertex.substr(0,vertex.find(" "));
        }
        else
            tmp = vertex;

        var.insert(tmp);

        vertex = vertex.substr(vertex.find(" ")+1);

    }

    //Pushes the var onto the key vector
    Keys.push_back(var);
}

bool Graph::findKey(std::string key)
{
    //Loops through the Vector to find the Key, returns true if found, false if not found
    int s = Keys.size();
    for(int i = 0; i < s; i++)
    {
        if(Keys.at(i).getHead()->getVal() == atof(key.c_str()))
        {
            return true;
        }
    }

    return false;
}

int Graph::findKeyPos(std::string key)
{
    //Loops through the Vector to find the Key, returns "i" if found, returns -1 if not found
    int s = Keys.size();
    for(int i = 0; i < s; i++)
    {
        if(Keys.at(i).getHead()->getVal() == atof(key.c_str()))
        {
            return i;
        }
    }

    return -1;
}

void Graph::traversal(Node* cur)
{
    std::stack<Node*> s;
    s.push(cur);

    while(s.size()>0)
    {
        Node* v = s.top();
        s.pop();

        if(v->v==false)
        {
            std::stringstream ss;
            int pos;
            ss << v->getVal();
            v->v=true;
            if(findKey(ss.str()))
            {
               pos = findKeyPos(ss.str());
            }
            else
            {
                break;
            }

            Node* tmp = Keys.at(pos).getHead();
            tmp = tmp->getNext();

            while(tmp!=nullptr)
            {
                s.push(tmp);
                tmp = tmp->getNext();
            }
        }
    }
}


