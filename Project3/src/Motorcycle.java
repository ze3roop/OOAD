public class Motorcycle extends Vehicle {
    public Motorcycle() {
		super(); // invoking base-class Staff constructor.
		vehicleType = Types_of_Vehicles.motorcycle; 
		
		name = "Motorcycle_" + Helper.GenerateUniqueID();
		
		cost = Helper.RandDouble(20000, 40000);
		DetermineCost();
		salesPrice = cost * 2.0;
	}
}
