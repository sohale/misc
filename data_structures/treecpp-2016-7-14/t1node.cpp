#include <iostream>
#include <vector>

struct t1node {
    t1node* child[2];
    std::string data;

    t1node(std::string data){
        this->data = data;
        child[0] = NULL;
        child[1] = NULL;
    }
};

//template <typename T>
std::vector<t1node*> garbage_collector;
t1node* new_node(std::string data) {
    t1node* n = new t1node(data);
}

void gc() {
    int counter = 0;
    for(auto it = garbage_collector.begin(); it < garbage_collector.end(); it++) {
        delete *it;
        counter++;
    }
    std::cout << counter << " garbages collected" << std::endl;
}

void traverse_inorder(t1node* root) {
    for(int ci = 0; ci < 2; ci++){
        traverse_inorder(root->child[0]);
        if(ci == 0){
            //visit
            std::cout << " " << root->data << " ";
        }
    }
}

int main() {
    t1node* root = new_node("1");
    //root.add_child( new_node("2"), 0);
    root->child[0] = new_node("2");

    gc();
}
