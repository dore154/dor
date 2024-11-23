import java.util.*;
public class BinaryMerge {

    // Merge Sort implementation
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


    // Binary Search implementation
    public static int binarySearch(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == key) {
                return mid;  // Key found
            } else if (array[mid] < key) {
                left = mid + 1;  // Search in the right half
            } else {
                right = mid - 1;  // Search in the left half
            }
        }
        return -1;  // Key not found
    }

    public static void main(String[] args) {
        // Generate a random list of 5000 integers
        Scanner sc=new Scanner(System.in);
        Random random = new Random();
        int[] randomList = new int[500];
        for (int i = 0; i < randomList.length; i++) {
            randomList[i] = random.nextInt(1000) + 1;  // Random integers from 1 to 10000
        }
        while (true) {
            System.out.println("\nMenu:\n");
            System.out.println("1. Display Array\n");
            System.out.println("2. Sort Array\n");
            System.out.println("3. Search for an Element\n");
            System.out.println("4. Exit\n");
            System.out.println("Enter your choice: ");
            int choice=sc.nextInt(); 
    
            switch (choice) {
                case 1:
                  System.out.println("Unsorted Random List");
                  for(int i=0;i<500;i++){
                  System.out.print(randomList[i]+" ");
                   }
                   System.out.println();
                    break;
                case 2:
                
                   mergeSort(randomList);
          
                   System.out.println("sorted Random List");
                   for(int i=0;i<500;i++){    
                     System.out.print(randomList[i]+" ");
                   }
                   System.out.println();
                   break;
                case 3:
                    System.out.print("Enter the key to search for: ");
                    int key = sc.nextInt();
        
                    // Perform binary search
                    int result = binarySearch(randomList, key);
                
                    if (result != -1) {
                       System.out.println("Key " + key + " found at index " + result + " in the sorted list.");
                    } else {
                       System.out.println("Key " + key + " not found in the list.");
                    }
                    break;
                case 4:
                    return;
                default:
                  System.out.println("Invalid choice. Please try again.\n");    
            }
        }
       
        // Sort the list using merge sort
       
         
        // User input for the key
    }
}

