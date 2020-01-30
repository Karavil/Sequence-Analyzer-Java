/**
 * Source code:  PrimeSequence.java
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
 * javac PrimeSequence.java
 *
 * Purpose:  This class implements the next() method from the Sequence class,
 * an isPrime() method to determine if a number is prime to supply the user
 * with a sequence of prime numbers, starting at 2.
 *
 * The user is only required, and allowed to use the next() method, inherited
 * from the Sequence interface, to request prime numbers.
 *
 * If the current prime number reaches any value over the max integer allowed
 * (integer overflow), an integer value of 0 is returned.
 *
 * The isPrime() method simulates these steps, given on planetmath.org, to
 * check if a number is prime or not.
 * https://planetmath.org/howtofindwhetheragivennumberisprimeornot
 *
 * 1) Find a whole number nearly greater than the square root of potential
 * prime number (input). Lets call this number K.
 *
 * 2) Test whether the potential prime is divisible by any prime numbers
 * smaller than K.
 *
 * 3) If divisible, then the potential prime (input) is not a prime. If it is
 * not divisible by any prime lower than K, then the possible prime (input)
 * is proven to be a prime number.
 *
 * The isPrime() method duplicates the process above with a few changes. These
 * changes include:
 *
 * 1) If the square root of the potential prime (input) is a whole number, it
 * implies that the potential prime is a perfect square, thus not a prime.
 * This will return a false value for the method.
 *
 * 2) If the number is not a perfect square, then we have to check if it is
 * divisible by any prime numbers smaller than the nearly greater value of
 * its square root, which is number K. To achieve this, the Math.ceil()
 * method is utilized to get a whole number (integer) nearly greater than the
 * square root. The next step is to check if the potential prime is divisible
 * by primes smaller than number K. PROBLEM: As a computer doesn't have an
 * "understanding" of what a prime number is, we can't check if it is
 * divisible by smaller primes, so our next best bet is checking if it is
 * divisible by any number smaller than K, which includes smaller primes and
 * their multipliers (which do not effect the outcome).
 *
 * If any of these numbers can divide our potential prime, then a false value
 * is returned as it is proven not to be a prime. If no false value has been
 * returned, then our potential prime has been proven to be a prime number
 * and a true value will be returned.
 *
 * Inherits From:  None
 *
 * Interfaces: Sequence.java
 *
 * +-----------------------------------------------------------------------
 *
 * Constants:
 *
 * NON_PRIME_START - Start value of the prime search
 *
 * +-----------------------------------------------------------------------
 *
 * Constructors:  This class has no constructors.
 *
 * +-----------------------------------------------------------------------
 *
 * Class Methods:
 *
 * private boolean isPrime(int potentialNumber)
 * Returns a boolean value respective to if that number is a prime or not.
 * Arguments:
 * int potentialNumber - will be checked if it is prime or not
 * Output:
 * boolean value (true if argument is prime, false if not)
 *
 * +-----------------------------------------------------------------------
 *
 * Instance Methods:
 *
 * public int next() - Returns the next value, a prime number, in the sequence.
 * Arguments: No arguments
 * Output: int prime number
 */

public class PrimeSequence implements Sequence
{
   //Start value of the prime search
   public final int NON_PRIME_START = 1;
   private int currentNumber = NON_PRIME_START;
   private boolean primeIntegerOverflow = false;

   /**
    * Returns the next prime number in the sequence.
    *
    * This method is initialized at a value of 1, as it is the first
    * non-prime positive number, and every time the next() method is called
    * the number is increased until a prime number is hit. This method is
    * able to check if a number is prime by using the isPrime(input) method
    * which supplies a true or false depending on if the input is a prime
    * number or not.
    *
    * If the current number goes over the maximum integer value (which
    * indicates that an integer overflow will occur), an integer value of 0
    * will be returned. If this scenario is reached, a boolean value
    * primeIntegerOverflow will be declared true, and further next() calls
    * will return 0.
    *
    * @return The next number in the sequence.
    */
   public int next()
   {
      //If integer overflow has happened, return a 0 value.
      if (primeIntegerOverflow) {
         return 0;
      }

      //Increase current number is not a prime and no int overflow was detected
      do
      {
         //If maximum integer is reached, next value will overflow
         if (currentNumber == Integer.MAX_VALUE)
         {
            primeIntegerOverflow = true;
         }
         currentNumber++;
      } while (!isPrime(currentNumber) && !primeIntegerOverflow);

      return currentNumber;
   }

   /**
    * Returns true if inputted integer is a prime, false otherwise.
    *
    * According to planetmath.org, these are the steps for checking if a number
    * is prime or not:
    * https://planetmath.org/howtofindwhetheragivennumberisprimeornot
    *
    * (This algorithm is explained more in detail in the class description.)
    *
    * This method duplicates the process above with a few changes. These changes
    * include:
    *
    * 1) If the square root of the potential prime (input) is a whole number, it
    * implies that the potential prime is a perfect square, thus not a prime.
    * This will return a false value for the method.
    *
    * 2) If the number is not a perfect square, then we have to check if it is
    * divisible by any prime numbers smaller than the nearly greater value of
    * its square root, which is number K. To achieve this, the Math.ceil()
    * method is utilized to get a whole number (integer) nearly greater than the
    * square root. The next step is to check if the potential prime is divisible
    * by primes smaller than number K. PROBLEM: As a computer doesn't have an
    * "understanding" of what a prime number is, we can't check if it is
    * divisible by smaller primes, so our next best bet is checking if it is
    * divisible by any number smaller than K, which includes smaller primes and
    * their multipliers (which do not effect the outcome).
    *
    * If any of these numbers can divide our potential prime, then a false value
    * is returned as it is proven not to be a prime. If no false value has been
    * returned, then our potential prime has been proven to be a prime number
    * and a true value will be returned.
    *
    * @param potentialPrime The number that will be tested to be prime
    * @return True if the number is prime, false otherwise.
    */
   private boolean isPrime(int potentialPrime)
   {
      final int DIVISION_BY_ONE = 1;
      final int NO_REMAINDER = 0;
      double inputSquareRoot = Math.sqrt(potentialPrime);

      //If the number is a perfect square, it won't have any trailing numbers
      if (inputSquareRoot % DIVISION_BY_ONE == NO_REMAINDER)
      {
         return false;
      }

      //Round up to the closest integer for a division check
      int nearlyGreaterRoot = (int) Math.ceil(inputSquareRoot);
      for (int remainderChecker = 2; remainderChecker < nearlyGreaterRoot;
           remainderChecker++)
      {
         //If divisible, it is not a prime, thus false
         if (potentialPrime % remainderChecker == NO_REMAINDER)
         {
            return false;
         }
      }

      //If it was not divisible by any number, then it is a prime, thus true
      return true;
   }
}