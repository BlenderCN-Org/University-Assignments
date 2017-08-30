#pragma once
#include "Node.h"
class Heap
{
	// Array-Heap Tree
public:
	// Default Constructors / Destructors
	Heap();
	~Heap();

	// Get and Set Methods
	int getSize();
	int getCapacity();
	int getLevel();

	// Utility Functons
	void insert(int);
	void reallocate();
	void print();
	void minHeapify(int);

private:
	// Variables
	int size;
	int capacity;
	int level;
	Node* minHeap;
};

