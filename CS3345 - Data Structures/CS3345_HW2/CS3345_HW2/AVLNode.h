#pragma once
class AVLNode
{
public:
	// Constructors / Destructors
	AVLNode();
	~AVLNode();
	
	// Where the node is
	int key;

	int value;

	// Node's current height
	int height;

	// Left node pointer
	AVLNode* left;
	// Right node pointer
	AVLNode& right;
};

