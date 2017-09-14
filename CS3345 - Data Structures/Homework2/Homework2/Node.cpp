#include "stdafx.h"
#include "Node.h"


Node::Node() {
	value = 0;
	height = 0;
	left = nullptr;
	right = nullptr;
}

Node::~Node() {
}

Node::Node(int val) {
	value = val;
	height = 0;
	left = nullptr;
	right = nullptr;
}

Node* Node::getLeft() {
	return left;
}

void Node::setLeft(Node* tmp) {
	left = tmp;
}

Node* Node::getRight() {
	return right;
}

void Node::setRight(Node* tmp) {
	right = tmp;
}

int Node::getValue() {
	return value;
}

int Node::getHeight() {
	return height;
}
