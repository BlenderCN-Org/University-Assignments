#include "stdafx.h"
#include "BinarySearchTree.h"

using std::cout;
using std::endl;

// Default Constructor, initializes all variables to 0 or nullptr
BinarySearchTree::BinarySearchTree() {
	head = nullptr;	
}

// Virtual Destructor
BinarySearchTree::~BinarySearchTree() {
}

// Returns the Head Node*
AVLNode* BinarySearchTree::getHead() {
	return head;
}

// Sets the Head Node* to Node* cur
void BinarySearchTree::setHead(AVLNode* cur) {
	head = cur;
}

// Reassigns the head node after insertion to fix AVL rotations
void BinarySearchTree::insert(int key, std::string name) {
	setHead(insert(key, getHead(),name));
}

// Recursive Binary Search Tree Insertion
AVLNode* BinarySearchTree::insert(int key, AVLNode* cur,std::string name) {
	if (cur == nullptr) {
		cur = new AVLNode(key, new Book(key, name));
	}
	else if (key < cur->getKey()) {
		cur->setLeft(insert(key, cur->getLeft(),name));
	}
	else if (key > cur->getKey()) {
		cur->setRight(insert(key, cur->getRight(),name));
	}
	else {
		return cur;
	}

	cur->setHeight(greaterHeight(height(cur->getLeft()), height(cur->getRight())) + 1);

	int balance = getBalance(cur);

	// Left Left 
	if (balance > 1 && key < cur->getLeft()->getKey()) {
		cout << "*Imbalance occured at insertion of ISBN: " << key << ", fixed by Right Rotation" << endl;
		return rotateRight(cur);
	}
	
	// Right Right
	if (balance < -1 && key > cur->getRight()->getKey()) {
		cout << "*Imbalance occured at insertion of ISBN: " << key << ", fixed by Left Rotation" << endl;
		return rotateLeft(cur);
	}

	// Left Right
	if (balance > 1 && key > cur->getLeft()->getKey()) {
		cout << "*Imbalance occured at insertion of ISBN: " << key << ", fixed by Left-Right Rotation" << endl;
		cur->setLeft(rotateLeft(cur->getLeft()));
		return rotateRight(cur);
	}

	// Right Left
	if (balance < -1 && key < cur->getRight()->getKey()) {
		cout << "*Imbalance occured at insertion of ISBN: " << key << ", fixed by Right-Left Rotation" << endl;
		cur->setRight(rotateRight(cur->getRight()));
		return rotateLeft(cur);
	}
	
	return cur;
}

// Returns if int x is greater than int y
int BinarySearchTree::greaterHeight(int x, int y) {
	return (x > y) ? x : y;
}

// // Returns the height of a given Node* cur
int BinarySearchTree::height(AVLNode* cur) {
	return (cur == nullptr) ? -1 : cur->getHeight();
}

// Rotates the tree at pivot Node* cur to the right once
AVLNode* BinarySearchTree::rotateRight(AVLNode* cur) {
	AVLNode* x = cur->getLeft();
	AVLNode* y = x->getRight();

	x->setRight(cur);
	cur->setLeft(y);
	
	cur->setHeight(greaterHeight(height(cur->getLeft()), height(cur->getRight())) + 1);
	x->setHeight(greaterHeight(height(x->getLeft()), height(x->getRight())) + 1);

	return x;
}

// Rotates the tree at pivot Node* cur to the left once
AVLNode* BinarySearchTree::rotateLeft(AVLNode* cur) {
	AVLNode* x = cur->getRight();
	AVLNode* y = x->getLeft();

	x->setLeft(cur);
	cur->setRight(y);

	cur->setHeight(greaterHeight(height(cur->getLeft()), height(cur->getRight())) + 1);
	x->setHeight(greaterHeight(height(x->getLeft()), height(x->getRight())) + 1);

	return x;
}

// Retunrs the balance of a given node
int BinarySearchTree::getBalance(AVLNode* cur) {
	if (cur == nullptr) {
		return 0;
	}
	else {
		return height(cur->getLeft()) - height(cur->getRight());
	}
}

// Prints the tree in an 'IN-ORDER' traversal
void BinarySearchTree::print(AVLNode* cur) {
	if (cur == nullptr) {
		return;
	}	

	print(cur->getLeft());
	cout << "	ISBN:: " << cur->getKey() << ", Height:: " << cur->getHeight() << endl;
	print(cur->getRight());

}
