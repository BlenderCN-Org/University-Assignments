#include "node.h"
#ifndef STACK_H
#define STACK_H

class Stack
{
private:
    Node* head;

public:
    Stack(){head=nullptr;}
    ~Stack();

    Node* getHead(){return head;}
    void setHead(Node* n){head=n;}

    int pop();
    void push(int);
    bool isEmpty(){return (!head);}
    int top() {return head->getNum();}
    void print();

};
#endif // NODE_H

