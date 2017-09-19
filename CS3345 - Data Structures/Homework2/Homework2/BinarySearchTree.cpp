#include "stdafx.h"
#include "BinarySearchTree.h"

using std::cout;
using std::endl;

BinarySearchTree::BinarySearchTree() {
	head = nullptr;
	maxHeight = -1;
}


BinarySearchTree::~BinarySearchTree() {
}

void BinarySearchTree::insert(int val) {
	Node* tmp = new Node(val);
	Node* cur = getHead();

	int curHeight = 0;

	if (getHead() == nullptr) {
		setHead(tmp);
	}
	else {
		while (true) {

			if (tmp->getValue() < cur->getValue()) {
				if (cur->getLeft() == nullptr) {
					cur->setLeft(tmp);
					if (tmp->getLeft() == nullptr && tmp->getRight() == nullptr) {
						maxHeight++;
						cout << "MaxHeight:: " << maxHeight << endl;
					}
					break;
				}
				else {
					cur = cur->getLeft();
					curHeight++;
				}
			}

			else if(tmp->getValue() > cur->getValue()) {
				if (cur->getRight() == nullptr) {
					cur->setRight(tmp);
					if (tmp->getLeft() == nullptr && tmp->getRight() == nullptr) {
						maxHeight++;
						cout << "MaxHeight:: " << maxHeight << endl;
					}
					break;
				}
				else {
					cur = cur->getRight();
					curHeight++;
				}
			}

		}
	}
}

void BinarySearchTree::adjustHeights(int val) {
	AVLNode* cur = getHead();
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
