#include "circularLinkedList.h"
#include <string>

#include <BaseNode.h>

using namespace std;

circularLinkedList::circularLinkedList()
{
    Start = nullptr;
}
bool circularLinkedList::isEmpty()
{
    if(start == nullptr)
    {
        return true;
    }
    else
    {
        return false;
    }
}
int circularLinkedList::listLength()
{
    int c;
    while(ptr != getStart())
    {
        c++;
        ptr = ptr->getNext();
    }

    return c;
}
void circularLinkedList::printList()
{
    Node* ptr = getStart()->getNext();
    cout << getStart << endl;
    while(ptr != getStart())
    {
        if(ptr->getNext() == getStart())
        {
            cout << ptr << endl;
        }
        else
        {
             ptr = ptr->getNext();
        }
    }
}
void circularLinkedList::add(Node* n)
{
    if(Start == nullptr)
    {
        Start = n;
        n->getNext()->setNext(n);
    }
    else
    {
        Node* ptr = getStart()->getNext();

        while(ptr != getStart())
        {
            if(ptr->getNext() == getStart())
            {
                ptr->getNext()->setNext(n);
                n->setNext(getStart());
            }
        }
    }
     ptr = ptr->getNext();
}
void circularLinkedList::del()
{
    Node* ptr = getStart()->getNext();

        while(ptr != getStart())
        {
            if(ptr->getNext() == getStart())
            {
                ptr->getNext()->setNext(nullptr);
                delete ptr->getNext();
            }
            ptr = ptr->getNext();
        }
}
bool circularLinkedList::node::findL(Node* n)
{
    Node* ptr = getStart()->getNext();

        while(ptr != getStart())
        {
            if(ptr == n)
            {
                return true;
            }
            ptr = ptr->getNext();
        }
         return false;
}

