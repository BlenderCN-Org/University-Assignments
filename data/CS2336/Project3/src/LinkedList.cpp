#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>




using namespace std;

#include "LinkedList.h"

LinkedList::LinkedList() {
    head = nullptr;
    tail = nullptr;
}

DoubleLinkNode* LinkedList::getHead() const{ //Returns the Head Node
    return head;
}
DoubleLinkNode* LinkedList::getTail() const{ //Return the Tail Node
    return tail;
}

void LinkedList::setHead(DoubleLinkNode *x) { //Sets the Head Node
    head = x;
}

void LinkedList::setTail(DoubleLinkNode *x){ //Sets the Tail Node
    tail = x;
}

void LinkedList::destr() { //Destroys the List

}

ostream& operator<<(ostream& o,const LinkedList& tmp) { //Outprints the List

    DoubleLinkNode* ptr = tmp.getHead(); // <- Defined Incorrectly
    while(ptr != nullptr)
    {
        cout << "Name:: " << ptr->getName() << " | Area:: " << ptr->getArea() << endl;
        ptr = ptr->getNext();
    }

    return o;
}

//Sorts the Linked List
void LinkedList::sortList(string s){ //Sorts the List via String Input

    isSort = false;
    //cout << "<< called" << endl;

    if(s.find("area") != string::npos) // Sort by Area, [Min to Max]
    {
        // Swaps Nodes
        DoubleLinkNode *ptr;
        ptr = getHead();

        while(isSort == false)
        {
            while (ptr->getNext())
            {
                isSort = true;

                if(ptr->getArea() > ptr->getNext()->getArea())
                {
                    swapNodes(ptr, ptr->getNext());
                    isSort= false;
                    ptr = getHead();
                }
                else
                {
                    ptr = ptr->getNext();
                }
                //cout << "after sort--- \n" << *this;
            }
        }
    }
    else if(s.find("name") != string::npos) // Sort by Name, [Min to Max]
    {

        // Swaps Nodes
        DoubleLinkNode *ptr;
        ptr = getHead();
        string z;
        string y;
        //cout << "Sorting Name" << endl;

        while(isSort == false)
        {
            while (ptr->getNext())
            {
                isSort = true;

                if(ptr->getName().compare(ptr->getNext()->getName()) > 0)
                {
                    swapNodes(ptr, ptr->getNext());
                    isSort= false;
                    ptr = getHead();
                }
                else
                {
                    ptr = ptr->getNext();
                }

            }
        }
    }

}

bool LinkedList::find_name_in_list(string n) // Returns true if there is a matching name inside the list
{
    DoubleLinkNode* ptr;
    ptr = getHead();

    bool isHere = false;

    while(ptr != nullptr)
    {
        if(ptr->getName() == n)
        {
            cout << "Found:: " << n << endl;
            cout << "Currently at:: " << ptr->getName() << endl;
            cout << endl;
            isHere = true;
            break;
        }
        else
        {
            cout << "Searching for:: " << n << endl;
            cout << "Currently at:: " << ptr->getName() << endl;
            cout << endl;
            ptr = ptr->getNext();
        }
    }

    return isHere;
}
bool LinkedList::find_area_in_list(float a) // Returns true if there is a matching area inside the list
{
    DoubleLinkNode* ptr;
    ptr = getHead();

    bool isHere = false;

    while(ptr->getNext() != nullptr)
    {
        if(ptr->getArea() == a)
        {
            isHere = true;
            break;
        }
        else
        {
            ptr = ptr->getNext();
        }
    }

    return isHere;
}
void LinkedList::swapNodes(DoubleLinkNode* ptr,DoubleLinkNode* ptr2) //Swaps the Nodes in the list
{
    if(ptr->getNext() != ptr2) //Nodes are NOT next to EachOther
    {
        DoubleLinkNode* tmp = new DoubleLinkNode();
        tmp->setPrev(ptr->getPrev());
        tmp->setNext(ptr->getNext());

        ptr->setPrev(ptr2->getPrev());
        ptr->setNext(ptr2->getNext());

        ptr2->setPrev(tmp->getPrev());
        ptr2->setNext(tmp->getNext());

        if(ptr->getNext() != nullptr){
            ptr->getNext()->setPrev(ptr);
        }
        if(ptr->getPrev() != nullptr){
            ptr->getPrev()->setNext(ptr);
        }
        if(ptr2->getNext() != nullptr){
            ptr2->getNext()->setPrev(ptr2);
        }
        if(ptr2->getPrev() != nullptr){
            ptr2->getPrev()->setNext(ptr2);
        }

        if(ptr == getHead())
        {
            setHead(ptr2);
        }
        if(ptr == getTail())
        {
            setTail(ptr2);
        }
        if(ptr2 == getHead())
        {
            setHead(ptr);
        }
        if(ptr2 == getTail())
        {
            setTail(ptr);
        }
        delete tmp;
    }
    else
    {
        if(ptr->getPrev() != nullptr)
        {
            ptr->getPrev()->setNext(ptr2);
        }
        if(ptr2->getNext() != nullptr)
        {
            ptr2->getNext()->setPrev(ptr);
        }

        //ptr->setPrev(tmp2->getPrev());
        ptr->setNext(ptr2->getNext());
        ptr2->setPrev(ptr->getPrev());

        ptr2->setNext(ptr);
        ptr->setPrev(ptr2);

        if(ptr == getHead())
        {
            setHead(ptr2);
        }
        else if(ptr2 == getHead())
        {
            setHead(ptr);
        }
        if(ptr == getTail())
        {
            setTail(ptr2);
        }
        else if(ptr2 == getTail())
        {
            setTail(ptr);
        }
    }
}

void LinkedList::write(string fName)
{
    ofstream area(fName);
    DoubleLinkNode* ptr = getHead(); // <- Defined Incorrectly
    while(ptr != nullptr)    {
        area << setprecision(2) << fixed << "Name:: " << ptr->getName() << " | Area:: " << ptr->getArea() << endl;
        ptr = ptr->getNext();
    }
}
