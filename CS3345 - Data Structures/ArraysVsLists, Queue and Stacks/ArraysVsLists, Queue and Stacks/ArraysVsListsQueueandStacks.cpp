
// ArraysVsListsQueueandStacks.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "CircularArrayQueue.h"

using namespace std;

int main()
{	
	CircularArrayQueue *CAQ = new CircularArrayQueue(5);
	CAQ->enqueue(3);
	CAQ->enqueue(6);
	CAQ->enqueue(2);
	CAQ->enqueue(1);
	CAQ->printQueue();

	// Wierd Part
	CAQ->enqueue(5);
	CAQ->printQueue();

	cout << endl;
	
	cout << "Removed:: " << CAQ->dequeue() << endl;
	CAQ->printQueue();
	cout << "Removed:: " << CAQ->dequeue() << endl;
	CAQ->printQueue();
	cout << "Removed:: " << CAQ->dequeue() << endl;
	CAQ->printQueue();
	cout << "Removed:: " << CAQ->dequeue() << endl;
	CAQ->printQueue();

	CAQ->dequeue();

    return 0;
}

