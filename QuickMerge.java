import java.util.*;
public class QuickMerge {
    /* PART A: Implement Quicksort using divide and conquer strategy and display time for sorting
    for 500 values.
   PART B: Use same data for Mergesort and compare the time required with Quicksort.
*/

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void mergeSort(int[] array) {
        if (array.length < 2) {
            return;
        }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        
        mergeSort(left);
        mergeSort(right);
        
        merge(array, left, right);

    }

    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }
    public static void main(String[] args) {
        int n = 500;
        int[] sample = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            sample[i] = random.nextInt(1000);
        }

        int[] mergeSample = sample.clone();
        System.out.println("------------ Using Quick sort --------------");
        for (int i : sample) {
            System.out.print(i + " ");
        }

        long startTime = System.nanoTime();
        quickSort(sample, 0, n - 1);
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println();
        System.out.println("Sorting the array using Quick sort");
        for (int i : sample) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("----------------------------------------------------");
        System.out.println("Time taken for Quick sort: " + timeElapsed + " ns");
        System.out.println("----------------------------------------------------");

        System.out.println("------------ Using Merge sort --------------");
        for (int i : mergeSample) {
            System.out.print(i + " ");
        }

        startTime = System.nanoTime();
        mergeSort(mergeSample);
        endTime = System.nanoTime();
        timeElapsed = endTime - startTime;
        System.out.println();
        System.out.println("Sorting the array using Merge sort");
        for (int i : mergeSample) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("----------------------------------------------------");
        System.out.println("Time taken for Merge sort: " + timeElapsed + " ns");
        System.out.println("----------------------------------------------------");

        System.out.println("!! Thank You !! \n Have a nice day");
        System.out.println("----------------------------------------------------");
    }
}

