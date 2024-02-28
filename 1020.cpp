#include <stdio.h>
#include <iostream>
int main() {
    int age, restYear, month;
    std::cin >>age;
    restYear = age % 365;
    month = restYear/30;
    printf("%d ano(s)\n%d mes(es)\n%d dia(s)\n", age/365, month, restYear%30);
    return 0;
}