/* ============== STAFF SUPER CLASS ============= */
public class Staff {
	protected String name = "NILL";
	protected Double totalSalary = 0.0;
	protected Integer JobsDone = 0;
	protected Double dailySalary = 0.0;
	protected Double bonus_ = 0.0;
	
	// setters
	public void ResetJobsDOne(){ JobsDone = 0; }
	
	//getters
	public String GetName() { return name; }
	public Double GetDailySalary() { return dailySalary; }
	public Double GetTotalSalary() { return totalSalary; }
	public Double GetBonus() { return bonus_; }
	
	// public functions
	public void pay() { totalSalary = totalSalary + dailySalary; }
	
	public void Bonus(Vehicle vehicle) {
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
		} else {
			// System.out.println("\t" + name + " cannot do any more jobs -- Comment Me Out");
			return false;
		}
	}
}