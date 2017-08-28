#pragma once
class Node
{


public:
	// Constructors
	Node(int);
	virtual ~Node();

	// Get & Set Functions
	Node* getLeft();
	Node* getRight();
	void setLeft(Node*);
	void setRight(Node*);
	int getValue();
	void setValue(int);
	

private:
	int value;
	Node* left;
	Node* right;
};

