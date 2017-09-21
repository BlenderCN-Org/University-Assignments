#include "stdafx.h"
#include "AVLNode.h"


AVLNode::AVLNode() {	
	key = 0;	
	book = nullptr;
	height = 1;
	left = nullptr;
	right = nullptr;
}

AVLNode::AVLNode(int k, Book* b) {
	key = k;
	book = b;
	height = 0;
	left = nullptr;
	right = nullptr;
}

AVLNode::~AVLNode(){
}

AVLNode* AVLNode::getRight() {
	return right;
}

AVLNode* AVLNode::getLeft() {
	return left;
}

void AVLNode::setLeft(AVLNode* cur) {
	left = cur;
}

void AVLNode::setRight(AVLNode* cur) {
	right = cur;
}

int AVLNode::getHeight() {
	return height;
}

int AVLNode::getKey() {
	return key;
}

void AVLNode::setHeight(int val) {
	height = val;
}

void AVLNode::setKey(int val) {
	key = val;
}

void AVLNode::setBook(int key, std::string n) {
	book = new Book(key, n);
}