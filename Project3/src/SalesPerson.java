class SalesPerson extends Staff // INHERITANCE 
{

	public SalesPerson() { //IDENTITY
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		CreateName("Salesperson_" + Helper.GenerateUniqueID());
		dailySalary = (double) 500; // whatever the base salary for SalesPeople is 
		totalSalary = 0.0;
		bonus_ = 0.0;
	}
	
	public SalesPerson(String name) { //IDENTITY
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		CreateName(name);
		dailySalary = (double) 500; // whatever the base salary for SalesPeople is 
		totalSalary = 0.0;
		bonus_ = 0.0;
	}

}