#ifndef BTR_H
#define BTR_H

#include <functional>


class Node
{
    public:
        Node() {val = 41;}
        Node(int t) {val = t;}
        int val = 0;
        Node* left = nullptr;
        Node* right = nullptr;
};


class BTR
{
    public:
        BTR();
        Node* root = nullptr;
        enum SEARCHTYPE {PRE,IN,POST};
        void traversal(SEARCHTYPE t,std::function <void(Node*)> f,Node* cur); //<== C++11 Code
        bool search(int);
        void insert(Node*);
        void Delete(Node*);

    protected:

    private:
};

#endif // BTR_H
