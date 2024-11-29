public class knapsackUsingBacktracking {
        static int maxProfit = 0;
    
        // Function to calculate the maximum profit
        public static void knapsack(int[] weights, int[] values, int capacity, int currentIndex, int currentProfit, 
                                      int currentWeight) {
            // Base condition
            if (currentIndex == weights.length) {
                if (currentWeight <= capacity) {
                    maxProfit = Math.max(maxProfit, currentProfit);
                }
                return;
            }
    
            // Exclude the current item
            knapsack(weights, values, capacity, currentIndex + 1, currentProfit, currentWeight);
    
            // Include the current item (if within capacity)
            if (currentWeight + weights[currentIndex] <= capacity) {
                knapsack(weights, values, capacity, currentIndex + 1,
                        currentProfit + values[currentIndex], currentWeight + weights[currentIndex]);
            }
        }
    
        public static void main(String[] args) {
            int[] weights = {10, 20, 30};
            
            int[] values = {60, 100, 120};
            int capacity = 50;
    
            knapsack(weights, values, capacity, 0, 0, 0);
            System.out.println("Maximum profit (Backtracking): " + maxProfit);
        }
    }
    
    // TC 2^n
    // SC n

