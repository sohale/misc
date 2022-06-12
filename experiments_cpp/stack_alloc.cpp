/*
Tested in http://codepad.org/p8Q8bEpp

The idea is to allocate space in Stack and use it as a piece of memory for operations.
Result:
Fails as this space will not be "contiguous".
Also, why is end - begin negative?
*/

#include <iostream>

// #include <cstddef> // nullptr
// nullptr <- NULL

void f(int n, volatile int* first_v) {
    volatile int v = 0;
    // int v = 0;
    cout << "call:"<<n<<" ";
    if (first_v == NULL) {
        first_v = &v;
    }
    if (n<=1) {
        // Now you have the first one
        volatile int* begin = first_v;
        volatile int* end = &v;
        cout << (int)(end - begin) << std::endl;
        // -48 !
        // It is not a continuous space
        if (begin == end) { cout << "(same)";}
        int ctr =0;
        for(volatile int*x = begin; x<=end; x++) {
           cout << ":" << *x << ":";
           *x = ctr++;
        }
        cout << "esc:"<<""<<std::endl;
        return;
    }
    f(n-1, first_v);
    cout << "ret:"<<n<<" " << v << std::endl;
}

int main() {
    f(5,NULL);
}
