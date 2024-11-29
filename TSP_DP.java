import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

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


    // Main method
    public static void main(String[] args) {
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
        
        // int[][] graph={
        //     {0,16,11,6},
        //     {8,0,13,16},
        //     {4,7,0,9},
        //     {5,12,2,0}
        // };
        
        System.out.println("TSP using Dynamic Programming (Held-Karp): " + tspDP(graph, n));

       sc.close();
    }
}

// Method	            Time Complexity	         Space Complexity	 Suitability
// Dynamic Programming	O(n² × 2ⁿ)	             O(n × 2ⁿ)	         Best for small to medium n (≤20)
// Branch and Bound	    O(n!) (worst-case)	       O(n²)	         Suitable for small   n (≤10)
