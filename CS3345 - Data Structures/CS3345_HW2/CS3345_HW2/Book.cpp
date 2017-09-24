#include "stdafx.h"
#include "Book.h"


Book::Book() {
	ISBN = 0;
	name = "";
}

Book::Book(int i, std::string s) {
	ISBN = i;
	name = s;
}

int Book::getISBN() {
	return ISBN;
}

std::string Book::getName() {
	return name;
}

void Book::setISBN(int i) {
	ISBN = i;
}

void Book::setName(std::string s){
	name = s;
}

Book::~Book() {
}
