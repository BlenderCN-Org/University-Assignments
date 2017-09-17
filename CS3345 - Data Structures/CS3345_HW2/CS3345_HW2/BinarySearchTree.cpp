#include "stdafx.h"
#include "BinarySearchTree.h"

using std::cout;
using std::endl;

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

void BinarySearchTree::insert(int val) {
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
	AVLNode* parent = new AVLNode();
	numNodes++;
	int curHeight = 0;

	if (getHead() == nullptr) { 
		setHead(new AVLNode(val, curHeight));
		cout << "Inserted new Value:: " << val << " with Height:: " << curHeight << endl;
		cout << numNodes << " , " << maxHeight << endl;
		return;
	}
	
	while (cur != nullptr) {
		parent = cur;
		if (val < cur->getKey()) {
			cur = cur->getLeft();
			curHeight++;
		}
		else {
			cur = cur->getRight();
			curHeight++;
		}
	}	

	if (val < parent->getKey()) {
		cout << "Inserted Value:: " << val << " at Height:: " << curHeight << endl;
		parent->setLeft(new AVLNode(val, curHeight));
	}
	else {
		cout << "Inserted Value:: " << val << " at Height:: " << curHeight << endl;
		parent->setRight(new AVLNode(val, curHeight));
	}
	
	if (!verifyAVLTree()) { fixAVLTree(); }

	if (curHeight > maxHeight) { maxHeight = curHeight; }
	cout << numNodes << " , " << maxHeight << endl << endl;

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
	numNodes >= pow(2, maxHeight + 1) - 1 ? verify = true : verify = false;
	return verify;

}

void BinarySearchTree::fixAVLTree() {

}