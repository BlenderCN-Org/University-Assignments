#ifndef LINKEDLIST_H
#define LINKEDLIST_H

#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>

#include "BaseNode.h"
#include "DoubleLinkNode.h"

class LinkedList
{
    public:
        LinkedList();
        LinkedList(DoubleLinkNode*);
        DoubleLinkNode *head;
        DoubleLinkNode *tail;

        DoubleLinkNode* getHead() const;
        DoubleLinkNode* getTail() const;

        void setHead(DoubleLinkNode *);
        void setTail(DoubleLinkNode *);

        void destr();

        friend ostream& operator<<(ostream&,const LinkedList&);

        void sortList(string); // Name or Alphabetically
        bool find_name_in_list(string n);
        bool find_area_in_list(float a);
        void swapNodes(DoubleLinkNode*,DoubleLinkNode*);

        void write(string);

    protected:

    private:
        bool isSort = false;
};

#endif // LINKEDLIST_H
