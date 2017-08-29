#pragma once
class Node
{
public:
	// Constructors
	Node();
	Node(int);
	~Node();

	// Get and Set Methods
	int getValue();
	void setValue(int);

private:
	// Variables
	int value;
};

