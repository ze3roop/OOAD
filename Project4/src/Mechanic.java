class Mechanic extends Staff // INHERITANCE 
{
	public Mechanic() {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		name = "Mechanic_" + Helper.GenerateUniqueID();
		dailySalary = 400.0; // whatever the base salary for Mechanics is 
		JobsDone = 0; // start with 0 jobsdone
		totalSalary = 0.0;
		bonus_ = 0.0;
	}
	
	public Mechanic(String name_) {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		name = name_;
		dailySalary = 400.0; // whatever the base salary for Mechanics is 
		JobsDone = 0; // start with 0 jobsdone
		totalSalary = 0.0;
		bonus_ = 0.0;
	}
}
