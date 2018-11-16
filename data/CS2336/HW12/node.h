#ifndef NODE_H
#define NODE_H


class node
{
    public:
        node() {val = 0;}
        node(int x) {val = x;}
        int val;
        node* left = nullptr;
        node* right = nullptr;

        int getVal(){return val;}
        void setVal(int x){val = x;}


    protected:

    private:
};

#endif // NODE_H
