#pragma once
#include "Node.h"
#include <iostream>
#include <string>
#include <sstream>
class CircularArrayQueue
{
public:
	// Constructors and Destructors
	CircularArrayQueue(int);
	~CircularArrayQueue();

	// Insertion and Deletion Functions
	void enqueue(int);
	int dequeue();

	// Utility Functions
	void printQueue();

private:
	//if Read Pointer = Write Pointer -> EMPTY, FULL when size-1 is = READ POINTER
	int read; //Front
	int write; //Back
	int size;
	Node *arr;
};

