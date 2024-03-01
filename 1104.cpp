#include <stdio.h>
#include <iostream>

int call(int number, int valor){
  std::cout << number/valor << " nota(s) de R$ " << valor << ",00\n";
  return number-((number/valor)*valor);
}

int main() {
    int number;
    std::cin >> number;
    std::cout << number << "\n";
    number = call(number, 100);
    number = call(number, 50);
    number = call(number, 20);
    number = call(number, 10);
    number = call(number, 5);
    number = call(number, 2);
    number = call(number, 1);
    return 0;
}