#pragma once
#include <algorithm>
#include <iostream>
class Node
{
public:
	// Default Constructors / Destructors
	Node();
	Node(int);
	~Node();

	// Get & Set Functions
	int getValue();
	void setValue(int);

private:
	// Payload
	int value;
};

