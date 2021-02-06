package ya.a2;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Created by asavan on 06.02.2021.
 */
public class TEventStats {

    public TEventStats(int TIME_LIMIT) {
        this.TIME_LIMIT = TIME_LIMIT;
    }

    static class Event {
        public int userId;
        public int time;

        public Event(int userId, int time) {
            this.userId = userId;
            this.time = time;
        }
    }
    int TIME_LIMIT = 5*60*1000;
    Queue<Event> events = new ArrayDeque<>();
    Map<Integer, Integer> userToCount = new HashMap<>();
    Map<Integer, Integer> countToUsers = new HashMap<>();
    public void registerEvent(int userId, int time) {
        removeOld(time);
        Event e = new Event(userId, time);
        events.add(e);
        Integer count = userToCount.merge(userId, 1, Integer::sum);
        if (count > 1) {
            countToUsers.merge(count-1, -1, Integer::sum);
        }
        countToUsers.merge(count, 1, Integer::sum);
    }

    private void removeOld(int time) {
        while (true) {
            if (events.isEmpty()) {
                return;
            }
            Event e = events.peek();
            if (e.time + TIME_LIMIT < time) {
                events.poll();
                Integer count = userToCount.get(e.userId);
                if (!count.equals(1)) {
                    userToCount.put(e.userId, count - 1);
                    countToUsers.merge(count, -1, Integer::sum);
                    countToUsers.merge(count-1, 1, Integer::sum);
                } else {
                    userToCount.remove(e.userId);
                }
            } else {
                return;
            }
        }
    }



    public int query(int count, int time) {
        removeOld(time);
        Integer users = countToUsers.get(count);
        return users != null ? users : 0;
    }

    public static void main(String[] args) {
        TEventStats tEventStats = new TEventStats(5*60*1000);

        tEventStats.registerEvent(5, 1);
        tEventStats.registerEvent(6, 2);
        tEventStats.registerEvent(5, 3);
        int res = tEventStats.query(2, 4);
        System.out.println(res);
        int res2 = tEventStats.query(1, 5);
        System.out.println(res2);

        int res3 = tEventStats.query(3, 6);
        System.out.println(res3);


    }

}
