class Intern extends Staff
{
	public Intern() {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		dailySalary = 100.0; // whatever the base salary for Interns is 
		name = "Intern_" + Helper.GenerateUniqueID();
		JobsDone = 0; // start with 0 jobsdone
		totalSalary = 0.0;
		bonus_ = 0.0;
	}
}