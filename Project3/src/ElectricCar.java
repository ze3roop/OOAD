public class ElectricCar extends Vehicle {

    int range = Helper.RandInt(60, 400);

    public ElectricCar() {
		super(); // invoking base-class Staff constructor.
		vehicleType = Types_of_Vehicles.electricCar;

		applyRageBoost();
		
		name = "ElectricCar_" + Helper.GenerateUniqueID();
		
		cost = Helper.RandDouble(20000, 40000);
		DetermineCost();
		salesPrice = cost * 2.0;
	}

	// setters

	public void applyRageBoost(){
		if (isLikeNew()){
			range += 100;
		}
	}
    
	public void applyRageDecrease(){
		if (!isLikeNew()){
			range -= 100;
		}
	}
}
