#include "node.h"
#ifndef QUEUE_H
#define QUEUE_H

class Queue
{
private:
    node* front;
    node* tail;

public:
    Queue(){front=nullptr; tail = nullptr;}
    ~Queue();

    node* getFront(){return front;}
    node* getTail(){return tail;}
    void setFront(node* n){front=n;}
    void setTail(node* n){tail=n;}
    void moveNthFront();
    char dequeue();
    void enqueue(char);
    bool isEmpty(){return (!front);}
    int inFront();
    void print();
    int big();
    bool operator==(Queue);


};
#endif // QUEUE_H

