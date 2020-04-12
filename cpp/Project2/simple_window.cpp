#include <iostream>
#include "window.h"

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
}

void SimpleWindow::draw() {
	std::cout << "Window";
}


Window * createSimpleWindow() {
	return new SimpleWindow();
}
