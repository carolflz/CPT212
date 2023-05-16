
/** 1. Convert the radix sort algorithm that is described in the Appendix section 
to Java program. Note: You must implement exactly like what is 
described. (40%) **/

import java.util.Arrays;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class RadixSort_hundredth {

    // counter for counting the number of operations
    static long counter = 0;

    public static void main(String[] args) {
        // Sample input array to be sorted
        int[] arr = { 275, 87, 426, 61, 409, 170, 677, 503 };
        // To get new number of inputs for experiment for q3
        int input_size = 1000;

        // add randomly generated integer to array list
        List<Integer> tempList = new ArrayList<>();
        for (int integer : arr) {
            tempList.add(integer);

        }

        // add new integer that are not duplicated with the initial array input
        Random rand = new Random();
        while (tempList.size() < input_size) {
            int random_Integer = rand.nextInt();
            if (!tempList.contains(random_Integer)) {
                tempList.add(random_Integer);
            }
        }

        int[] newArr = new int[tempList.size()];
        for (int i = 0; i < tempList.size(); i++) {
            newArr[i] = tempList.get(i);
        }

        // Call radixSort method to sort the input array
        int[] sortedArray = radixSort(newArr);

        // Print the final sorted array
        System.out.println("Final sorted array:");
        System.out.println(Arrays.toString(sortedArray));
        System.out.println("Number of operations:" + counter + "\n");

    }

    public static int[] radixSort(int[] newArr) {
        // Define the maximum number of digits to sort, up to the hundredth place
        int maxDigits = 4;

        // Initialize 2D arrays (bins) Array1 and Array2 to store values during sorting
        int[][] Array1 = new int[10][newArr.length];
        int[][] Array2 = new int[10][newArr.length];

        // Loop through each digit position up to maxDigits
        counter++; // for d=0 assignment
        for (int d = 0; d < maxDigits; d++) {
        counter += 3; // for comparison, addition, assignment

            // Initialize counting array to store counts of digits in the current position
            int[] count = new int[10];

            // Iterate through the input array to extract digits at the current position
            for (int i : newArr) {
                // Calculate the digit at the current position using integer division and modulo
                int digit = (i / (int) Math.pow(10, d)) % 10;
                counter += 3; // for division, method calling and arithmetic
                
                // Add the value to the appropriate bin in Array1 or Array2 based on digit position
                counter++; //for comparison of d%2
                if (d % 2 == 0) {
                    Array1[digit][count[digit]++] = i;
                    counter+=2; //for addition and assignment
                } else {
                    Array2[digit][count[digit]++] = i;
                    counter+=2; // for addition and assignment
                }
                
            }

            // Initialize an index to track the position in the input array
            int index = 0;

            // Iterate through the bins in Array1 or Array2 and copy values back into the
            // input array
            counter++; // for i=0 assignment 
            for (int i = 0; i < 10; i++) {
                counter += 3; // for comparison, addittion and assignment of i
                counter++; // for assignment of j=0
                for (int j = 0; j < count[i]; j++) {
                    counter +=3; // for comparison, addition and assignment of j
                    newArr[index++] = d % 2 == 0 ? Array1[i][j] : Array2[i][j];
                   counter += 4; // for addition,assignment, arithmetic and comparison 
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

            // Clear Array1 for reuse in the third iteration
            if (d == 0) {
                for (int i = 0; i < 10; i++) {
                    Arrays.fill(Array1[i], 0, count[i], 0);
                }
                /*
                 * Comparison +1 for d==0
                 * Addition & Assignment +2 for i<10 and i++
                 * 
                 */
                counter+=2;
            }
        }
        // Return the sorted input array
        return newArr;

    }

    // Helper method to print 2D bin arrays with their counts
    private static void print2DArray(int[][] newArr, int[] count) {
        // Iterate through each row of the 2D array (bins)
        for (int i = 0; i < newArr.length; i++) {
            // Print the bin number
            System.out.print("[" + i + "]: ");
            // Iterate through each element in the current bin up to its count
            for (int j = 0; j < count[i]; j++) {
                // Print the element in the current bin
                System.out.print(newArr[i][j] + " ");
            }

            // Print a newline to separate bins
            System.out.println();
        }
    }

}
