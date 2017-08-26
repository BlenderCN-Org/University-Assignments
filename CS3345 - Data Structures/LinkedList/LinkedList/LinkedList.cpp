#include "stdafx.h"
#include "LinkedList.h"

// Default Constr
LinkedList::LinkedList() {
	head = nullptr;
	tail = nullptr;
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
}

Node* LinkedList::getTail() {
	return tail;
}

void LinkedList::setTail(Node* cur) {
	tail = cur;
}

// Returns true of the key if found within the list
bool LinkedList::has(std::string key) {
	Node* tmp = head;

	while (tmp != nullptr) {
		if (tmp->getString() == key) { return true; }
		tmp = tmp->getNext();
	}

	return false;
}

// Inserts a new NODE into the list at the END
void LinkedList::insert(Node* cur) {
	// Empty List
	if (head == nullptr) { setHead(cur); }

	//List has ONLY Head
	else if (head->getNext() == nullptr) { head->setNext(cur); setTail(cur); }

	// List has head and tail already
	else { tail->setNext(cur); setTail(cur); }
}

// Inserts a newly created NODE into the list at the END
void LinkedList::insert(std::string key) {
	Node* tmp = new Node(key);

	// Empty List
	if (head == nullptr) { setHead(tmp); }

	//List has ONLY Head
	else if (head->getNext() == nullptr) { head->setNext(tmp); setTail(tmp); }

	// List has head and tail already
	else { tail->setNext(tmp); setTail(tmp); }
}

// Removes specified and Resizes the list
void LinkedList::remove(std::string key) {

	Node* tmp = head;

	if (head->getString() == key && head->getNext() == nullptr) {
		head = nullptr;
	}
	else {
		while (tmp->getNext() != nullptr) {
			if (tmp->getNext()->getString() == key) {
				tmp->setNext(tmp->getNext()->getNext());
			}
			else {
				tmp = tmp->getNext();
			}
		}
	}

}

// Prints out the contents of the list
void LinkedList::print() {
	Node* tmp = head;

	while (tmp != nullptr) {
		std::cout << tmp->getString() << " ";
		tmp = tmp->getNext();
	}
	std::cout << std::endl;
}

