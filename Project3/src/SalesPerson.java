class SalesPerson extends Staff // INHERITANCE 
{

	public SalesPerson() { //IDENTITY
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		name = "Salesperson_" + Helper.GenerateUniqueID();
		dailySalary = 500.0; // whatever the base salary for SalesPeople is 
		totalSalary = 0.0;
		bonus_ = 0.0;
	}
	
	public SalesPerson(String name_) { //IDENTITY
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		name = name_;
		dailySalary = 500.0; // whatever the base salary for SalesPeople is 
		totalSalary = 0.0;
		bonus_ = 0.0;
	}

}