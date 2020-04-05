import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.assertEquals;

public class Normalizer {
    public static void main(String[] args) {
        String a = normalize("/foo/bar//baz/asdf/quux/..");
        String b = normalize("a/../../b");
        String c = normalize("/////documents/root/.././../etc");
        String d = normalize("./config/../etc");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);

    }

    @Test
    public void test() {
        assertEquals("/foo/bar/baz/asdf", normalize("/foo/bar//baz/asdf/quux/.."));
        assertEquals( "../b", normalize("a/../../b"));
        assertEquals("/etc", normalize("/////documents/root/.././../etc"));
        assertEquals("etc", normalize("./config/../etc"));
    }

    private static String normalize(String path) {
        String LEVEL_UP = "..";
        String CURRENT_LEVEL = ".";
        String ROOT = "";
        String[] paths = path.split("/");
        boolean isRoot = false;

        if (paths.length == 0) {
            throw new RuntimeException("Wrong path");
        }
        if (ROOT.equals(paths[0])) {
            isRoot = true;
        }
        Deque<String> normalized = new ArrayDeque<>();
        for (String subpath : paths) {
            if (subpath.equals(CURRENT_LEVEL) || subpath.equals(ROOT)) {
                continue;
            }
            if (subpath.equals(LEVEL_UP)) {
                if (normalized.size() > 0) {
                    normalized.pollLast();
                } else {
                    normalized.add(LEVEL_UP);
                }

                continue;
            }
            normalized.add(subpath);
        }
        String answer = String.join("/", normalized);
        if (isRoot) {
            return "/" + answer;
        }
        return answer;
    }
}

