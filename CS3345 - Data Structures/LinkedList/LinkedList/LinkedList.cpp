#include "stdafx.h"
#include "LinkedList.h"

// Default Constr
LinkedList::LinkedList() {
	head = nullptr;
}

// Virtual Destr
LinkedList::~LinkedList() {
}

// Returns the memaddr of Node* head
Node* LinkedList::getHead() {
	return head;
}

// Sets the memaddr of Node* head to the memaddr of Node* cur
void LinkedList::setHead(Node* cur) {
	head = cur;
	delete cur;
}

// Returns true of the key if found within the list
bool LinkedList::find(std::string key) {
	Node* tmp = head;

	while (tmp != nullptr) {
		if (tmp->getString() == key) { return true; }
		tmp = tmp->getNext();
	}

	return false;
}


