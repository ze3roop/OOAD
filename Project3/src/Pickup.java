class Pickup extends Vehicle { // INHERITANCE 
	public Pickup() { //IDENTITY
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		vehicleType = Types_of_Vehicles.pickup;
		name = "Pickup_" + Helper.GenerateUniqueID(); 
		cost = Helper.RandDouble(10000, 40000); //10,000 - 40,000
		DetermineCost();
		salesPrice = cost * 2.0; // randomly select a value between $20,000 and $40,0000.
	}
	
}