import java.util.concurrent.ThreadLocalRandom;

public class Vehicles {
	public String name;
	public Double salesPrice;
	public String[] condition = {"Like New", "Used", "Broken"};
	public String conditionType;
	public String type; 
	public String[] cleanliness = {"dirty", "clean", "sparkling"};
	public String cleanType; 
	
	
	public String vehicleType;
	
	public Vehicles()
	{
		
	}
	public void DetermineSalesPrice() {
			int randomInt1 = ThreadLocalRandom.current().nextInt(0,3);
			conditionType = condition[randomInt1];//randomly determined to be Like New, Used, or Broken.
			if(conditionType == "Used") {
				salesPrice = salesPrice - (salesPrice * .2); 
			}
			else if (conditionType == "Broken") {
				salesPrice = salesPrice - (salesPrice * .5);
			}
			
			
			int randomInt2 = ThreadLocalRandom.current().nextInt(0,3); 
			cleanType = cleanliness[randomInt2]; //randomly determine to be sparkling, clean, or dirty.
			if(cleanType == "sparkling") {
				salesPrice = salesPrice - (salesPrice * .05); 
			}
			else if(cleanType == "clean") {
				salesPrice = salesPrice - (salesPrice * .35); 
			}
			else if(cleanType == "dirty") {
				salesPrice = salesPrice - (salesPrice * .60); 
			}
	}

	public String CreateName() {
		int randInt = ThreadLocalRandom.current().nextInt(0,1000);
		return vehicleType + ": " + randInt; //unique name algorithm goes here..
		
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
}


/* ===================================== PERFORMANCE CARS ============================================== */
class PerformanceCars extends Vehicles {
	
	public PerformanceCars() {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		salesPrice = (double) 2; // randomly select a value between $20,000 and $40,0000.
		vehicleType = "Performance Car"; 
		name = CreateName(); 
		DetermineSalesPrice();
	}
	
}

/* =============================================== CARS ============================================== */
class Cars extends Vehicles {
	public Cars() {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		salesPrice = (double) 2; // randomly select a value between $10,000 and $20,000.
		vehicleType = "Car"; 
		name = CreateName(); 
		DetermineSalesPrice();
	}
	
	
}

/* ======================================== PICKUPS ================================================ */
class Pickups extends Vehicles {
	public Pickups() {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		salesPrice = (double) 2; // randomly select a value between $10,000 and $40,000.
		vehicleType = "Pickup";
		name = CreateName(); 
		DetermineSalesPrice();
	}
	
}