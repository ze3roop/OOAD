import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.chrono.IsoEra;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class FNCD {

	protected ArrayList<Salespeople> salesPeople = new ArrayList<Salespeople>();
	protected ArrayList<Mechanics> mechanics = new ArrayList<Mechanics>();
	protected ArrayList<Interns> interns = new ArrayList<Interns>();
	final protected Integer MIN_STAFF = 3;

	protected ArrayList<PerformanceCars> performanceCars = new ArrayList<PerformanceCars>();
	protected ArrayList<Cars> cars = new ArrayList<Cars>();
	protected ArrayList<Pickups> pickups = new ArrayList<Pickups>();
	final protected Integer MIN_VEHICLES = 4;

	protected Double budget_;
	protected ArrayList<Vehicles> soldVehicles = new ArrayList<Vehicles>();
	protected ArrayList<Vehicles> allSoldVehicles = new ArrayList<Vehicles>();
	protected ArrayList<Staff> departedStaff = new ArrayList<Staff>();
	
	protected int day;

	protected int daysToSimulate_;
	protected Double totalSalesPerDay;
	
	/*
	----------------------------------------------------------------------
	Condtructor
	----------------------------------------------------------------------
	 */
	public FNCD(int daysToSimulate)
	{
		budget_ = 500000.0; //INITIAL OPERATING BUDGET 
		totalSalesPerDay = 0.0;
		daysToSimulate_ = daysToSimulate;
		day = 0;
		
		// //initialize staff and vehicles
		// for (int i = 0; i < MIN_STAFF; i++) {
		// 	interns.add( new Interns() );
		// 	mechanics.add( new Mechanics() );
		// 	salesPeople.add( new Salespeople() );
		// }

		// for (int i = 0; i < MIN_VEHICLES; i++) {
		// 	performanceCars.add(new PerformanceCars() );
		// 	cars.add( new Cars() );
		// 	pickups.add(new Pickups() );
		// }
	}

	private Helper.Names_of_Day GetDay() {return Helper.Week[day % 6];} // function to get the day of the week

	private Boolean isOpen() {return GetDay() != Helper.Names_of_Day.Sunday;} // open every day but sunday

	private Boolean isBusy() { return (GetDay() == Helper.Names_of_Day.Friday) || (GetDay() == Helper.Names_of_Day.Saturday);}

	private void HireStaff() {
		int numInterns = interns.size();
		int numMechanics = mechanics.size();
		int numSalesPeople = salesPeople.size();

		if(numInterns < MIN_STAFF) {
			for (int i = 0; numInterns < MIN_STAFF; i++) {
				interns.add( new Interns() );
				System.out.println("\tHired " + interns.get(i).GetName());
				numInterns ++;
			}
		}

		if(numMechanics < MIN_STAFF) {
			for (int i = 0; numMechanics < MIN_STAFF; i++) {
				mechanics.add( new Mechanics() );
				System.out.println("\tHired " + mechanics.get(i).GetName());
				numMechanics ++;
			}
		}

		if(numSalesPeople < MIN_STAFF) {
			for (int i = 0; numSalesPeople < MIN_STAFF; i++) {
				salesPeople.add( new Salespeople() );
				System.out.println("\tHired " + salesPeople.get(i).GetName());
				numSalesPeople ++;
			}
		}
	}

	private void BuyVehicles() {
		
		int numCars = cars.size();
		int numPerformanceCars = performanceCars.size();
		int numPickups = pickups.size();

		if(performanceCars.size() < MIN_VEHICLES) {
			for (int i = 0; numPerformanceCars < MIN_VEHICLES; i++) {
				performanceCars.add( new PerformanceCars() );
				//take money from operating budget. 
				budget_ = budget_ - performanceCars.get(i).GetCost(); 
				System.out.printf("\tPurchased " + performanceCars.get(i).GetName() + ". For: $%.2f\n",performanceCars.get(i).GetCost());
				numPerformanceCars ++;
			}
		}

		if(numCars < MIN_VEHICLES) {
			for (int i = 0; numCars < MIN_VEHICLES; i++) {
				cars.add( new Cars() );
				//take money from operating budget. 
				//SetBudget( performanceCars just instantiated.getSalesPrice() ); 
				budget_ = budget_ - cars.get(i).GetCost(); 
				System.out.printf("\tPurchased " + cars.get(i).GetName() + ". For: $%.2f\n",cars.get(i).GetCost());
				numCars ++;
			}
		}
		if(numPickups < MIN_VEHICLES) {
			for (int i = 0; numPickups < MIN_VEHICLES; i++) {
				pickups.add( new Pickups() );
				//take money from operating budget. 
				//SetBudget( pickup just instantiated.getSalesPrice() ); 
				budget_ = budget_ - pickups.get(i).GetCost(); 
				System.out.printf("\tPurchased " + pickups.get(i).GetName() + ". For: $%.2f\n",pickups.get(i).GetCost());
				numPickups ++;
			}
		}

		System.out.println("\t -------------- Inventory: --------------");
		
		System.out.printf("\t%-40s%-20s%-20s%-20s%-20s%-20s\n", "Name",
				"Cost", "Sale Price", "Condition", "Cleanliness", "Sold or In Stock");
		for(int i = 0; i < performanceCars.size(); i++) {
			System.out.printf("\t" + performanceCars.get(i).getInfo_asString() + "%-20s\n","In Stock");
		}
		for(int i = 0; i < cars.size(); i++) {
			System.out.printf("\t" + cars.get(i).getInfo_asString() + "%-20s\n","In Stock");
		}
		for(int i = 0; i < pickups.size(); i++) {
			System.out.printf("\t" + pickups.get(i).getInfo_asString() + "%-20s\n","In Stock");
		}
	}
	
	public void Start() {
		try {
			// Redirect System.out to a file
			FileOutputStream fos = new FileOutputStream("output.txt");
			PrintStream ps = new PrintStream(fos);
			System.setOut(ps);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i <= daysToSimulate_; i++) {
			Opening();
			totalSalesPerDay = 0.0;
		}
		System.out.println("=====================================================");
		System.out.println("================= End of Simulation =================");
		System.out.println("=====================================================");

		try {
			// Reset System.out to print to terminal
			System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Exiting program");
	}
	
	public void Opening() {
		System.out.println("\n\n******************************************************************************\n");
		System.out.println("FNCD DAY " + day);

		if(isOpen()) {
			System.out.println("Opening...");

			HireStaff();
			BuyVehicles();

			Washing(); 

		} else { System.out.println("SUNDAY - CLOSED"); }

		day++;
	}
	
	public void Washing() {
		System.out.println("Washing...");
		ArrayList<Vehicles> dirtyVehicles = new ArrayList<Vehicles>(); // local variable to store all the dirty vehicles
		ArrayList<Vehicles> cleanVehicles = new ArrayList<Vehicles>(); // local variable to store all the clean vehicles
		ArrayList<Vehicles> sparklingVehicles = new ArrayList<Vehicles>(); // local variable to store all the sparkling vehicles


		/*
		Every working day the interns will wash vehicles. 
		run a for loop through each type of car. 
		sort each vehicle into their appropriate list
		 */

		for(int i = 0; i < MIN_VEHICLES; i++) {
			Cars current_car = cars.get(i);
			PerformanceCars current_PerformanceCar = performanceCars.get(i);
			Pickups current_Pickup = pickups.get(i);

			if (current_PerformanceCar.isDirty()) {dirtyVehicles.add(current_PerformanceCar);}
			else if (current_PerformanceCar.isClean()) {cleanVehicles.add(current_PerformanceCar);}
			else if (current_PerformanceCar.isSparkling()) {sparklingVehicles.add(current_PerformanceCar);}

			if (current_Pickup.isDirty()) {dirtyVehicles.add(current_Pickup);}
			else if (current_Pickup.isClean()) {cleanVehicles.add(current_Pickup);}
			else if (current_Pickup.isSparkling()) {sparklingVehicles.add(current_Pickup);}

			if (current_car.isDirty()) {dirtyVehicles.add(current_car);}
			else if (current_car.isClean()) {cleanVehicles.add(current_car);}
			else if (current_car.isSparkling()) {sparklingVehicles.add(current_car);}

		}

		performanceCars.clear();
		cars.clear();
		pickups.clear();

		
		for (int i = 0; i < MIN_STAFF; i++) {
			String internName = interns.get(i).GetName();

			while(interns.get(i).doJob()) { //Loop through every intern one at a time, if the can do their job they will, oterwise skip them

				if (!dirtyVehicles.isEmpty()) { // first check to see if there are dirty vehicles to wash
					
					Vehicles working_vehicle = dirtyVehicles.get(0); // remove vehicle from list and store into local temp variable
					dirtyVehicles.remove((0));
					
					if (Helper.PercentChance(10)){ // 10 percent chance to make dirty vehicle sparkling
						
						System.out.println("\t" + internName + " washed " + working_vehicle.GetName() + " and made it sparkling" );
						interns.get(i).Bonus(working_vehicle);      //IF MAKES SPARKLING, INTERN GETS A BONUS BY TYPE OF VEHICLE. 
						
						working_vehicle.makeSparkling();

					} else if (Helper.PercentChance(80)){ // 80 percent chance to make dirty vehicle clean
						
						System.out.println("\t" + internName + " washed " + working_vehicle.GetName() + " and made it clean" );
						
						working_vehicle.makeClean();
					} else { System.out.println("\t" + internName + " did not wash " + working_vehicle.GetName() + " and did not do his job" ); }

					if (working_vehicle.isClean()) {cleanVehicles.add(working_vehicle);}
					else if (working_vehicle.isDirty()) {dirtyVehicles.add(working_vehicle);}
					else if (working_vehicle.isSparkling()) {sparklingVehicles.add(working_vehicle);}

				} else if (!cleanVehicles.isEmpty()) { // then check to see if there are any clean nehicles to wash
					
					Vehicles working_vehicle = cleanVehicles.get(0);
					cleanVehicles.remove(0);

					if(Helper.PercentChance(5)) { // 5 percent chance to make clean vehicle dirty

						System.out.println("\t" + internName + " washed " + working_vehicle.GetName() + " and made it dirty." );
						working_vehicle.makeDirty();

					} else if (Helper.PercentChance(30)) { // 30 percent chance to make clean vehicle sparkling

						System.out.println("\t" + internName + " washed " + working_vehicle.GetName() + " and made it sparkling." );
						interns.get(i).Bonus(working_vehicle);      //IF MAKES SPARKLING, INTERN GETS A BONUS BY TYPE OF VEHICLE.
					} else {System.out.println("\t" + internName + " did not wash " + working_vehicle.GetName() + " and did not do his job" );}

					if (working_vehicle.isClean()) {cleanVehicles.add(working_vehicle);}
					else if (working_vehicle.isDirty()) {dirtyVehicles.add(working_vehicle);}
					else if (working_vehicle.isSparkling()) {sparklingVehicles.add(working_vehicle);}

				} else {System.out.println("\t" + internName + " tried to wash a car but there are none" );}
			}
		}

		// return cars to inventory lists

		for (int i = 0 ; i < cleanVehicles.size() ; i++){
			Vehicles current_Vehicle = cleanVehicles.get(i);
			if (current_Vehicle.isCar()) {cars.add((Cars)current_Vehicle);}
			else if (current_Vehicle.isPerformanceCar()) {performanceCars.add((PerformanceCars) current_Vehicle);}
			else if (current_Vehicle.isPickup()) {pickups.add((Pickups)current_Vehicle);}
		}
		for (int i = 0; i < dirtyVehicles.size(); i++){
			Vehicles current_Vehicle = dirtyVehicles.get(i);
			if (current_Vehicle.isCar()) {cars.add((Cars)current_Vehicle);}
			else if (current_Vehicle.isPerformanceCar()) {performanceCars.add((PerformanceCars) current_Vehicle);}
			else if (current_Vehicle.isPickup()) {pickups.add((Pickups)current_Vehicle);}

		}
		for(int i = 0; i < sparklingVehicles.size(); i++){
			Vehicles current_Vehicle = sparklingVehicles.get(i);
			if (current_Vehicle.isCar()) {cars.add((Cars)current_Vehicle);}
			else if (current_Vehicle.isPerformanceCar()) {performanceCars.add((PerformanceCars) current_Vehicle);}
			else if (current_Vehicle.isPickup()) {pickups.add((Pickups)current_Vehicle);}

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
		

		System.out.println("Reparing...");
		ArrayList<Vehicles> brokenVehicles = new ArrayList<Vehicles>(); // local variable to store all the dirty vehicles
		ArrayList<Vehicles> usedVehicles = new ArrayList<Vehicles>(); // local variable to store all the clean vehicles
		ArrayList<Vehicles> likeNewVehicles = new ArrayList<Vehicles>(); // local variable to store all the sparkling vehicles


		/*
		Every working day the interns will wash vehicles. 
		run a for loop through each type of car. 
		sort each vehicle into their appropriate list
		 */

		for(int i = 0; i < MIN_VEHICLES; i++) {
			Cars current_car = cars.get(i);
			PerformanceCars current_PerformanceCar = performanceCars.get(i);
			Pickups current_Pickup = pickups.get(i);

			if (current_PerformanceCar.isBroken()) {brokenVehicles.add(current_PerformanceCar);}
			else if (current_PerformanceCar.isUsed()) {usedVehicles.add(current_PerformanceCar);}
			else if (current_PerformanceCar.isLikeNew()) {likeNewVehicles.add(current_PerformanceCar);}

			if (current_Pickup.isBroken()) {brokenVehicles.add(current_Pickup);}
			else if (current_Pickup.isUsed()) {usedVehicles.add(current_Pickup);}
			else if (current_Pickup.isLikeNew()) {likeNewVehicles.add(current_Pickup);}

			if (current_car.isBroken()) {brokenVehicles.add(current_car);}
			else if (current_car.isLikeNew()) {likeNewVehicles.add(current_car);}
			else if (current_car.isUsed()) {usedVehicles.add(current_car);}

		}

		performanceCars.clear();
		cars.clear();
		pickups.clear();

		
		for (int i = 0; i < MIN_STAFF; i++) {
			
			String MechanicName = mechanics.get(i).GetName();

			while(mechanics.get(i).doJob()) { //Loop through every intern one at a time, if the can do their job they will, oterwise skip them
				if (Helper.PercentChance(80)){
					if (!brokenVehicles.isEmpty()){

						usedVehicles.add(brokenVehicles.get((0)));
						System.out.println("\t" + MechanicName + " just repaired " + brokenVehicles.get(0).GetName() + " and made it Used");
						mechanics.get(i).Bonus(brokenVehicles.get(0));
						brokenVehicles.get(0).ReduceCleanliness();
						brokenVehicles.get(0).SetSalesPrice(.5);
						brokenVehicles.remove(0);

					} else if (!usedVehicles.isEmpty()){

						likeNewVehicles.add(usedVehicles.get((0)));
						System.out.println("\t" + MechanicName + " just repaired " + usedVehicles.get(0).GetName() + " and made it LikeNew");
						mechanics.get(i).Bonus(usedVehicles.get(0));
						usedVehicles.get(0).ReduceCleanliness();
						usedVehicles.get(0).SetSalesPrice(.5);
						usedVehicles.remove(0);

					} else {System.out.println("\t" + MechanicName + " tried to repair a vehicle but there are none");}
					
				}
			}
		}

		// return cars to inventory lists

		for (int i = 0 ; i < brokenVehicles.size() ; i++){
			Vehicles current_Vehicle = brokenVehicles.get(i);
			if (current_Vehicle.isCar()) {cars.add((Cars)current_Vehicle);}
			else if (current_Vehicle.isPerformanceCar()) {performanceCars.add((PerformanceCars) current_Vehicle);}
			else if (current_Vehicle.isPickup()) {pickups.add((Pickups)current_Vehicle);}
		}
		for (int i = 0; i < usedVehicles.size(); i++){
			Vehicles current_Vehicle = usedVehicles.get(i);
			if (current_Vehicle.isCar()) {cars.add((Cars)current_Vehicle);}
			else if (current_Vehicle.isPerformanceCar()) {performanceCars.add((PerformanceCars) current_Vehicle);}
			else if (current_Vehicle.isPickup()) {pickups.add((Pickups)current_Vehicle);}

		}
		for(int i = 0; i < likeNewVehicles.size(); i++){
			Vehicles current_Vehicle = likeNewVehicles.get(i);
			if (current_Vehicle.isCar()) {cars.add((Cars)current_Vehicle);}
			else if (current_Vehicle.isPerformanceCar()) {performanceCars.add((PerformanceCars) current_Vehicle);}
			else if (current_Vehicle.isPickup()) {pickups.add((Pickups)current_Vehicle);}

		}
		
		Selling();
	}
	
	public void Selling() {
		 
		System.out.println("Selling...");
		
		ArrayList<Buyer> buyers = new ArrayList<Buyer>();
		
		/*==== INSTANTIATE THE BUYERS DEPENDING ON THE DAY OF THE WEEK ===*/
		if(!isBusy()) {
			int randomNum = Helper.RandInt(0, 5);
			
			for (int i = 0; i<=randomNum; i++)
			{
				buyers.add(new Buyer());
			}
			
		} else {
			int randomNum = Helper.RandInt(2, 8);
			
			for (int i = 0; i<=randomNum; i++)
			{
				buyers.add(new Buyer());
			}
		}
		
		/*=== INSTANTIATE ARRAY LISTS, OF SELLABLE VEHICLES ===*/
		ArrayList<PerformanceCars> SellablePerformanceCars = new ArrayList<PerformanceCars>();
		ArrayList<Cars> SellableperCars = new ArrayList<Cars>();
		ArrayList<Pickups> SellablePickups = new ArrayList<Pickups>();
		
		for ( int i = 0; i < MIN_VEHICLES; i++ ) {
			PerformanceCars current_performanceCar = performanceCars.get(i);
			Cars current_Car = cars.get(i);
			Pickups current_Pickup = pickups.get(i);

			// create lists of vehicles that contain no broken cars (sellable cars)
			if(!current_performanceCar.isBroken())
			{
				SellablePerformanceCars.add(current_performanceCar);
			}

			if(!current_Car.isBroken())
			{
				SellableperCars.add(current_Car);
			}

			if(!current_Pickup.isBroken())
			{
				SellablePickups.add(current_Pickup);
			}

		}

		// check to see if there are any vehicles to sell
		if (SellablePerformanceCars.isEmpty() && SellablePickups.isEmpty() && SellableperCars.isEmpty()){
			System.out.println("\tNo vehicles to sell...");
			return;
		}

		// sort the lists of sellable cars by price (decreasing)
		Collections.sort(SellablePerformanceCars, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
		Collections.sort(SellableperCars, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
		Collections.sort(SellablePickups, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());

		int numBuyers = buyers.size();

		for (int i = 0; i < numBuyers; i++){

			// check to see if there are any vehicles to sell
			if (SellablePerformanceCars.isEmpty() && SellablePickups.isEmpty() && SellableperCars.isEmpty()){
				System.out.println("\tNo vehicles to sell...");
				return;
			}

			Buyer currentBuyer = buyers.get(i);
			int sellerID = Helper.RandInt(0, MIN_STAFF - 1); // local id for salespeople array
			int chance = buyers.get(i).GetChance(); // varying chance due to car sold, condtion, cleanliness, and matching buyer preferances
			Vehicles vehicle_to_sell = new Vehicles(); // local temp variable to referance what car is being sold

			// local vehicles to store which cehicle we will sell
			PerformanceCars bestPerformanceCar = new PerformanceCars();
			Cars bestCar = new Cars();
			Pickups bestPickup = new Pickups();

			// update local best variables with vehicle if one exsists
			if (!SellablePerformanceCars.isEmpty()) {bestPerformanceCar = SellablePerformanceCars.get(0);}
			if (!SellableperCars.isEmpty()) { bestCar = SellableperCars.get(0);}
			if (!SellablePickups.isEmpty()) { bestPickup = SellablePickups.get(0);}
			
			

			// determine what vehicile to sell and if the chance of success decreases due to missmatch
			if (currentBuyer.WantsCar()){
				if (bestCar.isVehicle()) {
					vehicle_to_sell = bestCar;
				}
				else if (bestPerformanceCar.isVehicle()){
					if (bestPickup.isVehicle()){
						if (bestPerformanceCar.GetSalesPrice() >= bestPickup.GetSalesPrice()){
							vehicle_to_sell = bestPerformanceCar;
						}
						else {
							vehicle_to_sell = bestPickup;
						}
					}
					else {
						vehicle_to_sell = bestPerformanceCar;
					}
				}
				else {
					vehicle_to_sell = bestPickup;
				}
			}
			else if (currentBuyer.WantsPerformanceCar()){
				if (bestPerformanceCar.isVehicle()) {
					vehicle_to_sell = bestPerformanceCar;
				}
				else if (bestCar.isVehicle()){
					if (bestPickup.isVehicle()){
						if (bestCar.GetSalesPrice() >= bestPickup.GetSalesPrice()){
							vehicle_to_sell = bestCar;
						}
						else {
							vehicle_to_sell = bestPickup;
						}
					}
					else {
						vehicle_to_sell = bestCar;
					}
				}
				else {
					vehicle_to_sell = bestPickup;
				}
			}
			else if (currentBuyer.WantsPickup()){
				if (bestPickup.isVehicle()) {
					vehicle_to_sell = bestPickup;
				}
				else if (bestCar.isVehicle()){
					if (bestPerformanceCar.isVehicle()){
						if (bestCar.GetSalesPrice() >= bestPerformanceCar.GetSalesPrice()){
							vehicle_to_sell = bestCar;
						}
						else {
							vehicle_to_sell = bestPerformanceCar;
						}
					}
					else {
						vehicle_to_sell = bestCar;
					}
				}
				else {
					vehicle_to_sell = bestPerformanceCar;
				}
			}

			// apply bonusus depending on isSparkling and isLikeNew and decrement if type missmatch
			if (vehicle_to_sell.isSparkling()) {chance += 10;}
			if (vehicle_to_sell.isLikeNew()) {chance += 10;}
			if (vehicle_to_sell.isCar() && currentBuyer.GetPreference() != Vehicles.Types_of_Vehicles.Car){chance -= 20;}
			else if (vehicle_to_sell.isPerformanceCar() && currentBuyer.GetPreference() != Vehicles.Types_of_Vehicles.performanceCar){chance -= 20;}
			else if (vehicle_to_sell.isPickup() && currentBuyer.GetPreference() != Vehicles.Types_of_Vehicles.pickup){chance -= 20;}


			if (Helper.PercentChance(chance)) { // enters if we were able to sell the vehicle based on percent chance

				// remove the vehicle from the appropriate possible to sell list
				if (vehicle_to_sell.isCar()){
					SellableperCars.remove(vehicle_to_sell);
				} else if (vehicle_to_sell.isPickup()){
					SellablePickups.remove(vehicle_to_sell);
				} else if (vehicle_to_sell.isPerformanceCar()){
					SellablePerformanceCars.remove(vehicle_to_sell);
				}

				double soldPrice = vehicle_to_sell.GetSalesPrice(); // local variable to referance what we sold the car for

				budget_ += soldPrice; // add money to the budget for the price of the car
				
				// display message in system
				System.out.printf( "\t" + salesPeople.get(sellerID).GetName() + " just sold " + vehicle_to_sell.GetName() + " for $%.2f\n", soldPrice);

				// increase total sales for the day
				totalSalesPerDay = totalSalesPerDay + soldPrice;

				// give bonus to salesperson
				salesPeople.get(sellerID).Bonus(vehicle_to_sell);

				// add vehicle to sold vehicles list
				soldVehicles.add( vehicle_to_sell );

				// remove vehicile from inventory

				if (vehicle_to_sell.isCar()){
					cars.remove(vehicle_to_sell);
				} else if (vehicle_to_sell.isPerformanceCar()){
					performanceCars.remove(vehicle_to_sell);
				} else if (vehicle_to_sell.isPickup()){
					pickups.remove(vehicle_to_sell);
				}
			}
		}
		
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
		
		System.out.println("\n============== End of day report ==============");
		System.out.println("\t-------------- QUIT: --------------");
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
			System.out.println("\t NONE QUIT");
		}
		System.out.println("\t-------------- WORKING: --------------");
		System.out.println("\t SALESPEOPLE:");
		for (int i = 0; i < salesPeople.size(); i++) {
			System.out.println("\t \t" + salesPeople.get(i).GetName());
			System.out.println("\t \t \t Total Normal Pay: " + salesPeople.get(i).GetTotalSalary());
			System.out.println("\t \t \t Total Bonus Pay: " + salesPeople.get(i).GetTotalBonusPay());
		}
		System.out.println("\t  MECHANICS:");
		for (int i = 0; i < mechanics.size(); i++) {
			System.out.println("\t \t" + mechanics.get(i).GetName());
			System.out.println("\t \t \t Total Normal Pay: " + mechanics.get(i).GetTotalSalary());
			System.out.println("\t \t \t Total Bonus Pay: " + mechanics.get(i).GetTotalBonusPay());
		}
		System.out.println("\t INTERNS:");
		for (int i = 0; i < interns.size(); i++) {
			System.out.println("\t \t" + interns.get(i).GetName());
			System.out.println("\t \t \t Total Normal Pay: " + interns.get(i).GetTotalSalary());
			System.out.println("\t \t \t Total Bonus Pay: " + interns.get(i).GetTotalBonusPay());
		}
		
		/* ============== REST OF THE REPORT ============ */
			//Inventory - List of all Vehicles with Name, Cost, Sale Price, Condition, Cleanliness, Sold or In Stock
			//Total $ in operating budget, total sales $ for the day.
		
			System.out.println("\t -------------- Inventory: --------------");
		
		System.out.printf("\t%-40s%-20s%-20s%-20s%-20s%-20s\n", "Name",
				"Cost", "Sale Price", "Condition", "Cleanliness", "Sold or In Stock");
		for(int i = 0; i < performanceCars.size(); i++) {
			System.out.printf("\t" + performanceCars.get(i).getInfo_asString() + "%-20s\n","In Stock");
		}
		for(int i = 0; i < cars.size(); i++) {
			System.out.printf("\t" + cars.get(i).getInfo_asString() + "%-20s\n","In Stock");
		}
		for(int i = 0; i < pickups.size(); i++) {
			System.out.printf("\t" + pickups.get(i).getInfo_asString() + "%-20s\n","In Stock");
		}

		System.out.println("\t -------------- Sold Vehicles: --------------");

		for(int i = 0; i < soldVehicles.size(); i++) {
			System.out.printf("\t" + soldVehicles.get(i).getInfo_asString() + "%-20s\n","Sold");
		}

		System.out.printf("\n \t ---- TOTAL MONEY IN BUDGET: %.2f ----\n",budget_);
		
		System.out.printf("\t ---- SALES FOR THE DAY: %.2f ----\n",totalSalesPerDay); 
		//have to create a variable that is set to 0 at the opening function, 
		//but when we make a sale we add the salesprice to this and it accumulates 
		//over the day for this print statement, then set back to 0 on opening function
		System.out.println( "\n\t ---- ALL STAFF THAT HAS LEFT: ----");
		for(int i = 0; i < departedStaff.size(); i++) {
			System.out.println("\t" + departedStaff.get(i).GetName());
		}
	}

}	
