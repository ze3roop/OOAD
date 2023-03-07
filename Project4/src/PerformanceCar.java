class PerformanceCar extends Vehicle { // INHERITANCE 
	public PerformanceCar() { //IDENTITY
		super(); // invoking base-class Staff constructor.
		vehicleType = Types_of_Vehicles.performanceCar; 
		
		name = "PerformanceCar_" + Helper.GenerateUniqueID();
		
		cost = Helper.RandDouble(20000, 40000);
		DetermineCost();
		salesPrice = cost * 2.0;
	}
}