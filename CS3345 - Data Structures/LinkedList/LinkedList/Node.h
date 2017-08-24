#pragma once
#include <string>

class Node {
public:	

	// Get and Set Methods
	Node* getNext();
	void setNext(Node* cur);
	std::string getString();
	void setString(std::string);

	// Constructors and Destructor
	Node();
	Node(std::string);
	virtual ~Node();

private:
	// Node Pointer(s)
	Node* next = nullptr;

	// Payload Contents
	std::string name;
};

