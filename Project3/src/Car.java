class Car extends Vehicle { // INHERITANCE 
	public Car() { //IDENTITY
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		vehicleType = Types_of_Vehicles.Car; 
		name = "Car_" + Helper.GenerateUniqueID(); 
		cost = Helper.RandDouble(10000, 20000);//10,000 - 20,0000
		DetermineCost();
		salesPrice = cost * 2.0; // randomly select a value between $20,000 and $40,0000.
	}
	
	
}