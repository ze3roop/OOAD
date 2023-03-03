public class ElectricCar extends Vehicle {


    public ElectricCar() {
		super(); // invoking base-class Staff constructor.
		vehicleType = Types_of_Vehicles.electricCar;

		range = Helper.RandInt(60, 400);

		applyRageBoost();
		
		name = "ElectricCar_" + Helper.GenerateUniqueID();
		
		cost = Helper.RandDouble(20000, 40000);
		DetermineCost();
		salesPrice = cost * 2.0;
	}

	// setters
	
}
