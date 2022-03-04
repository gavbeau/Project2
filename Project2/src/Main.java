/************************************************************************************************
* CLASS: Main (Main.java)
*
* DESCRIPTION
* This program writes a formatted file containing a 
* student's ID#, name, and tuition total. 
* It takes a formatted input file, adds info to 
* an ArrayList, and performs caluclation prior to 
* writing.
* Calculations are dependent on student type
* (online/on-campus), residency for on-campus
* (resident/non-resident), program fees, tech fees,
* and number of credits. Calculation constants are in
* the TutionConstants class.
* The Main class runs the program dependent on the Student
* class, and its subclasses OnCampusStudent and 
* OnlineStudent, and associated with the Sorter class.
* The Student class is an abstract class with a composition
* relationship to the Main class.
* The OnCampusStudent and OnlineStudent subclasses are
* associated with the TuitionConstants class.
* The Sorter class is dependent on the Student class as it
* has method parameters that contain Student objects.
*
* COURSE AND PROJECT INFORMATION
* CSE205 Object Oriented Programming and Data Structures, 
* Spring Term A 2022
* Project Number: project-2
*
* AUTHOR: Gavin Beaudry, gbeaudry, gbeaudry@asu.edu
* AUTHOR: Chavon Kattner, ckattner, ckattner@asu.edu
************************************************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main 
{

  public static void main (String[] pArgs) 
  {
    new Main().run();
  }

  private void run()
  {
    /**
    *  This ArrayList will be filled from the input file and 
    *  used to perform calculations to create the final Arraylist 
    *  that will be used for the writte file.
    *  
    */
    ArrayList<Student> studentList = new ArrayList<>();

    /**
    *  This try block will attempt to read the input file,
    *  using a method defined in Main.
    *  Catches FileNotFoundException, prints error message,
    *  closes program.
    */
    try
    {
      studentList = readInputFile(new File("p2-students.txt"));
    }
    catch(FileNotFoundException e)
    {
      System.out.println("Sorry, could not open 'p2-students.txt' for reading. Stopping.");
      System.exit(-1);
    }

    //This calcTuition method is defined in Main.
    calcTuition(studentList);

    //insertionSort is a static method from Sorter.
    Sorter.insertionSort(studentList, Sorter.SORT_ASCENDING);

    /**
    *  This try block attempts to write the new file.
    *  Catches FileNotFoundException, prints error message,
    *  closes program.
    */
    try
    {
      writeOutFile(studentList);
    }
    catch(FileNotFoundException e)
    {
      System.out.println("Sorry, could not open 'p2-tuition.txt' for writing. Stopping.");
      System.exit(-1);
    }
  }

    /**
    *  This calcTuition method performs the calcTuition abstract 
    *  method from Student, that is overridden in OnCampusStudent 
    *  and OnlineStudent, and applies it to each element in the 
    *  paramater ArrayList.
    *  @param: ArryaList<Student>
    */
  private void calcTuition(ArrayList<Student> pStudentList) 
  {
    for (Student student : pStudentList) { student.calcTuition(); }
  }

    
  /**
   *  readInputFile method scans the input file and utilizes other methods
   *  dependent on whether input elements are indicated to be on-campus or online.
   *  @param: File inputFile
   *  @return: ArrayList<Student>
   *  @throws: FileNotFoundException
  */
  private ArrayList<Student> readInputFile(File inputFile) throws FileNotFoundException
  {
    ArrayList<Student> studentList = new ArrayList<>();

    Scanner in = new Scanner(inputFile);

    while (in.hasNext())
    {
      String studentType = in.next();  
       
      //if student is indicated to be on-campus in the input file:
      if (studentType.equalsIgnoreCase("C")) 
      {
        //readOnCampusStudent is defined in Main.
        studentList.add(readOnCampusStudent(in));
        in.nextLine();
      }
      
      //else if not on-campus: 
      else 
      {
       //readOnlineStudent is defined in Main.
       studentList.add(readOnlineStudent(in));   
       in.nextLine(); 
      }
    }
    
    in.close();
    return studentList;
  }

  /**
   * This method creates a new file and writes the elements from the input parameter
   * specifically formatted. Utilizes getters from Student.
   * @param pStudentList
   * @throws FileNotFoundException
  */
  private void writeOutFile(ArrayList<Student> pStudentList) throws FileNotFoundException
  {
    PrintWriter out = new PrintWriter("p2-tuition.txt");
    
    for (Student student : pStudentList) 
    {
      out.printf("%-16s%-20s%-15s%8.2f\n", student.getId(), student.getLastName(), student.getFirstName(), student.getTuition());
    } 
    out.close();
  }

  /**
   * Reads the input data to create OnCampusStudent object to 
   * be utilized in calculations and printing.
   * @param: Scanner pIn
   * @return: OnCampusStudent
  */
  private OnCampusStudent readOnCampusStudent(Scanner pIn)
  {
    //student ID.
    String id = pIn.next(); 
    //student last name.
    String lname = pIn.next();
    //student first name.
    String fname = pIn.next();
    //creates OnCampusStudent with above String inputs.
    OnCampusStudent student = new OnCampusStudent(id, fname, lname); 
    //Residency status.
    String res = pIn.next();
    //fee dependent on pre-professional program (i.e. law, dentistry, etc.).
    double fee = pIn.nextDouble();
    //# of credits student is taking.
    int credits = pIn.nextInt();

    //if-else utilizes methods from OnCampusStudent.
    if (res.equalsIgnoreCase("R"))
    {
      student.setResidency(1); 
    }
    else
    {
      student.setResidency(2); 
    }

    //methods from OnCampusSutdent.
    student.setProgramFee(fee);
    student.setCredits(credits);

    return student;
  }

  /**
   * method similar to readOnCampusStudent, utilizes OnlineStudent.
   * @param: Scanner pIn
   * @return OnlineStudent
  */
  private OnlineStudent readOnlineStudent(Scanner pIn)
  {
    String id = pIn.next();
    String lname = pIn.next();
    String fname = pIn.next();
    OnlineStudent student = new OnlineStudent(id, fname, lname);
    String fee = pIn.next();
    int credits = pIn.nextInt();

    //if-else utilizes OnlineStudent methods
    if (fee.equalsIgnoreCase("T")) { student.setTechFee(true); }
    else { student.setTechFee(false); }

    student.setCredits(credits); 
    return student;
  }
}
