#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
#include <vector>
#include <cstdlib>
#include <sstream>
#include <iomanip>
#include "BaseNode.h"




using namespace std;

BaseNode::BaseNode() {
    name = "";
    area = 0.0;
}
BaseNode::BaseNode(string s, float f) {
    name = s;
    area = f;
}
BaseNode::~BaseNode() {

}
string BaseNode::getName() {
    return name;
}

float BaseNode::getArea() {
    return area;
}
void BaseNode::setArea(float f){
    area = f;
}
void BaseNode::setName(string s){
    name = s;
}
