// Heap Practice.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "Node.h"
#include "Heap.h"

int main()
{
	
	Heap* h = new Heap();
	h->insert(8);	
	h->insert(2);
	h->insert(3);
	h->insert(6);
	h->insert(10);
	h->insert(9);
	h->insert(7);
	h->print();

	std::cout << std::endl;

	return 0;
}

