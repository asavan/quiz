package meta.maze;

import java.util.*;

public class Maze {
    public record Point(int x, int y) {}
    public static List<Point> path(Integer[][] map, Point begin, Point end) {
        List<List<Integer>> visited = new ArrayList<>(map.length);
        for (int i = 0; i < map.length; ++i) {
            visited.add(new ArrayList<>(Arrays.asList(map[i])));
        }
        List<Point> result = new ArrayList<>();
        Queue<Point> q = new ArrayDeque<>();
        q.add(begin);
        visited.get(begin.x).set(begin.y, 1);
        while (!q.isEmpty()) {
            Point p = q.poll();
            result.add(p);
            if (p == end) {
                break;
            }
            for (int dx = -1; dx <= 1; ++dx) {
                for (int dy = -1; dy <= 1; ++dy) {
                    if (Math.abs(dx) + Math.abs(dy) != 1) {
                        continue;
                    }
                    if (!inBounds(p.x + dx, p.y + dy, map[0].length, map.length)) {
                        continue;
                    }
                    if (visited.get(p.y + dy).get(p.x + dx) == 1) {
                        continue;
                    }
                    visited.get(p.y + dy).set(p.x + dx, 1);
                    q.add(new Point(p.x + dx, p.y + dy));
                }
            }
        }
        if (result.getLast() == end) {
            return result;
        }
        return Collections.emptyList();
    }

    private static boolean inBounds(int x, int y, int maxX, int maxY) {
        return x >= 0 && y >= 0 && x < maxX && y < maxY;
    }
}
