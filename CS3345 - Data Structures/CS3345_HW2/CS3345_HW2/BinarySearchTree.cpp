#include "stdafx.h"
#include "BinarySearchTree.h"

#define DONOTHING()


BinarySearchTree::BinarySearchTree() {
	head = nullptr;
	numNodes = 0;
	maxHeight = 0;
}


BinarySearchTree::~BinarySearchTree() {
}

AVLNode* BinarySearchTree::getHead() {
	return head;
}

void BinarySearchTree::setHead(AVLNode* cur) {
	head = cur;
}

AVLNode* BinarySearchTree::insert(AVLNode* cur,int val, int curHeight) {
	if (cur == nullptr) {
		numNodes++;		
		return new AVLNode(val, curHeight);
	}

	if (val < cur->getValue()) {
		curHeight++;
		if (curHeight > maxHeight) { maxHeight = curHeight; }		
		cur->setLeft(insert(cur->getLeft(), val, curHeight));
	}
	else if (val > cur->getValue()) {
		curHeight++;
		if (curHeight > maxHeight) { maxHeight = curHeight; }
		cur->setRight(insert(cur->getRight(), val, curHeight));
	}	
}

void BinarySearchTree::print(AVLNode* cur) {
	if (cur == nullptr) {
		return;
	}

	print(cur->getLeft());
	std::cout << cur->getValue() << "   ";
	print(cur->getRight());

}

bool BinarySearchTree::verifyAVLTree() {
	bool verify;
	!(numNodes >= pow(2, maxHeight)) ? (verify = false) : (verify = true);
	return verify;
}