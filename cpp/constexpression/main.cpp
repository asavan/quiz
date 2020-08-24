#include <iostream>
#include <array>

constexpr size_t ndc_helper(const char* str, const size_t length, size_t pos,    
                            std::array<bool, 256> seen_chars) {                  
  return (((pos != 0) ? (seen_chars[(unsigned char)str[pos - 1]] = true) : true),               
          (pos < length))                                                        
           ? (!seen_chars[(unsigned char)str[pos]] +                             
                 ndc_helper(str, length, pos + 1, seen_chars))                   
           : 0;                                                                  
}

template <size_t L>
constexpr size_t ndc(const char (&str)[L]) {
  return (L == 1) ? 0 : ndc_helper(str, L - 1, 0, std::array<bool, 256>());
}

int main() {
  constexpr char str[] = "mamamylaramy" ;
  constexpr int size = ndc(str);
  int array[size];  
  std::cout << size << std::endl;
  return 0;
}
