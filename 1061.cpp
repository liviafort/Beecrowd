#include <iostream>
#include <string>

int extrairValor(std::string str) {
    return std::stoi(str.substr(str.find_first_of("0123456789")));
}

int main() {
    std::string beg, fin, hour_beg, hour_fin;
    std::getline(std::cin, beg);
    std::getline(std::cin, hour_beg);
    std::getline(std::cin, fin);
    std::getline(std::cin, hour_fin);

    int dias = extrairValor(fin) - extrairValor(beg);
    int horas = std::stoi(hour_fin) - std::stoi(hour_beg);
    int minutos = std::stoi(hour_fin.substr(5, 2)) - std::stoi(hour_beg.substr(5, 2));
    int segundos = std::stoi(hour_fin.substr(10, 2)) - std::stoi(hour_beg.substr(10, 2));

    if(segundos < 0) {
      segundos+=60;
      minutos-=1;
    }
    if(minutos < 0) {
      minutos+=60;
      horas-=1;
    }
    if(horas < 0) {
      horas+=24;
      dias-=1;
    }

    std::cout << dias << " dia(s)\n" << horas << " hora(s)\n" << minutos << " minuto(s)\n" << segundos << " segundo(s)\n";

    return 0;
}