//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Creating an improved course enrollment system
// Course: CS 300 Fall 2023
//
// Author: Dylan Zulkosky
// Email: dzulkosky@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: None
// Partner Email: None
// Partner Lecturer's Name: None
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

/**
 * This utility class implements unit tests to check the correctness of methods defined in the
 * ExceptionalCourseEnrollment class of the Exceptional Course Enrollment System program.
 *
 */
public class ExceptionalCourseEnrollmentTester {

  /**
   * Ensures the correctness of the StudentRecord.equals() method.
   * 
   * Defines at least two StudentRecord objects and checks for the following test cases:<BR>
   * (1) StudentRecord.equals() is expected to return true when passed a StudentRecord with the same
   * campusID as the current one. You can compare a student record to itself.<BR>
   * (2) StudentRecord.equals() is expected to return false when passed a StudentRecord with
   * campusID different from the campusID of the current student record. (3) StudentRecord.equals()
   * is expected to return false when passed a String as input (4) StudentRecord.equals() is
   * expected to return false when passed the reference null as input
   * 
   * 
   * @return true if and only if the tester verifies a correct functionality and false if at least
   *         one bug is detected
   */
  public static boolean studentRecordEqualsTester() {
    // Test 1: Checks to see if returns true from same campusID
    boolean t1 = false;
    try {
      StudentRecord student1 = new StudentRecord("Dylan", "dzulkosky@wisc.edu", "9084387290", true);
      t1 = student1.equals(student1);
    } catch (DataFormatException e) {
      // Handles exceptions
      System.out.println("CampusID does not match.");
      t1 = false;
    }

    // Test 2: Checks to see if returns false for different campusID
    boolean t2 = false;
    try {
      StudentRecord student1 = new StudentRecord("Dylan", "dzulkosky@wisc.edu", "9084387290", true);
      StudentRecord student2 = new StudentRecord("Lucas", "lsmith@wisc.edu", "4726409132", true);
      t2 = (!student1.equals(student2));
    } catch (DataFormatException e) {
      // Handles exception
      t2 = false;
    }

    // Test 3: Checks to see if equals() returns false when a string is passed as input
    boolean t3 = false;
    try {
      StudentRecord student1 = new StudentRecord("Dylan", "dzulkosky@wisc.edu", "9084387290", true);
      t3 = (!student1.equals("Not a student"));
    } catch (DataFormatException e) {
      // Handle Exception
      System.out.println("Not right input format.");
      t3 = false;
    }

    // Test 4: Checks to see if return false when passed the reference null as input
    boolean t4 = false;
    try {
      StudentRecord student1 = new StudentRecord("Dylan", "dzulkosky@wisc.edu", "9084387290", true);
      t4 = (!student1.equals(null));
    } catch (DataFormatException e) {
      // Handle exception
      t4 = false;
    }

    // Return test values
    return t1 && t2 && t3 && t4;
  }

  /**
   * Ensures the correctness of the constructor of the StudentRecord class when called with VALID
   * input
   * 
   * @return true if and only if the tester verifies a correct functionality and false if at least
   *         one bug is detected
   */
  public static boolean studentRecordConstructorSuccessful() {
    // Test: student1 (valid input)
    boolean t1 = false;
    try {
      String name = "Dylan";
      String email = "dzulkosky@wisc.edu";
      String campusID = "9084387290";
      boolean preReq = true;

      // Create student record
      StudentRecord student1 = new StudentRecord(name, email, campusID, preReq);

      boolean p1 = student1.getName().equals(name);
      boolean p2 = student1.getEmail().equals(email);
      boolean p3 = student1.getCampusID().equals(campusID);
      boolean p4 = student1.isPrerequisiteSatisfied() == preReq;

      t1 = p1 && p2 && p3 && p4;
    } catch (DataFormatException e) {
      // Handle exception
      t1 = false;
    }
    return t1;
  }

  /**
   * Ensures the correctness of the constructor of the StudentRecord class when called with one
   * INVALID input
   * 
   * @return true if and only if the tester verifies a correct functionality and false if at least
   *         one bug is detected
   */
  public static boolean studentRecordConstructorUnSuccessful() {
    boolean t1 = false;
    try {
      // Invalid data: Blank name
      String name = "";
      String email = "dzulkosky@wisc.edu";
      String campusID = "9084387290";
      boolean preReq = true;

      StudentRecord student1 = new StudentRecord(name, email, campusID, preReq);

      // If the above line doesn't throw an exception, set t1 to true (indicating failure)
      t1 = true;
    } catch (DataFormatException e) {
      // Handle exception
    } catch (Exception e) {
      // Handle any other unexpected exceptions
      t1 = true;
    }
    return !t1;
  }

  /**
   * Ensures the correctness of the searchById() method
   * 
   * Creates an ArrayList which contains at least 2 student records, and defines at least two cases:
   * 
   * (1) successful search<BR>
   * (2) unsuccessful search<BR>
   * 
   * 
   * @throws NoSuchElementException if the search result is not found
   * @return true if and only if the tester verifies a correct functionality and false if at least
   *         one bug is detected
   */
  public static boolean searchByIdTester() {
    String errMsg = "Bug detected: search did not return the expected result.";
    try {
      // Create an arraylist which contains 3 student records
      ArrayList<StudentRecord> records = new ArrayList<StudentRecord>();
      StudentRecord s1 = new StudentRecord("Rob", "rob@wisc.edu", "1234567890", true);
      StudentRecord s2 = new StudentRecord("Joey", "joey@wisc.edu", "1233367890", true);
      StudentRecord s3 = new StudentRecord("NotHere", "no@wisc.edu", "1111167890", true);
      records.add(s1);
      records.add(s2);

      // Finds a student in the arraylist

      StudentRecord r1 = ExceptionalCourseEnrollment.searchById(s1.getCampusID(), records);
      if (r1 != s1) {
        return false;
      }
      // Does'nt find a student not in the array
      try {
        ExceptionalCourseEnrollment.searchById(s3.getCampusID(), records);
        return false; // a NoSuchElementException was not thrown as expected
      } catch (NoSuchElementException e) {
        // check for the error message
        String expectedErrorMessage = "No student record found!";
        if (!e.getMessage().equals(expectedErrorMessage)) {
          System.out
              .println("The NoSuchElementException did not contain the expected error message!");
          return false;
        }
      }

    } catch (Exception e) {
      System.out.println(errMsg);
      return false;
    }


    try {

    } catch (Exception e) {
      return true;
    }
    return true;

  }

  // You are welcome but NOT required to implement additional tester methods at your choice

  /**
   * Runs all the tester methods defined in this class.
   * 
   * @return true if no bugs are detected.
   */
  public static boolean runAllTests() {
    boolean searchTesterOutput = searchByIdTester();
    System.out.println("searchTester: " + (searchTesterOutput ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean studentRecordEqualsTesterOutput = studentRecordEqualsTester();
    System.out.println(
        "studentRecordEqualsTester: " + (studentRecordEqualsTesterOutput ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean studentRecordConstructorSuccessfulOutput = studentRecordConstructorSuccessful();
    System.out.println("studentRecordConstructorSuccessful: "
        + (studentRecordConstructorSuccessfulOutput ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean studentRecordConstructorUnSuccessfulOutput = studentRecordConstructorUnSuccessful();
    System.out.println("studentRecordConstructorUnSuccessful: "
        + (studentRecordConstructorUnSuccessfulOutput ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------");
    return searchTesterOutput && studentRecordEqualsTesterOutput
        && studentRecordConstructorSuccessfulOutput;
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("-----------------------------------------------");
    System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
  }

}
