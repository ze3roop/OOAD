import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FNCD {

	protected ArrayList<SalesPerson> salesPeople = new ArrayList<SalesPerson>();
	protected ArrayList<Mechanic> mechanics = new ArrayList<Mechanic>();
	protected ArrayList<Intern> interns = new ArrayList<Intern>();
	final protected Integer MIN_STAFF = 3;

	protected ArrayList<Vehicle> inventory = new ArrayList<Vehicle>();
	final protected Integer MIN_VEHICLES = 4;

	protected Double budget_;
	protected ArrayList<Vehicle> soldVehicles = new ArrayList<Vehicle>();
	protected ArrayList<Vehicle> allSoldVehicles = new ArrayList<Vehicle>();
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
				interns.add( new Intern() );
				System.out.println("\tHired " + interns.get(i).GetName());
				numInterns ++;
			}
		}

		if(numMechanics < MIN_STAFF) {
			for (int i = 0; numMechanics < MIN_STAFF; i++) {
				mechanics.add( new Mechanic() );
				System.out.println("\tHired " + mechanics.get(i).GetName());
				numMechanics ++;
			}
		}

		if(numSalesPeople < MIN_STAFF) {
			for (int i = 0; numSalesPeople < MIN_STAFF; i++) {
				salesPeople.add( new SalesPerson() );
				System.out.println("\tHired " + salesPeople.get(i).GetName());
				numSalesPeople ++;
			}
		}
	}

	private void BuyVehicles() {
		
		int numCars = 0;
		int numPerformanceCars = 0;
		int numPickups = 0;

		for (int i = 0; i < inventory.size(); i++){
			Vehicle current_Vehicle = inventory.get(i);
			if (current_Vehicle.isCar()) {numCars ++;}
			else if (current_Vehicle.isPerformanceCar()) {numPerformanceCars ++;}
			else if (current_Vehicle.isPickup()) {numPickups ++;}
		}

		if(numPerformanceCars < MIN_VEHICLES) {
			int vehicile_to_buy = MIN_VEHICLES - numPerformanceCars;
			for (int i = 0; i <  vehicile_to_buy; i++) {
				PerformanceCar boughtPerformanceCar = new PerformanceCar();

				inventory.add( boughtPerformanceCar);
				//take money from operating budget. 
				budget_ = budget_ - boughtPerformanceCar.GetCost(); 
				System.out.printf("\tPurchased " + boughtPerformanceCar.GetName() + ". For: $%.2f\n",boughtPerformanceCar.GetCost());
				numPerformanceCars ++;
			}
		}

		if(numCars < MIN_VEHICLES) {
			int vehicile_to_buy = MIN_VEHICLES - numCars;
			for (int i = 0; i < vehicile_to_buy; i++) {
				Car boughtCar = new Car();

				inventory.add( boughtCar );
				//take money from operating budget. 
				budget_ = budget_ - boughtCar.GetCost(); 
				System.out.printf("\tPurchased " + boughtCar.GetName() + ". For: $%.2f\n",boughtCar.GetCost());
				numCars ++;
			}
		}
		if(numPickups < MIN_VEHICLES) {
			int vehicile_to_buy = MIN_VEHICLES - numPickups;
			for (int i = 0; i < vehicile_to_buy; i++) {
				Pickup boughtPickup = new Pickup();
				inventory.add( boughtPickup );
				//take money from operating budget. 
				budget_ = budget_ - boughtPickup.GetCost(); 
				System.out.printf("\tPurchased " + boughtPickup.GetName() + ". For: $%.2f\n",boughtPickup.GetCost());
				numPickups ++;
			}
		}
	}

	public void PrintInventory(String tittle) {
		if (tittle == null) {tittle = "";}
		System.out.println("\t -------------- " + tittle + " Inventory: --------------");
		
		System.out.printf("\t%-40s%-20s%-20s%-20s%-20s%-20s\n", "Name",
				"Cost", "Sale Price", "Condition", "Cleanliness", "Sold or In Stock");
		for(int i = 0; i < inventory.size(); i++) {
			System.out.printf("\t" + inventory.get(i).getInfo_asString() + "%-20s\n","In Stock");
		}

		System.out.println("\t-------------------------------------------------------------");
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

		// set the employess jobs completed to 0
		for (int i = 0; i < interns.size(); i++){
			interns.get(i).ResetJobsDOne();
		}
		for (int i = 0; i < mechanics.size(); i++){
			mechanics.get(i).ResetJobsDOne();
		}
		for (int i = 0; i < salesPeople.size(); i++){
			salesPeople.get(i).ResetJobsDOne();
		}

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

		// PrintInventory("Washing");

		/*
		Every working day the interns will wash vehicles. 
		run a for loop through each type of car. 
		sort each vehicle into their appropriate list
		 */

		
		for (int i = 0; i < MIN_STAFF; i++) {
			String internName = interns.get(i).GetName();

			while(interns.get(i).doJob()) { //Loop through every intern one at a time, if the can do their job they will, oterwise skip them
				boolean washedVehicile = false;
				for(int j = 0; j < inventory.size(); j++){
					if (!washedVehicile){
						if (inventory.get(j).isDirty()){
							washedVehicile = true;
							if (Helper.PercentChance(10)){ // 10 percent chance to make dirty vehicle sparkling
							
								System.out.println("\t" + internName + " washed " + inventory.get(j).GetName() + " and made it sparkling" );
								interns.get(i).Bonus(inventory.get(j));      //IF MAKES SPARKLING, INTERN GETS A BONUS BY TYPE OF VEHICLE. 
								
								inventory.get(j).makeSparkling();
		
							} else if (Helper.PercentChance(80)){ // 80 percent chance to make dirty vehicle clean
								
								System.out.println("\t" + internName + " washed " + inventory.get(j).GetName() + " and made it clean" );
								
								inventory.get(j).makeClean();
							} else { System.out.println("\t" + internName + " did not wash " + inventory.get(j).GetName() + " and did not do his job" ); }
						}
					}
				}

				if (!washedVehicile){
					for(int j = 0; j < inventory.size(); j++){
						if (!washedVehicile){
							if (inventory.get(j).isClean()){
								washedVehicile = true;
								if (Helper.PercentChance(30)){ // 30 percent chance to make clean vehicle sparkling
								
									System.out.println("\t" + internName + " washed " + inventory.get(j).GetName() + " and made it sparkling" );
									interns.get(i).Bonus(inventory.get(j));      //IF MAKES SPARKLING, INTERN GETS A BONUS BY TYPE OF VEHICLE. 
									
									inventory.get(j).makeSparkling();
			
								} else if (Helper.PercentChance(5)){ // 80 percent chance to make clean vehicle dirty
									
									System.out.println("\t" + internName + " washed " + inventory.get(j).GetName() + " and made it dirty" );
									
									inventory.get(j).makeDirty();
								} else { System.out.println("\t" + internName + " did not wash " + inventory.get(j).GetName() + " and did not do his job" ); }
							}
						}
					}
				}

				if (!washedVehicile) { System.out.println("\t" + internName + " tried to wash a car but there are none"); }
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

		System.out.println("Reparing...");
		//PrintInventory("Reparing");

		/*
		Every working day the interns will wash vehicles. 
		run a for loop through each type of car. 
		sort each vehicle into their appropriate list
		 */

		
		for (int i = 0; i < MIN_STAFF; i++) {
			
			String MechanicName = mechanics.get(i).GetName();

			for (int k = 0; k < 2; k++){
				boolean fixedVehicle = false;

				if (Helper.PercentChance(100)){ // make 80 percent
					for (int j = 0; j < inventory.size(); j++){
						if (!fixedVehicle){
							if (inventory.get(j).isBroken()){
								inventory.get(j).makeUsed();
								inventory.get(j).ReduceCleanliness();
	
								fixedVehicle = true;
								System.out.println("\t" + MechanicName + " just repaired " + inventory.get(j).GetName() + " and made it Used");
							}
						}
					}
					if (!fixedVehicle){
						for (int j = 0; j < inventory.size(); j++){
							if (!fixedVehicle){
								if (inventory.get(j).isUsed()){
									inventory.get(j).makeLikeNew();
									inventory.get(j).ReduceCleanliness();
									mechanics.get(i).Bonus(inventory.get(j));
		
									fixedVehicle = true;
									System.out.println("\t" + MechanicName + " just repaired " + inventory.get(j).GetName() + " and made it Like New");
								}
							}
						}

						if (!fixedVehicle) {System.out.println("\t" + MechanicName + " has no cars to repair");}
					}
				} else {System.out.println("\t" + MechanicName + " was unable to repair any cars");}
			}

		}

		Selling();
	}
	
	public void Selling() {
		 
		System.out.println("Selling...");
		//PrintInventory("Selling");
		
		ArrayList<Buyer> buyers = new ArrayList<Buyer>();
		
		/*==== INSTANTIATE THE BUYERS DEPENDING ON THE DAY OF THE WEEK ===*/
		if(!isBusy()) {
			int randomNum = Helper.RandInt(0, 5);
			
			for (int i = 0; i < randomNum; i++)
			{
				buyers.add(new Buyer());
			}
			
		} else {
			int randomNum = Helper.RandInt(2, 8);
			
			for (int i = 0; i < randomNum; i++)
			{
				buyers.add(new Buyer());
			}
		}

		System.out.println("\tnumber of customers: " + buyers.size());

		// sort the lists of sellable cars by price (decreasing)
		Collections.sort(inventory, Comparator.comparingDouble(Vehicle::GetSalesPrice).reversed());

		for (int i = 0; i < buyers.size(); i++){
			int sellerID = Helper.RandInt(0, MIN_STAFF - 1); // local id for salespeople array
			int chance = buyers.get(i).GetChance(); // varying chance due to car sold, condtion, cleanliness, and matching buyer preferances

			boolean found_car = false;

			if (!inventory.isEmpty()){
				for (int j = 0; j < inventory.size(); j++){
					if (!found_car){
						if (inventory.get(j).vehicleType == buyers.get(i).GetPreference()){
							found_car = true;
							if (inventory.get(j).isSparkling()) { chance += 10;}
							if (inventory.get(j).isLikeNew()) { chance += 10;}
							if (Helper.PercentChance(chance)){
								// increase the budget
								budget_ += inventory.get(j).GetSalesPrice();
								// display message
								System.out.printf( "\t" + salesPeople.get(sellerID).GetName() + " just sold " + inventory.get(j).GetName() + " for $%.2f\n", inventory.get(j).GetSalesPrice());
								// increase the daily sales total
								totalSalesPerDay = totalSalesPerDay + inventory.get(j).GetSalesPrice();
								// give bonus to salesperson
								salesPeople.get(sellerID).Bonus(inventory.get(j));
								// add vehicle to sold cars list
								soldVehicles.add(inventory.get(j));
								// remove vehicle from inventory
								inventory.remove(j);
							} else { System.out.println("\tcustomer did not want to buy a vehicle");}
						}
					}
				}
				if (!found_car){
					chance -= 20;
					if (inventory.get(0).isSparkling()) { chance += 10;}
					if (inventory.get(0).isLikeNew()) {chance += 10;}
					if (Helper.PercentChance(chance)){
						// increase the budget
						budget_ += inventory.get(0).GetSalesPrice();
						// display message
						System.out.printf( "\t" + salesPeople.get(sellerID).GetName() + " just sold " + inventory.get(0).GetName() + " for $%.2f\n", inventory.get(0).GetSalesPrice());
						// increase the daily sales total
						totalSalesPerDay = totalSalesPerDay + inventory.get(0).GetSalesPrice();
						// give bonus to salesperson
						salesPeople.get(sellerID).Bonus(inventory.get(0));
						// add vehicle to sold cars list
						soldVehicles.add(inventory.get(0));
						// remove vehicle from inventory
						inventory.remove(0);
					} else { System.out.println("\tcustomer did not want to buy a vehicle");}
				}
			} else { System.out.println("\tno vehiciles to sell, out of stock");}
		}
		
		Ending();
		
	}

	void Ending() {

		System.out.println("Ending...");

		ArrayList<String> Employees_Quit = new ArrayList<>();

		////////////////////////////////////////////
		// Give all Staff members their daily salary pay for today’s work (out of the operating budget).
		////////////////////////////////////////////
		for (int i = 0; i < salesPeople.size(); i++) {
			salesPeople.get(i).pay();
			budget_ = budget_ - salesPeople.get(i).GetDailySalary();
		}
		for (int i = 0; i < mechanics.size(); i++) {
			mechanics.get(i).pay();
			budget_ = budget_ - mechanics.get(i).GetDailySalary();
		}
		for (int i = 0; i < interns.size(); i++) {
			interns.get(i).pay();
			budget_ = budget_ - interns.get(i).GetDailySalary();
		}

		////////////////////////////////////////////
		// It’s possible at the end of every day one Mechanic, one Salesperson, and one Intern may quit the FNCD. 
		////////////////////////////////////////////
		/*/
		There is a 10% chance for each Staff type that one member may quit. 
		If an Intern quits, simply remove them from Staff.
		If a Mechanic or a Salesperson quits, remove them from the store Staff and add a new Mechanic or Salesperson to the Staff (as needed)
			using an existing Intern to provide the name for this new Staff member. 
			Remove the Intern used here from the Interns working at the FNCD. (The Intern has become the new Mechanic or Salesperson.)
		 When removing Staff from the FNCD, store the leaving instance in a collection of Departed Staff.
		*/

		// if an intern quits
		if (Helper.PercentChance(10)) {
			int InternID = Helper.RandInt(0, interns.size()-1);
			departedStaff.add(interns.get(InternID));
			Employees_Quit.add(interns.get(InternID).GetName());
			interns.remove(InternID); 
		}

		// if a mechanic quits
		if (Helper.PercentChance(10)) {
			
			int MechanicID = Helper.RandInt(0, mechanics.size()-1);

			departedStaff.add(mechanics.get(MechanicID));
			Employees_Quit.add(mechanics.get(MechanicID).GetName());

			mechanics.remove(MechanicID);

			int InternID = Helper.RandInt(0, interns.size()-1);

			mechanics.add(new Mechanic("mechanic_" + interns.get(InternID).GetName()));
			interns.remove(InternID);

		}

		// if a salesperson quits
		if (Helper.PercentChance(10)) {

			int SalesPersonID = Helper.RandInt(0, salesPeople.size()-1);

			departedStaff.add(salesPeople.get(SalesPersonID));
			Employees_Quit.add(salesPeople.get(SalesPersonID).GetName());

			salesPeople.remove(SalesPersonID);
			
			int InternID = Helper.RandInt(0, interns.size()-1);

			salesPeople.add(new SalesPerson("salesperson_" + interns.get(InternID).GetName()));
			interns.remove(InternID);
		}

		////////////////////////////////////////////
		// Produce a readable, tabular report of:
		////////////////////////////////////////////
		/*
		Staff members – with total days worked, total normal pay, total bonus pay, working or quit the FNCD Inventory 
		– List of all Vehicles with Name, Cost, Sale Price, Condition, Cleanliness, Sold or In Stock Total
		$ in operating budget
		total sales $ for day.
		*/

		// --------- staff who quit for the day
		System.out.println("\t-------------- Quit: --------------");
		if (Employees_Quit.isEmpty()){
			System.out.println("\t none quit");
		} else {
			for (int i = 0; i < Employees_Quit.size(); i++){
				System.out.println("\t" + Employees_Quit.get(i));
			}
		}
		
		// --------- staff who are still working
		System.out.println("\t-------------- WORKING: --------------");
		System.out.println("\t SALESPEOPLE:");
		for (int i = 0; i < salesPeople.size(); i++) {
			System.out.println("\t \t" + salesPeople.get(i).GetName());
			System.out.println("\t \t \t Total Normal Pay: " + salesPeople.get(i).GetTotalSalary());
			System.out.println("\t \t \t Total Bonus Pay: " + salesPeople.get(i).GetBonus());
		}
		System.out.println("\t  MECHANICS:");
		for (int i = 0; i < mechanics.size(); i++) {
			System.out.println("\t \t" + mechanics.get(i).GetName());
			System.out.println("\t \t \t Total Normal Pay: " + mechanics.get(i).GetTotalSalary());
			System.out.println("\t \t \t Total Bonus Pay: " + mechanics.get(i).GetBonus());
		}
		System.out.println("\t INTERNS:");
		for (int i = 0; i < interns.size(); i++) {
			System.out.println("\t \t" + interns.get(i).GetName());
			System.out.println("\t \t \t Total Normal Pay: " + interns.get(i).GetTotalSalary());
			System.out.println("\t \t \t Total Bonus Pay: " + interns.get(i).GetBonus());
		}
		
		// --------- Inventory
		PrintInventory(null);

		// --------- all sold vehicles
		System.out.println("\t -------------- Sold Vehicles: --------------");

		for(int i = 0; i < soldVehicles.size(); i++) {
			System.out.printf("\t" + soldVehicles.get(i).getInfo_asString() + "%-20s\n","Sold");
		}

		// --------- Budget left
		System.out.printf("\n \t ---- TOTAL MONEY IN BUDGET: %.2f ----\n",budget_);
		
		// --------- total sales for the day
		System.out.printf("\t ---- SALES FOR THE DAY: %.2f ----\n",totalSalesPerDay); 
		
		// --------- all staff that has left
		System.out.println( "\n\t ---- ALL STAFF THAT HAS LEFT: ----");
		for(int i = 0; i < departedStaff.size(); i++) {
			System.out.println("\t" + departedStaff.get(i).GetName());
		}
	}

}	