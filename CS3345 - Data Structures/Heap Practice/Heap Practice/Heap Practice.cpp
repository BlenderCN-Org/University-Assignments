// Heap Practice.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "Node.h"
#include "Heap.h"

int main()
{
	
	Heap* h = new Heap();
	h->insert(20);	
	h->insert(18);	
	h->insert(22);
	h->insert(4);
	h->print(); std::cout << std::endl;

	std::cout << std::endl;

	return 0;
}

