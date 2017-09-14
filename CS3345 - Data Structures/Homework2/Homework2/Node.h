#pragma once
class Node
{
public:
	Node();
	Node(int);
	~Node();

	Node* getRight();
	void setRight(Node*);
	Node* getLeft();
	void setLeft(Node*);
	int getValue();
	int getHeight();

private:
	int height;
	int value;
	Node* right;
	Node* left;
};

