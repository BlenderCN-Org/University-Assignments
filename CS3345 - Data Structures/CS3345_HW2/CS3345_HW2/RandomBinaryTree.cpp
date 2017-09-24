#include "stdafx.h"
#include <stdio.h>  
#include <stdlib.h> 
#include <math.h>
#include <iostream>
#include <ctime>
#include "RandomBinaryTree.h"


RandomBinaryTree::RandomBinaryTree() {
	head = nullptr;
}

RandomBinaryTree::~RandomBinaryTree() {
}

void RandomBinaryTree::createTree() {
	srand((int)time(0));

	int tree_val = round((rand() % 20) + 1);
	int node_val;
	for (int i = 0; i < tree_val; i++) {
		node_val = rand() % 100;		
		insert(node_val);
	}
}

bool RandomBinaryTree::verifyBST() {
	return verifyBST(getHead(), INT_MIN, INT_MAX);
}

bool RandomBinaryTree::verifyBST(AVLNode* cur, int l, int r) {
	if (cur == nullptr) {
		return true;
	}

	if (cur->getKey() < l || cur->getKey() > r) {
		return false;
	}
	return verifyBST(cur->getLeft(), l, cur->getKey()) && verifyBST(cur->getRight(), cur->getKey(), r);
}

bool RandomBinaryTree::verifyAVL() {
	if (verifyBST()) {
		return verifyAVL(getHead());
	}
	else {
		return false;
	}		
}

bool RandomBinaryTree::verifyAVL(AVLNode* cur) {
	if (cur == nullptr) {
		return true;
	}
	if (abs(getBalance(cur)) >= 2 ) {
		return false;
	}
	return verifyAVL(cur->getLeft()) && verifyAVL(cur->getRight());
}

AVLNode* RandomBinaryTree::getHead() {
	return head;
}

void RandomBinaryTree::setHead(AVLNode* cur) {
	head = cur;
}

void RandomBinaryTree::insert(int key) {
	setHead(insert(key, getHead()));
}

AVLNode* RandomBinaryTree::insert(int key, AVLNode* cur) {	
	int lor = rand() % 2;

	if (cur == nullptr) {
		cur = new AVLNode(key);
	}
	else if (lor == 0) {
		cur->setLeft(insert(key, cur->getLeft()));
	}
	else if (lor == 1) {
		cur->setRight(insert(key, cur->getRight()));
	}
	cur->setHeight(greaterHeight(height(cur->getLeft()), height(cur->getRight())) + 1);
	return cur;
}

void RandomBinaryTree::print(AVLNode* cur) {
	if (cur == nullptr) {
		return;
	}
	print(cur->getLeft());
	std::cout << cur->getKey() << "   ";
	print(cur->getRight());

}

int RandomBinaryTree::greaterHeight(int x, int y) {
	return (x > y) ? x : y;
}

int RandomBinaryTree::height(AVLNode* cur) {
	return (cur == nullptr) ? -1 : cur->getHeight();
}

int RandomBinaryTree::getBalance(AVLNode* cur) {
	if (cur == nullptr) {
		return 0;
	}
	else {
		return height(cur->getLeft()) - height(cur->getRight());
	}
}
