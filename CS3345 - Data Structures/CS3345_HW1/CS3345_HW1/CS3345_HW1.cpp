// CS3345_HW1.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "Node.h"
#include "Tree.h"
#include <vector>

using namespace std;

int main()
{
	int list[] = {12,10,4,8,6,7,15,3,14,9,2};

	Tree* t = new Tree();
	
	
	for (int i = 0; i < 11; i++) {
		t->insert(new Node(list[i]));
		t->print(t->getRoot());
		cout << endl;
	}
	
    return 0;
}

