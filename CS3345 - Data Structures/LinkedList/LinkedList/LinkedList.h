#pragma once
#include <string>
#include "Node.h"

class LinkedList {
public:
	// Constructor and Destructor
	LinkedList();
	~LinkedList();

	// Get and Set Methods
	Node* getHead();
	void setHead(Node*);

	// Traversal Methods
	bool find(std::string); 	

private:
	// Node Parameters
	Node* head;

};

