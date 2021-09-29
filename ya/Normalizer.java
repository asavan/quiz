import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.assertEquals;

public class Normalizer {

    private static final String LEVEL_UP = "..";
    private static final String CURRENT_LEVEL = ".";
    private static final String ROOT = "";
    private static final String DELIMITER = "/";

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
        assertEquals("../b", normalize("a/../../b"));
        assertEquals("/documents/root", normalize("/////documents/root/"));
        assertEquals("/etc", normalize("/////documents/root/.././../etc"));
        assertEquals("etc", normalize("./config/../etc"));
        assertEquals("/etc", normalize("/etc/"));
        assertEquals("foo", normalize("foo"));
        assertEquals("bar", normalize("bar"));
        assertEquals(".", normalize(""));
        assertEquals("/zog", normalize("/../../../zog"));
        assertEquals("../../../zog", normalize("./../../../zog"));
        assertEquals(".", normalize("."));
        assertEquals(".", normalize("zog/.."));
        assertEquals("/", normalize("//"));
        assertEquals("../b", normalize("a/..///../b"));
        assertEquals("foo/bar", normalize("foo/./bar"));
    }

    @Test
    public void test2() {
        assertEquals("/foo/bar/baz/asdf", normalize2("/foo/bar//baz/asdf/quux/.."));
        assertEquals("../b", normalize2("a/../../b"));
        assertEquals("etc", normalize2("./config/../etc"));
        assertEquals("/etc", normalize2("/etc/"));
        assertEquals("etc", normalize2("etc/"));
        // assertEquals("/documents/root", normalize2("\\\\\\\\\\\\documents\\root\\"));
        // assertEquals("/etc", normalize2("/////documents/root/.././../etc"));
    }

    private static String normalize(String path) {
        if (path.length() == 0) {
            return CURRENT_LEVEL;
        }
        String[] paths = path.split(DELIMITER);
        boolean isRoot = path.charAt(0) == '/';

        Deque<String> normalized = new ArrayDeque<>();
        for (String subpath : paths) {
            processOneLevel(normalized, subpath, isRoot);
        }
        if (isRoot) {
            normalized.addFirst(ROOT);
            if (normalized.size() == 1) {
                normalized.addFirst(ROOT);
            }
        }
        if (normalized.isEmpty()) {
            return CURRENT_LEVEL;
        }
        return String.join(DELIMITER, normalized);
    }

    private static void processOneLevel(Deque<String> normalized, String subpath, boolean isRoot) {
        if (subpath.equals(CURRENT_LEVEL) || subpath.equals(ROOT)) {
            return;
        }
        if (subpath.equals(LEVEL_UP)) {
            if (!normalized.isEmpty() && !LEVEL_UP.equals(normalized.peekLast())) {
                normalized.pollLast();
                return;
            }
            if (isRoot) {
                return;
            }
        }
        normalized.addLast(subpath);
    }

    private static String normalize2(String path) {
        Path p = Paths.get(path);
//        for (Path path1 : p) {
//            System.out.println(path1.toString());
//        }
        return p.normalize().toString().replace('\\', '/');
    }
}
