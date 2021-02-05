package ya.a1;

import java.util.Iterator;

/**
 * Created by asavan on 05.02.2021.
 */
class MultiIterator<T> implements Iterator<T> {
    private final Iterator<T> a;
    private final Iterator<T> b;
    private boolean isFirst;

    public MultiIterator(Iterator<T> a, Iterator<T> b) {
        this.a = a;
        this.b = b;
        isFirst = true;
    }

    public T next() {
        if (a.hasNext()) {
            return a.next();
        }
        isFirst = false;
        return b.next();
    }
    public boolean hasNext() {
        return a.hasNext() || b.hasNext();
    }
    public void remove() {
        if (isFirst) {
            a.remove();
        } else {
            b.remove();
        }
    }
}
