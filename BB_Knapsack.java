import java.util.PriorityQueue;

class Knapsack_Using_BB {
    static class Node {
        int level, profit, weight, bound;

        Node(int level, int profit, int weight, int bound) {
            this.level = level;
            this.profit = profit;
            this.weight = weight;
            this.bound = bound;
        }
    }

    private static int calculateBound(Node node, int[] weights, int[] values, int capacity, int n) {
        if (node.weight >= capacity) return 0; // Bound is invalid if weight exceeds capacity

        int profitBound = node.profit;
        int j = node.level + 1;
        int totalWeight = node.weight;

        // Include items in the bound as much as possible within capacity
        while (j < n && totalWeight + weights[j] <= capacity) {
            totalWeight += weights[j];
            profitBound += values[j];
            j++;
        }

        // Add fractional part of the next item (if any)
        if (j < n) {
            profitBound += (capacity - totalWeight) * values[j] / weights[j];
        }

        return profitBound;
    }

    public static int knapsackBranchAndBound(int[] weights, int[] values, int capacity) {
        int n = weights.length;

        // Sort items by value/weight ratio in descending order
        double[] ratio = new double[n];
        for (int i = 0; i < n; i++) {
            ratio[i] = (double) values[i] / weights[i];
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (ratio[i] < ratio[j]) {
                    // Swap ratios
                    double tempRatio = ratio[i];
                    ratio[i] = ratio[j];
                    ratio[j] = tempRatio;

                    // Swap weights and values
                    int tempWeight = weights[i];
                    weights[i] = weights[j];
                    weights[j] = tempWeight;

                    int tempValue = values[i];
                    values[i] = values[j];
                    values[j] = tempValue;
                }
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.bound, a.bound));
        Node root = new Node(-1, 0, 0, 0);
        root.bound = calculateBound(root, weights, values, capacity, n);
        pq.add(root);

        int maxProfit = 0;

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();

            // If the bound of the current node is greater than the max profit, process it
            if (currentNode.bound > maxProfit) {
                // Consider including the next item
                if (currentNode.level + 1 < n) {
                    Node withItem = new Node(
                            currentNode.level + 1,
                            currentNode.profit + values[currentNode.level + 1],
                            currentNode.weight + weights[currentNode.level + 1],
                            0
                    );

                    if (withItem.weight <= capacity) {
                        maxProfit = Math.max(maxProfit, withItem.profit);
                        withItem.bound = calculateBound(withItem, weights, values, capacity, n);
                        if (withItem.bound > maxProfit) {
                            pq.add(withItem);
                        }
                    }

                    // Consider excluding the next item
                    Node withoutItem = new Node(
                            currentNode.level + 1,
                            currentNode.profit,
                            currentNode.weight,
                            0
                    );

                    withoutItem.bound = calculateBound(withoutItem, weights, values, capacity, n);
                    if (withoutItem.bound > maxProfit) {
                        pq.add(withoutItem);
                    }
                }
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] weights = {3,5,5,8,4};
        int[] values = {10,20,21,30,16};
        int capacity = 20;

        System.out.println("Maximum profit (Branch and Bound): " + knapsackBranchAndBound(weights, values, capacity));
    }
}
//Time Complexity (Worst Case): O(n⋅2^n)
//Space Complexity O(2^n)
