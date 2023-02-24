import java.util.ArrayList;



public class Vehicle {

	public ArrayList<String> types_of_condition;
	public ArrayList<String> types_of_cleanliness;

	private static boolean created_ArrList = false;

	protected String name;
	protected Double salesPrice;
	protected Double cost;
	protected String condition;
	protected String cleanliness;
	protected String VehicleType;
	
	public Vehicle()
	{
		// create the static list of arrays (should only happen once on the first initalization)
		if (!created_ArrList){
			types_of_condition.add("Like New");
			types_of_condition.add("Used");
			types_of_condition.add("Broken");
			types_of_cleanliness.add("Like New");
			types_of_cleanliness.add("Clean");
			types_of_cleanliness.add("Spaekling");
			created_ArrList = true;
		}

		name = "NULL";
		salesPrice = 0.0;
		cost = 0.0;
		condition = RandomCondition();
        cleanliness = RandomCleanliness();
	}

	public String GetVehicleType() {
		return VehicleType;
	}

	public String GetName() { //POLYMORPHISM  AND ABSTRACTION
		return name;
	}
	
	public Double GetCost() { //POLYMORPHISM AND ABSTRACTION
		return cost;
	}
	
	public String GetCondition() { //POLYMORPHISM AND ABSTRACTION
		return condition;
		
	}

	public void SetCondition(String newCondition) { //POLYMORPHISM AND ABSTRACTION
		if (types_of_condition.contains(newCondition)){
			condition = newCondition; 
		} else {
			System.out.println("Error " + newCondition + " is not a type of condition");
			System.exit(404);
		}
	}

	public String GetCleanliness() { //POLYMORPHISM AND ABSTRACTION
		return cleanliness; 
	}
	
	public void SetCleanliness(String newCleanliness) { //POLYMORPHISM AND ABSTRACTION
		if (types_of_condition.contains(newCleanliness)){
			condition = newCleanliness; 
		} else {
			System.out.println("Error " + newCleanliness + " is not a type of condition");
			System.exit(403);
		}
	}
	
	public void SetSalesPrice(Double amount) { //POLYMORPHISM AND ABSTRACTION
		salesPrice = (salesPrice * amount) + salesPrice; 
		
	}
	
	public Double GetSalesPrice() { //POLYMORPHISM AND ABSTRACTION
		return salesPrice;
		
	}

	protected String RandomCleanliness() {
		Helper h = new Helper();
		int ranInt = h.randInt(1, 100);

		final int P_SPARKLING = 5;
		final int P_CLEAN = 35;
		final int P_DIRTY = 60;

		if (ranInt <= P_SPARKLING) {return types_of_cleanliness.get(2);}
		else if(ranInt <= P_SPARKLING + P_CLEAN) {return types_of_cleanliness.get(1);}
		else if (ranInt <= P_SPARKLING + P_CLEAN + P_DIRTY) { return types_of_cleanliness.get(1);}
		else {return "Error";}

	}

	protected String RandomCondition() {
		Helper h = new Helper();
		int ranInt = h.randInt(0, 2);

		return types_of_condition.get(ranInt);
	}

	protected void adjustCost () {
		if (condition == types_of_condition.get(1)){ // if condition is Used
			cost = cost * .8;
		}
		else if (condition == types_of_cleanliness.get(2)) { // if condition is broken
			cost = cost * .5;
		}
	}

	protected void CalculateSalesPrice () {
		salesPrice = cost * 2;
	}
}