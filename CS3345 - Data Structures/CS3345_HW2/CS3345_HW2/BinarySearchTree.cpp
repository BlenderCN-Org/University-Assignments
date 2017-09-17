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


	cur->setHeight(greaterHeight(height(cur->getLeft()), height(cur->getRight())) + 1);
	return cur;
}

int BinarySearchTree::greaterHeight(int x, int y) {
	return x > y ? x : y;
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
