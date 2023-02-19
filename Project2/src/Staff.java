import java.util.concurrent.ThreadLocalRandom;

/* ============== STAFF SUPER CLASS ============= */
public class Staff {
	public String name;
	public Double totalSalary;
	public Integer JobsDone;
	public Double dailySalary;
	public Double bonus_;
	
	// the Staff constructor
	public Staff()
	{
	}
	
	public void CreateName(String str) {
		name = str;
	}
	
	public void SetDailySalary() {
		totalSalary = totalSalary + dailySalary;
	}
	
	public Double GetDailySalary() {
		return dailySalary;
	}
	
	public Double GetTotalSalary()
	{
		return totalSalary;
	}
	public Double GetTotalBonusPay()
	{
		return bonus_;
	}
	
	public void Bonus(String vehicleType) {
		if(vehicleType == "Performance Car")
		{
			bonus_ = bonus_ + 5000;
		}
		else if(vehicleType == "Car")
		{
			bonus_ = bonus_ + 2500; 
		}
		else if(vehicleType == "Pickup")
		{
			bonus_ = bonus_ + 2000; 
		}
	}
}


/* ============== SALESPEOPLE ============= */
class Salespeople extends Staff
{

	public Salespeople() {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		Integer randomInt = ThreadLocalRandom.current().nextInt(000000, 100000);
		name = "Salespersons_" + randomInt; 
		dailySalary = (double) 500; // whatever the base salary for SalesPeople is 
		totalSalary = 0.0;
		bonus_ = 0.0;
	}
	
	public Salespeople(String name) {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		dailySalary = (double) 500; // whatever the base salary for SalesPeople is 
		totalSalary = 0.0;
		CreateName(name); 
		bonus_ = 0.0;
	}
	public String GetName() {
		return name;
	}
}


/* ============== MECHANICS ============= */
class Mechanics extends Staff
{

	public Mechanics() {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		Integer randomInt = ThreadLocalRandom.current().nextInt(000000, 100000);
		name = "Mechanic_" + randomInt; 
		dailySalary = (double) 400; // whatever the base salary for Mechanics is 
		JobsDone = 0; // start with 0 jobsdone
		totalSalary = 0.0;
		bonus_ = 0.0;
	}
	public Mechanics(String name) {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		dailySalary = (double) 400; // whatever the base salary for SalesPeople is 
		JobsDone = 0;
		CreateName(name); 
		totalSalary = 0.0;
		bonus_ = 0.0;
	}
	public Integer GetJobsDone() {
		return JobsDone;
	}
	public void SetJobsDone() {
		JobsDone = JobsDone + 1; 
	}
	public String GetName() {
		return name;
	}
}


/* =============== INTERNS ============== */
class Interns extends Staff
{

	public Interns() {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		dailySalary = (double) 100; // whatever the base salary for Interns is 
		Integer randomInt = ThreadLocalRandom.current().nextInt(000000, 100000);
		name = "Intern_" + randomInt; 
		JobsDone = 0; // start with 0 jobsdone
		totalSalary = 0.0;
		bonus_ = 0.0;
	}
	public Integer GetJobsDone() {
		return JobsDone;
	}
	public void SetJobsDone() {
		JobsDone = JobsDone + 1; 
	}
	public String GetName() {
		return name;
	}
	
}