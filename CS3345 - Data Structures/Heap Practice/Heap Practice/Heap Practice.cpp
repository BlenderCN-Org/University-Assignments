// Heap Practice.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "Heap.h"
#include <chrono>
typedef std::chrono::high_resolution_clock Clock;

int main()
{
	
	std::chrono::time_point<std::chrono::system_clock> m_StartTime = std::chrono::system_clock::now();

	Heap* h = new Heap();
	h->insert(21);
	h->insert(12);
	h->insert(11);
	h->insert(5);
	h->insert(7);
	h->insert(10);	
	h->insert(4);
	h->insert(1);

	h->print(); std::cout << std::endl;
	std::cout << std::endl;

	std::chrono::time_point<std::chrono::system_clock> m_EndTime = std::chrono::system_clock::now();
	std::cout << std::chrono::duration_cast<std::chrono::milliseconds>(m_EndTime - m_StartTime).count() << "ms" << std::endl;


	return 0;
}

