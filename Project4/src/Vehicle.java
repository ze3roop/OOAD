public class Vehicle {

	// enums
	protected enum Types_of_Vehicles {performanceCar, car, pickup, 
		electricCar, motorcycle, monsterTruck, 
		transformer, semiTruck, moped,
		NILL}
	protected enum Types_of_Cleanliness {dirty, clean, sparkling, NILL}
	protected enum Types_of_Condition {broken, used, likeNew, NILL}
	protected enum Transformer_Faction {Cybertronians, Autobot, Deceptecon, Maximal, Predacon, MiniCon, Dinobot, Combiner, NILL}

	// variables
	protected String name = "NILL";
	protected Double salesPrice = 0.0;

	protected Types_of_Condition condition = Types_of_Condition.NILL;
	protected Types_of_Cleanliness cleanliness = Types_of_Cleanliness.NILL;

	protected Double cost = 0.0;
	
	protected Types_of_Vehicles vehicleType = Types_of_Vehicles.NILL;

	protected int racesWon = 0;

	protected int range = 0;
	protected double engineSize = 0.0;

	protected Transformer_Faction Faction = Transformer_Faction.NILL;

	// for decorator
	String description = "Unknown";
	public String getDescription(){
		return description;
	}

	
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
	public void increaseWinCount() { racesWon++; }
	
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

		// range for electric cars
		public void applyRageBoost(){
			if (isLikeNew() && isElectricCar()){
				range += 100;
			}
		}
		
		public void applyRageDecrease(){
			if (!isLikeNew() && isElectricCar()){
				range -= 100;
			}
		}

	
	// getter
	public Double GetCost() { return cost; }
	public String GetName() { return name; }
	public Double GetSalesPrice() { return salesPrice; }
	public Types_of_Vehicles GetType() { return vehicleType; }
	public int GetRange() {return range;}
	public double getEngine() {return engineSize;}
	public String getFaction_String() {
		String Faction_str = "";
		
		if (isTransformer()){ 
			if (Faction == Transformer_Faction.Autobot) {Faction_str = "Autobot";}
			else if (Faction == Transformer_Faction.Combiner) {Faction_str = "Combiner";}
			else if (Faction == Transformer_Faction.Cybertronians) {Faction_str = "Cybertronian";}
			else if (Faction == Transformer_Faction.Deceptecon) {Faction_str = "Deceptecon";}
			else if (Faction == Transformer_Faction.Dinobot) {Faction_str = "DinoBot";}
			else if (Faction == Transformer_Faction.Maximal) {Faction_str = "Maximal";}
			else if (Faction == Transformer_Faction.MiniCon) {Faction_str = "Mini-Con";}
			else if (Faction == Transformer_Faction.Predacon) {Faction_str = "Predacon";}
		} else {Faction_str = "N/A";}

		return Faction_str;
	}
	
	public String getInfo_asString() {

		String name_str = name;
		double salesPrice_Str = (double) Math.round(salesPrice * 100) / 100;
		String condition_str = "N/A";
		String cleanliness_str = "N/A";
		double cost_str = (double) Math.round(cost * 100) / 100;

		String vehicleType_str = "N/A";

		String racesWon_Str = String.valueOf(racesWon);

		String range_str = "N/A";
		String engineSize_str = "N/A";
		String Faction_str = "N/A";
	
		if (condition == Types_of_Condition.broken) { condition_str = "Broken";}
		else if (condition == Types_of_Condition.likeNew) { condition_str = "Like New";}
		else if (condition == Types_of_Condition.used) { condition_str = "Used";}
	
		if (cleanliness == Types_of_Cleanliness.clean) { cleanliness_str = "Clean";}
		else if (cleanliness == Types_of_Cleanliness.dirty) {cleanliness_str = "Dirty";}
		else if (cleanliness == Types_of_Cleanliness.sparkling) {cleanliness_str = "Sparkling";}

		if (isPerformanceCar()){ vehicleType_str = "Performance Car"; }
		else if (isCar()){ vehicleType_str = "Car"; }
		else if (isPickup()){ vehicleType_str = "Pickup"; }
		
		else if (isElectricCar()){
			range_str = String.valueOf(range);
			vehicleType_str = "Electric Car";
		}
		else if (isMotorcycle()){
			engineSize_str = String.valueOf((double)Math.round(engineSize * 100) / 100) + "cc";
			vehicleType_str = "Motorcycle";
		}
		else if (isMonserTruck()){ vehicleType_str = "Monster Truck"; }
		
		else if (isMoped()){
			vehicleType_str = "Moped";
			engineSize_str = String.valueOf((double)Math.round(engineSize * 100) / 100) + "cc";
		}
		else if (isSemiTruck()){vehicleType_str = "Semi Truck"; }
		else if (isTransformer()){ 
			vehicleType_str = "Transformer"; 
			if (Faction == Transformer_Faction.Autobot) {Faction_str = "Autobot";}
			else if (Faction == Transformer_Faction.Combiner) {Faction_str = "Combiner";}
			else if (Faction == Transformer_Faction.Cybertronians) {Faction_str = "Cybertronian";}
			else if (Faction == Transformer_Faction.Deceptecon) {Faction_str = "Deceptecon";}
			else if (Faction == Transformer_Faction.Dinobot) {Faction_str = "DinoBot";}
			else if (Faction == Transformer_Faction.Maximal) {Faction_str = "Maximal";}
			else if (Faction == Transformer_Faction.MiniCon) {Faction_str = "Mini-Con";}
			else if (Faction == Transformer_Faction.Predacon) {Faction_str = "Predacon";}
		}

		return String.format("%-35s%-20.2f%-20s%-20s%-20.2f%-20s%-20s%-20s%-20s%-20s",
		name_str,
		salesPrice_Str,
		condition_str,
		cleanliness_str,
		cost_str,
		vehicleType_str,
		racesWon_Str,
		range_str,
		engineSize_str,
		Faction_str);

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

	// Vehicles that have a Win count of 1 or more increase their sale price in the Selling Activity by 10%.
	public void applyWinnerBonus() {
		if (racesWon > 0){
			salesPrice *= 1.1;
		}
	}

		// is Functions
		public Boolean isDirty() 			{ return cleanliness == Types_of_Cleanliness.dirty;}
		public Boolean isClean() 			{ return cleanliness == Types_of_Cleanliness.clean; }
		public Boolean isSparkling() 		{ return cleanliness == Types_of_Cleanliness.sparkling; }
		public Boolean isBroken() 			{ return condition == Types_of_Condition.broken; }
		public Boolean isLikeNew() 			{ return condition == Types_of_Condition.likeNew; }
		public Boolean isUsed() 			{ return condition == Types_of_Condition.used; }
		public Boolean isPickup() 			{ return vehicleType == Types_of_Vehicles.pickup; }
		public Boolean isCar() 				{ return vehicleType == Types_of_Vehicles.car; }
		public Boolean isPerformanceCar() 	{ return vehicleType == Types_of_Vehicles.performanceCar; }
		public Boolean isVehicle() 			{ return vehicleType == Types_of_Vehicles.NILL;}
		public Boolean isElectricCar() 		{ return vehicleType == Types_of_Vehicles.electricCar;}
		public Boolean isMonserTruck() 		{ return vehicleType == Types_of_Vehicles.monsterTruck;}
		public Boolean isMotorcycle() 		{ return vehicleType == Types_of_Vehicles.motorcycle;}
		public Boolean isSemiTruck() 		{ return vehicleType == Types_of_Vehicles.semiTruck; }
		public Boolean isTransformer() 		{ return vehicleType == Types_of_Vehicles.transformer; }
		public Boolean isMoped() 			{ return vehicleType == Types_of_Vehicles.moped; }

}
/* =========== DECORATOR =========== */
abstract class VehicleDecorator extends Vehicle {
	String name = "NILL";
	Double salesPrice = 0.0;

	Types_of_Condition condition = Types_of_Condition.NILL;
	Types_of_Cleanliness cleanliness = Types_of_Cleanliness.NILL;

	Double cost = 0.0;
	
	Types_of_Vehicles vehicleType = Types_of_Vehicles.NILL;

	int racesWon = 0;

	int range = 0;
	double engineSize = 0.0;
	public abstract String GetName();
	public abstract Double GetSalesPrice();
}

/* =========== Extended Warranty =========== */
class ExtendedWarranty extends VehicleDecorator {
	Vehicle vehicle;

	public ExtendedWarranty(Vehicle vehicle){
		this.vehicle = vehicle;
		this.vehicle.cleanliness = vehicle.cleanliness;
		this.vehicle.condition = vehicle.condition;
		this.vehicle.cost = vehicle.cost;
		this.vehicle.engineSize = vehicle.engineSize;
		this.vehicle.name = vehicle.name;
		this.vehicle.racesWon = vehicle.racesWon;
		this.vehicle.range = vehicle.range;
		this.vehicle.salesPrice = vehicle.salesPrice;
		this.vehicle.vehicleType = vehicle.vehicleType;
	}
	public String GetName(){
		return vehicle.GetName() + ", Extended Warranty";
	}
	public Double GetSalesPrice(){
		//vehicle.salesPrice = ((vehicle.salesPrice * .2) + vehicle.salesPrice);
		return ((vehicle.salesPrice * .2) + vehicle.salesPrice); // 20% of vehicle salesprice, 25% chance of Buyer adding
	}
}

/* =========== Undercoating =========== */
class Undercoating extends VehicleDecorator {
	Vehicle vehicle;

	public Undercoating(Vehicle vehicle){
		this.vehicle = vehicle;
		this.vehicle.cleanliness = vehicle.cleanliness;
		this.vehicle.condition = vehicle.condition;
		this.vehicle.cost = vehicle.cost;
		this.vehicle.engineSize = vehicle.engineSize;
		this.vehicle.name = vehicle.name;
		this.vehicle.racesWon = vehicle.racesWon;
		this.vehicle.range = vehicle.range;
		this.vehicle.salesPrice = vehicle.salesPrice;
		this.vehicle.vehicleType = vehicle.vehicleType;
	}
	public String GetName(){
		return vehicle.GetName() + ", Undercoating";
	}
	public Double GetSalesPrice(){
		//vehicle.salesPrice = ((vehicle.salesPrice * .05) + vehicle.salesPrice);
		return ((vehicle.salesPrice * .05) + vehicle.salesPrice); // 5% of vehicle salesprice, 10% chance of Buyer adding
	}
}

/* =========== Road Rescue Coverage =========== */
class RoadRescueCoverage extends VehicleDecorator {
	Vehicle vehicle;

	public RoadRescueCoverage(Vehicle vehicle){
		this.vehicle = vehicle;
		this.vehicle.cleanliness = vehicle.cleanliness;
		this.vehicle.condition = vehicle.condition;
		this.vehicle.cost = vehicle.cost;
		this.vehicle.engineSize = vehicle.engineSize;
		this.vehicle.name = vehicle.name;
		this.vehicle.racesWon = vehicle.racesWon;
		this.vehicle.range = vehicle.range;
		this.vehicle.salesPrice = vehicle.salesPrice;
		this.vehicle.vehicleType = vehicle.vehicleType;
	}
	public String GetName(){
		return vehicle.GetName() + ", Road Rescue Coverage";
	}
	public Double GetSalesPrice(){
		//vehicle.salesPrice = ((vehicle.salesPrice * .02) + vehicle.salesPrice);
		return ((vehicle.salesPrice * .02) + vehicle.salesPrice); // 2% of vehicle salesprice, 5% chance of Buyer adding
	}
}

/* =========== Satellite Radio =========== */
class SatelliteRadio extends VehicleDecorator {
	Vehicle vehicle;

	public SatelliteRadio(Vehicle vehicle){
		this.vehicle = vehicle;
		this.vehicle.cleanliness = vehicle.cleanliness;
		this.vehicle.condition = vehicle.condition;
		this.vehicle.cost = vehicle.cost;
		this.vehicle.engineSize = vehicle.engineSize; 
		this.vehicle.name = vehicle.name;
		this.vehicle.racesWon = vehicle.racesWon;
		this.vehicle.range = vehicle.range;
		this.vehicle.salesPrice = vehicle.salesPrice;
		this.vehicle.vehicleType = vehicle.vehicleType;
	}
	public String GetName(){
		return vehicle.GetName() + ", Satellite Radio";
	}
	public Double GetSalesPrice(){
		//vehicle.salesPrice = ((vehicle.salesPrice * .02) + vehicle.salesPrice);
		return ((vehicle.salesPrice * .05) + vehicle.salesPrice); // 40% of vehicle salesprice, 5% chance of Buyer adding
	}
}
//Since the percent of chance of buyer adding any of the addons is static, we can just do this in the FNCD
//Like we do a undercoating chance, and if we get in that chance, then the buyer will add it
//same goes with the other addons. 