// LinkedList.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "LinkedList.h"
#include "Node.h"

using namespace std;

int main()
{
	LinkedList *l = new LinkedList();
	l->insert("Hello");
	l->insert("Merry");
	l->insert("World");
	l->print();
	l->remove("Merry");
	l->print();

	cout << l->has("World") << endl;
	
	return 0;
}


