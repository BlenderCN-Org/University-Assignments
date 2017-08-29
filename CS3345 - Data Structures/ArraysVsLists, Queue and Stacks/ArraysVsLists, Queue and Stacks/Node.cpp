#include "stdafx.h"
#include "Node.h"

// Default Constructor
Node::Node() {
	value = 0;
}

// Default Constructor w/ int param
Node::Node(int i) {
	value = i;
}

// Virtual Destructor
Node::~Node() {
}

// Returns the integer 'value'
int Node::getValue() {
	return value;
}

// Sets teh Value of Value to an int 'i'
void Node::setValue(int i) {
	value = i;
}