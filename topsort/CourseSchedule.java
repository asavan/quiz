package topsort;

import java.util.*;

// https://leetcode.com/problems/course-schedule/submissions/1341718088/
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        var visited = new ArrayList<>(Collections.nCopies(numCourses, 0));
        var graph = toMap(prerequisites);
        for (var pre : prerequisites) {
            boolean res = dfs(pre[0], visited, graph);
            if (!res) {
                return false;
            }
        }
        return true;
    }

    public Map<Integer, List<Integer>> toMap(int[][] prerequisites) {
        var map = new HashMap<Integer, List<Integer>>();
        for (var pre: prerequisites) {
            map.computeIfAbsent(pre[0], _k -> new ArrayList<>()).add(pre[1]);
        }
        return map;
    }

    public boolean dfs(int n, List<Integer> visited, Map<Integer, List<Integer>> prerequisites) {
        if (visited.get(n) == 2) {
            return true;
        }
        if (visited.get(n) == 1) {
            return false;
        }
        visited.set(n, 1);
        var list = prerequisites.get(n);
        if (list == null) {
            visited.set(n, 2);
            return true;
        }
        for (var next: list) {
            boolean res = dfs(next, visited, prerequisites);
            if (!res) {
                return false;
            }
        }
        visited.set(n, 2);
        return true;
    }
}
