#pragma once
#include <string>

class Book
{
public:
	Book();
	Book(int, std::string);
	~Book();

	int getISBN();
	std::string getName();

	void setName(std::string);
	void setISBN(int);

private:
	int ISBN;
	std::string name;
};

