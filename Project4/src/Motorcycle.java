public class Motorcycle extends Vehicle {

    public Motorcycle() {
		super(); // invoking base-class Staff constructor.
		vehicleType = Types_of_Vehicles.motorcycle; 

		engineSize = Helper.randDouble_Gaus(51.0,Double.MAX_VALUE,700.0, 300.0);
		
		name = "Motorcycle_" + Helper.GenerateUniqueID();
		
		cost = Helper.RandDouble(10000, 30000);
		DetermineCost();
		salesPrice = cost * 2.0;
	}
}
