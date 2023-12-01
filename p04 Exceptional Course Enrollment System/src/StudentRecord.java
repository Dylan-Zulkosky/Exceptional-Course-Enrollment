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
// Persons: None
// Online Sources: I used the StudentRecord javadocs
//////////////// (https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/fall2023/p04/doc
//////////////// /StudentRecord.html) to help create the methods in this project.
// I used educative (https://www.educative.io/answers/what-is-objectsequals-in-java) to help with
// the object equals method.
//
///////////////////////////////////////////////////////////////////////////////

import java.util.zip.DataFormatException;

/**
 * The StudentRecord class checks the validity of a students name, email, campusID, and prereqs to
 * be used if they can be enrolled, dropped, or added to the waitlist in the
 * ExceptionalCourseEnrollment class.
 * 
 * Each StudentRecord must have a valid name, email, campusID, and prereqs in this order made by the
 * toString method to be used for enrollment opportunities
 */
public class StudentRecord {

  // Unique id of student
  private String campusID;
  // Email of student
  private String email;
  // Boolean for student satisfying prerequisities
  private boolean isPreReqSatisfied;
  // Name of student
  private String name;

  /**
   * Constructor for a student record object. Assigns values to all fields.
   * 
   * @param name     the name of the student
   * @param email    the email of the student
   * @param campusID the campusID of the student
   * @param preReq   the boolean representing if the student satisfies the prerequisites
   * @throws DataFormatException with message "Bad name, email, or campusID!" if name or email or
   *                             campusID are NOT valid
   */
  public StudentRecord(String name, String email, String campusID, boolean preReq)
      throws DataFormatException {
    if (!isValidName(name) || !isValidEmail(email) || !isValidCampusID(campusID)) {
      throw new DataFormatException("Bad name, email, or campusID!");
    }

    this.name = name;
    this.email = email;
    this.campusID = campusID;
    this.isPreReqSatisfied = preReq;
  }

  /**
   * Validator method for a student's name
   * 
   * @param name the student's name
   * @return true if and only if the name is not null and not blank
   */
  public static boolean isValidName(String name) {
    if (name == null) {
      return false; // Null name
    }

    // Remove whitespace
    name = name.strip();

    if (name.isEmpty()) {
      return false; // Empty name
    }
    // Valid name
    return true;
  }

  /**
   * Validator method for a student's email
   * 
   * @param email the student's email
   * @return true if and only if the email is not null, has one @ symbol, ends with .edu, is between
   *         0 and 40 characters (EXCLUSIVE, that is, 0 and 40 are not allowed lengths but 1 and 39
   *         are), and has at least two characters before the @ symbol.
   */
  public static boolean isValidEmail(String email) {
    if (email == null) {
      return false; // Null email
    }

    // Remove whitespace
    email = email.strip();
    int emailLength = email.length();

    if (emailLength < 1 || emailLength > 39) {
      return false; // Not accepted email length
    }

    int index = email.indexOf('@');
    if (index == -1 || index < 2) {
      return false; // No '@' character or in first 2 positions of email
    }

    if (!email.substring(email.length() - 4).equals(".edu")) {
      return false; // Email does not end with ".edu"
    }
    // Valid email
    return true;
  }

  /**
   * Validator method for a student's id
   * 
   * @param campusID the student's campusID
   * @return true if and only if the campusID is not null and can be parsed to a long with
   *         10-digits. Extra leading and trailing whitespace should be disregarded.
   */
  public static boolean isValidCampusID(String campusID) {
    if (campusID == null) {
      return false; // Null campusID
    }

    // Remove whitespace
    campusID = campusID.strip();

    if (campusID.length() != 10) {
      return false; // Not 10 numbers long
    }

    try {
      Long.parseLong(campusID);
      return true; // Parsed as a 10 digit long
    } catch (NumberFormatException e) {
      return false; // Not parsed as a 10 digit long
    }
  }

  /**
   * Getter method for a student's name
   * 
   * @return the student's name
   */
  public String getName() {
    return name;
  }

  /**
   * Getter method for a student's email
   * 
   * @return the student's email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Getter method for a student's campusID
   * 
   * @return the student's campusID
   */
  public String getCampusID() {
    return campusID;
  }

  /**
   * Returns true if this student record satisfies the pre-requisites of the course
   * 
   * @return true if this student record satisfies the pre-requisites of the course
   */
  public boolean isPrerequisiteSatisfied() {
    return isPreReqSatisfied;
  }

  /**
   * Compared this StudentRecord to the specified object
   * 
   * @return true if anObject is instanceof StudentRecord and has the same campusID as this
   *         StudentRecord.
   */
  public boolean equals(Object other) {
    // Check to see if the other object is null
    if (other == null) {
      return false;
    }

    // Check if the other object is an instance of StudentRecord
    if (!(other instanceof StudentRecord)) {
      return false;
    }

    // Other object
    StudentRecord object = (StudentRecord) other;

    // Compare the campusID of this StudentRecord with the other StudentRecord
    if (this.campusID == null) {
      // Checks null for campusID
      return object.campusID == null;
    } else {
      return this.campusID.equals(object.campusID);
    }
  }


  /**
   * Returns a string representation of this student record in the following format (comma followed
   * by a space ", " separated): <BR>
   * name, email, campusID, preReqValue
   * 
   * @return the string representation of a studentRecord as described above
   */
  @Override
  public String toString() {
    return name + ", " + email + ", " + campusID + ", " + isPreReqSatisfied;
  }
}
