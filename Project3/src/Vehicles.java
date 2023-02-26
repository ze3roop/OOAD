import java.util.concurrent.ThreadLocalRandom;

public class Vehicles { // INHERITANCE

	protected enum Types_of_Vehicles {performanceCar, Car, pickup, NILL}
	protected enum Types_of_Cleanliness {dirty, clean, sparkling, NILL}
	protected enum Types_of_Condition {broken, used, likeNew, NILL}


	protected String name = "NILL"; // ENCAPSULATION
	protected Double salesPrice = 0.0; // ENCAPSULATION

	protected Types_of_Condition condition = Types_of_Condition.NILL;
	protected Types_of_Cleanliness cleanliness = Types_of_Cleanliness.NILL;

	protected Double cost = 0.0;  // ENCAPSULATION
	
	
	protected Types_of_Vehicles vehicleType = Types_of_Vehicles.NILL;
	
	public Vehicles() {
        int rand = Helper.RandInt(1, 3);
        if (rand == 1) {condition = Types_of_Condition.broken;}
        else if (rand == 2) {condition = Types_of_Condition.likeNew;}
        else if (rand == 3) {condition = Types_of_Condition.used;}

        rand = Helper.RandInt(1, 3);
        if (rand == 1) {cleanliness = Types_of_Cleanliness.clean;}
		else if (rand == 2) {cleanliness = Types_of_Cleanliness.dirty;}
		else if (rand == 3) {cleanliness = Types_of_Cleanliness.sparkling;}
    }

	public void DetermineCost() { // POLYMORPHISM AND ABSTRACTION
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
	public Double GetCost() { //POLYMORPHISM AND ABSTRACTION
		return cost;
	}

	public String GetName() { //POLYMORPHISM  AND ABSTRACTION
		return name;
	}
	
	public void SetSalesPrice(Double amount) { //POLYMORPHISM AND ABSTRACTION
		salesPrice = (salesPrice * amount) + salesPrice; 
		
	}
	
	public Double GetSalesPrice() { //POLYMORPHISM AND ABSTRACTION
		return salesPrice;
		
	}

	public Boolean isDirty() {
		return cleanliness == Types_of_Cleanliness.dirty;
	}
	public Boolean isClean() {
		return cleanliness == Types_of_Cleanliness.clean;
	}
	public Boolean isSparkling() {
		return cleanliness == Types_of_Cleanliness.sparkling;
	}
	public Boolean isBroken() {
		return condition == Types_of_Condition.broken;
	}
	public Boolean isLikeNew() {
		return condition == Types_of_Condition.likeNew;
	}
	public Boolean isUsed() {
		return condition == Types_of_Condition.used;
	}
	public Boolean isPickup() {
		return vehicleType == Types_of_Vehicles.pickup;
	}
	public Boolean isCar() {
		return vehicleType == Types_of_Vehicles.Car;
	}
	public Boolean isPerformanceCar() {
		return vehicleType == Types_of_Vehicles.performanceCar;
	}

	public void makeDirty() {
		cleanliness = Types_of_Cleanliness.dirty;
	}
	public void makeClean() {
		cleanliness = Types_of_Cleanliness.clean;
	}
	public void makeSparkling() {
		cleanliness = Types_of_Cleanliness.sparkling;
	}
	public void makeBroken() {
		condition = Types_of_Condition.broken;
	}
	public void makeLikeNew() {
		condition = Types_of_Condition.likeNew;
	}
	public void makeUsed() {
		condition = Types_of_Condition.used;
	}
	public void makePickup() {
		vehicleType = Types_of_Vehicles.pickup;
	}
	public void makeCar() {
		vehicleType = Types_of_Vehicles.Car;
	}
	public void makePerformanceCar() {
		vehicleType = Types_of_Vehicles.performanceCar;
	}

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

}


/* ===================================== PERFORMANCE CARS ============================================== */
class PerformanceCars extends Vehicles { // INHERITANCE 
	
	public PerformanceCars() { //IDENTITY
		super(); // invoking base-class Staff constructor.
		vehicleType = Types_of_Vehicles.performanceCar; 
		name = "PerformanceCar_" + Helper.GenerateUniqueID();
		cost = ThreadLocalRandom.current().nextDouble(20000, 40000); //20,000 - 40,000
		DetermineCost();
		salesPrice = cost * 2.0;
	}
}

/* =============================================== CARS ============================================== */
class Cars extends Vehicles { // INHERITANCE 
	public Cars() { //IDENTITY
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		vehicleType = Types_of_Vehicles.Car; 
		name = "Car_" + Helper.GenerateUniqueID(); 
		cost = ThreadLocalRandom.current().nextDouble(10000, 20000);//10,000 - 20,0000
		DetermineCost();
		salesPrice = cost * 2.0; // randomly select a value between $20,000 and $40,0000.
	}
	
	
}

/* ======================================== PICKUPS ================================================ */
class Pickups extends Vehicles { // INHERITANCE 
	public Pickups() { //IDENTITY
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		vehicleType = Types_of_Vehicles.pickup;
		name = "Pickup_" + Helper.GenerateUniqueID(); 
		cost = ThreadLocalRandom.current().nextDouble(10000, 40000); //10,000 - 40,000
		DetermineCost();
		salesPrice = cost * 2.0; // randomly select a value between $20,000 and $40,0000.
	}
	
}