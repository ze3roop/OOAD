public class Driver extends Staff {

	private int racesWon = 0;

    public Driver() { //IDENTITY
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		name = "Driver_" + Helper.GenerateUniqueID();
		dailySalary = 800.0; // whatever the base salary for SalesPeople is 
		totalSalary = 0.0;
		bonus_ = 0.0;
	}

	// setter

	public void increaseRacesWon() { racesWon ++;}

	public int getRacedWon() { return racesWon;}
}
