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

	int tree_val = round((rand() % 100) + 40);
	int node_val;
	for (int i = 0; i < tree_val; i++) {
		node_val = rand() % 100;		
		insert(node_val);
	}
}

bool RandomBinaryTree::verifyBST(AVLNode* cur) {
	if (cur == nullptr || getHead() == nullptr) {
		return false;
	}
	verifyBST(cur->getLeft());
	if (cur->getLeft() != nullptr) {
		if (cur->getLeft()->getKey() >= cur->getKey()) {
			return false;
		}
	}
	if (cur->getRight() != nullptr) {
		if (cur->getRight()->getKey() <= cur->getKey()) {
			return false;
		}
	}
	verifyBST(cur->getRight());

	return true;
	
}

bool RandomBinaryTree::verifyAVL(AVLNode* cur) {
	if (verifyBST(getHead())) {
		if (cur == nullptr || getHead() == nullptr) {
			return false;
		}
		verifyAVL(cur->getLeft());
		if (abs(getBalance(cur)) >= 2) {
			return false;
		}
		verifyAVL(cur->getRight());
		return true;
	}
	else
		return false;
		return false;	
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
	float lor = rand() % 2;

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
