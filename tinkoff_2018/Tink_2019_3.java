import java.util.Scanner;

public class Tink_2019_3 {

    static class Event {
        int day;
        int hour;
        int min;
    }

    static void print(Event e) {
        System.out.println(e.day + " " + e.hour + " " + e.min);
    }

    public static void main2(String[] args) {
        Event event = new Event();
        event.day = 7;
        event.hour = 23;
        event.min = 59;

        Event cand = new Event();
        cand.day = 7;
        cand.min = 23;
        cand.hour = 0;

        Event merged = merge(cand, event);
        int dist = distance(merged, event);
        System.out.println(dist);

        print(merged);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Event event = scanEvent(scanner);
        Event minEvent = null;
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            Event curEvent = scanEvent(scanner);
            minEvent = min(minEvent, curEvent, event);
        }
        print(minEvent);
    }

    private static Event merge(Event curEvent, Event event) {
        if (curEvent.day != 0) {
            return curEvent;
        }
        if (curEvent.hour > event.hour) {
            curEvent.day = event.day;
            return curEvent;
        }
        if (curEvent.hour == event.hour) {
            if (curEvent.min >= event.min) {
                curEvent.day = event.day;
                return curEvent;
            }
        }
        curEvent.day = (event.day) % 7 + 1;
        return curEvent;
    }

    private static int distance(Event cand, Event event) {
        int day = (((cand.day + 7) - event.day) % 7);
        int hourdiff = cand.hour - event.hour;
        if (hourdiff < 0 && day == 0) {
            day = 7;
        }
        int hour = day * 24 + hourdiff;
        int mindiff = cand.min - event.min;
        if (mindiff < 0 && hour == 0) {
            hour = 7 * 24;
        }
        return 60 * (hour) + mindiff;
    }

    private static Event min(Event minEvent, Event curEvent, Event event) {
        Event merged = merge(curEvent, event);
        if (minEvent == null) {
            return merged;
        }
        int minDist = distance(minEvent, event);
        int curDist = distance(merged, event);
        if (minDist <= curDist) {
            return minEvent;
        }
        return merged;
    }

    private static Event scanEvent(Scanner scanner) {
        Event event = new Event();
        event.day = scanner.nextInt();
        event.hour = scanner.nextInt();
        event.min = scanner.nextInt();
        return event;
    }
}
