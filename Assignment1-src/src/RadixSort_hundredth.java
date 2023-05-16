/** 1. Convert the radix sort algorithm that is described in the Appendix section 
to Java program. Note: You must implement exactly like what is 
described. (40%) **/


import java.util.Arrays;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
public class RadixSort_hundredth {

//counter for counting the number of operations
static long counter = 0;

    public static void main(String[] args) {
        // Sample input array to be sorted
        int[] arr = {275,87,426,61,409,170,677,503};
        // To get 1000 number of inputs
        int input_size = 1000;

        //add randomly generated integer to array list
        List<Integer> tempList = new ArrayList<>();
        for (int integer:arr)
        {
            tempList.add(integer);
            
        }

        // add new integer that are not duplicated with the initial array input
        Random rand = new Random();
        while (tempList.size()< input_size)
        {
            int random_Integer = rand.nextInt(1000);
            if(!tempList.contains(random_Integer))
            {
                tempList.add(random_Integer);
            }
        }

        int[] newArr = new int [tempList.size()];
        for (int i=0; i < tempList.size(); i++)
        {
            newArr[i] = tempList.get(i);
        }

        // Call radixSort method to sort the input array
        int[] sortedArray = radixSort(newArr);

        // Print the final sorted array
        System.out.println("Final sorted array:");
        System.out.println(Arrays.toString(sortedArray));
        System.out.println("Number of operations:"+counter+"\n");
        
    }

    public static int[] radixSort(int[] newArr) {
        // Define the maximum number of digits to sort, up to the hundredth place
        int maxDigits = 4;
     
        // Initialize 2D arrays (bins) Array1 and Array2 to store values during sorting
        int[][] Array1 = new int[10][newArr.length];
        int[][] Array2 = new int[10][newArr.length];

        // Loop through each digit position up to maxDigits
        for (int d = 0; d < maxDigits; d++) {
            /*
             Comparison +1 for d < maxDigits
             Addition & Assignment +2 for d++
             */

            // Initialize counting array to store counts of digits in the current position
            int[] count = new int[10];
            
            // Iterate through the input array to extract digits at the current position
            for (int i : newArr) {
                // Calculate the digit at the current position using integer division and modulo
                int digit = (i / (int) Math.pow(10, d)) % 10;
                
                // Add the value to the appropriate bin in Array1 or Array2 based on digit position
                if (d % 2 == 0) {
                    Array1[digit][count[digit]++] = i;
                } else {
                    Array2[digit][count[digit]++] = i;
                }
            /*
               Arithmentic(Division) +1 for (i / (int) Math.pow(10, d))
               Arithmentic(Modulo) +1 for  % 10
               Calling method +1 for Math.pow(10, d)
               Assignment +1 for int digit
               Comparison +1 for d % 2 == 0
               Arithmentic(Modulo) +1 for d%2
             */
                counter += 6;
            }
            

            // Initialize an index to track the position in the input array
            int index = 0;
            
            // Iterate through the bins in Array1 or Array2 and copy values back into the input array
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < count[i]; j++) {
                    newArr[index++] = d % 2 == 0 ? Array1[i][j]: Array2[i][j];
                }

                /*
                 Comparison +1 for i<10
                 Addition & Assignment +2 for i++
                 Comparison +1 for j<count[i]
                 Addition & Assignment +2 for j++
                 Arithmetic(Modulo) & Comparison +2 for  d % 2 == 0
                 */
                counter+=8;
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








