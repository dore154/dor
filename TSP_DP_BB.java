import java.util.Arrays;
import java.util.PriorityQueue;

public class TSP_DP_BB {

    // Dynamic Programming (Held-Karp)
    public static int tspDP(int[][] graph, int n) {
        int VISITED_ALL = (1 << n) - 1; // All cities visited
        int[][] dp = new int[n][1 << n]; // dp[city][visitedSet]

        // Initialize dp with a large value
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE / 2);

        // Base case: starting from the first city
        dp[0][1] = 0;

        // Iterate over all subsets of cities
        for (int mask = 1; mask < (1 << n); mask++) {
            for (int city = 0; city < n; city++) {
                if ((mask & (1 << city)) != 0) { // If 'city' is part of the current subset
                    for (int prev = 0; prev < n; prev++) {
                        if ((mask & (1 << prev)) != 0 && prev != city) { // Valid previous city
                            dp[city][mask] = Math.min(dp[city][mask],
                                    dp[prev][mask ^ (1 << city)] + graph[prev][city]);
                        }
                    }
                }
            }
        }

        // Find the optimal tour cost
        int minCost = Integer.MAX_VALUE;
        for (int city = 1; city < n; city++) {
            minCost = Math.min(minCost, dp[city][VISITED_ALL] + graph[city][0]);
        }
        return minCost;
    }

    // Branch and Bound
    static class Node {
        int level, cost, bound;
        int[] path;
        boolean[] visited;

        Node(int level, int cost, int bound, int[] path, boolean[] visited) {
            this.level = level;
            this.cost = cost;
            this.bound = bound;
            this.path = path.clone();
            this.visited = visited.clone();
        }
    }

    private static int calculateBound(Node node, int[][] graph, int n) {
        int bound = node.cost;

        // Add the minimum outgoing edge for unvisited cities
        for (int i = 0; i < n; i++) {
            if (!node.visited[i]) {
                int minEdge = Integer.MAX_VALUE;
                for (int j = 0; j < n; j++) {
                    if (!node.visited[j] || j == 0) {
                        minEdge = Math.min(minEdge, graph[i][j]);
                    }
                }
                bound += minEdge;
            }
        }
        return bound;
    }

    public static int tspBB(int[][] graph, int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.bound, b.bound));
        int[] path = new int[n + 1];
        boolean[] visited = new boolean[n];

        Node root = new Node(0, 0, 0, path, visited);
        root.path[0] = 0; // Start from city 0
        root.visited[0] = true;
        root.bound = calculateBound(root, graph, n);
        pq.add(root);

        int minCost = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.bound >= minCost) continue;

            if (curr.level == n - 1) {
                int lastEdge = graph[curr.path[curr.level]][0];
                if (curr.cost + lastEdge < minCost) {
                    minCost = curr.cost + lastEdge;
                }
                continue;
            }

            for (int i = 0; i < n; i++) {
                if (!curr.visited[i]) {
                    Node child = new Node(curr.level + 1, curr.cost + graph[curr.path[curr.level]][i], 0, curr.path, curr.visited);
                    child.path[child.level] = i;
                    child.visited[i] = true;

                    child.bound = calculateBound(child, graph, n);
                    if (child.bound < minCost) pq.add(child);
                }
            }
        }

        return minCost;
    }

    // Main method
    public static void main(String[] args) {
        // int[][] graph = {
        //     {0, 10, 15, 20},
        //     {10, 0, 35, 25},
        //     {15, 35, 0, 30},
        //     {20, 25, 30, 0}
        // };
        int[][] graph={
            {0,16,11,6},
            {8,0,13,16},
            {4,7,0,9},
            {5,12,2,0}
        };
        int n = graph.length;

        // Using Dynamic Programming
        System.out.println("TSP using Dynamic Programming (Held-Karp): " + tspDP(graph, n));

        // Using Branch and Bound
        System.out.println("TSP using Branch and Bound: " + tspBB(graph, n));
    }
}

// Method	            Time Complexity	         Space Complexity	 Suitability
// Dynamic Programming	O(n² × 2ⁿ)	             O(n × 2ⁿ)	         Best for small to medium n (≤20)
// Branch and Bound	O(n!) (worst-case)	       O(n²)	         Suitable for small   n (≤10)