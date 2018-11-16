#include "binarysearchtree.h"
#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include <functional>

using namespace std;


void BinarySearchTree::traversal(string t,node* cur)
{
    if(t == "PRE")
    {
        if(cur)
        {
            cout << cur->getVal() << " ";
            traversal(t,cur->left);
            traversal(t,cur->right);
        }
    }

    if(t == "IN")
    {
        if(cur)
        {
            traversal(t,cur->left);
            cout << cur->getVal() << " ";
            traversal(t,cur->right);
        }
    }

    if(t == "POST")
    {
        if(cur)
        {
            traversal(t,cur->left);
            traversal(t,cur->right);
            cout << cur->getVal() << " ";
        }
    }
}

void BinarySearchTree::traversal(string t,node* cur,BinarySearchTree& x)
{
    if(t == "PRE")
    {
        if(cur)
        {
            x.insert(cur);
            cout << cur->getVal() << " ";
            traversal(t,cur->left);
            traversal(t,cur->right);
        }
    }

    if(t == "IN")
    {
        if(cur)
        {
            traversal(t,cur->left);

            x.insert(cur);
            cout << cur->getVal() << " ";

            traversal(t,cur->right);
        }
    }

    if(t == "POST")
    {
        if(cur)
        {
            traversal(t,cur->left);
            traversal(t,cur->right);

            x.  insert(cur);
            cout << cur->getVal() << " ";

        }
    }
}

void BinarySearchTree::insert(node* cur)
{

    node* tmp = root;
    while(true)
    {
        if(root == nullptr)
        {
          root = cur;
          break;
        }
        else if(cur->val < tmp->val && tmp->left == nullptr)
        {
            tmp->left = cur;
            break;
        }
        else if(cur->val > tmp->val && tmp->right == nullptr)
        {
            tmp->right = cur;
            break;
        }
        if(cur->val < tmp-> val && tmp->left != nullptr)
        {
            tmp = tmp->left;
        }

        if(cur->val > tmp-> val && tmp->right != nullptr)
        {
            tmp = tmp->right;
        }
    }

}


int BinarySearchTree::getHeight(node* cur)
{
    if (cur == nullptr)
        return -1;

    int lefth = getHeight(cur->left);
    int righth = getHeight(cur->right);

    if (lefth > righth)
        return lefth + 1;
    else
        return righth + 1;

}

int BinarySearchTree::getLeaves(node* cur)
{
    if( cur == nullptr )
        return 0;
    if( cur->left == nullptr && cur->right == nullptr )
        return 1;
    else
        return getLeaves(cur->left) + getLeaves(cur->right);

}

