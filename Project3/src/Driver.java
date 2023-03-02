public class Driver extends Staff {
    public Driver() { //IDENTITY
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		name = "Driver_" + Helper.GenerateUniqueID();
		dailySalary = 500.0; // whatever the base salary for SalesPeople is 
		totalSalary = 0.0;
		bonus_ = 0.0;
	}
}
