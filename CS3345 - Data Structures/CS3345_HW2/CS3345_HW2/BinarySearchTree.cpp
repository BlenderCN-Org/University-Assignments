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

void BinarySearchTree::insert(int key) {
	setHead(insert(key, getHead()));
}

AVLNode* BinarySearchTree::insert(int key, AVLNode* cur) {
	if (cur == nullptr) {
		cur = new AVLNode(key);
	}
	else if (key < cur->getKey()) {
		cur->setLeft(insert(key, cur->getLeft()));
	}
	else if (key > cur->getKey()) {
		cur->setRight(insert(key, cur->getRight()));
	}
	else {
		return cur;
	}

	cur->setHeight(greaterHeight(height(cur->getLeft()), height(cur->getRight())) + 1);

	int balance = getBalance(cur);

	// Left Left 
	if (balance > 1 && key < cur->getLeft()->getKey()) {
		return rotateRight(cur);
	}
	
	// Right Right
	if (balance < -1 && key > cur->getRight()->getKey()) {
		return rotateLeft(cur);
	}

	// Left Right
	if (balance > 1 && key > cur->getLeft()->getKey()) {
		cur->setLeft(rotateLeft(cur->getLeft()));
		return rotateRight(cur);
	}

	// Right Left
	if (balance < -1 && key < cur->getRight()->getKey()) {
		cur->setRight(rotateRight(cur->getRight()));
		return rotateLeft(cur);
	}

	return cur;
}

int BinarySearchTree::greaterHeight(int x, int y) {
	return (x > y) ? x : y;
}

int BinarySearchTree::height(AVLNode* cur) {
	return (cur == nullptr) ? -1 : cur->getHeight();
}

void BinarySearchTree::print(AVLNode* cur) {
	if (cur == nullptr) {
		return;
	}

	print(cur->getLeft());
	std::cout << cur->getKey() << ", " << cur->getHeight() << "   ";
	print(cur->getRight());

}

AVLNode* BinarySearchTree::rotateRight(AVLNode* cur) {
	AVLNode* x = cur->getLeft();
	AVLNode* y = x->getRight();

	x->setRight(cur);
	cur->setLeft(y);
	
	cur->setHeight(greaterHeight(height(cur->getLeft()), height(cur->getRight())) + 1);
	x->setHeight(greaterHeight(height(x->getLeft()), height(x->getRight())) + 1);

	return x;
}

AVLNode* BinarySearchTree::rotateLeft(AVLNode* cur) {
	AVLNode* x = cur->getRight();
	AVLNode* y = x->getLeft();

	x->setLeft(cur);
	cur->setRight(y);

	cur->setHeight(greaterHeight(height(cur->getLeft()), height(cur->getRight())) + 1);
	x->setHeight(greaterHeight(height(x->getLeft()), height(x->getRight())) + 1);

	return x;
}

int BinarySearchTree::getBalance(AVLNode* cur) {
	if (cur == nullptr) {
		return 0;
	}
	else {
		return height(cur->getLeft()) - height(cur->getRight());
	}
}