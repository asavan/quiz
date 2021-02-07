package ya.a2;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Created by asavan on 06.02.2021.
 */
public class RobotStatistics {
    public RobotStatistics(int threshold, int timeLimit) {
        this.threshold = threshold;
        this.timeLimit = timeLimit;
        robotsCount = 0;
    }

    private final int threshold;
    private final int timeLimit;

    private static class Event {
        int time;
        int userId;

        public Event(int time, int userId) {
            this.time = time;
            this.userId = userId;
        }
    }

    private final Queue<Event> queue = new ArrayDeque<>();
    private final Map<Integer, Integer> userToCount = new HashMap<>();
    private int robotsCount;

    public void onEvent(int now, int userId) {
        removeOld(now);
        Event e = new Event(now, userId);
        queue.add(e);
        Integer newCount = userToCount.merge(userId, 1, Integer::sum);
        if (newCount.equals(threshold)) {
            ++robotsCount;
        }
    }

    private void removeOld(int now) {
        while (!queue.isEmpty()) {
            Event e = queue.peek();
            if (e.time + timeLimit < now) {
                queue.poll();
                Integer newCount = userToCount.merge(e.userId, -1, Integer::sum);
                if (newCount.equals(threshold - 1)) {
                    --robotsCount;
                }
                if (newCount.equals(0)) {
                    userToCount.remove(e.userId);
                }
            } else {
                return;
            }
        }
    }

    public int getRobotCount(int now) {
        removeOld(now);
        return robotsCount;
    }

    public static void main(String[] args) {
        RobotStatistics robotStatistics = new RobotStatistics(3, 10);
        robotStatistics.onEvent(0, 4);
        robotStatistics.onEvent(1, 2);
        robotStatistics.onEvent(2, 4);
        robotStatistics.onEvent(3, 4);
        robotStatistics.onEvent(4, 4);
        robotStatistics.onEvent(5, 4);
        robotStatistics.onEvent(6, 1);
        int res = robotStatistics.getRobotCount(7);
        System.out.println(res); // 1

        robotStatistics.onEvent(8, 1);
        robotStatistics.onEvent(9, 2);
        robotStatistics.onEvent(10, 3);
        robotStatistics.onEvent(11, 6);
        robotStatistics.onEvent(12, 7);
        robotStatistics.onEvent(13, 7);
        int res2 = robotStatistics.getRobotCount(14);
        System.out.println(res2); // 0

        int res3 = robotStatistics.getRobotCount(140);
        System.out.println(res3); // 0


    }
}
