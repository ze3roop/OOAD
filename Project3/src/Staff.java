/* ============== STAFF SUPER CLASS ============= */
public class Staff { // INHERITANCE 
	protected String name; // ENCAPSULATION
	protected Double totalSalary; // ENCAPSULATION
	protected Integer JobsDone; // ENCAPSULATION
	protected Double dailySalary; // ENCAPSULATION
	protected Double bonus_; // ENCAPSULATION
	
	// the Staff constructor
	public Staff()
	{
	}
	
	public void CreateName(String str) { //POLYMORPHISM AND ABSTRACTION
		name = str;
	}
	
	public String GetName() { //POLYMORPHISM AND ABSTRACTION
		return name;
	}
	public void SetDailySalary() { //POLYMORPHISM AND ABSTRACTION
		totalSalary = totalSalary + dailySalary;
	}
	
	public Double GetDailySalary() { //POLYMORPHISM AND ABSTRACTION
		return dailySalary;
	}
	
	public Double GetTotalSalary() //POLYMORPHISM AND ABSTRACTION
	{
		return totalSalary;
	}
	public Double GetTotalBonusPay() //POLYMORPHISM AND ABSTRACTION
	{
		return bonus_;
	}
	
	public void Bonus(Vehicles vehicle) { //POLYMORPHISM AND ABSTRACTION
		if(vehicle.isPerformanceCar())
		{
			bonus_ = bonus_ + 5000;
		}
		else if(vehicle.isCar())
		{
			bonus_ = bonus_ + 2500; 
		}
		else if(vehicle.isPickup())
		{
			bonus_ = bonus_ + 2000; 
		}
	}

public boolean doJob() {
	if (JobsDone < 2) {
		JobsDone ++;
		return true;
	}
	// System.out.println("\t" + name + " cannot do any more jobs -- Comment Me Out");
	return false;
}

public void ResetJobsDOne(){
	JobsDone = 0;
}

}


/* ============== SALESPEOPLE ============= */
class Salespeople extends Staff // INHERITANCE 
{

	public Salespeople() { //IDENTITY
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		CreateName("Salesperson_" + Helper.GenerateUniqueID());
		dailySalary = (double) 500; // whatever the base salary for SalesPeople is 
		totalSalary = 0.0;
		bonus_ = 0.0;
	}
	
	public Salespeople(String name) { //IDENTITY
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		CreateName(name);
		dailySalary = (double) 500; // whatever the base salary for SalesPeople is 
		totalSalary = 0.0;
		bonus_ = 0.0;
	}

}


/* ============== MECHANICS ============= */
class Mechanics extends Staff // INHERITANCE 
{

	public Mechanics() {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		CreateName("Mechanic_" + Helper.GenerateUniqueID());
		dailySalary = (double) 400; // whatever the base salary for Mechanics is 
		JobsDone = 0; // start with 0 jobsdone
		totalSalary = 0.0;
		bonus_ = 0.0;
	}
	public Mechanics(String name) {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		CreateName(name);
		dailySalary = (double) 400; // whatever the base salary for Mechanics is 
		JobsDone = 0; // start with 0 jobsdone
		totalSalary = 0.0;
		bonus_ = 0.0;
	}
	public Integer GetJobsDone() {
		return JobsDone;
	}

}


/* =============== INTERNS ============== */
class Interns extends Staff // INHERITANCE 
{

	public Interns() {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		dailySalary = (double) 100; // whatever the base salary for Interns is 
		CreateName("Intern_" + Helper.GenerateUniqueID());
		JobsDone = 0; // start with 0 jobsdone
		totalSalary = 0.0;
		bonus_ = 0.0;
	}
	public Integer GetJobsDone() {
		return JobsDone;
	}

	
}