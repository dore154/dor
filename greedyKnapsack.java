import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Item {
    int profit, weight;
    double ratio;

    Item(int profit, int weight) {
        this.profit = profit;
        this.weight = weight;
        this.ratio = (double) profit / weight; // Calculate profit-to-weight ratio
    }
}

public class greedyKnapsack{

    public static double knapsack(Item[] items, int n, int W) {
        Arrays.sort(items, Comparator.comparingDouble(i -> -i.ratio));

        double totalWeight = 0; 
        double totalProfit = 0; 
        StringBuilder includedItems = new StringBuilder(); 

        for (int i = 0; i < n; i++) {
            if (totalWeight + items[i].weight <= W) {
                totalProfit += items[i].profit;
                totalWeight += items[i].weight;
                includedItems.append("Item ").append(i + 1).append(" (entirely), ");
            } else {
                int remainingWeight = W - (int) totalWeight;
                totalProfit += remainingWeight * items[i].ratio;
                includedItems.append("Item ").append(i + 1).append(" (partialy), ");
                break; // Knapsack is full
            }
        }

        System.out.println("\nItems included in the knapsack: " + includedItems.toString());
        return totalProfit; // Return the maximum profit
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = sc.nextInt();
        Item[] items = new Item[n]; 

        for (int i = 0; i < n; i++) {
            System.out.print("Enter the weight of item " + (i + 1) + ": ");
            int weight = sc.nextInt();
            System.out.print("Enter the profit of item " + (i + 1) + ": ");
            int profit = sc.nextInt();
            items[i] = new Item(profit, weight); 
        }

        System.out.print("Enter the maximum capacity of the knapsack: ");
        int W = sc.nextInt();

        double maxProfit = knapsack(items, n, W);

        System.out.println("\nMaximum profit for the knapsack with capacity " + W + " is: " + maxProfit);

        sc.close();
    }
}

// TC nlogn
// SC n
