import java.util.concurrent.ThreadLocalRandom;

public class Vehicles { // INHERITANCE 
	protected String name; // ENCAPSULATION
	protected Double salesPrice; // ENCAPSULATION
	protected String[] condition = {"Like New", "Used", "Broken"}; // ENCAPSULATION
	protected String conditionType; // ENCAPSULATION
	protected String[] cleanliness = {"dirty", "clean", "sparkling"}; // ENCAPSULATION
	protected String cleanType;  // ENCAPSULATION
	protected Double cost;  // ENCAPSULATION
	
	
	protected String vehicleType;
	
	public Vehicles()
	{
		
	}
	public void DetermineCost() { // POLYMORPHISM AND ABSTRACTION
			int randomInt1 = ThreadLocalRandom.current().nextInt(0,3);
			conditionType = condition[randomInt1];//randomly determined to be Like New, Used, or Broken.
			if(conditionType == "Used") {
				cost  = cost - (cost * .2); 
			}
			else if (conditionType == "Broken") {
				cost = cost - (cost * .5);
			}
			
			
			int randomInt2 = ThreadLocalRandom.current().nextInt(0,3); 
			cleanType = cleanliness[randomInt2]; //randomly determine to be sparkling, clean, or dirty.
			if(cleanType == "sparkling") {
				cost = cost - (cost * .05); 
			}
			else if(cleanType == "clean") {
				cost = cost - (cost * .35); 
			}
			else if(cleanType == "dirty") {
				cost = cost - (cost * .60); 
			}
	}
	public Double GetCost() { //POLYMORPHISM AND ABSTRACTION
		return cost;
	}

	public String CreateName() { //POLYMORPHISM AND ABSTRACTION
		int randInt = ThreadLocalRandom.current().nextInt(0,1000);
		return vehicleType + "_" + randInt; //unique name algorithm goes here..
	}
	public String GetName() { //POLYMORPHISM  AND ABSTRACTION
		return name;
	}
	
	public String GetVehicleType() { //POLYMORPHISM AND ABSTRACTION
		return vehicleType;
		
	}
	
	public String GetCondition() { //POLYMORPHISM AND ABSTRACTION
		return conditionType;
		
	}
	public void SetCondition(String newCondition) { //POLYMORPHISM AND ABSTRACTION
		conditionType = newCondition; 
	}
	public String GetCleanliness() { //POLYMORPHISM AND ABSTRACTION
		return cleanType; 
	}
	
	public void SetCleanliness(String newCleanliness) { //POLYMORPHISM AND ABSTRACTION
		cleanType = newCleanliness;
	}
	
	public void SetSalesPrice(Double amount) { //POLYMORPHISM AND ABSTRACTION
		salesPrice = (salesPrice * amount) + salesPrice; 
		
	}
	
	public Double GetSalesPrice() { //POLYMORPHISM AND ABSTRACTION
		return salesPrice;
		
	}
}


/* ===================================== PERFORMANCE CARS ============================================== */
class PerformanceCars extends Vehicles { // INHERITANCE 
	
	public PerformanceCars() { //IDENTITY
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		vehicleType = "Performance Car"; 
		name = CreateName(); 
		cost = ThreadLocalRandom.current().nextDouble(20000, 40000); //20,000 - 40,000
		DetermineCost();
		salesPrice = cost * 2.0;
	}
}

/* =============================================== CARS ============================================== */
class Cars extends Vehicles { // INHERITANCE 
	public Cars() { //IDENTITY
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		vehicleType = "Car"; 
		name = CreateName(); 
		cost = ThreadLocalRandom.current().nextDouble(10000, 20000);//10,000 - 20,0000
		DetermineCost();
		salesPrice = cost * 2.0; // randomly select a value between $20,000 and $40,0000.
	}
	
	
}

/* ======================================== PICKUPS ================================================ */
class Pickups extends Vehicles { // INHERITANCE 
	public Pickups() { //IDENTITY
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		vehicleType = "Pickup";
		name = CreateName(); 
		cost = ThreadLocalRandom.current().nextDouble(10000, 40000); //10,000 - 40,000
		DetermineCost();
		salesPrice = cost * 2.0; // randomly select a value between $20,000 and $40,0000.
	}
	
}