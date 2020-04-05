import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

public class Tink_2019_7 {

    static class Report {
        LocalDate date;
        int tk;
    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        Report[] arr = new Report[n];
        for (int i = 0; i < n; i++) {
            Report report = new Report();
            String name = scanner.next();
            String date = scanner.next();
            report.tk = scanner.nextInt();
            report.date = LocalDate.parse(date, FORMATTER);
            arr[i] = report;
        }

        Arrays.sort(arr, Comparator.comparing(r -> r.date));

        PriorityQueue<LocalDate> queue =
                new PriorityQueue<>(10, Comparator.reverseOrder());

        LocalDate prevDate = arr[n - 1].date;
        queue.add(getMinDate(arr[n - 1]));
        for (int i = n - 2; i >= 0; --i) {
            Report curReport = arr[i];
            long daysBetween = DAYS.between(curReport.date, prevDate);
            for (int j = 0; j < daysBetween - 1; ++j) {
                LocalDate lastMin = queue.poll();
                if (lastMin == null) {
                    break;
                }
                if (!prevDate.isAfter(lastMin)) {
                    System.out.println("NO");
                    return;
                }
                prevDate = prevDate.minusDays(1);
            }

            queue.add(getMinDate(curReport));
            prevDate = curReport.date;
        }

        while (!queue.isEmpty()) {
            LocalDate lastMin = queue.poll();
            if (!prevDate.isAfter(lastMin)) {
                System.out.println("NO");
                return;
            }
            prevDate = prevDate.minusDays(1);
        }
        System.out.println(prevDate.format(FORMATTER));
    }

    private static LocalDate getMinDate(Report report) {
        return report.date.minusDays(report.tk);
    }
}
