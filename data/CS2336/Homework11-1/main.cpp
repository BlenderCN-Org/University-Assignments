//Matthew McMillian 1162 #13
#include <iostream>
#include <string>
#include <BaseNode.h>
#include <circularLinkedList.h>
using namespace std;

int main()
{
    circularLinkedList h = new circularLinkedList();
    h.add("Dent");
    h.add("Hewey");
    h.add("Jacob");

    cout << h.isEmpty() << endl;
    cout <<h.listLength() << endl;
    h.printList();
    h.del();
    BaseNode x = new BaseNode("Hewey");
    cout << h.findL(x);
}
