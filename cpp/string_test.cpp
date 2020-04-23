#include <string>
#include <iostream>

int main() {	
	std::string s = "abc";
	int count = 0;
	for (char c : s) {
		++count;
	}
	
	int count2 = 0;
	for (char c : "abc") {
		++count2;
	}
	
	std::cout << (count == count2) << std::endl;

	return 0;
}
