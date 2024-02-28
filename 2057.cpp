#include <stdio.h>
#include <iostream>
int main() {
    int hour, diff, tz;
    std::cin >> hour >> diff >> tz;
    if((hour + diff) > 23) hour-=24;
    if(((hour+diff)+tz) <= 0) printf("%d\n", ((hour+diff)+24)+tz);
    if(((hour+diff)+tz) == 24) printf("%d\n", 0);
    else printf("%d\n", (hour+diff)+tz);
    return 0;
}