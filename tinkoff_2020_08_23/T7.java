package tinkoff_2020_08_23;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class T7 {
    public static class Request {
        public Request() {
        }

        public Request(int k, int c) {
            K = k;
            C = c;
        }

        public int K;
        public int C;
    }

    public static void main(String[] args) {
        List<Integer> arr = getPrimesAndBounds();
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

//    public static void maxDiffCalc(List<Integer> arr) {
//        for (int j = 1; j < arr.size(); j++) {
//            int diff = 0;
//            int maxIndex = -1;
//            for (int i = 0; i + j < arr.size(); i++) {
//                int b = arr.get(i + j) - arr.get(i);
//                if (diff < b) {
//                    diff = b;
//                    maxIndex = i;
//                }
//            }
//            System.out.println(j + " " + diff + " " + maxIndex + " " + arr.get(maxIndex) + " " + arr.get(maxIndex + j));
//        }
//    }

    public static List<Integer> getPrimesAndBounds() {
        List<Integer> arr = new ArrayList<>(9594);
        arr.add(0);
        arr.add(2);
        for (int i = 3; i <= 100_000; i += 2) {
            if (isPrime(i)) {
                arr.add(i);
            }
        }
        arr.add(100_001);
        return arr;
    }

    public static int processRequest(List<Integer> arr, Request request) {
        if (request.C > 9592 || request.C < 0) {
            return -1;
        }
        if (request.K > 100_000 || request.K <= 0) {
            return -1;
        }
//        if (request.C == 9592) {
//            if (request.K > 100_000) {
//                return -1;
//            }
//            if (request.K == 100_000) {
//                return 1;
//            }
//            if (request.K >= 99990) {
//                return 2;
//            }
//            return -1;
//        }
        if (request.C == 0) {
            if (request.K < 72) {
                return 31398;
            }
            return -1;
        }
        if (request.C == 1) {
            if (request.K < 72) {
                return 31397;
            }
            if (request.K < 100) {
                return 58790;
            }
            return -1;
        }

        for (int i = 1; i < arr.size(); i++) {
            if (i + request.C >= arr.size()) {
                return -1;
            }
            int prev = arr.get(i - 1);
            int last = arr.get(request.C + i - 1);
            int next = arr.get(request.C + i);
            int first = arr.get(i);
            int len = last - first + 1;
            int left = first - prev - 1;
            int right = next - last - 1;
            if (len == request.K) {
                return first;
            }
            if (len < request.K && len + left + right >= request.K) {
                return Math.max(first - request.K + len, prev + 1);
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
