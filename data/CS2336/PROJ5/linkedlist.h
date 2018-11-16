#ifndef LINKEDLIST_H
#define LINKEDLIST_H
#include "node.h"
#include <iostream>
#include <cstdlib>

class LinkedList
{
    public:
        //Default Constructor
        LinkedList() {head = nullptr;}

        //Another Constructor
        LinkedList(Node* cur) {head = cur;}

        //Destructor Constructor
        virtual ~LinkedList() {};

        //Size of the List
        int size;

        //Head Node
        Node* head = nullptr;

        //Returns the Head Node
        Node* getHead() {return head;}

        //Returns the Size of the List
        int getSize(){return size;}

        //Sets the Size of the List
        void setSize(int i){i=size;}

        //Sets the head of the list
        void setHead(Node* cur) {head = cur;}

        //Inserts into the List
        void insert(std::string);

        //Returns true if the input exists within the list
        bool find(std::string);

        //Returns a node if a node is found within the list
        Node* findNode(std::string);

    protected:

    private:
};

#endif // LINKEDLIST_H
