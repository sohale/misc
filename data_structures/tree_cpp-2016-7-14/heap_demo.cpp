/* Copyright sohail

*/
#include <iostream>
#include <vector>
#include <cassert>
#include <cmath>
#include <algorithm>

typedef float data_t;

std::vector<data_t> heap;
typedef std::vector<data_t>::size_type index_t;

index_t binary_digits(index_t i) {
    assert(i > 0);
    int counter = 0;
    while (i > 0) {
        counter++;
        i = i / 2;
    }
    return counter;
}

index_t binary_digits_l(index_t i) {
    assert(i > 0);
    const float EPS = 0.000001;
    index_t counter = static_cast<int>(
        EPS + std::log(i)/std::log(2.0))+1;  // Why called static?
    return counter;
}

void testcase_shl(index_t n) {
    assert(binary_digits(1u << n) == n + 1);
    if (n > 0) {
        assert(binary_digits((1u << n) - 1) == n + 1 - 1);
    }
}


void test_1() {
    // assert(binary_digits(0) == 0);
    assert(binary_digits(1) == 1);
    assert(binary_digits(2) == 2);
    assert(binary_digits(3) == 2);
    assert(binary_digits(4) == 3);
    assert(binary_digits(7) == 3);
    assert(binary_digits(8) == 4);
    assert(binary_digits(1 << 8) == 8+1);
    assert(binary_digits((1 << 8) - 1) == 8+1 - 1);
    assert(binary_digits(1 << 10) == 10+1);
    assert(binary_digits((1 << 10) - 1) == 10+1 - 1);
    for ( int i = 0; i < 14; i++ )
        testcase_shl(i);
    for ( int x = 1; x < 140; x++ ) {
        // std::cout << " x= " << x << "  ";
        assert(binary_digits(x) == binary_digits_l(x));
    }
}

inline index_t get_row(int i) {
    index_t row = binary_digits(i) - 1;
    return row;
}
// 0 | 1,2 | 3,4,5,6  |  7,8,9,10,11,12,13,14, ...
// 0,1,3,7,15, ... : 2^row-1
// i+1
// 1,2,4,8,...
int father(index_t i) {
    // 2^row-1 <= i
    // 2^row <= i+1
    // 2^row < i+2
    //index_t row = binary_digits(i+1) - 1;
    index_t row = get_row(i);
    assert(row >= 0);
    if (row == 0)
        return -1;
    assert(row > 0);
    const index_t _1u = static_cast<index_t>(1);
    // index_t row_start = (_1u << row) - 1;

    index_t father_row_start = (_1u << (row-1)) - 1;

    return father_row_start;
}

void test_2() {
    // assert(father(0));
    assert(father(0+1) == -1);

    assert(father(1+1) == 0);
    assert(father(2+1) == 0);

    assert(father(3+1) == 1);
    assert(father(4+1) == 1);
    assert(father(5+1) == 1);
    assert(father(6+1) == 1);

    assert(father(7+1) == 3);
}
/// a<=n a<n+1


int father(int i) {
    assert(i > 0);
    return i / 2;
}

bool is_right_child(int i) {
    assert(i >= 1);
    return (i - 1) % 2 == 1;
}

void test_3() {
    assert(is_right_child(1) == false);
}

auto& access(int index) {
    assert(index >= 1);
    assert(index <= static_cast<int>(heap.size()));
    return heap[index-1];
}

void balance(int unbalanced_idx) {
}

void insert(data_t x) {
    heap.push_back(x);
    int active_idx = heap.size() - 1 + 1;

    balance(active_idx);

    int fath_idx = father(active_idx);
    std::cout << "father: ["<< fath_idx << "] " << "active: [" << active_idx << "] ";
    /*
    //fath_elem = (... . .........
    fath_elem =
    if (is_right_child(active_idx)) {
        fath_el ;  ..........
    }
    */
    if( access(fath_idx) < access(active_idx) ){
        std::cout << "swapping" << fath_idx << "," << active_idx << std::endl;
        std::swap( access(fath_idx), access(active_idx) );
    }
}


void show_heap() {
    std::cout << std::endl;
    index_t last_row = -1;
    for(int i=0;i<heap.size();i++) {
        index_t row = get_row(i+1);
        if(row != last_row)
            std::cout << std::endl;

        std::cout << access(i+1) << ", ";

        last_row = row;
    }
    std::cout << std::endl;
}

int main() {
    test_1();
    test_2();

    heap.push_back(200);
    heap.push_back(100);
    heap.push_back(2);
    heap.push_back(1);
    //show_heap();
    insert(100);
    //show_heap();
    insert(101);
    show_heap();
    std::cout << "OK" << std::endl;
}
