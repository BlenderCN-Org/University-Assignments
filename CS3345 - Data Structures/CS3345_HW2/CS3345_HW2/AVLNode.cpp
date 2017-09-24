#include "stdafx.h"
#include "AVLNode.h"

// Default Constructor
AVLNode::AVLNode() {	
	key = 0;	
	book = nullptr;
	height = 1;
	left = nullptr;
	right = nullptr;
}

// Constructor w/ Integer arguments, mainly for the random tree
AVLNode::AVLNode(int k) {
	key = k;
	book = nullptr;
	height = 0;
	left = nullptr;
	right = nullptr;
}

// Constructor with Integer and Book* arguments, mainly for the main assignment
AVLNode::AVLNode(int k, Book* b) {
	key = k;
	book = b;
	height = 0;
	left = nullptr;
	right = nullptr;
}

// Virtual Destructor
AVLNode::~AVLNode(){
}

// Returns the right Node*
AVLNode* AVLNode::getRight() {
	return right;
}

// Returns the left Node*
AVLNode* AVLNode::getLeft() {
	return left;
}

// Sets the left Node* to Node* cur
void AVLNode::setLeft(AVLNode* cur) {
	left = cur;
}

// Sets the right Node* to Node* cur
void AVLNode::setRight(AVLNode* cur) {
	right = cur;
}

// Returns the Node*'s int height
int AVLNode::getHeight() {
	return height;
}

// Returns the Node*'s int key (ISBN)
int AVLNode::getKey() {
	return key;
}

// Sets the Node's int height to int val
void AVLNode::setHeight(int val) {
	height = val;
}

// Sets the Node's int key to int val
void AVLNode::setKey(int val) {
	key = val;
}

// Sets the Node's Book* book to a new Book* object with ISBN 'key' and Name 'n'
void AVLNode::setBook(int key, std::string n) {
	book = new Book(key, n);
}