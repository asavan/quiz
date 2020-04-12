#include <string>
#include <iostream>

int main() {
	std::string s = "abcde";
	s[2] = 'z';
	std::cout << s << std::endl;
	return 0;
}