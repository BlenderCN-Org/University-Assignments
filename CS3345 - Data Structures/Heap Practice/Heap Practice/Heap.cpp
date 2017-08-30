#include "stdafx.h"
#include "Heap.h"

using namespace std;

// Default Constructor
Heap::Heap() {
	size = 0;
	capacity = 2;
	level = 0;
	minHeap = new Node[capacity];
}
// Virtual Destructor
Heap::~Heap() {
}

// Returns how many elements are in the array
int Heap::getSize() {
	return size;
}

// Returns the maximum amount of elements the heap can hold at this time
int Heap::getCapacity() {
	return capacity;
}

// Returns the maximum level of the heap
int Heap::getLevel() {
	return level;
}

// Inserts a new element into the heap, following leftmost rule ordering
void Heap::insert(int v) {

	// If HEAD is null
	if (minHeap[0].getValue() == -1) {
		cout << "Inserting @ HEAD" << endl;
		minHeap[0] = *new Node(v);
		level++; // BC Level 0 is now FULL
	}
	// if HEAD is NOT null
	else {
		// i (first spot in level) = 2^level - 1, while its less than all the nodes in that level, i++ until node is found
		for (int i = pow(2,level)-1; i < (pow(2, level) - 1) + pow(2, level); i++) {
			if (minHeap[i].getValue() == -1) {
				minHeap[i] = *new Node(v);
				cout << "Inserting @:: " << i << endl;				
				if (i == (pow(2, level) - 1) + pow(2, level) - 1) {
					level++;
				}
				break;
			}
		}
	}

	size++;
	
	if (size >= capacity / 2) {
		reallocate();
	}
}

// Reallocates memory and instinitates a new array
void Heap::reallocate() {

	capacity *= 2;
	int newCapacity = 2 * capacity;
	Node* newHeap = new Node[newCapacity];
	for (int i = 0; i < size; i++) {
		newHeap[i] = minHeap[i];
	}
	minHeap = newHeap;
}

// Makes sure the heap follows the min-heap ruleset
void Heap::minHeapify() {

}

// Outprints the tree
void Heap::print() {
	int tmpLevel = 0;
	for (int i = 0; i < size; i++) {
		std::cout << minHeap[i].getValue() << " ";

		if (i == 0 || i == 2 || i == 6 || i == 14 || i == 30 || i == 62) {
			std::cout << std::endl;
		}

	}
}