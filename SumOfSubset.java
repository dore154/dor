import java.util.ArrayList;
import java.util.Scanner;
public class SumOfSubset {

    // Function to print a subset
    public static void printSubset(ArrayList<Integer> subset) {
        System.out.print("{ ");
        for (int num : subset) {
            System.out.print(num + " ");
        }
        System.out.println("}");
    }

    // Backtracking function to find subsets
    public static void findSubsets(int[] arr, ArrayList<Integer> subset, int index, int sum, int total, boolean[] found) {
        // If the sum equals the target, print the subset and set found to true
        if (sum == total) {
            printSubset(subset);
            found[0] = true;
            return;
        }

        // If the sum exceeds the target or we've considered all elements, return
        if (sum > total || index >= arr.length) {
            return;
        }

        // Include the current element in the subset and recurse
        subset.add(arr[index]);
        findSubsets(arr, subset, index + 1, sum + arr[index], total, found);

        // Exclude the current element from the subset and recurse
        subset.remove(subset.size() - 1);
        findSubsets(arr, subset, index + 1, sum, total, found);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: size of the set
        System.out.print("Enter the size of the set: ");
        int n = sc.nextInt();

        // Input: elements of the set
        int[] arr = new int[n];
        System.out.println("Enter the elements of the set: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Input: target sum
        System.out.print("Enter the target sum: ");
        int total = sc.nextInt();

        // Find subsets
        System.out.println("Subsets that sum to " + total + " are:");
        ArrayList<Integer> subset = new ArrayList<>();
        boolean[] found = {false};

        findSubsets(arr, subset, 0, 0, total, found);

        // If no subset is found, display the appropriate message
        if (!found[0]) {
            System.out.println("No solution found.");
        }

        sc.close();
    }
}

//BackTracking 
//TC= O(n* 2^n)
//SC= O(n)
