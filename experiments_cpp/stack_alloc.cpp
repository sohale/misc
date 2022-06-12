/*
Tested in http://codepad.org/p8Q8bEpp

The idea is to allocate space in Stack and use it as a piece of memory for operations.
Result:
Fails as this space will not be "contiguous".
Also, why is end - begin negative?

Output:

call:5 (0)from -420204. call:4 (-12)from -420204. call:3 (-24)from -420204. call:2 (-36)from -420204. call:1 (-48)from -420204. -48
esc:
ret:2 0
ret:3 0
ret:4 0
ret:5 0

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
    cout << "(" << (int)(&v - first_v) << ")from " << (int)(first_v)<< ". ";

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

