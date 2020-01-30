/**
 * Source Code: SequenceDemo.java
 *
 * Author:  Alp Karavil
 * Student ID:  5827197
 * Assignment:  Program #5 - Prime Sequence
 *
 * Course:  COP 3337 (Intermediate Programming)
 * Section:  U09
 * Instructor:  William Feild
 * Due Date:  1 November 2018, by the beginning of class
 *
 * I hereby certify that this collective work is my own
 * and none of it is the work of any other person or entity.
 * ______________________________________ [Signature]
 *
 * Language: Java
 * Compile/Run:
 * javac SequenceDemo.java PrimeSequence.java SequenceAnalysis.java
 * Sequence.java
 * java SequenceDemo {start number} {number of prime numbers to be sequenced}
 *
 * Example: java SequenceDemo 2 25 <---- The output of primes will start with
 * the next prime number after 2, which is 3, and will include a sequence of 25
 * primes that come after 2 (3, 5, 7...)
 *
 * WARNING: Make sure the Sequence interface (Sequence.java) is in the same
 * directory as all other files (the source code).
 *
 * ------------------------------------------------------------------
 *
 * Description:
 *
 * This is a demo class for the PrimeSequence class, which implements the
 * Sequence interface, to provide the user with a sequence of prime integers.
 *
 * This class also utilizes the SequenceAnalysis class, which allows the user
 * to record and access the history/log of the Sequence run and statistics
 * about the last digits of the Sequence that was last run. These statistics
 * include counters for the last digits and the percentage of occurrence of
 * the last digits of the Sequence analyzed.
 *
 * In this class, a SequenceAnalysis object is created, then the
 * analyzeAndLog() method is called with the PrimeSequence object, the amount
 * of values, and the lowest value that should be logged in the Sequence.
 *
 * The Sequence parameter is initialized in the main method, and the amount
 * of values that should be accepted from the Sequence, and the min/lowest
 * value that should be logged in the PrimeSequence is inputted by the user
 * while initializing the program.
 *
 * The start value inputted by the user is 1 less than the minimum value
 * inputted as the minimum value in the parameter refers to the minimum prime
 * value that should be allowed into the Sequence log and counters.
 *
 * After the analyzeAndLog() method us called, the getter methods of the
 * SequenceAnalysis class are utilized. To print a square of the prime
 * sequence this class will request an ArrayList of sequence values, and then
 * print them in a "square" format. After printing them as a square, last
 * digit counters and percentage values are requested from the
 * SequenceAnalysis class and a last digit histogram is constructed.
 *
 * Input:
 *
 * Input will be provided on the command-line. Two input constants will be
 * placed on the command-line upon execution. For example:
 * java SequenceDemo 2 25 ; where the prime sequence will start with the next
 * prime after the first number (3), and the second number (n) is the number of
 * prime numbers to be sequenced (25). Both numbers will be integers and both
 * numbers must be 1 or greater.
 *
 * Command-line input is validated. If either input fails validation, the
 * program will display why the input failed, and will be gracefully
 * terminated, using System.exit(1). .
 *
 * Output:
 *
 * This demo class outputs a table of n prime numbers. The table will be as
 * close to “square” as possible - i.e. same number of rows and columns -
 * with at most 10 entries per row. All entries will be right-aligned.
 *
 * It also includes a “histogram” of the occurrences of the last digit [0-9] of
 * each prime number. The histogram is horizontal (not vertical) and is scaled
 * (as a percentage %), with a legend at the bottom to explain the
 * scale and display the total count of primes. Fraction percentages are
 * rounded up to the nearest integer percentage.
 *
 * Process:
 * 1. Validate user arguments
 * 2. Create PrimeSequence object
 * 3. Create SequenceAnalysis object
 * 3. Analyze and log the Sequence with SequenceAnalysis class
 * This analysis includes counting the last digits of each prime number,
 * calculating the percentage of occurrence, and storing each prime
 * sequence value.
 * 4. Print output
 *
 * An algorithm to very if a number is prime is used. This algorithm explanation
 * can be found in the PrimeSequence class and also at:
 * https://planetmath.org/howtofindwhetheragivennumberisprimeornot
 *
 * Known Bugs: None
 **/

//Import ArrayList for Sequence history from SequenceAnalysis

import java.util.ArrayList;
//Import Scanner for integer parsing of user console arguments
import java.util.Scanner;

public class SequenceDemo
{
   public static void main(String[] commandLineArguments)
   {
      final int ARGUMENTS_MIN_USER_INPUT = 1;
      final int ARGUMENTS_REQUIRED = 2;
      //Validate the command line args, exit if there is an error.
      validateArgs(commandLineArguments, ARGUMENTS_MIN_USER_INPUT,
                   ARGUMENTS_REQUIRED);

      //Initialize objects
      Sequence myPrimeSequence = new PrimeSequence();
      SequenceAnalysis sequenceStats = new SequenceAnalysis();

      //The min value allowed in the sequence is the input + 1
      final int NEXT_VALUE_OFFSET = 1;
      final int MIN_VALUE_TO_PROCESS =
              Integer.parseInt(commandLineArguments[0]) + NEXT_VALUE_OFFSET;

      //Store how many values to process
      final int VALUES_TO_PROCESS = Integer.parseInt(commandLineArguments[1]);
      //Analyze sequence output
      sequenceStats.analyzeAndLog(myPrimeSequence, VALUES_TO_PROCESS,
                                  MIN_VALUE_TO_PROCESS);

      //Store sequence name and history ArrayList
      final String SEQUENCE_NAME = "prime";
      ArrayList<Integer> sequenceHistory = sequenceStats.getSequenceLog();
      //Print square containing sequence outputs/history
      printSequenceSquare(sequenceHistory, SEQUENCE_NAME);

      //Store last digit occurrence values
      double[] lastDigitPercentage = sequenceStats
              .getLastDigitPercentageArray();
      int[] lastDigitCount = sequenceStats.getLastDigitCountArray();
      //Print histogram containing last digit occurrence
      printSequenceLastDigitHistogram(lastDigitPercentage, lastDigitCount);

   }

   /**
    * This method is used to validate the arguments used when executing the
    * SequenceDemo program. The method accepts an Array of strings, which is
    * inputted by the user while executing the program, and a minimum integer
    * allowed for the arguments. If the user inputs a string, or any form of
    * value that is not an integer, the program will terminate.
    *
    * If there are more or less than 2 arguments, the program will exit with
    * a code of 1.
    *
    * If the arguments are lower than the minimum input allowed, which is
    * currently 1 for both values, the argument will exit with a code of 1.
    *
    * If the arguments are not both integers, the program will exit with a
    * code of 1.
    *
    * If all of these conditions are satisfied, the program will not exit.
    *
    * @param commandLineArguments Arguments inputted by the user in the
    *                             command line
    * @param ARGUMENTS_MIN_INPUT  Minimum integer value allowed for the arguments
    */
   private static void validateArgs(String[] commandLineArguments,
                                    int ARGUMENTS_MIN_INPUT,
                                    int ARGUMENTS_REQUIRED)
   {
      final int EXIT_CODE_1 = 1;

      //Validate argument length
      if (commandLineArguments.length != ARGUMENTS_REQUIRED)
      {
         System.out.println(
                 "ERROR: Please make sure you have " + ARGUMENTS_REQUIRED +
                 " integer arguments for Sequence analysis. ");
         System.out.println("Example: java SequenceDemo 2 25 <--- This will" +
                            " print 25 prime numbers after the number 2.");
         System.exit(EXIT_CODE_1);
      }

      //Concatenate the arguments as a String for Scanner use.
      String CONCATENATED_ARGUMENTS = "";
      for (int currentArgument = 0;
           currentArgument < commandLineArguments.length; currentArgument++)
      {
         CONCATENATED_ARGUMENTS += commandLineArguments[currentArgument] + " ";
      }

      Scanner userArguments = new Scanner(CONCATENATED_ARGUMENTS);

      //Parse and validate the arguments to an ArrayList
      ArrayList<Integer> parsedArguments = new ArrayList<Integer>();
      while (userArguments.hasNextInt())
      {
         int thisArgument = userArguments.nextInt();

         //If argument is lower than minimum allowed, exit program.
         if (thisArgument < ARGUMENTS_MIN_INPUT)
         {
            System.out.println("ERROR: Please make sure your arguments are " +
                               "greater or equal to " + ARGUMENTS_MIN_INPUT);
            System.exit(EXIT_CODE_1);
         }
         else
         {
            parsedArguments.add(thisArgument);
         }
      }

      //If the integer arguments are less than required, exit program.
      if (parsedArguments.size() != ARGUMENTS_REQUIRED)
      {
         System.out.println(
                 "ERROR: Please make sure you have " + ARGUMENTS_REQUIRED +
                 " integer arguments for Sequence analysis. String arguments" +
                 " are not allowed." + " ");
         System.out.println("Example: java SequenceDemo 2 25 <--- This will" +
                            " print 25 prime numbers after the number 2.");
         System.exit(EXIT_CODE_1);
      }
   }

   /**
    * Prints a table of a sequence output. The table will be as close to
    * "square" as possible - i.e. same number of rows and columns - with at
    * most 10 entries per row. All entries will be right-aligned.
    *
    * The amount of columns is calculated after the amount of rows is. This
    * is done through dividing the amount of values by the amount of rows,
    * then rounding it UP so that every value can fit in the "square".
    *
    * The user is required to input the sequence output as an ArrayList
    * object, and also provide a name for it which will be used while
    * printing the table of
    * elements.
    *
    * @param sequenceArray An ArrayList which holds the value of the sequence
    * @param sequenceName  Name of the sequence (Example: "prime")
    */
   private static void printSequenceSquare(ArrayList<Integer> sequenceArray,
                                           String sequenceName)
   {
      final int MAXIMUM_ROWS = 10;
      final int MAXIMUM_ROW_VALUE_COUNT = 100;
      //rows and columns will be calculated dynamically
      int rows = 0;
      int columns = 0;
      int valueCount = sequenceArray.size();

      if (valueCount >= MAXIMUM_ROW_VALUE_COUNT)
      {
         rows = MAXIMUM_ROWS;
         //Columns = valueCount / rows, rounded up
         columns = (int) Math.ceil(valueCount / (double) rows);
      }
      else
      {
         rows = (int) Math.ceil(Math.sqrt(sequenceArray.size()));
         //Columns = valueCount / rows, rounded up
         columns = (int) Math.ceil(valueCount / (double) rows);
      }


      //Print the "square" output
      System.out.println(
              "\nPrinting a sequence of " + sequenceArray.size() + " " +
              sequenceName + " numbers: \n");

      for (int valueCounter = 0; valueCounter < sequenceArray.size();
           valueCounter++)
      {
         //If current row is filled up, move onto second row
         if (valueCounter % columns == 0 && valueCounter != 0)
         {
            System.out.printf("\n%6d ", sequenceArray.get(valueCounter));
         }
         else //If current row is not full, keep printing on same row
         {
            System.out.printf("%6d ", sequenceArray.get(valueCounter));
         }
      }

      System.out.println();
   }

   /**
    * This method prints a histogram analysis of a sequence's last digits.
    * The histogram will include a representation of the percentage, in which
    * each percentage point is represented by an asterisk ("*"), a numeric
    * occurrence count, and a numeric occurrence percentage of the last
    * digits.
    *
    * The percentage values are rounded up to the closest integer.
    *
    * The representation of the percentage by the use of asterisks ("*") will
    * be dynamically scaled to the largest last digit percentage amount of
    * the sequence output.
    *
    * @param lastDigitPercentage Array holding the percentage of occurrence
    *                            of the last digit in a sequence
    * @param lastDigitCounter    Array holding the occurrence count of the last
    *                            digit in a sequence
    */
   private static void printSequenceLastDigitHistogram(
           double[] lastDigitPercentage, int[] lastDigitCounter)
   {
      //The maximum digit a number can end with
      final int MAX_DIGIT = 9;
      //String representation of 1% using an asterisk
      final String ONE_PERCENT = "*";

      //Find the highest digit percentage of occurrence for histogram scaling
      int maxDigitPercentage = 0;
      for (double digitCountPercent : lastDigitPercentage)
      {
         if (digitCountPercent > maxDigitPercentage)
         {
            maxDigitPercentage = (int) Math.ceil(digitCountPercent);
         }
      }

      //Store the total percentage and count for final output
      int digitPercentageTotal = 0;
      int digitCountTotal = 0;

      System.out.println("\nLast Digit Histogram:");

      //Print the histogram
      for (int currentDigit = 0; currentDigit <= MAX_DIGIT; currentDigit++)
      {
         //Update count and percentage counters
         int roundedPercentage = (int) Math
                 .ceil(lastDigitPercentage[currentDigit]);

         digitPercentageTotal += roundedPercentage;
         digitCountTotal += lastDigitCounter[currentDigit];

         System.out.print("[" + currentDigit + "]");

         //Generate string representation of the percentage of occurrence
         String DIGIT_PERCENTAGE = "";
         for (int i = 1; i <= roundedPercentage; i++)
         {
            DIGIT_PERCENTAGE += ONE_PERCENT;
         }

         System.out.printf("%-" + maxDigitPercentage + "s   (%-5s %3d%%)\n",
                           DIGIT_PERCENTAGE,
                           lastDigitCounter[currentDigit] + ",",
                           roundedPercentage);
      }

      //Print final output using the counters above
      System.out.printf("%-" + (maxDigitPercentage + 3) + "s   (%-5s %3d%%)\n",
                        "Total (actual count, %)  ", digitCountTotal + ",",
                        digitPercentageTotal);
      System.out.println("\nScaled as %, each " + ONE_PERCENT + " = 1%");
      System.out.println(
              "Total count may vary slightly from 100% due to rounding");
   }

}