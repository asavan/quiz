import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class T7 {
    public static class Request {
        public int K;
        public int C;
    }

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        arr.add(2);
        for (int i = 3; i < 100004; i += 2) {
            if (isPrime(i)) {
                arr.add(i);
            }
        }

        for (int j = 1; j < arr.size(); j++) {
            int diff = 0;
            int maxIndex = -1;
            for (int i = 0; i + j < arr.size(); i++) {
                int b = arr.get(i + j) - arr.get(i);
                if (diff < b) {
                    diff = b;
                    maxIndex = i;
                }
            }
            // System.out.println(j + " " + diff + " " + maxIndex + " " + arr.get(maxIndex));
        }

//        System.out.println(arr);
//        Request r = new Request();
//        r.C = 3;
//        r.K = 5;
//        processRequest(arr, r);

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        Request[] requests = new Request[n];
        for (int i = 0; i < n; i++) {
            requests[i] = new Request();
            requests[i].K = scanner.nextInt();
            requests[i].C = scanner.nextInt();
        }
        for (Request request : requests) {
            System.out.println(processRequest(arr, request));
        }
    }

    private static int processRequest(List<Integer> arr, Request request) {
        for (int i = 0; i < arr.size(); i++) {
            if (i + request.C > arr.size() || request.C <= 0) {
                return -1;
            }
            int last = arr.get(request.C + i - 1);
            int last2 = arr.get(request.C + i);
            int first = arr.get(i);
            int len = last - first + 1;
            // System.out.println(last2 + " " + last + " " + first);
            if (len >= request.K) {
                return arr.get(i);
            }
        }
        return -1;
    }

    private static boolean isPrime(long n) {
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
