#pragma once
class AVLNode
{
public:
	// Constructors / Destructors
	AVLNode();
	AVLNode(int,int);
	~AVLNode();	
	
	// Get / Set Functions
	AVLNode* getRight();	
	AVLNode* getLeft();

	int getValue();
	int getHeight();
	int getKey();

	void setRight(AVLNode*);
	void setLeft(AVLNode*);

	void setValue(int);
	void setKey(int);
	void setHeight(int);

private:
	// Where the node is
	int key;

	// Node's value
	int value;

	// Node's current height
	int height;

	// Left node pointer
	AVLNode* left;
	// Right node pointer
	AVLNode* right;

};

