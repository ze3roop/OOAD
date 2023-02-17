import java.util.concurrent.ThreadLocalRandom;

public class Buyer {
	//Every working day, 0 to 5 buyers will arrive to buy a vehicle (2-8 buyers on Friday/Saturday) from a Salesperson.
	//Buyers buy from a Salesperson (randomly selected). Buyers are initialized randomly with one of three types of Buying
	//, each with a base chance of buying a vehicle: Just Looking (10%), Wants One (40%), Needs One (70%).
	//The buyers will have a type of vehicle they want (Performance Car, Car, Pickup) randomly determined.
	
	public String[] chanceOfBuying = {"Just Looking", "Wants One", "Needs One"};
	public String[] vehicleTypes = {"Performance Car", "Car", "Pickup"};
	public String chance;
	public String preference; 
	
	public Buyer()
	{
		int randomInt1 = ThreadLocalRandom.current().nextInt(0,3);
		chance = chanceOfBuying[randomInt1];//randomly determined to be just looking, wants one, or needs one
		int randomInt2 = ThreadLocalRandom.current().nextInt(0,3);
		preference = vehicleTypes[randomInt2];//randomly determined to be performance car, car, pickup
	}
	
	public String GetPreference() {
		return preference; 
	}
}
