#include "stdafx.h"
#include "Book.h"

// Default Constructor
Book::Book() {
	ISBN = 0;
	name = "";
}

// Virtual Destructor
Book::~Book() {
}

// Constructor with arguments ISBN 'i' and Name 's'
Book::Book(int i, std::string s) {
	ISBN = i;
	name = s;
}

// Returns the ISBN Number of the Book
int Book::getISBN() {
	return ISBN;
}

// Returns the name of the book
std::string Book::getName() {
	return name;
}

// Sets the ISBN of the book to int i
void Book::setISBN(int i) {
	ISBN = i;
}

// Sets the ISBN of the book to string s
void Book::setName(std::string s){
	name = s;
}
