#include <stdio.h>
#include <iostream>
int main() {
    int a, b, c, d, sum;
    std::cin >>a>>b>>c>>d;
    sum = (a+b+c+d)-3;
    printf("%d\n", sum);
    return 0;
}