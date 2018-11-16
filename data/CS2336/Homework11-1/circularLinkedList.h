#ifndef CIRCULARLINKEDLIST_H
#define CIRCULARLINKEDLIST_H

#include <string>

#include <BaseNode.h>

class circularLinkedList
{
    public:
        circularLinkedList();
        BaseNode Start;
        BaseNode* getStart() {return start;} const;
        bool isEmpty();
        int listLength();
        void printList();
        void add(Node*);
        void del();
        bool findL(Node*);



    protected:

    private:
};

#endif // CIRCULARLINKEDLIST_H

/*

1. Initialize the list (to an empty state).
2. Determine if the list is empty.
3. Destroy the list.
4. Print the list.
5. Find the length of the list.
6. Search the list for a given item.
7. Insert an item in the list.
8. Delete an item from the list.
9. Copy the list

*/
