#pragma once
class Window {
public:
	virtual void draw() = 0;
};

class Simple_Window;
class Window_With_Border;
class Window_With_Vertical_Scrollbar;
class Window_With_Horizontal_Scrollbar;

Window * createSimpleWindow();
Window * createWindowWithBorder(Window *w);

class Window_With_Border : public Window
{
public:
	Window_With_Border(Window* w);
	virtual ~Window_With_Border();
	virtual void draw() override;
private:
	Window* _w;
};
