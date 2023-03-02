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
	
	public void Bonus(Vehicle vehicle) { //POLYMORPHISM AND ABSTRACTION
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