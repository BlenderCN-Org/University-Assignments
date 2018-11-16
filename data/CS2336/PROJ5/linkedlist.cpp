#include "linkedlist.h"

void LinkedList::insert(std::string vertex)
{
    size++;
    Node* cur = new Node(vertex);

    //If the head is null, puts the new node into the head
    if(head == nullptr) {
        head = cur;
    }

    //If head is not null, finds the next null node and inserts it there
    else {
        Node* tmp = head;
        while(tmp->getNext()) {
            tmp = tmp->getNext();
        }
        tmp->setNext(cur);

    }
}

bool LinkedList::find(std::string vertex)
{
    double tmp = atof(vertex.c_str());
    Node* cur = getHead();

    //While the list has values, will continue to loop until it ends, then returns true if a node was found that matched the input
    while(cur)
    {
        if(cur->getVal() == tmp)
            return true;
        else
            cur = cur->getNext();
    }

    return false;
}

Node* LinkedList::findNode(std::string vertex)
{
    double tmp = atof(vertex.c_str());
    Node* cur = getHead();

    //While the list has values, will continue to loop until it ends, then returns true if a node was found that matched the input
    while(cur)
    {
        if(cur->getVal() == tmp){
            return cur;
        }
        else
            cur = cur->getNext();
    }
}
