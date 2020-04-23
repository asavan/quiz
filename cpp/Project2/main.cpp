#include "window.h"

int main(int argc, char * argv[]) {
	Window * w = createSimpleWindow();
	Window* w2 = createWindowWithBorder(w);
	w2->draw();
	delete w2;
	delete w;
	return 0;
}
