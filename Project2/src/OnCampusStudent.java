/************************************************************************************************
* CLASS: OnCampusStudent (OnCampusStudent.java)
*
* DESCRIPTION
* 
* OnCampusStudent class is a subclass of the Student class. 
* It contains two constants (Resident and Non resident). 
* Some on campus students pay an aditional program fee. 
* The OnCampusStudent class creates an on campus student and 
* based on the student's information, such as residency and 
* program fee, calculates the student's tuition. 
* The calcTuition method is inherited from the Student class 
* and is therefore overriden. 
*
* COURSE AND PROJECT INFORMATION
* CSE205 Object Oriented Programming and Data Structures, 
* Spring Term A 2022
* Project Number: project-2
*
* AUTHOR: Gavin Beaudry, gbeaudry, gbeaudry@asu.edu
* AUTHOR: Chavon Kattner, ckattner, ckattner@asu.edu **
************************************************************************************************/
//OnCampusStudent class is a subclass of abstract Student class.
public class OnCampusStudent extends Student
{
  //Two public int constants are created for Resident and Non Resident.
  int RESIDENT = 1;
  int NON_RESIDENT = 2;
  //Instance variables for mResident and mProgramFee.
  private int mResident;
  private double mProgramFee;
  //Creates an on Campus Student
  public OnCampusStudent (String pId, String pFirstName, String pLastName)
  {
    super (pId, pFirstName, pLastName);
  }
  //Calculates tuition for an On Campus Student by overriding the calcTuition method. Calls the setTuition() mutator method inherited from the Student class.
  @Override
  public void calcTuition()
  {
    double t;
    int status = getResidency();
    if (status == RESIDENT)    
    {
      t = TuitionConstants.ONCAMP_RES_BASE;
    }
    else 
    {
      t = TuitionConstants.ONCAMP_NONRES_BASE;
    }
    t = t + getProgramFee();
    if (getCredits() > TuitionConstants.ONCAMP_MAX_CREDITS)
    {
      t = t + (getCredits() - TuitionConstants.ONCAMP_MAX_CREDITS) * TuitionConstants.ONCAMP_ADD_CREDITS;
    }
    setTuition(t);
  }
  public double getProgramFee()
  {
    return mProgramFee;
  }
  public int getResidency()
  {
    return mResident;
  }
  public void setProgramFee(double pProgramFee)
  {
    this.mProgramFee = pProgramFee;
  }
  public void setResidency( int pResident)
  {
    this.mResident = pResident;
  }

}





