#include "stdafx.h"
#include "CircularArrayQueue.h"

// Default Constructor
CircularArrayQueue::CircularArrayQueue(int s) {
	read = write = 0;
	size = s;
	arr = new Node[s];
}

// Virtual Destructor
CircularArrayQueue::~CircularArrayQueue() {

}


// Enqueues a value in the queue if there is room
void CircularArrayQueue::enqueue(int value) {
	if (read == (write + 1) % (size)) {
		printf("Queue is Full\n");
	}

	arr[write] = *new Node(value);
	write = (write + 1) % (size);
}

// Dequeues the queue, returning the first val
int CircularArrayQueue::dequeue() {

	if (read == write) {
		printf("Queue is Empty\n");
	}

	int val = arr[read].getValue();
	read = (read + 1) % (size);

	return val;
}

// Outprints the Array
void CircularArrayQueue::printQueue() {
	std::stringstream ss;	
	ss << "[";
	for (int i = read; ((i) % size) != write; i++) {
		ss <<  arr[i].toString();
		if(((i + 1) % size) != write)
			ss << " , ";    
	}
	ss << "]";
	std::cout << ss.str() << std::endl;
}
