//**************************************************************
//  main.cpp
//  HashTable
//
//  Created by Kar Beringer on June 19, 2014.
//  Demonstrate a simple Hash Table in C++.
//  Implements a Linked List class.
//**************************************************************

#include "HashTable.h"

int main()
{
    // Create 26 Items to store in the Hash Table.
    Item * A = new Item {"Apple", NULL};
    Item * B = new Item {"Banana", NULL};
    Item * C = new Item {"Caterpillar", NULL};
    Item * D = new Item {"Dog", NULL};
    Item * E = new Item {"Elephant", NULL};


    // Create a Hash Table of 13 Linked List elements.
    HashTable table;

    // Add 3 Items to Hash Table.
    table.insertItem(A);
    table.printTable();
    table.insertItem(B);
    table.printTable();
    table.insertItem(C);
    table.printTable();
    table.insertItem(D);
    table.printTable();
    table.insertItem(E);
    table.printTable();
    table.printHistogram();

    // Remove one item from Hash Table.

    // Add 23 items to Hash Table.




    // Look up an item in the hash table
    //Item * result = table.getItemByKey("Snakes");
    //cout << result -> key << endl;
    return 0;
}
