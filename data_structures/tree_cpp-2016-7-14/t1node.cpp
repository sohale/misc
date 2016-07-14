#include <iostream>
#include <vector>

struct t1node {
    t1node* child[2];  // 8 byte for each
    std::string data;    // 8 bytes!

    t1node(std::string data_){
        data = data_;
        child[0] = NULL;
        child[1] = NULL;
    }
};

//template <typename T>
std::vector<t1node*> garbage_collector;

t1node* new_node(std::string data) {
    t1node* n = new t1node(data);
    garbage_collector.push_back(n);
    std::cout << "new: " << n << std::endl;
    return n;
}

void gc() {
    int counter = 0;
    for(auto it = garbage_collector.begin(); it < garbage_collector.end(); it++) {
        std::cout << "delete: " << *it << std::endl << std::flush;
        //delete *it;
        counter++;
    }
    std::cout << counter << " garbages collected." << std::endl << std::flush;
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
    std::cout << "root: " << sizeof(*root) << std::endl << std::flush;
    //root->child[0]
    t1node* z = new_node("2");
    root->child[0] = z;
    //(*root).child[0] = z;

    gc();
}
