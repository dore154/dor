import java.util.PriorityQueue;
import java.util.Scanner;

public class TSP_BB {
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
        // int[][] graph={
        //     {0,16,11,6},
        //     {8,0,13,16},
        //     {4,7,0,9},
        //     {5,12,2,0}
        // };
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter The No. of Citites");
        int n=sc.nextInt();
        int[][] graph=new int[n][n];
        System.out.println("Enter distance Matrix (N*N): ");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                graph[i][j]=sc.nextInt();
            }
        }


        // Using Branch and Bound
        System.out.println("TSP using Branch and Bound: " + tspBB(graph, n));
    }
}
 

// Method	            Time Complexity	         Space Complexity	 Suitability
// Branch and Bound	O(n!) (worst-case)	       O(n²)	         Suitable for small   n (≤10)

