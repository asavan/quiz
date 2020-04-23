#include <string>
#include <vector>
#include <iostream>

int main() {
	std::string s = "abcde";
	s[2] = 'z';
	std::cout << s << std::endl;
	
	for (char c : s) {
		std::cout << c << "|\t";
	}
	std::cout << std::endl;
	
	for (char c : "abcde") {
		std::cout << c << "|\t";
	}
	std::cout << std::endl;
	
	std::vector<char> v(s.begin(), s.end());
	std::cout << v.size() << std::endl;

	std::cout << v << std::endl;
	return 0;
}
