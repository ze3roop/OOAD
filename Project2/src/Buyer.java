import java.util.concurrent.ThreadLocalRandom;

public class Buyer {
	//Every working day, 0 to 5 buyers will arrive to buy a vehicle (2-8 buyers on Friday/Saturday) from a Salesperson.
	//Buyers buy from a Salesperson (randomly selected). Buyers are initialized randomly with one of three types of Buying
	//, each with a base chance of buying a vehicle: Just Looking (10%), Wants One (40%), Needs One (70%).
	//The buyers will have a type of vehicle they want (Performance Car, Car, Pickup) randomly determined.
	
	protected String[] chanceOfBuying = {"Just Looking", "Wants One", "Needs One"}; // ENCAPSULATION
	protected String[] vehicleTypes = {"Performance Car", "Car", "Pickup"}; // ENCAPSULATION
	protected String chanceStr; // ENCAPSULATION
	protected Double chance; // ENCAPSULATION 
	protected String preference;  // ENCAPSULATION
	
	public Buyer()
	{
		int randomInt1 = ThreadLocalRandom.current().nextInt(0,3);
		chanceStr = chanceOfBuying[randomInt1];//randomly determined to be just looking, wants one, or needs one
		if(chanceStr == "Just Looking") {
			chance = 10.0;
		}
		else if (chanceStr == "Wants One") {
			chance = 40.0;
		}
		else if (chanceStr == "Needs One") {
			chance = 70.0;
		}
		int randomInt2 = ThreadLocalRandom.current().nextInt(0,3);
		preference = vehicleTypes[randomInt2];//randomly determined to be performance car, car, pickup
	}
	
	public String GetPreference() {
		return preference; 
	}
	public Double GetChance() {
		return chance; 
	}
}
