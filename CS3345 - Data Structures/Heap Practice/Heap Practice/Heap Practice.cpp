// Heap Practice.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "Node.h"
#include "Heap.h"

int main()
{
	
	Heap* h = new Heap();
	h->insert(8);

	h->print();
	

	return 0;
}

