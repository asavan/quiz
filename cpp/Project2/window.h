#pragma once
class Window {
public:
	virtual void draw() = 0;
	virtual ~Window() {};
};

class Simple_Window;
class Window_With_Border;
class Window_With_Vertical_Scrollbar;
class Window_With_Horizontal_Scrollbar;

Window * createSimpleWindow();
Window * createWindowWithBorder(Window *w);
