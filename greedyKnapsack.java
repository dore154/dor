import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Item {
    int profit, weight;
    double ratio;

    // Constructor to initialize an item
    public Item(int profit, int weight) {
        this.profit = profit;
        this.weight = weight;
        this.ratio = (double) profit / weight; // Calculate profit-to-weight ratio
    }
}

public class greedyKnapsack {

    // Method to calculate the maximum profit
    public static double knapsack(Item[] items, int n, int W) {
        // Sort items by their ratio in descending order
        Arrays.sort(items, Comparator.comparingDouble(i -> -i.ratio));

        double totalWeight = 0; // Track current weight of the knapsack
        double totalProfit = 0; // Track total profit

        for (int i = 0; i < n; i++) {
            if (totalWeight + items[i].weight <= W) {
                // If item can fit entirely, add it to the knapsack
                totalProfit += items[i].profit;
                totalWeight += items[i].weight;
            } else {
                // If item can't fit entirely, take a fraction of it
                int remainingWeight = W - (int) totalWeight;
                totalProfit += remainingWeight * items[i].ratio;
                break; // Knapsack is full
            }
        }

        return totalProfit; // Return the maximum profit
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = sc.nextInt();
        Item[] items = new Item[n]; // Create an array to store items

        // Input items data
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the weight of item " + (i + 1) + ": ");
            int weight = sc.nextInt();
            System.out.print("Enter the profit of item " + (i + 1) + ": ");
            int profit = sc.nextInt();
            items[i] = new Item(profit, weight); // Create an item object
        }

        System.out.print("Enter the maximum capacity of the knapsack: ");
        int W = sc.nextInt();

        // Calculate the maximum profit
        double maxProfit = knapsack(items, n, W);

        System.out.println("\nMaximum profit for the knapsack with capacity " + W + " is: " + maxProfit);

        sc.close();
    }
}

// The user inputs the number of items (say 3).
// For each item, the user inputs the profit and weight (e.g., profit = 60, weight = 10 for item 1).
// The program calculates the profit-to-weight ratio for each item and sorts the items by this ratio in descending order.
// The program selects the items to include in the knapsack based on the greedy approach:
// If an item can fit entirely, it's added to the knapsack.
// If not, the program adds the fraction of the item that fits.
// The program calculates and prints the maximum profit that can be obtained given the knapsack's capacity.

