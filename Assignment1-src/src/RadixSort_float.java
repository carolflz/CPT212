
/** 2. Modify the radix sort algorithm to sort floating point number. The program 
must be a modification, and not a different implementation or variant of 
radix sort. (30%) **/

import java.util.Arrays;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
// import javax.management.openmbean.ArrayType;

public class RadixSort_float {
    // Counter for counting primitive operations
    static long counter = 0;

    public static void main(String[] args) {
        int size = 1;
        int wholeNum_figures = 1;
        int decimal_figures = 1;
        for (; size < 100; wholeNum_figures += 2,decimal_figures += 1 , size *= 2) {
                // Generate random array of size 10^i
                double[] arr = generateRandomArray(size, wholeNum_figures, decimal_figures);

                // Call radixSort method to sort the input array
                double[] sortedArray = radixSort(arr);

                // Print the final sorted array
                System.out.println("Final sorted array:");
                System.out.println(Arrays.toString(sortedArray));
                System.out.println("Number of operations:" + counter + "\n");
            
        }
    }

    // Radix Sort Method
    public static double[] radixSort(double[] newArr) {
        // Define the maximum number of digits to sort, based on the largest magnitude
        // of the input array
        int maxLengthOfDecimalPlaces = maxLengthOfDecimalPlaces(newArr);
        int maxLengthOfWholeNum = maxLengthOfWholeNum(newArr);
        int maxDigits = maxLengthOfDecimalPlaces + maxLengthOfWholeNum;

        // Initialize 2D arrays (bins) Array1 and Array2 to store values during sorting
        double[][] Array1 = new double[10][newArr.length];
        double[][] Array2 = new double[10][newArr.length];

        // Loop through each digit position up to maxDigits
       counter++; // for assignment of d
        for (int d = 0; d < maxDigits; d++) {
            counter +=3; // for comparison,addition and assignment

            // Initialize counting array to store counts of digits in the current position
            int[] count = new int[10];

            // Calculate the place value of the current digit position
            int placeValue = d - maxLengthOfDecimalPlaces;

            // Initialize a pattern to extract digits at the current position
            DecimalFormat decimalFormat;
            String pattern = "#.#";

            counter +=2; //for arithmetic and comparison
            if ((maxLengthOfDecimalPlaces - d) > 0) {
                pattern = "#." + "#".repeat(maxLengthOfDecimalPlaces - d);
                counter+= 4; // for assignment,arithmetic (addition),method calling, arithmetic(minus)
            }

            decimalFormat = new DecimalFormat(pattern);
            counter+=2; //for assignment and method calling

            // Iterate through the input array to extract digits at the current position
            for (double number : newArr) {
                // Calculate the digit at the current position using integer division and modulo
                String numberString = decimalFormat.format(number / Math.pow(10, placeValue)); // Use decimalFormat to
                                                                                               // prevent Java binary
                                                                                               // floating point error
                counter += 2; //for method calling and arithmetic(division)

                int digit = (int) (Double.parseDouble(numberString) % 10); // Parse the decimalFormat string back to
                                                                           // double format
                counter += 2; //for method calling and arithmetic(modulus)
                
                // Add the value to the appropriate bin in Array1 or Array2 based on digit
                // position

                counter+=2; // for arithmetic(modulus) and comparison
                if (d % 2 == 0) {
                    Array1[digit][count[digit]++] = number;
                    counter+=2; // for addition and assignment
                } else {
                    Array2[digit][count[digit]++] = number;
                    counter+=2; // for addition and assignment
                }

               
            }

            // Initialize an index to track the position in the input array
            int index = 0;

            // Iterate through the bins in Array1 or Array2 and copy values back into the
            // input array

            counter ++; //for initialization of i
            for (int i = 0; i < 10; i++) {
                counter += 3; // for comparison,addition and assignment
                counter++; //for assignment of j=0
                for (int j = 0; j < count[i]; j++) {
                    counter += 3; //for comparison,addition and assignment

                    newArr[index++] = d % 2 == 0 ? Array1[i][j] : Array2[i][j];
                    counter+=4; //for addtion,assignment,arithmetic(modulus)and comparison
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
        return newArr;
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

            /*
             * Comparison +1 for i<arr.length
             * Addition & Assignment +2 for i++
             * Comparison +1 for j<count
             * Addition & Assignment +2 for j++
             */
            counter += 6;
        }
    }

    // Method to find the maximum length of whole numbers of an array of doubles
    // (eg: 1234.45, maxLength = 4)
    public static int maxLengthOfWholeNum(double[] numbers) {
        int maxLength = 0;

        for (double number : numbers) {
            String numberString = Double.toString(number); // Convert Double to String
            int WholeNumLength = numberString.indexOf("."); // Identify the index/position of the decimal point
            maxLength = Math.max(maxLength, WholeNumLength); // Compare and return the maximum length
        } /*
           * Assignment +1 for String numberString
           * Assignment +1 for int WholeNumLength
           * Assignment +1 for maxLength
           * Calling method +1 for Double.toString
           * Calling method +1 for numberString.indexOf
           * Calling method +1 for Math.max
           * Return method +1 for return maxLength;
           */
        counter += 7;
        return maxLength;

    }

    // Method to find the maximum length of decimal places of an array of doubles
    // (eg: 1234.45, maxLength = 2)
    public static int maxLengthOfDecimalPlaces(double[] numbers) {
        int maxLength = 0;

        for (double num : numbers) {
            String str = Double.toString(num); // Convert Double to String
            int decimalIndex = str.indexOf('.'); // Identify the index/position of the decimal point
            int decimalLength = str.length() - 1 - decimalIndex; // Calculate the length of decimal places
            maxLength = Math.max(maxLength, decimalLength); // Compare and return the maximum length

        }
        /*
         * Assignment +1 for String str
         * Assignment +1 for int decimalIndex
         * Assignment +1 for int decimalLength
         * Assignment +1 for maxLength
         * Return method +1 for maxLength
         */
        counter += 5;
        return maxLength;
    }

    // Method to find the maximum length of significant figures of an array of
    // doubles (eg: 1234.45, maxLength = 6)
    public static int maxLengthOfSignificantFigure(double[] numbers) {
        // Add the maximum length of whole number and decimal places
        int maxLength = maxLengthOfWholeNum(numbers) + maxLengthOfDecimalPlaces(numbers);

        return maxLength;
    }

    // Helper method to generate an array of random integers
    private static double[] generateRandomArray(int size, int wholeNum_figures, int decimal_figures) {
        // Initialize a new array of the given size
        double[] arr = new double[size];

        // Initialize a new Random object
        Random rand = new Random();

        // Iterate through the array and assign each element a random integer
        for (int i = 0; i < size; i++) {
            int total_figures = wholeNum_figures + decimal_figures;
            int bound = (int) Math.pow(10, total_figures);
            arr[i] = (double) rand.nextInt(bound) / (double) Math.pow(10, decimal_figures) ;
        }

        // Return the array
        return arr;
    }

}
