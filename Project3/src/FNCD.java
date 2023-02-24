import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class FNCD {

	final int REQ_CARS = 4;
	final int REQ_STAFF = 3;

	protected ArrayList<Salesperson> salesPeople = new ArrayList<Salesperson>();
	protected ArrayList<Mechanic> mechanics = new ArrayList<Mechanic>();
	protected ArrayList<Intern> interns = new ArrayList<Intern>();


	protected ArrayList<PerformanceCar> performanceCars = new ArrayList<PerformanceCar>();
	protected ArrayList<Car> cars = new ArrayList<Car>();
	protected ArrayList<Pickup> pickups = new ArrayList<Pickup>();

	private Double budget = 500000.0; //this is going to be private.
	private int num_loans = 0;

	protected ArrayList<Vehicle> soldVehicles = new ArrayList<Vehicle>();
	protected ArrayList<Vehicle> allSoldVehicles = new ArrayList<Vehicle>();
	protected ArrayList<Staff> departedStaff = new ArrayList<Staff>();
	
	static protected String[] daysOfTheWeek_ = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	static protected int dayCount = 0; 
	static protected int daysToSimulate = 0;

	protected Double totalSalesPerDay;
	
	public FNCD(int daysToSimulate)
	{		
		//initialize staff and vehicles
		for (int a = 0; a<REQ_STAFF + 1; a++) {
			interns.add( new Intern() );
		}
		for (int b = 0; b<REQ_STAFF + 1; b++) {
			mechanics.add( new Mechanic() );
		}
		for (int c = 0; c<REQ_STAFF + 1; c++) {
			salesPeople.add( new Salesperson() );
		}

		for (int a = 0; a < REQ_STAFF; a++) {
			performanceCars.add(new PerformanceCar() );
		}
		for (int a = 0; a < REQ_STAFF; a++) {
			cars.add( new Car() );
		}
		for (int a = 0; a < REQ_STAFF; a++) {
			pickups.add(new Pickup() );
		}
	}
	
	public void Start() {
		for (int i = 0; i <= daysToSimulate; i++) {
			Opening();
			totalSalesPerDay = 0.0;
		}
		System.out.println("ALL STAFF THAT HAS LEFT: ");

		int staff_left = departedStaff.size();

		for(int i = 0; i < staff_left; i++) {
			System.out.println( departedStaff.get(i).GetName());
		}
	}
	
	public void Opening() {

		System.out.println("FNCD DAY " + dayCount + " - " + daysOfTheWeek_[dayCount % 6]);
		if (dayCount % 6 == 0) {
			System.out.println("CLOSED");
			return;
		}

		System.out.println("Opening..."); 
		

		
		//NOTE: EVERY TIME MONEY IS TAKEN OUT OF THE BUDGET, CHECK IF BUDGET IS <= 0. IF IT IS THEN ADD $250,000 TO THE OPERATING BUDGET, AND ANNOUNCE IT.
		
		
		//if interns are less than three interns, hire (e.g. add) new interns to the FNCD to bring the count back to three. 
		if(interns.size() < REQ_STAFF) {
			for (int i = 0; interns.size()<REQ_STAFF; i++) {
				interns.add( new Intern() );
				System.out.println("Hired " + interns.get(i).GetName());
			}
		}
		
		
		//There should be four of each type of Vehicle in inventory. If there are not for any reason, additional vehicles will be sent to the FNCD.
		//These newly instantiated vehicles must be paid for, reducing the operating  budget by the cost of the Vehicle. The Vehicles will then be
		//part of the FNCD inventory.
		
		if(performanceCars.size() < REQ_CARS) {
			for (int i = 0; performanceCars.size()<REQ_CARS; i++) {
				performanceCars.add( new PerformanceCar() );
				//take money from operating budget. 
				budget = budget - performanceCars.get(i).GetCost(); 
				System.out.println("Purchased " + performanceCars.get(i).GetName() + ". For: " + performanceCars.get(i).GetCost());
			}
		}
		if(cars.size() < REQ_CARS) {
			for (int i = 0; cars.size() < REQ_CARS; i++) {
				cars.add( new Car() );
				//take money from operating budget. 
				//SetBudget( performanceCars just instantiated.getSalesPrice() ); 
				budget = budget - cars.get(i).GetCost(); 
				System.out.println("Purchased " + cars.get(i).GetName() + ". For: " + cars.get(i).GetCost());
			}
		}
		if(pickups.size() < REQ_CARS) {
			for (int i = 0; pickups.size() < REQ_CARS; i++) {
				pickups.add( new Pickup() );
				//take money from operating budget. 
				//SetBudget( pickup just instantiated.getSalesPrice() ); 
				budget = budget - pickups.get(i).GetCost(); 
				System.out.println("Purchased " + pickups.get(i).GetName() + ". For: " + pickups.get(i).GetCost());
			}
		}
		
		Washing(); 
		
	}
	
	public void Washing() {
		Helper h = new Helper();

		System.out.println("Washing...");
		ArrayList<Vehicle> dirtyVehicles = new ArrayList<Vehicle>();
		ArrayList<Vehicle> cleanVehicles = new ArrayList<Vehicle>();
		ArrayList<Vehicle> sparklingVehicles = new ArrayList<Vehicle>();
		
		for(int i = 0; i < REQ_CARS; i++) {
			if(h.compareString(performanceCars.get(i).GetCleanliness(),"dirty")) {
				dirtyVehicles.add(performanceCars.get(i));
				performanceCars.remove(i);
			} else if (h.compareString(performanceCars.get(i).GetCleanliness(),"clean")) {
				cleanVehicles.add( performanceCars.get(i));
				performanceCars.remove(i);
			}

			if(h.compareString(cars.get(i).GetCleanliness(), "dirty")) {
				dirtyVehicles.add( cars.get(i));
				cars.remove(i);
			} else if (h.compareString(cars.get(i).GetCleanliness(),"clean")) {
				cleanVehicles.add( cars.get(i));
				cars.remove(i);
			}

			if(h.compareString(pickups.get(i).GetCleanliness(),"dirty")) {
				dirtyVehicles.add( pickups.get(i) );
				pickups.remove(i);
			} else if (h.compareString(pickups.get(i).GetCleanliness(),"clean")) {
				cleanVehicles.add( pickups.get(i));
				pickups.remove(i);
			}
		}
		
		for (int i = 0; i<REQ_STAFF; i++) { //So, loop through every intern one at a time, if we have vehicl to wasj, and the intern still has jobs to do, then iterate through the while loop.
			// Once either the intern has done all available jobs for himself, then we go to the next intern.

			while (interns.get(i).doJob()) { // if the intern can do their job, do the job
				if (!dirtyVehicles.isEmpty()){ // if there is a dirty car, wash it
					if (h.percentChance(80)) { // chanve to be clean
						cleanVehicles.add(dirtyVehicles.get(0));
						dirtyVehicles.remove(0);
					} else if (h.percentChance(5)){ // chance to be sparkling
						interns.get(i).EarnBonus(dirtyVehicles.get(0).GetVehicleType());
						sparklingVehicles.add(dirtyVehicles.get(0));
						dirtyVehicles.remove(0);
					}
				} else if (!cleanVehicles.isEmpty()) { // if there is a clean car to wasj, wash it
					if (h.percentChance(5)) { // chance to become dirty
						dirtyVehicles.add(cleanVehicles.get(0));
						cleanVehicles.remove(0);
					} else if (h.percentChance(30)){
						interns.get(i).EarnBonus(dirtyVehicles.get(0).GetVehicleType());
						sparklingVehicles.add(cleanVehicles.get(0));
						cleanVehicles.remove(0);
					}
				} else {
					// do nothing
				}
			} // Intern cannot work any more
			
		} // No more interns to work

		// put the cars back into the inventory

		while (!dirtyVehicles.isEmpty()){
			string vechicle_type = dirtyVehicles.get(0).GetVehicleType();
			if (h.compareString(vechicle_type, "PerformanceCCar")){
				performanceCars.add((PerformanceCar)dirtyVehicles.get(0));
				dirtyVehicles.remove(0);
			} else if (h.compareString(vechicle_type, "Pickup")){
				pickups.add((Pickup)dirtyVehicles.get(0));
				dirtyVehicles.remove(0);
			} else if (h.compareString(vechicle_type, "Car")){
				cars.add((Car)dirtyVehicles.get(0));
				dirtyVehicles.remove(0);
			}
		}
		
		Repairing();
	}
	
	public void Repairing() {
		//Each mechanic can fix two vehicles per day. Broken vehicles that are fixed become Used.
		//Used Vehicles that are fixed become Like New.
		//Each mechanic has an 80% chance of fixing any Vehicle worked on. Whether fixed or not, any Vehicle worked on will go down one class
		//of cleanliness (if not already dirty).
		//A vehicle that becomes used has its sales price increased 50%.
		//A vehicle that becomes like new has its sales price increased 25%.
		//Mechanics receive a bonus from each successful repair by Vehicle type. 
		
		System.out.println("Repairing...");
		
		ArrayList<Vehicle> brokenVehicles = new ArrayList<Vehicle>();
		for(int i = 0; i<carAmount; i++) {
			if(performanceCars.get(i).GetCondition() == "Broken") {
				brokenVehicles.add( performanceCars.get(i) );
			}
			if(cars.get(i).GetCondition() == "Broken") {
				brokenVehicles.add( cars.get(i) );
			}
			if(pickups.get(i).GetCondition() == "Broken") {
				brokenVehicles.add( pickups.get(i) );
			}
		}
		
		ArrayList<Vehicle> usedVehicles = new ArrayList<Vehicle>();
		for(int i = 0; i<carAmount; i++) {
			if(performanceCars.get(i).GetCondition() == "Used") {
				usedVehicles.add( performanceCars.get(i) );
			}
 			if(cars.get(i).GetCondition() == "Used") {
				usedVehicles.add( cars.get(i) );
			}
			if(pickups.get(i).GetCondition() == "Used") {
				usedVehicles.add( pickups.get(i) );
			}
		}
		
		ArrayList<Vehicle> likeNewVehicles = new ArrayList<Vehicle>();
		for(int i = 0; i<carAmount; i++) {
			if(performanceCars.get(i).GetCondition() == "Like New") {
				likeNewVehicles.add( performanceCars.get(i) );
			}
			if(cars.get(i).GetCondition() == "Like New") {
				likeNewVehicles.add( cars.get(i) );
			}
			if(pickups.get(i).GetCondition() == "Like New") {
				likeNewVehicles.add( pickups.get(i) );
			}
		}
		
		for (int i = 0; i<=staffPerType; i++) {  
			while(mechanics.get(i).GetJobsDone() < 2 || (brokenVehicles.size() == 0 && usedVehicles.size() == 0)) {
				if (brokenVehicles.size() > 0) 
				{
					var d = Math.random() * 100;
					if (d <= 80) {
						//80% chance to fix a vehicle
						//give mechanic a bonus for fixing a vehicle by vehicle type
						System.out.println(mechanics.get(i).GetName() + " just repaired " + brokenVehicles.get(0).GetName() + " and made it Used");
						mechanics.get(i).Bonus(brokenVehicles.get(0).GetVehicleType());
						//bring vehicle down a level of cleanliness
						if(brokenVehicles.get(0).GetCleanliness() == "sparkling"){
							brokenVehicles.get(0).SetCleanliness("clean");
						}
						else if(brokenVehicles.get(0).GetCleanliness() == "clean"){
							brokenVehicles.get(0).SetCleanliness("dirty");
						}
						//A vehicle that becomes used has its sales price increased 50%. 
						brokenVehicles.get(0).SetSalesPrice(.5);
						
						//add fixed vehicle to the usedList, and remove it from the brokenList. 
						usedVehicles.add(brokenVehicles.get(0));
						brokenVehicles.remove(0);
					}
					else {
						if(brokenVehicles.get(0).GetCleanliness() == "sparkling"){
							brokenVehicles.get(0).SetCleanliness("clean");
						}
						else if(brokenVehicles.get(0).GetCleanliness() == "clean"){
							brokenVehicles.get(0).SetCleanliness("dirty");
						}
					}
					
					mechanics.get(i).SetJobsDone();
					    
				}
				else if(usedVehicles.size() > 0)
				{
					var d = Math.random() * 100;
					if (d <= 80) {
						//80% chance to fix a vehicle
						//give mechanic a bonus for fixing a vehicle by vehicle type
						System.out.println(mechanics.get(i).GetName() + " just repaired " + usedVehicles.get(0).GetName() + " and made it Like New");
						mechanics.get(i).Bonus(usedVehicles.get(0).GetVehicleType());
						//bring vehicle down a level of cleanliness
						if(usedVehicles.get(0).GetCleanliness() == "sparkling"){
							usedVehicles.get(0).SetCleanliness("clean");
						}
						else if(usedVehicles.get(0).GetCleanliness() == "clean"){
							usedVehicles.get(0).SetCleanliness("dirty");
						}
						//A vehicle that becomes used has its sales price increased 25%. 
						usedVehicles.get(0).SetSalesPrice(.25);
						
						//add fixed vehicle to the likeNewVehicles, and remove it from the usedList. 
						likeNewVehicles.add(usedVehicles.get(0));
						usedVehicles.remove(0); 
					}
					else {
						if(usedVehicles.get(0).GetCleanliness() == "sparkling"){
							usedVehicles.get(0).SetCleanliness("clean");
						}
						else if(usedVehicles.get(0).GetCleanliness() == "clean"){
							usedVehicles.get(0).SetCleanliness("dirty");
						}
					}
					
					mechanics.get(i).SetJobsDone();
				}
			}
			
		}
		
		performanceCars.clear();
		cars.clear();
		pickups.clear();
		
		//drivableVehicles.addAll(drivablePerfCars);
		for(int i = 0; i < brokenVehicles.size(); i++) {
			if(brokenVehicles.get(i).GetVehicleType() == "Performance Car") {
				performanceCars.add((PerformanceCars) brokenVehicles.get(i));
			}
			if(brokenVehicles.get(i).GetVehicleType() == "Car") {
				cars.add((Cars) brokenVehicles.get(i));
			}
			if(brokenVehicles.get(i).GetVehicleType() == "Pickup") {
				pickups.add((Pickups) brokenVehicles.get(i));
			}
		}
		for(int a = 0; a < usedVehicles.size(); a++) {
			if(usedVehicles.get(a).GetVehicleType() == "Performance Car") {
				performanceCars.add((PerformanceCars) usedVehicles.get(a));
			}
			if(usedVehicles.get(a).GetVehicleType() == "Car") {
				cars.add((Cars) usedVehicles.get(a));
			}
			if(usedVehicles.get(a).GetVehicleType() == "Pickup") {
				pickups.add((Pickups) usedVehicles.get(a));
			}
		}
		for(int b = 0; b < likeNewVehicles.size(); b++) {
			if(likeNewVehicles.get(b).GetVehicleType() == "Performance Car") {
				performanceCars.add((PerformanceCars) likeNewVehicles.get(b));
			}
			if(likeNewVehicles.get(b).GetVehicleType() == "Car") {
				cars.add((Cars) likeNewVehicles.get(b));
			}
			if(likeNewVehicles.get(b).GetVehicleType() == "Pickup") {
				pickups.add((Pickups) likeNewVehicles.get(b));
			}
		}
		
		Selling();
	}
	
	public void Selling() {
		 
		System.out.println("Selling...");
		
		ArrayList<Buyer> buyers = new ArrayList<Buyer>();
		
		/*==== INSTANTIATE THE BUYERS DEPENDING ON THE DAY OF THE WEEK ===*/
		if(day_ != "Friday" || day_!= "Saturday") {
			int randomNum = ThreadLocalRandom.current().nextInt(0, 5 + 1);
			
			for (int i = 0; i<=randomNum; i++)
			{
				buyers.add(new Buyer());
			}
			
		}
		else {
			int randomNum = ThreadLocalRandom.current().nextInt(2, 8 + 1);
			
			for (int i = 0; i<=randomNum; i++)
			{
				buyers.add(new Buyer());
			}
		}
		
		
		/*=== INSTANTIATE AN ALL VEHICLES ARRAY LIST, EXCLUDING BROKEN VEHICLES ===*/
		ArrayList<PerformanceCars> brokenPerf = new ArrayList<PerformanceCars>();
		ArrayList<Cars> brokenCar = new ArrayList<Cars>();
		ArrayList<Pickups> brokenPickup = new ArrayList<Pickups>();
		
		for ( int i = 0; i < performanceCars.size(); i++) {
			if(performanceCars.get(i).GetCondition() == "Broken")
			{
				brokenPerf.add(performanceCars.get(i));
				performanceCars.remove(i);
			}
		}
		for ( int i = 0; i < cars.size(); i++) {
			if(cars.get(i).GetCondition() == "Broken")
			{
				brokenCar.add(cars.get(i));
				cars.remove(i);
			}
		}
		for ( int i = 0; i < pickups.size(); i++) {
			if(pickups.get(i).GetCondition() == "Broken")
			{
				brokenPickup.add(pickups.get(i));
				pickups.remove(i);
			}
		}
		Collections.sort(performanceCars, Comparator.comparingDouble(Vehicle::GetSalesPrice).reversed());
		Collections.sort(cars, Comparator.comparingDouble(Vehicle::GetSalesPrice).reversed());
		Collections.sort(pickups, Comparator.comparingDouble(Vehicle::GetSalesPrice).reversed());
		
		
		
		/*=== SORT ALLDRIVABLEVEHICLES IN DESCENDING ORDER OF SALESPRICE ===*/
		//Collections.sort(allDrivableVehicles, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
		
		/*=== CHECK BUYER PREFERENCE ===*/
		for (int i = 0; i<buyers.size(); i++) {
			//INSTANTIATE A CHANCE VARIABLE HERE
			Double chance; 
			
			//INSTANTIATE OUR SALESPERSON HERE AS WELL ( RANDOMLY )
			int randomNum2 = ThreadLocalRandom.current().nextInt(0, 4); 
			salesPeople.get(randomNum2); // get a random salesPerson. 
			
			if(buyers.get(i).GetPreference() == "Performance Car") {
				
				if(performanceCars.size() >= 1) {
					//sell the one in the first spot, since that is the highest priced one.	
					//BUT FIRST CHECK IF WE CAN INCREASE SALESCHANCE 
					//WE COULD UPDATE SALESCHANCE IN THE SUB CLASS, BUT WE WOULD HAVE TO UPDATE EACH ITERATION IN REPAIRING AND CLEANING. OR WE CAN JUST CHECK HERE
					for(int a = 0; a<performanceCars.size(); a++) {
						if(performanceCars.get(a).GetCondition() != "Broken") {
						
							chance = buyers.get(i).GetChance();
							
							if(performanceCars.get(a).GetCleanliness() == "sparkling") {
								//chance of sales up 10%.
								chance = chance + 10.0;
							}
							if(performanceCars.get(a).GetCondition() == "Like New") {
								//chance of sales up 10%.
								chance = chance + 10.0;
							}
							//NOW SELL VEHICLE WITH UPDATED CHANCE. 
							var d = Math.random() * 100;
							if (d <= chance) {
								System.out.println( salesPeople.get(randomNum2).GetName() + " just sold " + performanceCars.get(a).GetName() + " for " + performanceCars.get(a).GetSalesPrice());
								totalSalesPerDay = totalSalesPerDay + performanceCars.get(a).GetSalesPrice();
								salesPeople.get(randomNum2).Bonus(performanceCars.get(a).GetVehicleType());
								budget_ = budget_ + performanceCars.get(a).GetSalesPrice();
								soldVehicles.add( performanceCars.get(a) );
								//Collections.sort(performanceCars, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
								performanceCars.remove(a); 
								break;
							}
						}
						
					}
					
				}
				else {
					//WE DON'T HAVE ANY PERFORMANCE CARS, SO 
					ArrayList<Vehicle> drivableVehicles = new ArrayList<Vehicle>();
					drivableVehicles.addAll(cars);
					drivableVehicles.addAll(pickups);
					Collections.sort(drivableVehicles, Comparator.comparingDouble(Vehicle::GetSalesPrice).reversed());
					//NOW SELL FIRST ELEMENT OF DRIVABLE VEHICLES. 
					
					chance = buyers.get(i).GetChance();
					
					if(drivableVehicles.get(0).GetCleanliness() == "sparkling") {
						//chance of sales up 10%.
						chance = chance + 10.0;
					}
					if(drivableVehicles.get(0).GetCondition() == "Like New") {
						//chance of sales up 10%.
						chance = chance + 10.0;
					}
					
					chance = chance - 20.0; // - 20% because it's not the preferred type of car. 
					
					//NOW SELL VEHICLE, BUT DECREASES SALES CHANCE BY 20%.
					var d = Math.random() * 100;
					if (d <= chance - 20.0) {
						for(int a = 0; a<drivableVehicles.size(); a++) {
							if(drivableVehicles.get(a).GetVehicleType() == "Car" && drivableVehicles.get(a).GetCondition()!="Broken") {
								
								Collections.sort(performanceCars, Comparator.comparingDouble(Vehicle::GetSalesPrice).reversed());
								System.out.println( salesPeople.get(randomNum2).GetName() + " just sold " + drivableVehicles.get(a).GetName() + " for " + drivableVehicles.get(a).GetSalesPrice());
								cars.remove(a); 
								totalSalesPerDay = totalSalesPerDay + drivableVehicles.get(a).GetSalesPrice();
								salesPeople.get(randomNum2).Bonus(drivableVehicles.get(a).GetVehicleType());
								budget_ = budget_ + drivableVehicles.get(a).GetSalesPrice();
								soldVehicles.add( drivableVehicles.get(a) );
								break;
							}
							else if(drivableVehicles.get(a).GetVehicleType() == "Pickup" && drivableVehicles.get(a).GetCondition()!="Broken") {
								
								Collections.sort(cars, Comparator.comparingDouble(Vehicle::GetSalesPrice).reversed());
								System.out.println( salesPeople.get(randomNum2).GetName() + " just sold " + drivableVehicles.get(a).GetName() + " for " + drivableVehicles.get(a).GetSalesPrice());
								pickups.remove(a); 
								totalSalesPerDay = totalSalesPerDay + drivableVehicles.get(a).GetSalesPrice();
								salesPeople.get(randomNum2).Bonus(drivableVehicles.get(a).GetVehicleType());
								budget_ = budget_ + drivableVehicles.get(a).GetSalesPrice();
								soldVehicles.add( drivableVehicles.get(a) );
								break;
							}	
							
						}				
					}
				}
			}
			else if(buyers.get(i).GetPreference() == "Car") {
				if(cars.size() >= 1) {
					//sell the one in the first spot, since that is the highest priced one.	
					//BUT FIRST CHECK IF WE CAN INCREASE SALESCHANCE 
					//WE COULD UPDATE SALESCHANCE IN THE SUB CLASS, BUT WE WOULD HAVE TO UPDATE EACH ITERATION IN REPAIRING AND CLEANING. OR WE CAN JUST CHECK HERE
					for(int a = 0; a<cars.size(); a++) {
						if(cars.get(a).GetCondition() != "Broken") {
						
							chance = buyers.get(i).GetChance();
							
							if(cars.get(a).GetCleanliness() == "sparkling") {
								//chance of sales up 10%.
								chance = chance + 10.0;
							}
							if(cars.get(a).GetCondition() == "Like New") {
								//chance of sales up 10%.
								chance = chance + 10.0;
							}
							//NOW SELL VEHICLE WITH UPDATED CHANCE. 
							var d = Math.random() * 100;
							if (d <= chance) {
								System.out.println( salesPeople.get(randomNum2).GetName() + " just sold " + cars.get(a).GetName() + " for " + cars.get(a).GetSalesPrice());
								totalSalesPerDay = totalSalesPerDay + cars.get(a).GetSalesPrice();
								salesPeople.get(randomNum2).Bonus(cars.get(a).GetVehicleType());
								budget_ = budget_ + cars.get(a).GetSalesPrice();
								soldVehicles.add( cars.get(a) );
								//Collections.sort(performanceCars, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
								cars.remove(a); 
							}
						}
						
					}
				}
				else {		
					//WE DON'T HAVE ANY DRIVABLE CARS, SO SELL ONE OF THE PERFORMANCE CARS OR THE PICKUP, WHICH EVER IS THE HIGHEST PRICE. 
					ArrayList<Vehicle> drivableVehicles = new ArrayList<Vehicle>();
					drivableVehicles.addAll(performanceCars);
					drivableVehicles.addAll(pickups);
					Collections.sort(drivableVehicles, Comparator.comparingDouble(Vehicle::GetSalesPrice).reversed());
					//NOW SELL FIRST ELEMENT OF DRIVABLE VEHICLES. 
					
					chance = buyers.get(i).GetChance();
					
					if(drivableVehicles.get(0).GetCleanliness() == "sparkling") {
						//chance of sales up 10%.
						chance = chance + 10.0;
					}
					if(drivableVehicles.get(0).GetCondition() == "Like New") {
						//chance of sales up 10%.
						chance = chance + 10.0;
					}
					
					chance = chance - 20.0; // - 20% because it's not the preferred type of car. 
					
					//NOW SELL VEHICLE, BUT DECREASES SALES CHANCE BY 20%.
					var d = Math.random() * 100;
					
					if (d <= chance - 20.0) {
						for(int a = 0; a<drivableVehicles.size(); a++) {
							if(drivableVehicles.get(a).GetVehicleType() == "Performance Car" && drivableVehicles.get(a).GetCondition()!="Broken") {
								
								Collections.sort(performanceCars, Comparator.comparingDouble(Vehicle::GetSalesPrice).reversed());
								System.out.println( salesPeople.get(randomNum2).GetName() + " just sold " + drivableVehicles.get(a).GetName() + " for " + drivableVehicles.get(a).GetSalesPrice());
								performanceCars.remove(a); 
								totalSalesPerDay = totalSalesPerDay + drivableVehicles.get(a).GetSalesPrice();
								salesPeople.get(randomNum2).Bonus(drivableVehicles.get(a).GetVehicleType());
								budget_ = budget_ + drivableVehicles.get(a).GetSalesPrice();
								soldVehicles.add( drivableVehicles.get(a) );
								break;
							}
							else if(drivableVehicles.get(a).GetVehicleType() == "Pickup" && drivableVehicles.get(a).GetCondition()!="Broken") {
								Collections.sort(cars, Comparator.comparingDouble(Vehicle::GetSalesPrice).reversed());
								System.out.println( salesPeople.get(randomNum2).GetName() + " just sold " + drivableVehicles.get(a).GetName() + " for " + drivableVehicles.get(a).GetSalesPrice());
								pickups.remove(a); 
								totalSalesPerDay = totalSalesPerDay + drivableVehicles.get(a).GetSalesPrice();
								salesPeople.get(randomNum2).Bonus(drivableVehicles.get(a).GetVehicleType());
								budget_ = budget_ + drivableVehicles.get(a).GetSalesPrice();
								soldVehicles.add( drivableVehicles.get(a) );
								break;
							}	
							
						}			
					}
				}
			}
			else if(buyers.get(i).GetPreference() == "Pickup") {
				
				if(pickups.size() >= 1) {
					//sell the one in the first spot, since that is the highest priced one.	
					//BUT FIRST CHECK IF WE CAN INCREASE SALESCHANCE 
					//WE COULD UPDATE SALESCHANCE IN THE SUB CLASS, BUT WE WOULD HAVE TO UPDATE EACH ITERATION IN REPAIRING AND CLEANING. OR WE CAN JUST CHECK HERE
					
					for(int a = 0; a<pickups.size(); a++) {
						if(pickups.get(a).GetCondition() != "Broken") {
						
							chance = buyers.get(i).GetChance();
							
							if(pickups.get(0).GetCleanliness() == "sparkling") {
								//chance of sales up 10%.
								chance = chance + 10.0;
							}
							if(pickups.get(0).GetCondition() == "Like New") {
								//chance of sales up 10%.
								chance = chance + 10.0;
							}
							//NOW SELL VEHICLE WITH UPDATED CHANCE. 
							var d = Math.random() * 100;
							if (d <= chance) {
								System.out.println( salesPeople.get(randomNum2).GetName() + " just sold " + pickups.get(a).GetName() + " for " + pickups.get(a).GetSalesPrice());
								totalSalesPerDay = totalSalesPerDay + pickups.get(0).GetSalesPrice();
								salesPeople.get(randomNum2).Bonus(pickups.get(0).GetVehicleType());
								budget_ = budget_ + pickups.get(0).GetSalesPrice();
								soldVehicles.add( pickups.get(0) );
								//Collections.sort(performanceCars, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
								pickups.remove(0); 
							}
						}
						
					}
				}
				else {		
					//WE DON'T HAVE ANY DRIVABLE CARS, SO SELL ONE OF THE PERFORMANCE CARS OR THE PICKUP, WHICH EVER IS THE HIGHEST PRICE. 
					ArrayList<Vehicle> drivableVehicles = new ArrayList<Vehicle>();
					drivableVehicles.addAll(performanceCars);
					drivableVehicles.addAll(cars);
					Collections.sort(drivableVehicles, Comparator.comparingDouble(Vehicle::GetSalesPrice).reversed());
					//NOW SELL FIRST ELEMENT OF DRIVABLE VEHICLES. 
					
					chance = buyers.get(i).GetChance();
					
					if(drivableVehicles.get(0).GetCleanliness() == "sparkling") {
						//chance of sales up 10%.
						chance = chance + 10.0;
					}
					if(drivableVehicles.get(0).GetCondition() == "Like New") {
						//chance of sales up 10%.
						chance = chance + 10.0;
					}
					
					chance = chance - 20.0; // - 20% because it's not the preferred type of car. 
					
					//NOW SELL VEHICLE, BUT DECREASES SALES CHANCE BY 20%.
					var d = Math.random() * 100;
					if (d <= chance - 20.0) {
						for(int a = 0; a<drivableVehicles.size(); a++) {
							if(drivableVehicles.get(a).GetVehicleType() == "Performance Car" && drivableVehicles.get(a).GetCondition()!="Broken") {
								Collections.sort(performanceCars, Comparator.comparingDouble(Vehicle::GetSalesPrice).reversed());
								System.out.println( salesPeople.get(randomNum2).GetName() + " just sold " + drivableVehicles.get(a).GetName() + " for " + drivableVehicles.get(a).GetSalesPrice());
								performanceCars.remove(a); 
								totalSalesPerDay = totalSalesPerDay + drivableVehicles.get(a).GetSalesPrice();
								salesPeople.get(randomNum2).Bonus(drivableVehicles.get(a).GetVehicleType());
								budget_ = budget_ + drivableVehicles.get(a).GetSalesPrice();
								soldVehicles.add( drivableVehicles.get(a) );
								break;
							}
							else if(drivableVehicles.get(a).GetVehicleType() == "Car" && drivableVehicles.get(a).GetCondition()!="Broken") {
								Collections.sort(cars, Comparator.comparingDouble(Vehicle::GetSalesPrice).reversed());
								System.out.println( salesPeople.get(randomNum2).GetName() + " just sold " + drivableVehicles.get(a).GetName() + " for " + drivableVehicles.get(a).GetSalesPrice());
								cars.remove(a); 
								totalSalesPerDay = totalSalesPerDay + drivableVehicles.get(a).GetSalesPrice();
								salesPeople.get(randomNum2).Bonus(drivableVehicles.get(a).GetVehicleType());
								budget_ = budget_ + drivableVehicles.get(a).GetSalesPrice();
								soldVehicles.add( drivableVehicles.get(a) );
								break;
							}	
							
						}
								
					}
				}
				
				
			}
		}
		
		performanceCars.addAll( brokenPerf );
		cars.addAll( brokenCar );
		pickups.addAll( brokenPickup );
		
		Ending();
		
	}
	
	void Ending() {
		
		//give all staff members their daily salary pay for today's work (out of the operating budget) 
		//It's possible at the end of every day one mechanic, one salesperson, and one intern may quit the FNCD. 
		//There is a 10% chance for each staff type that one member may quit.
		//If an intern quits, simply remove them from Staff. If a Mechanic quits or a Salesperson quits, remove them from the store staff
		//and add a new mechanic or salesperson to the staff (as needed) using an existing intern to provide the name for this new staff member. 
		//Remove the intern used here from the interns working at the FNCD. (The intern has become the new mechanic or salesperson)
		//When removing staff from the FNCD, store the leaving instance in a collection of departed staff.
		
		//produce a readable tabular report of:
		//staff members - with total days worked, total normal pay, total bonus pay, working or quit the FNCD
		//Inventory - List of all Vehicles with Name, Cost, Sale Price, Condition, Cleanliness, Sold or In Stock
		//Total $ in operating budget, total sales $ for the day.
		
		System.out.println("Ending...");
		
		
		for (int i = 0; i < salesPeople.size(); i++) {
			salesPeople.get(i).SetDailySalary();
			budget_ = budget_ - salesPeople.get(i).GetDailySalary();
		}
		for (int i = 0; i < mechanics.size(); i++) {
			mechanics.get(i).SetDailySalary();
			budget_ = budget_ - mechanics.get(i).GetDailySalary();
		}
		for (int i = 0; i < interns.size(); i++) {
			interns.get(i).SetDailySalary();
			budget_ = budget_ - interns.get(i).GetDailySalary();
		}
		
		
		System.out.println("============== QUIT: ==============");
		int acc = 0;
		var salespeopleChance = Math.random() * 100;
		if (salespeopleChance <= 10) {
			int randomNum = ThreadLocalRandom.current().nextInt(0, 3);
			System.out.println("\t" + salesPeople.get(randomNum).GetName());
			departedStaff.add(salesPeople.get(randomNum));
			salesPeople.remove(randomNum);
			salesPeople.add( new Salespeople (interns.get(0).GetName() ) );
			interns.remove(0);
		}
		else { acc++; }
		var mechanicChance = Math.random() * 100;
		if (mechanicChance <= 10) {
			int randomNum = ThreadLocalRandom.current().nextInt(0, 3);
			System.out.println("\t" + mechanics.get(randomNum).GetName());
			departedStaff.add(mechanics.get(randomNum));
			mechanics.remove(randomNum);
			mechanics.add( new Mechanics (interns.get(0).GetName() ) );
			interns.remove(0);
		}
		else { acc++; }
		var internChance = Math.random() * 100;
		if (internChance <= 10) {
			int randomNum = ThreadLocalRandom.current().nextInt(0, 3);
			System.out.println("\t" + interns.get(randomNum).GetName());
			departedStaff.add(interns.get(randomNum));
			interns.remove(randomNum);
		}
		else { acc++; }
		if(acc == 3) {
			System.out.println("NONE QUIT");
		}
		System.out.println("============== WORKING: ==============");
		System.out.println("===== SALESPEOPLE: =====");
		for (int i = 0; i < salesPeople.size(); i++) {
			System.out.println("\t" + salesPeople.get(i).GetName());
			System.out.println("\t \t Total Normal Pay: " + salesPeople.get(i).GetTotalSalary());
			System.out.println("\t \t Total Bonus Pay: " + salesPeople.get(i).GetTotalBonusPay());
		}
		System.out.println("===== MECHANICS: =====");
		for (int i = 0; i < mechanics.size(); i++) {
			System.out.println("\t" + mechanics.get(i).GetName());
			System.out.println("\t \t Total Normal Pay: " + mechanics.get(i).GetTotalSalary());
			System.out.println("\t \t Total Bonus Pay: " + mechanics.get(i).GetTotalBonusPay());
		}
		System.out.println("===== INTERNS: =====");
		for (int i = 0; i < interns.size(); i++) {
			System.out.println("\t" + interns.get(i).GetName());
			System.out.println("\t \t Total Normal Pay: " + interns.get(i).GetTotalSalary());
			System.out.println("\t \t Total Bonus Pay: " + interns.get(i).GetTotalBonusPay());
		}
		
		/* ============== REST OF THE REPORT ============ */
			//Inventory - List of all Vehicles with Name, Cost, Sale Price, Condition, Cleanliness, Sold or In Stock
			//Total $ in operating budget, total sales $ for the day.
		System.out.printf("%-40s%-20s%-20s%-20s%-20s%-20s\n", "Name",
				"Cost", "Sale Price", "Condition", "Cleanliness", "Sold or In Stock");
		for(int i = 0; i < performanceCars.size(); i++) {
			System.out.printf("%-40s%-20f%-20f%-20s%-20s%-20s\n", performanceCars.get(i).GetName(), 
					performanceCars.get(i).GetCost(), performanceCars.get(i).GetSalesPrice(),
					performanceCars.get(i).GetCondition(), performanceCars.get(i).GetCleanliness(), "In Stock");
		}
		for(int i = 0; i < cars.size(); i++) {
			System.out.printf("%-40s%-20f%-20f%-20s%-20s%-20s\n", cars.get(i).GetName(), 
					cars.get(i).GetCost(), cars.get(i).GetSalesPrice(),
					cars.get(i).GetCondition(), cars.get(i).GetCleanliness(), "In Stock");
		}
		for(int i = 0; i < pickups.size(); i++) {
			System.out.printf("%-40s%-20f%-20f%-20s%-20s%-20s\n", pickups.get(i).GetName(), 
					pickups.get(i).GetCost(), pickups.get(i).GetSalesPrice(),
					pickups.get(i).GetCondition(), pickups.get(i).GetCleanliness(), "In Stock");
		}
		for(int i = 0; i < soldVehicles.size(); i++) {
			System.out.printf("%-40s%-20f%-20f%-20s%-20s%-20s\n", soldVehicles.get(i).GetName(), 
					soldVehicles.get(i).GetCost(), soldVehicles.get(i).GetSalesPrice(),
					soldVehicles.get(i).GetCondition(), soldVehicles.get(i).GetCleanliness(), "Sold");
		}
		System.out.println("===== TOTAL MONEY IN BUDGET: " + budget_ + " =====");
		
		System.out.println("===== SALES FOR THE DAY: " + totalSalesPerDay + " ====="); 
		//have to create a variable that is set to 0 at the opening function, 
		//but when we make a sale we add the salesprice to this and it accumulates 
		//over the day for this print statement, then set back to 0 on opening function
	}

}	
