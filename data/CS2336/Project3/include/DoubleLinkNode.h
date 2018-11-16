#ifndef DOUBLELINKNODE_H
#define DOUBLELINKNODE_H

#include <BaseNode.h>


class DoubleLinkNode : public BaseNode
{
    public:
        DoubleLinkNode();
        DoubleLinkNode(const DoubleLinkNode*);
        DoubleLinkNode *next;
        DoubleLinkNode *prev;

        void setNext(DoubleLinkNode *);
        void setPrev(DoubleLinkNode *);

        DoubleLinkNode* getNext();
        DoubleLinkNode* getPrev();

    protected:

    private:
};

#endif // DOUBLELINKNODE_H
