#include "stdafx.h"
#include "Node.h"

// Default Constr
Node::Node() {
	name = "";
	next = nullptr;
}

// Constr w/ string input, sets name param to string input
Node::Node(std::string s) {
	name = s;
	next = nullptr;
}

// Virtual Destr
Node::~Node() {
}

// Returns the memaddr of the next node
Node* Node::getNext() {
	return next;
}

// Sets the memaddr of Node* next to the memaddr of Node* cur
void Node::setNext(Node* cur) {
	next = cur;
}

// Returns the string name variable
std::string Node::getString() {
	return name;
}

// Sets the string name variable to s
void Node::setString(std::string s) {
	name = s;
}
