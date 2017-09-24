#pragma once
#include <string>

class Book
{
public:
	// Constructors / Destructors
	Book();
	Book(int, std::string);
	~Book();

	// Get / Set Functions
	int getISBN();
	std::string getName();
	void setName(std::string);
	void setISBN(int);

private:
	// Book's ISBN
	int ISBN;
	// Book's Name
	std::string name;
};

