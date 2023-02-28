public class Buyer {
	//Every working day, 0 to 5 buyers will arrive to buy a vehicle (2-8 buyers on Friday/Saturday) from a Salesperson.
	//Buyers buy from a Salesperson (randomly selected). Buyers are initialized randomly with one of three types of Buying
	//, each with a base chance of buying a vehicle: Just Looking (10%), Wants One (40%), Needs One (70%).
	//The buyers will have a type of vehicle they want (Performance Car, Car, Pickup) randomly determined.

	private enum type_of_buying {Just_looking, Want_One, Need_One}
	
	protected type_of_buying chanceOfBuying; // ENCAPSULATION
	protected Vehicles.Types_of_Vehicles preference; // ENCAPSULATION
	
	protected String chanceStr; // ENCAPSULATION
	protected int chance; // ENCAPSULATION 

	private String name = "Buyer_" + Helper.GenerateUniqueID();
	
	public Buyer()
	{
		int ran = Helper.RandInt(1, type_of_buying.values().length);

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

		ran = Helper.RandInt(0,Vehicles.Types_of_Vehicles.values().length - 1);
		preference = Vehicles.Types_of_Vehicles.values()[ran]; //randomly determined to be performance car, car, pickup

	}
	
	public Vehicles.Types_of_Vehicles GetPreference() {
		return preference; 
	}
	public int GetChance() {
		return chance; 
	}

	public Boolean WantsPickup() {
		return preference == Vehicles.Types_of_Vehicles.pickup;
	}

	public Boolean WantsPerformanceCar() {
		return preference == Vehicles.Types_of_Vehicles.performanceCar;
	}

	public Boolean WantsCar() {
		return preference == Vehicles.Types_of_Vehicles.performanceCar;
	}

	public String GetName() { return name;}

}
