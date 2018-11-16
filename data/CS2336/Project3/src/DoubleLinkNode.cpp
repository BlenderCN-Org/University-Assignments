#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>




using namespace std;

#include "DoubleLinkNode.h"

DoubleLinkNode::DoubleLinkNode(){
    next = nullptr;
    prev = nullptr;
}

DoubleLinkNode::DoubleLinkNode(const DoubleLinkNode* tmp) {
    next = nullptr;
    prev = nullptr;
}

void DoubleLinkNode::setNext(DoubleLinkNode *x){
    next = x;
}

void DoubleLinkNode::setPrev(DoubleLinkNode *x){
    prev = x;
}
DoubleLinkNode* DoubleLinkNode::getNext(){
    return next;
}

DoubleLinkNode* DoubleLinkNode::getPrev(){
    return prev;
}

