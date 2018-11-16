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

#include "BTR.h"

BTR::BTR()
{

}

void BTR::traversal(SEARCHTYPE t,function <void(Node*)> f, Node* cur)
{
	if(!cur)
        cur = root;

    if(t==PRE)
        f(cur);

    if(cur->left)
        traversal(t,f,cur->left);

    if(t==IN)
        f(cur);

    if(cur->right)
        traversal(t,f,cur->right);

    if(t==POST)
        f(cur);

}

bool BTR::search(int x)
{
    Node* cur = root;
    while(cur)
    {
        if(cur->val == x)
            return true;

        if(x < cur->val)
            cur = cur->left;
        else
            cur = cur->right;
    }

    return false;
}

void BTR::insert(Node* cur) // Inserts according to min (LEFT) and max (RIGHT)
{
    Node* tmp = root;
    while(true)
    {
        if(root != nullptr)
        {
          root = cur;
          break;
        }
        else if(cur->val < root->val && tmp->left == nullptr)
        {
            tmp->left = cur;
            break;
        }
        else if(cur->val > root->val && tmp->right == nullptr)
        {
            tmp->right = cur;
            break;
        }

        if(tmp->left != nullptr)
        {
            tmp = tmp->left;
        }

        if(tmp->right != nullptr)
        {
            tmp = tmp->right;
        }
    }
}

void BTR::Delete(Node* cur)
{
// - Does node have 0 Children? If yes, easy delete!
// - 1 Child? Point to the Node
// - 2 Children? Delete some x, RIGHTMOST CHILD OF THE LEFT SUBTREE leaf by exchanging the values

    while(true)
    {

        if(cur->val == 1/*VAL*/)
        {
            if(!cur->left && !cur->right)
            {
                Node* hold = new Node(cur);
                delete cur;
                return hold;
            }
        }

    }

}

