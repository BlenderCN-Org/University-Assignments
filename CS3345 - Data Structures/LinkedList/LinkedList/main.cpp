// LinkedList.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "LinkedList.h"
#include "Node.h"

int main()
{
	LinkedList *l = new LinkedList();
	l->insert("Hello");
	l->insert("Merry");
	l->insert("World");
	l->print();
	l->remove("Merry");
	l->print();
	
	return 0;
}


