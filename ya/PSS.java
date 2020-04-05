/**
 * Created by asavan on 22.09.2019.
 */

import java.util.Scanner;

public class PSS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n <= 0) {
            return;
        }

        helper("", 0, 0, n);
    }

    private static boolean helper(String str, int l, int r, int total) {
        if (l == total && r == total) {
            System.out.println(str);
            return true;
        }
        boolean res = false;
        if (l < total) {
            res |= helper(str + "(", l + 1, r, total);
        }
        if (r < l) {
            res |= helper(str + ")", l, r + 1, total);
        }
        return res;
    }
}
    /*
    """
Дано целое число n. Требуется вывести все правильные скобочные последовательности длины 2 ⋅ n,
упорядоченные лексикографически (см. https://ru.wikipedia.org/wiki/Лексикографический_порядок).
В задаче используются только круглые скобки.
Желательно получить решение, которое работает за время, пропорциональное общему количеству
правильных скобочныхпоследовательностей в ответе, и при этом использует объём памяти,
пропорциональный n.
"""
import sys
n = int(sys.stdin.readline().strip())


def foo(s, l, r, pairs):
    if l == pairs and r == pairs:
        print(s)
    else:
        if l < pairs:
            foo(s + '(', l + 1, r, pairs)
        if r < l:
            foo(s + ')', l, r + 1, pairs)


foo('', 0, 0, n)
     */

