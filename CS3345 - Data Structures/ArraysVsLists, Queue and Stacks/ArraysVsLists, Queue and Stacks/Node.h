#pragma once
#include <string>
#include <stdio.h>
#include <stdlib.h>
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
	std::string toString();

private:
	// Variables
	int value;
	std::string s_value;
};

