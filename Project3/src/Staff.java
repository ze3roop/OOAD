/* ============== STAFF SUPER CLASS ============= */
public class Staff extends Person{	

	protected Integer daysWorked;
	protected Double salary;
	protected Double bonus;
	protected Integer jobs_completed;

	// the Staff constructor
	public Staff()
	{
		super();

		daysWorked = 0;
		salary = 0.0;
		bonus = 0.0;

		jobs_completed = 0;

	}
	
	public Double GetDailySalary() {
		return salary;
	}

	public Double GetBonus() {
		return bonus;
	}
	
	public void EarnBonus(String vehicleType) {
		if(vehicleType == "PerformanceCar")
		{
			bonus = bonus + 5000.0;
			System.out.println(name + " Earned a $5000 bonus");
		}
		else if(vehicleType == "Car")
		{
			bonus = bonus + 2500.0;
			System.out.println(name + " Earned a $2500 bonus");
		}
		else if(vehicleType == "Pickup")
		{
			bonus = bonus + 3000.0; 
			System.out.println(name + " Earned a $3000 bonus");
		}
	}

	public void RevertBonus() {bonus = 0.0;}

	// returns true if they can do the job
	// returns false if they cannot do a job
	public boolean doJob() {
		if (jobs_completed < 2){
			jobs_completed = jobs_completed + 1;
			return true;
		} else {
			// System.out.println( name + ": I am not doing that, I have already done " + jobs_completed + "jobs\n");
			return false;
		}
	}

	public void RevertJobs() {jobs_completed = 0;}

	public Boolean quit() {
		Helper h = new Helper();
		return h.percentChance(10);
	}
}