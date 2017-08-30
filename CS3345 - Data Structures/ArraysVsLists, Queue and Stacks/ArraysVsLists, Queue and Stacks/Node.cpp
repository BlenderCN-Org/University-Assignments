#include "stdafx.h"
#include "Node.h"

// Default Constructor
Node::Node() {
	value = 0;
	s_value = "";
}

// Default Constructor w/ int param
Node::Node(int i) {
	value = i;
	s_value = std::to_string(i);
}

// Virtual Destructor
Node::~Node() {
}

// Returns the integer 'value'
int Node::getValue() {
	return value;
}

std::string Node::toString() {
	return s_value;
}

// Sets teh Value of Value to an int 'i'
void Node::setValue(int i) {
	value = i;
}