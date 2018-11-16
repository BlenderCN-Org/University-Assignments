#ifndef NODE_H
#define NODE_H


class node
{
    public:
        node();
        node* next;
        node* prev;
        node(char c){c = x;}
        node* getNext(){return next;}
        node* getPrev(){return prev;}
        char getChar(){return x;}
        virtual ~node();
        char x;

    protected:

    private:
};

#endif // NODE_H
