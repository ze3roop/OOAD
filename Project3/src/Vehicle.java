public class Vehicle {

	// enums
	protected enum Types_of_Vehicles {performanceCar, car, pickup, electricCar, motorcycle, monsterTruck, NILL}
	protected enum Types_of_Cleanliness {dirty, clean, sparkling, NILL}
	protected enum Types_of_Condition {broken, used, likeNew, NILL}

	// variables
	protected String name = "NILL";
	protected Double salesPrice = 0.0;

	protected Types_of_Condition condition = Types_of_Condition.NILL;
	protected Types_of_Cleanliness cleanliness = Types_of_Cleanliness.NILL;

	protected Double cost = 0.0;
	
	protected Types_of_Vehicles vehicleType = Types_of_Vehicles.NILL;
	
	// constructor
	public Vehicle() {
        int rand = Helper.RandInt(1, 3);
        if (rand == 1) {condition = Types_of_Condition.broken;}
        else if (rand == 2) {condition = Types_of_Condition.likeNew;}
        else if (rand == 3) {condition = Types_of_Condition.used;}

        rand = Helper.RandInt(1, 3);
        if (rand == 1) {cleanliness = Types_of_Cleanliness.clean;}
		else if (rand == 2) {cleanliness = Types_of_Cleanliness.dirty;}
		else if (rand == 3) {cleanliness = Types_of_Cleanliness.sparkling;}
    }

	// setter
	public void SetSalesPrice(Double amount) { salesPrice = (salesPrice * amount) + salesPrice; }
	
		// cleanliness
		public void makeDirty() { cleanliness = Types_of_Cleanliness.dirty; }
		public void makeClean() { cleanliness = Types_of_Cleanliness.clean; }
		public void makeSparkling() { cleanliness = Types_of_Cleanliness.sparkling; }
		
		// condition
		public void makeBroken() { condition = Types_of_Condition.broken; }
		public void makeLikeNew() { condition = Types_of_Condition.likeNew; }
		public void makeUsed() { condition = Types_of_Condition.used; }
		
		// vehicleType
		public void makePickup() { vehicleType = Types_of_Vehicles.pickup; }
		public void makeCar() { vehicleType = Types_of_Vehicles.car; }
		public void makePerformanceCar() { vehicleType = Types_of_Vehicles.performanceCar; }

	
	// getter
	public Double GetCost() { return cost; }
	public String GetName() { return name; }
	public Double GetSalesPrice() { return salesPrice; }
	public Types_of_Vehicles GetType() { return vehicleType; }
	
	public String getInfo_asString() {
		String condition_str = "NILL";
		String cleanliness_str = "NILL";

		if (condition == Types_of_Condition.broken) { condition_str = "Broken";}
		else if (condition == Types_of_Condition.likeNew) { condition_str = "Like New";}
		else if (condition == Types_of_Condition.used) { condition_str = "Used";}

		if (cleanliness == Types_of_Cleanliness.clean) { cleanliness_str = "Clean";}
		else if (cleanliness == Types_of_Cleanliness.dirty) {cleanliness_str = "Dirty";}
		else if (cleanliness == Types_of_Cleanliness.sparkling) {cleanliness_str = "Sparkling";}


		return String.format("%-40s$%-20.2f$%-20.2f%-20s%-20s", name, cost, salesPrice, condition_str, cleanliness_str);
	}

	
	// public functions
	public void DetermineCost() {
			if(condition == Types_of_Condition.used) {
				cost  = cost - (cost * .2); 
			}
			else if (condition == Types_of_Condition.broken) {
				cost = cost - (cost * .5);
			}
			
			if(cleanliness == Types_of_Cleanliness.sparkling) {
				cost = cost - (cost * .05); 
			}
			else if(cleanliness == Types_of_Cleanliness.clean) {
				cost = cost - (cost * .35); 
			}
			else if(cleanliness == Types_of_Cleanliness.dirty) {
				cost = cost - (cost * .60); 
			}
	}
	public void ReduceCleanliness() {
		if (cleanliness == Types_of_Cleanliness.sparkling) { cleanliness = Types_of_Cleanliness.clean;}
		else if (cleanliness == Types_of_Cleanliness.clean) { cleanliness = Types_of_Cleanliness.dirty;}
	}

		// is Functions
		public Boolean isDirty() { return cleanliness == Types_of_Cleanliness.dirty;}
		public Boolean isClean() { return cleanliness == Types_of_Cleanliness.clean; }
		public Boolean isSparkling() { return cleanliness == Types_of_Cleanliness.sparkling; }
		public Boolean isBroken() { return condition == Types_of_Condition.broken; }
		public Boolean isLikeNew() { return condition == Types_of_Condition.likeNew; }
		public Boolean isUsed() { return condition == Types_of_Condition.used; }
		public Boolean isPickup() { return vehicleType == Types_of_Vehicles.pickup; }
		public Boolean isCar() { return vehicleType == Types_of_Vehicles.car; }
		public Boolean isPerformanceCar() { return vehicleType == Types_of_Vehicles.performanceCar; }
		public Boolean isVehicle() {return vehicleType == Types_of_Vehicles.NILL;}

}