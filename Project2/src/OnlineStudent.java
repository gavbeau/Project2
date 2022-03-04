/************************************************************************************************
* CLASS: OnlineStudent (OnlineStudent.java)
*
* DESCRIPTION
* 
* OnlineStudent class is a subclass of the Student class. 
* It contains an instance variable, mTechFee, that will 
* return true if the technology fee applies. The OnlineStudent 
* class creates an online student. The calcTuition method is 
* inherited from the Student class and is overriden. It calculates 
* the students tuition and adds any technology fees if applicable.
* 
* COURSE AND PROJECT INFORMATION
* CSE205 Object Oriented Programming and Data Structures, 
* Spring Term A 2022
* Project Number: project-2
*
* AUTHOR: Gavin Beaudry, gbeaudry, gbeaudry@asu.edu
* AUTHOR: Chavon Kattner, ckattner, ckattner@asu.edu **
************************************************************************************************/
//OnlineStudent class extends abstract Student class
public class OnlineStudent extends Student
{
  //Instance variable that will return true if the technology fee applies and flase if it does not.
  private boolean mTechFee;
  //Creates an Online Student
  public OnlineStudent (String pId, String pFirstName, String pLastName)
  {
    super(pId, pFirstName, pLastName);
  }
  //Overrides method calcTuition which returns nothing then calculates the tuition for the online student. Calls the setTuition() mutator method inherited from the Student class.
  @Override
  public void calcTuition()
  {
    double t = getCredits() * TuitionConstants.ONLINE_CREDIT_RATE;
    if (getTechFee())
    {
      t = t + TuitionConstants.ONLINE_TECH_FEE;
    } 
    setTuition(t);
  }
  public boolean getTechFee()
  {
    return mTechFee;
  }
  public void setTechFee (boolean pTechFee)
  {
    this.mTechFee = pTechFee;
  }
}




