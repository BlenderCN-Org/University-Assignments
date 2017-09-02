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
	for (int i = 1; i < 10000; i++) {
		h->insert(i);
	}	

	std::chrono::time_point<std::chrono::system_clock> m_EndTime = std::chrono::system_clock::now();
	std::cout << std::chrono::duration_cast<std::chrono::milliseconds>(m_EndTime - m_StartTime).count() << "ms" << std::endl;


	return 0;
}

