#pragma once
#include <string>
#include <iostream>
#include "Node.h"

class LinkedList {
public:
	// Constructor and Destructor
	LinkedList();
	~LinkedList();

	// Get and Set Methods
	Node* getHead();
	void setHead(Node*);
	Node* getTail();
	void setTail(Node*);

	// Traversal Methods
	bool has(std::string);
	
	// List Methods
	void insert(Node*);
	void insert(std::string);
	void remove(std::string);
	void print();


private:
	// Node Parameters
	Node* head;
	Node* tail;

};

