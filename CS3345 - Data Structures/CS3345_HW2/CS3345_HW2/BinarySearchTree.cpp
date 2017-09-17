#include "stdafx.h"
#include "BinarySearchTree.h"

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

AVLNode* BinarySearchTree::insert(int val) {
	/*
	if (cur == nullptr) {
		numNodes++;		
		return new AVLNode(val, curHeight);
	}

	if (val < cur->getKey()) {
		curHeight++;
		if (curHeight > maxHeight) { maxHeight = curHeight; }		
		cur->setLeft(insert(cur->getLeft(), val, curHeight));
	}
	else if (val > cur->getKey()) {
		curHeight++;
		if (curHeight > maxHeight) { maxHeight = curHeight; }
		cur->setRight(insert(cur->getRight(), val, curHeight));
	}
	*/

	AVLNode* cur = getHead();
	int curHeight = 0;
	if (cur == nullptr) {
		setHead(cur);
	}
	else {
		while (cur != nullptr) {
			if (val < cur->getKey()) {
				curHeight++;
				if (cur->getLeft() == nullptr) {
					cur->setLeft(new AVLNode(val,curHeight));
					break;
				}
				else {
					cur = cur->getLeft();
				}
				if (curHeight > maxHeight) { maxHeight = curHeight; }
			}
			else if (val > cur->getKey()) {
				curHeight++;
				if (cur->getRight() == nullptr) {
					
				}
			}
		}
	}

}

void BinarySearchTree::print(AVLNode* cur) {
	if (cur == nullptr) {
		return;
	}

	print(cur->getLeft());
	std::cout << cur->getKey() << "   ";
	print(cur->getRight());

}

bool BinarySearchTree::verifyAVLTree() {
	bool verify;
	!(numNodes >= pow(2, maxHeight)) ? (verify = false) : (verify = true);
	return verify; 
}