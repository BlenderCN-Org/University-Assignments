#include "stdafx.h"
#include "CircularArrayQueue.h"


CircularArrayQueue::CircularArrayQueue(int s) {
	read = write = -1; 	
	size = s;
	arr = new Node[size];
}


CircularArrayQueue::~CircularArrayQueue() {
}
