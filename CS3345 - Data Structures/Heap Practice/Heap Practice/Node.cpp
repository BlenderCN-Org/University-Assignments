#include "stdafx.h"
#include "Node.h"

// Default Constructor
Node::Node() {
	value = -1;
}

// Node Constructor w/ int parameter
Node::Node(int v) {
	value = v;
}

// Virtual Destructor
Node::~Node() {
}

// Returns 'value'
int Node::getValue() {
	return value;
}

// Sets 'value' to integer value 'v'
void Node::setValue(int v) {
	value = v;
}

