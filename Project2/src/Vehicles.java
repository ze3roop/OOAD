import java.util.concurrent.ThreadLocalRandom;

public class Vehicles {
	public String name;
	public Double salesPrice;
	public String[] condition = {"Like New", "Used", "Broken"};
	public String conditionType;
	public String type; 
	public String[] cleanliness = {"dirty", "clean", "sparkling"};
	public String cleanType; 
	public Double cost; 
	
	
	public String vehicleType;
	
	public Vehicles()
	{
		
	}
	public void DetermineCost() {
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
	public Double GetCost() {
		return cost;
	}

	public String CreateName() {
		int randInt = ThreadLocalRandom.current().nextInt(0,1000);
		return vehicleType + "_" + randInt; //unique name algorithm goes here..
	}
	public String GetName() {
		return name;
	}
	
	public String GetVehicleType() {
		return vehicleType;
		
	}
	
	public String GetCondition() {
		return conditionType;
		
	}
	public void SetCondition(String newCondition) {
		conditionType = newCondition; 
	}
	public String GetCleanliness() {
		return cleanType; 
	}
	
	public void SetCleanliness(String newCleanliness) {
		cleanType = newCleanliness;
	}
	
	public void SetSalesPrice(Double amount) {
		salesPrice = (salesPrice * amount) + salesPrice; 
		
	}
	
	public Double GetSalesPrice() {
		return salesPrice;
		
	}
	public Vehicles thisVehicle() {
		return this;
	}
}


/* ===================================== PERFORMANCE CARS ============================================== */
class PerformanceCars extends Vehicles {
	
	public PerformanceCars() {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		vehicleType = "Performance Car"; 
		name = CreateName(); 
		cost = ThreadLocalRandom.current().nextDouble(20000, 40000); //20,000 - 40,000
		DetermineCost();
		salesPrice = cost * 2.0;
	}
}

/* =============================================== CARS ============================================== */
class Cars extends Vehicles {
	public Cars() {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		vehicleType = "Car"; 
		name = CreateName(); 
		cost = ThreadLocalRandom.current().nextDouble(10000, 20000);//10,000 - 20,0000
		DetermineCost();
		salesPrice = cost * 2.0; // randomly select a value between $20,000 and $40,0000.
	}
	
	
}

/* ======================================== PICKUPS ================================================ */
class Pickups extends Vehicles {
	public Pickups() {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		vehicleType = "Pickup";
		name = CreateName(); 
		cost = ThreadLocalRandom.current().nextDouble(10000, 40000); //10,000 - 40,000
		DetermineCost();
		salesPrice = cost * 2.0; // randomly select a value between $20,000 and $40,0000.
	}
	
}