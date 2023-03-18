/* ============== STAFF SUPER CLASS ============= */
public class Staff {

	public enum Staff_Types { intern, mechanic, salesperson, driver }

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
	

	// ================= FACTORY ====================
	static Staff Create(Staff_Types staffType_){
		if(Staff_Types.intern.compareTo(staffType_) == 0){
			return new Intern();
		}
		else if(Staff_Types.mechanic.compareTo(staffType_) == 0){
			return new Mechanic();
		}
		else if(Staff_Types.salesperson.compareTo(staffType_) == 0){
			return new SalesPerson();
		}
		else if(Staff_Types.driver.compareTo(staffType_) == 0){
			return new Driver();
		}


		return null;
	}


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