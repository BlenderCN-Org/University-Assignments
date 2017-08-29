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

	int getValue();
	void setValue(int);

private:
	int value;
};

