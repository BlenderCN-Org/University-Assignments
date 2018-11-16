#include "queue.h"
#include <iostream>
using namespace std;

Queue::~Queue()
{
    node* hold;
    while (front)
    {
        hold = front;
        front = front->getNext();
        delete hold;
    }
}
void Queue::print()
{
    node* cur = front;
    while (cur)
    {
        cout << cur->getChar() << ' ';
        cur = cur->getNext();
    }
    cout << endl;
}

void Queue::enqueue(char ch)
{


}
void Queue::moveNthFront()
{

}

bool Queue:: operator==(Queue y)
{
    bool isEqual = true;
    Queue arr1 = *this;
    Queue arr2 = y;

    if(arr1.big() == arr2.big())
    {
        while((arr1.isEmpty() == false))
        {
            if(arr1.getFront() != arr2.getFront())
            {
                cout << "The Files are NOT identical" << endl;
                return false;
            }
            arr1.dequeue();
            arr2.dequeue();
        }
    }
    else
    {
        cout << "The Files are NOT identical" << endl;
        return false;
    }

    if(isEqual == true)
    {
        cout << "The Files ARE identical" << endl;
    }

    return isEqual;
}

int Queue:: big()
{
    int counter;
    while(getFront() != nullptr)
    {
        dequeue();
        counter++;
    }
    return counter;
}

char Queue::dequeue()
{
    char x;
    return x;

}
