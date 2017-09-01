
// ArraysVsListsQueueandStacks.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "CircularArrayQueue.h"

using namespace std;

int main()
{	
	CircularArrayQueue *CAQ = new CircularArrayQueue(5);
	// Testing EMPTY
	CAQ->dequeue();

	// Testing Enqueue
	CAQ->enqueue(3);
	CAQ->enqueue(6);
	CAQ->enqueue(2);
	CAQ->enqueue(1);
	CAQ->printQueue(); 

	// Testing FULL
	CAQ->enqueue(5);

	// Testing Removal
	cout << "Removed:: " << CAQ->dequeue() << endl;
	CAQ->printQueue();
	cout << "Removed:: " << CAQ->dequeue() << endl;
	CAQ->printQueue();
	cout << "Removed:: " << CAQ->dequeue() << endl;
	CAQ->printQueue();
	cout << "Removed:: " << CAQ->dequeue() << endl;
	CAQ->printQueue();
	CAQ->dequeue();

	// Other Testing
	CAQ->enqueue(4);
	CAQ->enqueue(2);
	CAQ->dequeue();
	CAQ->enqueue(7);
	CAQ->printQueue();

    return 0;
}

