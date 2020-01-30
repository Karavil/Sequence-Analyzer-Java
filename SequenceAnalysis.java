/**
 * Source code:  SequenceAnalysis.java
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
 * Language:  Java
 * Compile/Run:
 * javac SequenceAnalysis.java
 *
 * Purpose:
 *
 * This class is used to analyze and log a Sequence object. The
 * user is able to run the sequence until a required value amount is reached,
 * and the user is also able to decline values under the minimum. During the
 * Sequence run, this program records the Sequence output and the last digit
 * of each Sequence value. With this data, this class is then able to return
 * the user the history and last digit count/occurrence percentages regarding
 * the data.
 *
 * The user can create an object of this class, then call the analyzeAndLog()
 * overloaded methods to store a history of sequence values and the last digits
 * of the sequence values. The analyzeAndLog() method requires a Sequence
 * object, how many values should be logged, and if needed, the overloaded
 * method with a minimum value that should be logged of the sequence output
 * can be requested.
 *
 * After the analysis is complete, the user can request information about the
 * Sequence with the getLastDigitPercentageArray(), getLastDigitCountArray()
 * and getSequenceLog() methods.
 *
 *
 * Inherits From:  None
 *
 * Interfaces: None
 *
 * +-----------------------------------------------------------------------
 *
 * Constants:
 *
 * This class has no constants
 *
 * +-----------------------------------------------------------------------
 *
 * Constructors:
 *
 * SequenceAnalysis() - Default constructor, resets the private instance
 * variables, which then allows the user to use the analyze methods.
 *
 * +-----------------------------------------------------------------------
 *
 * Class Methods:
 *
 * private void resetAnalysis() - Resets all instance variables to 0
 * Arguments: No arguments
 * Output: No output
 *
 * +-----------------------------------------------------------------------
 *
 * Instance Methods:
 *
 * public void analyzeAndLog(Sequence sequenceInput, int valuesToProcess)
 * This method accepts a Sequence, and then records the last digit and the
 * sequence value of each next() call until inputted value count is reached.
 *
 * Arguments:
 * Sequence sequenceInput
 * int valuesToProcess
 * Output:
 * No output
 *
 * public void analyzeAndLog(Sequence sequenceInput, int valuesToProcess, int
 * minValueToProcess)
 * This method accepts a Sequence, and then records the
 * last digit and the sequence value of each next() call until inputted value
 * count is reached. Values under the minimum value are ignored.
 *
 * Arguments:
 * Sequence sequenceInput
 * int valuesToProcess - How many values to accept until termination. (Values
 * lower than minimum are not counted towards this limit)
 * int minValueToProcess - Minimum value that will be added to instance counters
 * Output:
 * No output
 *
 * public int[] getLastDigitPercentageArray()
 * Returns an array of the Sequence's values' last digit percentage of
 * occurrence.
 *
 * Arguments: No arguments
 * Output: An int array of length 10 (int[10]) holding the percentages of
 * last digit occurrences in the last Sequence run.
 *
 * public int[] getLastDigitCountArray()
 * Returns an array of the Sequence's values' counter for each last digit.
 *
 * Arguments: No arguments
 * Output: An int array of length 10 (int[10]) holding the count of each last
 * digit occurrence in the last Sequence run.
 *
 * public ArrayList getSequenceLog()
 * Returns a history/log of all Sequence values that was analyzed/ran.
 *
 * Arguments: No arguments
 * Output: An ArrayList holding a log/history of all the Sequence values
 * during the last run.
 */

//ArrayList for the Sequence value history
import java.util.ArrayList;

public class SequenceAnalysis
{
   //If integer overflow occurs, sequence will output 0
   public final int INT_OVERFLOW_SEQUENCE_OUTPUT = 0;
   //Holds a counter for each last digit of the Sequence
   private int[] digitCounter = new int[10];
   //History of Sequence values in the last run
   private ArrayList<Integer> sequenceLog = new ArrayList<Integer>();
   //Used to get the remainder (last digit) of a prime number
   private final int LAST_DIGIT_CHECKER = 10;


   /**
    * This the default constructor for a SequenceAnalysis object. This
    * constructor will reset/initialize the sequenceLog and digitCount arrays.
    */
   public SequenceAnalysis()
   {
      resetAnalysis();
   }

   /**
    * This method accepts a Sequence, and then records the last digit and the
    * sequence value of each next() call.
    *
    * The last digits are recorded as an integer array, which is updated each
    * next() call, and then the value of the sequence is recorded in an
    * ArrayList containing a history of all sequence next() calls. This
    * method will run until the recorded values reach a count of
    * valuesToProcess.
    *
    * After the analysis is complete, the user can call methods
    * getLastDigitPercentageArray(), getLastDigitCountArray(), getSequenceLog()
    * further information about the sequence analysis.
    *
    * This method will reset all values holding last digit counters and
    * sequence history at the start of each run.
    *
    * @param sequenceInput   Sequence that will be called and analyzed
    * @param valuesToProcess How many values to process before termination
    */
   public void analyzeAndLog(Sequence sequenceInput, int valuesToProcess)
   {
      resetAnalysis();
      //Call the next() Sequence value, store last digit, and store the value
      for (int valueCounter = 0; valueCounter < valuesToProcess; valueCounter++)
      {
         int currentValue = sequenceInput.next();
         int lastDigit = currentValue % LAST_DIGIT_CHECKER;
         digitCounter[lastDigit]++;

         sequenceLog.add(currentValue);
      }
   }

   /**
    * This method accepts a Sequence, and then records the last digit and the
    * sequence value of each next() call. The last digits are recorded as an
    * integer array, which is updated each next() call, and then the value of
    * the sequence is recorded in an ArrayList containing a history of all
    * sequence next() calls. This method will run until the recorded values
    * reach a count of valuesToProcess.
    *
    * This is an overloaded method which requires an integer value
    * minValueToProcess. If this value is inputted, any value returned by
    * the sequence lower than the minimum will not be recorded in the last
    * digit counter or the sequence log (history).
    *
    * After the analysis is complete, the user can call methods
    * getLastDigitPercentageArray(), getLastDigitCountArray(), getSequenceLog()
    * further information about the sequence analysis.
    *
    * This method will reset all values holding last digit counters and
    * sequence history at the start of each run.
    *
    * @param sequenceInput     Sequence to be called and analyzed
    * @param valuesToProcess   How many values to accept until termination. (Values
    *                          * lower than minimum are not counted towards this limit)
    * @param minValueToProcess Minimum value that will be analyzed for a last
    *                          digit, and added to the Sequence log
    */
   public void analyzeAndLog(Sequence sequenceInput, int valuesToProcess,
                             int minValueToProcess)
   {
      resetAnalysis();
      //Call the next() Sequence value, store last digit, and store the value
      int valueCounter = 0;
      while (valueCounter < valuesToProcess)
      {
         int currentValue = sequenceInput.next();
         //Make sure value is greater or equal to min, or the overflow value
         if (currentValue >= minValueToProcess ||
             currentValue == INT_OVERFLOW_SEQUENCE_OUTPUT)
         {
            valueCounter++;
            int lastDigit = currentValue % LAST_DIGIT_CHECKER;
            digitCounter[lastDigit]++;
            sequenceLog.add(currentValue);
         }
      }
   }

   /**
    * This method will return an integer array, with a length of 10, which
    * represent the percentage of occurrences of the last digits in the
    * analyzed Sequence outcome. Each index respectively represents a digit,
    * thus when accessed will return its percentage of occurrence.
    *
    * @return An integer array holding the percentage of occurrence of each
    * last digit
    */
   public double[] getLastDigitPercentageArray()
   {
      //Double array that holds last digit percentage of occurrence
      double[] lastDigitPercentage = new double[10];
      //Total digit values in the last analyzed array (amount of values)
      int totalDigitCount = 0;
      //Maximum digit a number can end with
      final int MAX_DIGIT = 9;

      //Calculate the total digit count (which should be equal to value count)
      for (int digitTotals : digitCounter)
      {
         totalDigitCount += digitTotals;
      }

      //Calculate the percentage of each digit occurrence, and store it
      for (int currentDigit = 0; currentDigit <= MAX_DIGIT; currentDigit++)
      {
         float digitRatio =
                 digitCounter[currentDigit] / (float) totalDigitCount;

         final int PERCENTAGE_SCALER = 100;
         float digitPercentage = PERCENTAGE_SCALER * digitRatio;

         lastDigitPercentage[currentDigit] = digitPercentage;
      }

      return lastDigitPercentage;
   }

   /**
    * This method will return an integer array, with a length of 10, which
    * represent the occurrence count of the last digits in the analyzed
    * Sequence outcome.
    *
    * This method can be called, and then the integer array can be accessed
    * to get the amount of times that integer occurred in the analyzed sequence.
    *
    * @return An integer array holding the count of occurrence of each
    * last digit
    */
   public int[] getLastDigitCountArray()
   {
      return digitCounter;
   }

   /**
    * This method will return an ArrayList holding a log/history of the
    * values analyzed and recorded in the sequence.
    *
    * If a value requested from the sequence was under the minimum allowed
    * value allowed, it will not be allowed into the sequence log, thus not
    * included in the ArrayList returned with this method.
    *
    * @return An ArrayList holding all the values of the Sequence that were
    * analyzed
    */
   public ArrayList<Integer> getSequenceLog()
   {
      return sequenceLog;
   }

   /**
    * This method will reset the digitCounter and sequenceLog variables. This
    * method will be called in the constructor, and when analysis methods,
    * such as analyzeAndLog(), is called.
    *
    * If this method is not called, complications such as digitCounter and
    * sequenceLog recording values of previous Sequences and its counter
    * information.
    */
   private void resetAnalysis()
   {
      digitCounter = new int[10];
      sequenceLog = new ArrayList<Integer>();
   }
}