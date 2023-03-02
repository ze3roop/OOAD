class Intern extends Staff // INHERITANCE 
{

	public Intern() {
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