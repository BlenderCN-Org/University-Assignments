#include "stdafx.h"
#include "AVLNode.h"


AVLNode::AVLNode() {	
	key = 0;	
	value = nullptr;
	height = 1;
	left = nullptr;
	right = nullptr;;
}

AVLNode::AVLNode(int k) {
	key = k;
	value = nullptr;
	height = 0;
	left = nullptr;
	right = nullptr;;
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

Book* AVLNode::getValue() {
	return value;
}

void AVLNode::setHeight(int val) {
	height = val;
}

void AVLNode::setKey(int val) {
	key = val;
}

void AVLNode::setValue(Book* val) {
	value = val;
}