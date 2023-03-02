public class ElectricCar extends Vehicle {

    // ------ Need to add unique features for the class

    public ElectricCar() {
		super(); // invoking base-class Staff constructor.
		vehicleType = Types_of_Vehicles.electricCar; 
		
		name = "ElectricCar_" + Helper.GenerateUniqueID();
		
		cost = Helper.RandDouble(20000, 40000);
		DetermineCost();
		salesPrice = cost * 2.0;
	}
    
}
