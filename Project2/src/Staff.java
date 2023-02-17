
/* ============== STAFF SUPER CLASS ============= */
public class Staff {
	public String name;
	public Double salary;
	public Integer JobsDone;
	public Double dailySalary;
	
	// the Staff constructor
	public Staff()
	{
		name = CreateName(); //random name generator 
		// Should I even let salary be in the constructor. There should be a default salary amount for each type 
	}
	
	public String CreateName() {
		return null;
	}
	
	public void SetDailySalary() {
		salary = salary + dailySalary;
	}
	
	public Double GetDailySalary()
	{
		return salary;
	}
	
	public void Bonus(String vehicleType) {
		if(vehicleType == "Performance Car")
		{
			salary = salary + 5000;
		}
		else if(vehicleType == "Car")
		{
			salary = salary + 2500; 
		}
		else if(vehicleType == "Pickup")
		{
			salary = salary + 2000; 
		}
	}
}


/* ============== SALESPEOPLE ============= */
class Salespeople extends Staff
{

	public Salespeople() {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		dailySalary = (double) 1; // whatever the base salary for SalesPeople is 
	}
	
}


/* ============== MECHANICS ============= */
class Mechanics extends Staff
{

	public Mechanics() {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		dailySalary = (double) 1; // whatever the base salary for Mechanics is 
		JobsDone = 0; // start with 0 jobsdone
	}
	public Integer GetJobsDone() {
		return JobsDone;
	}
	public void SetJobsDone() {
		JobsDone = JobsDone + 1; 
	}
	
}


/* =============== INTERNS ============== */
class Interns extends Staff
{

	public Interns() {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		dailySalary = (double) 1; // whatever the base salary for Interns is 
		JobsDone = 0; // start with 0 jobsdone
	}
	public Integer GetJobsDone() {
		return JobsDone;
	}
	public void SetJobsDone() {
		JobsDone = JobsDone + 1; 
	}
	
}