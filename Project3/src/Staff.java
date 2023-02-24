import java.util.concurrent.ThreadLocalRandom;

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
	
	public void Bonus(String vehicleType) { //POLYMORPHISM AND ABSTRACTION
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
class Salespeople extends Staff // INHERITANCE 
{

	public Salespeople() { //IDENTITY
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		Integer randomInt = ThreadLocalRandom.current().nextInt(000000, 100000);
		CreateName("Salesperson_" + randomInt);
		dailySalary = (double) 500; // whatever the base salary for SalesPeople is 
		totalSalary = 0.0;
		bonus_ = 0.0;
	}
	
	public Salespeople(String name) { //IDENTITY
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		dailySalary = (double) 500; // whatever the base salary for SalesPeople is 
		totalSalary = 0.0;
		CreateName(name); 
		bonus_ = 0.0;
	}

}


/* ============== MECHANICS ============= */
class Mechanics extends Staff // INHERITANCE 
{

	public Mechanics() {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		Integer randomInt = ThreadLocalRandom.current().nextInt(000000, 100000);
		CreateName("Mechanic_" + randomInt);
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

}


/* =============== INTERNS ============== */
class Interns extends Staff // INHERITANCE 
{

	public Interns() {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		dailySalary = (double) 100; // whatever the base salary for Interns is 
		Integer randomInt = ThreadLocalRandom.current().nextInt(000000, 100000);
		CreateName("Intern_" + randomInt);
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

	
}