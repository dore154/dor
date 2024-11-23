import java.util.*;

public class DpKnapsack {
    public static int knapsack(int[] weights, int[] values, int capacity, int n) {
        // DP table to store the maximum value for each subproblem
        int[][] dp = new int[n + 1][capacity + 1];

        // Build the table dp[][] in a bottom-up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;  // Base case: no items or capacity 0
                } else if (weights[i - 1] <= w) {
                    // If current item's weight is less than or equal to the capacity
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    // If current item's weight is greater than the remaining capacity
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

          // Display selected items
          System.out.print("Items included in the knapsack: ");
          int j = capacity;
          for (int i = n; i > 0 && j > 0; i--) {
              if (dp[i][j] != dp[i - 1][j]) {
                  System.out.print(i + " ");  // Item index (1-based)
                  j -= weights[i - 1];       // Reduce the remaining capacity
              }
          }
          System.out.println();

        return dp[n][capacity];  // Maximum value that can be obtained
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of items
        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        // Input capacity of the knapsack
        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        // Arrays to store weights and values of items
        int[] weights = new int[n];
        int[] values = new int[n];

        // Input weights and values of items
        System.out.println("Enter the weights and values of items:");
        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i+1) + " weight: ");
            weights[i] = scanner.nextInt();
            System.out.print("Item " + (i+1) + " value: ");
            values[i] = scanner.nextInt();
        }

        // Call knapsack function and display the result
        int maxValue = knapsack(weights, values, capacity, n);
        System.out.println("Maximum value that can be obtained: " + maxValue);

        scanner.close();
    }
}
