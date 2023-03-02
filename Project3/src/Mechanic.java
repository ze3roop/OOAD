class Mechanic extends Staff // INHERITANCE 
{

	public Mechanic() {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		CreateName("Mechanic_" + Helper.GenerateUniqueID());
		dailySalary = (double) 400; // whatever the base salary for Mechanics is 
		JobsDone = 0; // start with 0 jobsdone
		totalSalary = 0.0;
		bonus_ = 0.0;
	}
	public Mechanic(String name) {
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
