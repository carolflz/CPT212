/** 1. Convert the radix sort algorithm that is described in the Appendix section 
to Java program. Note: You must implement exactly like what is 
described. (40%) **/

import java.util.Arrays;

public class RadixSort_hundredth {

    public static void main(String[] args) {
        // Sample input array to be sorted
        int[] arr = {275,87,426,61,409,170,677,503};
        
        // Call radixSort method to sort the input array
        int[] sortedArray = radixSort(arr);
        
        // Print the final sorted array
        System.out.println("Final sorted array:");
        System.out.println(Arrays.toString(sortedArray));
    }

    public static int[] radixSort(int[] arr) {
        // Define the maximum number of digits to sort, up to the hundredth place
        int maxDigits = 3;
        
        // Initialize 2D arrays (bins) Array1 and Array2 to store values during sorting
        int[][] Array1 = new int[10][arr.length];
        int[][] Array2 = new int[10][arr.length];

        // Loop through each digit position up to maxDigits
        for (int d = 0; d < maxDigits; d++) {
            // Initialize counting array to store counts of digits in the current position
            int[] count = new int[10];
            
            // Iterate through the input array to extract digits at the current position
            for (int i : arr) {
                // Calculate the digit at the current position using integer division and modulo
                int digit = (i / (int) Math.pow(10, d)) % 10;
                
                // Add the value to the appropriate bin in Array1 or Array2 based on digit position
                if (d % 2 == 0) {
                    Array1[digit][count[digit]++] = i;
                } else {
                    Array2[digit][count[digit]++] = i;
                }
            }

            // Initialize an index to track the position in the input array
            int index = 0;
            
            // Iterate through the bins in Array1 or Array2 and copy values back into the input array
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < count[i]; j++) {
                    arr[index++] = d % 2 == 0 ? Array1[i][j] : Array2[i][j];
                }
            }

            // Print the 2D bin array after the current iteration
            if (d % 2 == 0) {
                System.out.println("Array1 after " + (d + 1) + " iteration:");
                print2DArray(Array1, count);
                System.out.println("\n");
            } else {
                System.out.println("Array2 after " + (d + 1) + " iteration:");
                print2DArray(Array2, count);
                System.out.println("\n");
            }

            // Clear Array1 for reuse in the third iteration (hundredth place)
            if (d == 0) {
                for (int i = 0; i < 10; i++) {
                    Arrays.fill(Array1[i], 0, count[i], 0);
                }
            }
        }
        // Return the sorted input array
        return arr;
    }

    // Helper method to print 2D bin arrays with their counts
    private static void print2DArray(int[][] arr, int[] count) {
        // Iterate through each row of the 2D array (bins)
        for (int i = 0; i < arr.length; i++) {
            // Print the bin number
            System.out.print("[" + i + "]: ");
            // Iterate through each element in the current bin up to its count
            for (int j = 0; j < count[i]; j++) {
                // Print the element in the current bin
                System.out.print(arr[i][j] + " ");
            }
            
            // Print a newline to separate bins
            System.out.println();
        }
    }
}








