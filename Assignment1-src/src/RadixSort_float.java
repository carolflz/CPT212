/** 2. Modify the radix sort algorithm to sort floating point number. The program 
must be a modification, and not a different implementation or variant of 
radix sort. (30%) **/

import java.util.Arrays;

public class RadixSort_float {

    public static void main(String[] args) {
        // Sample input array to be sorted
        double[] arr = {275.4,87.2222,87.2221,90.32,20.12,426.1,61.9,409.5,170.8,107.888,677.3,503.0};
        
        // Call radixSort method to sort the input array
        double[] sortedArray = radixSort(arr);
        
        // Print the final sorted array
        System.out.println("Final sorted array:");
        System.out.println(Arrays.toString(sortedArray));
    }

 /** Method 1: Can work but got 4 iterations
  * To find the maximun digits for the floationg-point number in an array
    public static int maxFloatDigits(double[] arr) {
        int maxDigits = 0;
        for (double num : arr) {
            String[] parts = Double.toString(num).split("\\.");
            int digits = parts[0].length() + parts[1].length();
            if (digits > maxDigits) {
                maxDigits = digits;
            }
        }
        return maxDigits;
    }

    public static double[] radixSort(double[] arr) {
        // Define the maximum number of digits to sort, up to the hundredth place
        int maxDigits = maxFloatDigits(arr); **/

    //Method 2: Settle in 3 iterations    
    public static double[] radixSort(double[] arr) {
        // Define the maximum number of digits to sort, based on the largest magnitude of the input array
        int maxDigits = maxLengthOfSignificantFigure(arr);
   

        // Initialize 2D arrays (bins) Array1 and Array2 to store values during sorting
        double[][] Array1 = new double[10][arr.length];
        double[][] Array2 = new double[10][arr.length];

        // Loop through each digit position up to maxDigits
        for (int d = 0; d < maxDigits; d++) {
            // Initialize counting array to store counts of digits in the current position
            int[] count = new int[10];
            
            // Iterate through the input array to extract digits at the current position
            for (double i : arr) {
                // Calculate the digit at the current position using integer division and modulo
                int digit = (int) ((i / (int) Math.pow(10, d)) % 10);
                
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
                    Arrays.fill(Array1[i], 0, count[i], 0.0);
                }
            }
        }
        // Return the sorted input array
        return arr;
    }

    // Helper method to print 2D bin arrays with their counts
    private static void print2DArray(double[][] arr, int[] count) {
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

    public static int findLongestSignificantValue(double[] arr) {
        int maxSignificantDigits = 0;
        for (double num : arr) {
            String numStr = String.valueOf(Math.abs(num));
            String trimmed = numStr.replaceFirst("^0+", "")
                                   .replaceFirst("0+$", "");
            maxSignificantDigits = Math.max(maxSignificantDigits, trimmed.length());
        }
        return maxSignificantDigits;
    }

    public static int maxLengthOfWholeNum(double[] numbers) {
        int maxLength = 0;

        for (double number : numbers) {
            String numberString = Double.toString(number);
            int WholeNumLength = numberString.indexOf(".");
            if (WholeNumLength != -1) {
                maxLength = Math.max(maxLength, WholeNumLength);
            }
        }
        return maxLength;
    }

    public static int maxLengthOfDecimalPlaces(double[] numbers) {
        int maxLength = 0;

        for(double num : numbers) {
            String str = Double.toString(num);
            int decimalIndex = str.indexOf('.');
            if(decimalIndex != -1) {
                int decimalLength = str.length() - 1 - decimalIndex;
                maxLength = Math.max(maxLength, decimalLength);
            }
        }
        return maxLength;
    }

    public static int maxLengthOfSignificantFigure(double[] numbers) {
        int maxLength = maxLengthOfWholeNum(numbers) + maxLengthOfDecimalPlaces(numbers);
        
        return maxLength;
    }
}

