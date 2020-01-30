/**
 * Source code:  Sequence.java
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
 * javac Sequence.java
 *
 * Purpose:  This is an interface for Sequences which can be utilized to
 * return sequences such as random, prime or etc. numbers.
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
 * This class has no constructors.
 *
 * +-----------------------------------------------------------------------
 *
 * Class Methods:
 *
 * This class has no methods.
 *
 * +-----------------------------------------------------------------------
 *
 * Instance Methods:
 *
 * int next() - This methods returns the next value of the implemented Sequence
 */

public interface Sequence
{
   /**
    * This interface method finds the next element in the sequence.
    *
    * @return The value of the next element.
    */
   int next();
}