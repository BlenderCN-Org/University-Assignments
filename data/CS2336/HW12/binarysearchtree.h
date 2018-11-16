#ifndef BINARYSEARCHTREE_H
#define BINARYSEARCHTREE_H
#include <functional>
#include <node.h>


class BinarySearchTree
{
    public:
        BinarySearchTree() {root = nullptr;}
        node* root = nullptr;
        int c = 0;
        enum SEARCHTYPE {PRE,IN,POST};
        void traversal(std::string,node* cur); //<== C++11 Code
        void traversal(std::string,node*,BinarySearchTree&);
        void insert(node*);
        void remove(node*);
        int getHeight(node*);
        int getLeaves(node*);


    protected:

    private:
};

#endif // BINARYSEARCHTREE_H
