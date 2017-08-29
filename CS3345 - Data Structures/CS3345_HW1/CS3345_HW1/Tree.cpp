#include "stdafx.h"
#include "Tree.h"

// Constructor
Tree::Tree() {
	root = nullptr;
	nodes = 0;
	level = 0;
}

// Virtual Destructor
Tree::~Tree() {

}

// Returns the Root Node
Node* Tree::getRoot() {
	return root;
}

// Sets the Root Node
void Tree::setRoot(Node* tmp) {
	root = tmp;
}


// Inserts a new int into the tree, then calls 'reheap()' if needed
void Tree::insert(Node* cur) {
	
}

// Reheaps the tree to correct bad ordering
void Tree::heapify(Node* cur) {
	
}

// Swaps node A with node B
void Tree::swap(Node* a, Node* b) {
	int val = a->getValue();
	a->setValue(b->getValue());
	b->setValue(val);
}

// Prints the current tree, must start with head, does an in-order traversal
void Tree::print(Node* cur) {
	std::cout << cur->getValue() << " ";
	
	if (cur->getLeft() != nullptr) {
		print(cur->getLeft());
	}
	if (cur->getRight() != nullptr) {
		print(cur->getRight());
	}
}
