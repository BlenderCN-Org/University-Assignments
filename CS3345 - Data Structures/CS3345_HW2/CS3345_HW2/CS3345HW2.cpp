// CS3345HW2.cpp : Defines the entry point for the console application.
//



#include "stdafx.h"
#include "BinarySearchTree.h"
#include <stdio.h>
#include <stdlib.h>

using std::cout;
using std::endl;
using std::cin;
using std::string;

int main()
{
	BinarySearchTree* bst = new BinarySearchTree();
	
	freopen("book_data.txt", "r", stdin);

	int tmpISBN;
	char tmpNAME[64];

	while (scanf("%i %s", &tmpISBN, &tmpNAME) == 2) {
		bst->insert(tmpISBN, tmpNAME);
	}
	
	bst->print(bst->getHead());

    return 0;
}

