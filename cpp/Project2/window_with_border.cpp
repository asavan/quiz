#include "window.h"
#include <iostream>

class Window_With_Border : public Window
{
public:
	Window_With_Border(Window* w);
	virtual ~Window_With_Border();
	virtual void draw() override;
private:
	Window* _w;
};


Window_With_Border::Window_With_Border(Window* w) : _w(w)
{
}

Window_With_Border::~Window_With_Border()
{
	std::cout << "Window_With_Border deleted" << std::endl;
}

void Window_With_Border::draw() {
	std::string border = " -------- ";
	std::cout << border.c_str() << std::endl;
	std::cout << "| ";
	if (_w != nullptr) {
		_w->draw();
	}
	std::cout << " |" << std::endl;
	std::cout << border.c_str() << std::endl;

}

Window * createWindowWithBorder(Window *w)
{
	return new Window_With_Border(w);
}
