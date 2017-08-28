#include "stdafx.h"
#include "Node.h"

// Constructor
Node::Node(int v) {
	value = v;
	left = nullptr;
	right = nullptr;
}

// Virtual Destructor
Node::~Node() {

}

// Returns the node to the left
Node* Node::getLeft() {
	return left;
}

// Returns the node to to the right
Node* Node::getRight() {
	return right;
}

// Sets the node to the left with a new node 'tmp'
void Node::setLeft(Node* tmp) {
	left = tmp;
}

// Sets the node to the right with a new node 'tmp'
void Node::setRight(Node* tmp) {
	right = tmp;
}

// Returns the integer payload 'value'
int Node::getValue() {
	return value;
}

// Sets the value of the integer payload 'value' with v
void Node::setValue(int v) {
	value = v;
}

