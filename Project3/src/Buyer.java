public class Buyer {

	// enums
	private enum type_of_buying {Just_looking, Want_One, Need_One, NILL}
	
	// variables
	protected type_of_buying chanceOfBuying = type_of_buying.NILL;
	protected Vehicle.Types_of_Vehicles preference = Vehicle.Types_of_Vehicles.NILL;
	
	protected int chance; 

	private String name = "Buyer_" + Helper.GenerateUniqueID();
	
	// constructor
	public Buyer()
	{
		int ran = Helper.RandInt(1, 3);

		if(ran == 1) {
			chanceOfBuying = type_of_buying.Just_looking;
			chance = 10;
		} else if (ran == 2) {
			chanceOfBuying = type_of_buying.Want_One;
			chance = 40;
		}
		else if (ran == 3) {
			chanceOfBuying = type_of_buying.Need_One;
			chance = 70;
		}

		ran = Helper.RandInt(0,Vehicle.Types_of_Vehicles.values().length - 2); // -1 due to Length, -1 last entry is NILL
		preference = Vehicle.Types_of_Vehicles.values()[ran]; //randomly determined to be performance car, car, pickup

	}
	
	// setters
	
	// getters
	public Vehicle.Types_of_Vehicles GetPreference() { return preference; }
	public int GetChance() { return chance; }
	public String GetName() { return name;}

	
	// public functions
		
		// Boolean functions
		public Boolean WantsPickup() { return preference == Vehicle.Types_of_Vehicles.pickup; }
		public Boolean WantsPerformanceCar() { return preference == Vehicle.Types_of_Vehicles.performanceCar; }
		public Boolean WantsCar() { return preference == Vehicle.Types_of_Vehicles.performanceCar; }

}
