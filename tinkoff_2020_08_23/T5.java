import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class T5 {
    public static class Request {
        public int v1;
        public int v2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        Request[] eges = new Request[m];
        for (int i = 0; i < m; i++) {

            eges[i] = new Request();
            eges[i].v1 = scanner.nextInt();
            eges[i].v2 = scanner.nextInt();
            addEdge(adj, eges[i].v1 - 1, eges[i].v2 - 1);
        }
        int q = scanner.nextInt();

        Request[] requests = new Request[q];
        for (int i = 0; i < q; i++) {
            requests[i] = new Request();
            requests[i].v1 = scanner.nextInt();
            requests[i].v2 = scanner.nextInt();
        }

        for (Request request : requests) {
            System.out.println(shortestDistance(adj, request.v1 - 1, request.v2 - 1, n));
        }
    }

    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j) {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }

    private static int shortestDistance(ArrayList<ArrayList<Integer>> adj, int s, int dest, int v) {
        if (s == dest) {
            return 0;
        }
        int[] pred = new int[v];
        int[] dist = new int[v];

        if (!BFS(adj, s, dest, v, pred, dist)) {
            return -1;
        }
        return dist[dest];
    }

    private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src,
                               int dest, int v, int[] pred, int[] dist) {
        LinkedList<Integer> queue = new LinkedList<>();

        boolean[] visited = new boolean[v];

        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }

        visited[src] = true;
        dist[src] = 0;
        queue.add(src);

        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                if (!visited[adj.get(u).get(i)]) {
                    visited[adj.get(u).get(i)] = true;
                    dist[adj.get(u).get(i)] = dist[u] + 1;
                    pred[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));

                    if (adj.get(u).get(i) == dest)
                        return true;
                }
            }
        }
        return false;
    }
}
