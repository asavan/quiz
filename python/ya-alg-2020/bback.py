from math import inf
from bisect import bisect_left, insort
def B():
    class SortedList:
        def __init__(self):
            self._items = []
            self.len = 0

        def add(self, item):
            insort(self._items, item)
            self.len += 1

        def __contains__(self, item):
            i = bisect_left(self._items, item)
            return i != self.len and self._items[i] == item

        def __getitem__(self, item):
            return self._items[item]

    with open('input.txt', 'r') as f_in, open('output.txt', 'w') as f_out:
        n, x, k = map(int, f_in.readline().split())
        t_list = list(map(int, f_in.readline().split()))
        t_list.sort()

        t_unique = SortedList()
        t0 = inf
        for t in t_list:
            if t > t0:
                break
            new_t = t % x
            if new_t in t_unique:
                continue
            t_unique.add(new_t)
            k += t // x
            k1, k2 = divmod(k-1, t_unique.len)
            t0 = x * k1 + t_unique[k2]
        f_out.write(f'{t0}')
B()
