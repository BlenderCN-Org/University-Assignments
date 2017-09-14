#include "stdafx.h"
#include "BinarySearchTree.h"


BinarySearchTree::BinarySearchTree() {
	head = nullptr;
}


BinarySearchTree::~BinarySearchTree() {
}

void BinarySearchTree::insert(int val) {
	Node* tmp = new Node(val);
	Node* cur = getHead();

	if (getHead() == nullptr) {
		setHead(tmp);
	}
	else {
		while (true) {

			if (tmp->getValue() < cur->getValue()) {
				if (cur->getLeft() == nullptr) {
					cur->setLeft(tmp);
					break;
				}
				else {
					cur = cur->getLeft();
				}
			}

			else if(tmp->getValue() > cur->getValue()) {
				if (cur->getRight() == nullptr) {
					cur->setRight(tmp);
					break;
				}
				else {
					cur = cur->getRight();
				}
			}

		}
	}
}

void BinarySearchTree::insert(Node* tmp) {

}

Node* BinarySearchTree::getHead() {
	return head;
}

void BinarySearchTree::setHead(Node* tmp) {
	head = tmp;
}

void BinarySearchTree::printTree(Node* tmp) {

	if (tmp == nullptr) {
		return;
	}

	printTree(tmp->getLeft());
	std::cout << tmp->getValue() << " " << std::endl;
	printTree(tmp->getRight());
	
}
