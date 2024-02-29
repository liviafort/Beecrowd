#include <stdio.h>
#include <iostream>
#include <vector>
#include <iterator>
#include <algorithm>
int main() {
    int size, number; std::vector<int> even, odd;
    std::cin >> size;
    for (int i = 0; i < size; ++i) {
        std::cin >> number;
        if (number % 2 == 0) even.push_back(number);
        else odd.push_back(number);
    }
    std::sort(even.begin(), even.end(), std::less<int>());
    std::sort(odd.begin(), odd.end(), std::greater<int>());
    std::copy(std::begin(even), std::end(even), std::ostream_iterator<int>(std::cout, " "));
    std::copy(std::begin(even), std::end(even), std::ostream_iterator<int>(std::cout, " "));
    return 0;
}