#pragma once
#include "AVLNode.h"
class RandomBinaryTree
{
public:
	RandomBinaryTree();
	~RandomBinaryTree();

	void createTree();
	bool verifyBST(AVLNode*);
	bool verifyAVL(AVLNode*);

	AVLNode* getHead();
	void setHead(AVLNode*);	
	void insert(int);
	AVLNode* insert(int, AVLNode*);

	int height(AVLNode*);
	int getBalance(AVLNode*);
	int greaterHeight(int, int);

	void print(AVLNode*);

private:
	AVLNode* head;

};

