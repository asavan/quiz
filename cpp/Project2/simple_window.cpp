#include "window.h"
#include <iostream>

class SimpleWindow : public Window
{
public:
	SimpleWindow();
	~SimpleWindow();
	virtual void draw() override;
	
private:

};

SimpleWindow::SimpleWindow()
{
}

SimpleWindow::~SimpleWindow()
{
	std::cout << "SimpleWindow deleted" << std::endl;
}

void SimpleWindow::draw() {
	std::cout << "Window";
}

Window * createSimpleWindow() {
	return new SimpleWindow();
}
