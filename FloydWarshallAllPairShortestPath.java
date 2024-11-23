import java.util.Scanner;
      
public class FloydWarshallAllPairShortestPath {

    final static int INF = 99999; // A large value to represent infinity

    // Method to perform Floyd-Warshall algorithm
    public static void floydWarshall(int[][] graph, int V) {
        // Distance matrix to store shortest distances between pairs
        int[][] dist = new int[V][V];

        // Initialize the solution matrix same as input graph matrix
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        // Floyd-Warshall Algorithm
        for (int k = 0; k < V; k++) { // Consider each vertex as intermediate
            for (int i = 0; i < V; i++) { // For each source vertex
                for (int j = 0; j < V; j++) { // For each destination vertex
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Print the final distance matrix
        printSolution(dist, V);
    }

    // Method to print the solution matrix
    public static void printSolution(int[][] dist, int V) {
        System.out.println("The following matrix shows the shortest distances between every pair of vertices:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = sc.nextInt();

        int[][] graph = new int[V][V];

        System.out.println("Enter the adjacency matrix (use " + INF + " for no direct edge):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        // Call Floyd-Warshall function
        floydWarshall(graph, V);

        sc.close();
    }
}

//Uses DP
//TC=O(n^3)
//SC =O(n^2)
