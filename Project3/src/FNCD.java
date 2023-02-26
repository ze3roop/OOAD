import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class FNCD {
	protected ArrayList<Salespeople> salesPeople = new ArrayList<Salespeople>();
	protected ArrayList<Mechanics> mechanics = new ArrayList<Mechanics>();
	protected ArrayList<Interns> interns = new ArrayList<Interns>();
	protected Integer staffPerType = 3; 
	protected ArrayList<PerformanceCars> performanceCars = new ArrayList<PerformanceCars>();
	protected ArrayList<Cars> cars = new ArrayList<Cars>();
	protected ArrayList<Pickups> pickups = new ArrayList<Pickups>();
	protected Integer carAmount = 4;
	protected Double budget_; //this is going to be private. 
	protected ArrayList<Vehicles> soldVehicles = new ArrayList<Vehicles>();
	protected ArrayList<Vehicles> allSoldVehicles = new ArrayList<Vehicles>();
	protected ArrayList<Staff> departedStaff = new ArrayList<Staff>();
	
	protected String[] daysOfTheWeek_ = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	protected int totalDayCount_;
	protected int dayCount_; 
	protected String day_; 
	protected int daysToSimulate_;
	protected Double totalSalesPerDay;
	
	public FNCD(int daysToSimulate)
	{
		budget_ = (double) 500000; //INITIAL OPERATING BUDGET 
		totalDayCount_ = 0;
		dayCount_ = 0; 
		totalSalesPerDay = 0.0;
		day_ = "Sunday"; // gonna use something like, day = daysOfTheWeek[dayCount]. When dayCount gets to 6, we will make it equal to 0. But 
		//We will keep increasing totalDayCount without taking anything away, for the sake of a simulation duration tracker. 
		//Also when calling this FNCD object, we could instantiate with an argument for the amount of days it should run. Then, on open, 
		//we check if totalDayCount is equal to the argument. We can also call the next function in the class. so Opening() will call Cleaning() next,
		//and so on and so forth.
		daysToSimulate_ = daysToSimulate;
		
		//initialize staff and vehicles
		for (int a = 0; a<staffPerType + 1; a++) {
			interns.add( new Interns() );
		}
		for (int b = 0; b<staffPerType + 1; b++) {
			mechanics.add( new Mechanics() );
		}
		for (int c = 0; c<staffPerType + 1; c++) {
			salesPeople.add( new Salespeople() );
		}

		for (int a = 0; a < carAmount; a++) {
			performanceCars.add(new PerformanceCars() );
		}
		for (int a = 0; a < carAmount; a++) {
			cars.add( new Cars() );
		}
		for (int a = 0; a < carAmount; a++) {
			pickups.add(new Pickups() );
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
		if(day_ == "Sunday") {
			System.out.println("\n\n******************************************************************************\n");
			System.out.println("FNCD DAY " + totalDayCount_);
			System.out.println("SUNDAY - CLOSED");
			dayCount_++;
			day_= daysOfTheWeek_[dayCount_];
			totalDayCount_++;
			return;
		}
		//THIS IS WHERE WE SHOULD CHECK WHAT DAY IT IS, AND ADD TO ALL OF THE ACCUMULATORS.
		
		System.out.println("\n\n******************************************************************************\n");
		System.out.println("FNCD DAY " + totalDayCount_);
		
		System.out.println("Opening..."); 
		
		if(dayCount_ >= 6) {
			dayCount_ = 0;
		}
		day_ = daysOfTheWeek_[dayCount_];
		totalDayCount_++;
		dayCount_++;
		
		
		

		
		//NOTE: EVERY TIME MONEY IS TAKEN OUT OF THE BUDGET, CHECK IF BUDGET IS <= 0. IF IT IS THEN ADD $250,000 TO THE OPERATING BUDGET, AND ANNOUNCE IT.
		
		
		//if interns are less than three interns, hire (e.g. add) new interns to the FNCD to bring the count back to three. 
		if(interns.size() < 4) {
			for (int i = 0; interns.size()<4; i++) {
				interns.add( new Interns() );
				System.out.println("\tHired " + interns.get(i).GetName());
			}
		}
		
		
		//There should be four of each type of Vehicle in inventory. If there are not for any reason, additional vehicles will be sent to the FNCD.
		//These newly instantiated vehicles must be paid for, reducing the operating  budget by the cost of the Vehicle. The Vehicles will then be
		//part of the FNCD inventory.
		
		if(performanceCars.size() < carAmount) {
			for (int i = 0; performanceCars.size()<carAmount; i++) {
				performanceCars.add( new PerformanceCars() );
				//take money from operating budget. 
				budget_ = budget_ - performanceCars.get(i).GetCost(); 
				System.out.println("Purchased " + performanceCars.get(i).GetName() + ". For: " + performanceCars.get(i).GetCost());
			}
		}
		if(cars.size() < carAmount) {
			for (int i = 0; cars.size()<carAmount; i++) {
				cars.add( new Cars() );
				//take money from operating budget. 
				//SetBudget( performanceCars just instantiated.getSalesPrice() ); 
				budget_ = budget_ - cars.get(i).GetCost(); 
				System.out.println("Purchased " + cars.get(i).GetName() + ". For: " + cars.get(i).GetCost());
			}
		}
		if(pickups.size() < carAmount) {
			for (int i = 0; pickups.size()<carAmount; i++) {
				pickups.add( new Pickups() );
				//take money from operating budget. 
				//SetBudget( pickup just instantiated.getSalesPrice() ); 
				budget_ = budget_ - pickups.get(i).GetCost(); 
				System.out.println("Purchased " + pickups.get(i).GetName() + ". For: " + pickups.get(i).GetCost());
			}
		}
		
		Washing(); 
		
	}
	
	public void Washing() {
		System.out.println("Washing...");
		ArrayList<Vehicles> dirtyVehicles = new ArrayList<Vehicles>();
		//Every working day the interns will wash vehicles. 
		//run a for loop through each type of car. 
		//if a vehicle is dirty, add to dirty vehicles array 
		for(int i = 0; i<carAmount; i++) {
			if(performanceCars.get(i).isDirty()) {
				dirtyVehicles.add( performanceCars.get(i) );
			}
			if(cars.get(i).isDirty()) {
				dirtyVehicles.add( cars.get(i) );
			}
			if(pickups.get(i).isDirty()) {
				dirtyVehicles.add( pickups.get(i) );
			}
		}
		//have intern wash a random car for dirtyVehicles with a 80% chance of it becoming clean, and a 10% chance of it becoming sparkling. 
		//if an intern makes a vehicle sparkling, give them a bonus.
		//also, regardless of whether successful of cleaning or not, add 1 to the amount of jobs done by the intern.
		//if(dirtyVehicles.size() <= 0)
		//do the clean vehicles. and initialize a cleanVehicles arrayList in the same was as the dirtyVehicles
		ArrayList<Vehicles> cleanVehicles = new ArrayList<Vehicles>();
		//Every working day the interns will wash vehicles. 
		//run a for loop through each type of car. 
		//if a vehicle is dirty, add to dirty vehicles array 
		for(int i = 0; i<carAmount; i++) {
			if(performanceCars.get(i).isClean()) {
				cleanVehicles.add( performanceCars.get(i) );
			}
			if(cars.get(i).isClean()) {
				cleanVehicles.add( cars.get(i) );
			}
			if(pickups.get(i).isClean()) {
				cleanVehicles.add( pickups.get(i) );
			}
		}
		ArrayList<Vehicles> sparklingVehicles = new ArrayList<Vehicles>();
		//Every working day the interns will wash vehicles. 
		//run a for loop through each type of car. 
		//if a vehicle is dirty, add to dirty vehicles array 
		for(int i = 0; i<carAmount; i++) {
			if(performanceCars.get(i).isSparkling()) {
				sparklingVehicles.add( performanceCars.get(i) );
			}
			if(cars.get(i).isSparkling()) {
				sparklingVehicles.add( cars.get(i) );
			}
			if(pickups.get(i).isSparkling()) {
				sparklingVehicles.add( pickups.get(i) );
			}
		}
		
		for (int i = 0; i<staffPerType; i++) { //So, loop through every intern one at a time, if we have dirty vehicles, and the intern still has jobs to do, then iterate through the while loop.
			//Once either the intern has done all available jobs for himself, then we go to the next intern. 
			while(interns.get(i).GetJobsDone() < 2 || (dirtyVehicles.size() == 0 && cleanVehicles.size() == 0)) { //GROUP the logic that both dirtyVehicles and cleanVehicles lists need to be empty.
				if (dirtyVehicles.size() > 0) 
				{
					//Clean car, make a call to an intern. 
					//if car is cleaned. remove from dirtyVehicles array list and put into the cleanVehicles array list. 
					//if car is cleaned . remove from dirtyVehicles array list and put into the clean vehicles array list.
					//Add 1 to the amount of jobs an intern has done. 
					var d = Math.random() * 100;
					if (d <= 10) { //check this one first, since it is included in 80% chance. Whereas if I called 80% chance first and it was 10% it wouldn't be called. 
						// 10% chance
						System.out.println("\t" + interns.get(i).GetName() + " washed " + dirtyVehicles.get(0).GetName() + " and made it sparkling" );
						dirtyVehicles.get(0).makeSparkling();
						interns.get(i).Bonus(dirtyVehicles.get(0));      //IF MAKES SPARKLING, INTERN GETS A BONUS BY TYPE OF VEHICLE. 
						sparklingVehicles.add(dirtyVehicles.get(0));
						dirtyVehicles.remove(0);
						
					}
					else if (d <= 80) {
						// 80% chance
						System.out.println("\t" + interns.get(i).GetName() + " washed " + dirtyVehicles.get(0).GetName() + " and made it clean" );
						cleanVehicles.add(dirtyVehicles.get(0));
						dirtyVehicles.remove(0);
					}
					interns.get(i).SetJobsDone(); //regardless of whether or not they cleaned a car add one to the amount of jobs the intern has done. 
					    
				}
				else if(cleanVehicles.size() > 0)// if there are no more dirtyVehicles, then iterate through the cleanVehicles list
				{
					//Clean car, make a call to an intern.
					//if car is cleaned. remove from cleanVehicles array list and put into the sparklingVehicle array list. 
					//if car is cleaned . remove from cleanVehicles array list and put into the sparklingVehicle array list.
					//Add 1 to the amount of jobs an intern has done. 
					var d = Math.random() * 100;
					if(d <= 5) {
						//5% chance of becoming dirty?
						System.out.println("\t" + interns.get(i).GetName() + " washed " + cleanVehicles.get(0).GetName() + " and made it dirty." );
						cleanVehicles.get(0).makeDirty();
						dirtyVehicles.add(cleanVehicles.get(0));
						cleanVehicles.remove(0);
					}
					else if (d <= 30) {
						//30% chance of making the car sparkling.
						System.out.println("\t" + interns.get(i).GetName() + " washed " + cleanVehicles.get(0).GetName() + " and made it sparkling." );
						cleanVehicles.get(0).makeSparkling();
						interns.get(i).Bonus(cleanVehicles.get(0));//IF MAKES SPARKLING, INTERN GETS A BONUS BY TYPE OF VEHICLE. 
						sparklingVehicles.add(cleanVehicles.get(0));
						cleanVehicles.remove(0);
					}
					//how to go about cleaning a car. 
					interns.get(i).SetJobsDone(); //regardless of whether or not they cleaned a car add one to the amount of jobs the intern has done. 
				}
			}
			
		}
		performanceCars.clear();
		cars.clear();
		pickups.clear();
		
		//drivableVehicles.addAll(drivablePerfCars);
		for(int i = 0; i < dirtyVehicles.size(); i++) {
			if(dirtyVehicles.get(i).isPerformanceCar()) {
				performanceCars.add((PerformanceCars) dirtyVehicles.get(i));
			}
			else if(dirtyVehicles.get(i).isCar()) {
				cars.add((Cars) dirtyVehicles.get(i));
			}
			else if(dirtyVehicles.get(i).isPickup()) {
				pickups.add((Pickups) dirtyVehicles.get(i));
			}
		}
		for(int i = 0; i < cleanVehicles.size(); i++) {
			if(cleanVehicles.get(i).isPerformanceCar()) {
				performanceCars.add((PerformanceCars) cleanVehicles.get(i));
			}
			else if(cleanVehicles.get(i).isCar()) {
				cars.add((Cars) cleanVehicles.get(i));
			}
			else if(cleanVehicles.get(i).isPickup()) {
				pickups.add((Pickups) cleanVehicles.get(i));
			}
		}
		for(int i = 0; i < sparklingVehicles.size(); i++) {
			if(sparklingVehicles.get(i).isPerformanceCar()) {
				performanceCars.add((PerformanceCars) sparklingVehicles.get(i));
			}
			else if(sparklingVehicles.get(i).isCar()) {
				cars.add((Cars) sparklingVehicles.get(i));
			}
			else if(sparklingVehicles.get(i).isPickup()) {
				pickups.add((Pickups) sparklingVehicles.get(i));
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
		
		ArrayList<Vehicles> brokenVehicles = new ArrayList<Vehicles>();
		for(int i = 0; i<carAmount; i++) {
			if(performanceCars.get(i).isBroken()) {
				brokenVehicles.add( performanceCars.get(i) );
			}
			if(cars.get(i).isBroken()) {
				brokenVehicles.add( cars.get(i) );
			}
			if(pickups.get(i).isBroken()) {
				brokenVehicles.add( pickups.get(i) );
			}
		}
		
		ArrayList<Vehicles> usedVehicles = new ArrayList<Vehicles>();
		for(int i = 0; i<carAmount; i++) {
			if(performanceCars.get(i).isUsed()) {
				usedVehicles.add( performanceCars.get(i) );
			}
 			if(cars.get(i).isUsed()) {
				usedVehicles.add( cars.get(i) );
			}
			if(pickups.get(i).isUsed()) {
				usedVehicles.add( pickups.get(i) );
			}
		}
		
		ArrayList<Vehicles> likeNewVehicles = new ArrayList<Vehicles>();
		for(int i = 0; i<carAmount; i++) {
			if(performanceCars.get(i).isLikeNew()) {
				likeNewVehicles.add( performanceCars.get(i) );
			}
			if(cars.get(i).isLikeNew()) {
				likeNewVehicles.add( cars.get(i) );
			}
			if(pickups.get(i).isLikeNew()) {
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
						System.out.println("\t" + mechanics.get(i).GetName() + " just repaired " + brokenVehicles.get(0).GetName() + " and made it Used");
						mechanics.get(i).Bonus(brokenVehicles.get(0));
						//bring vehicle down a level of cleanliness
						if(brokenVehicles.get(0).isSparkling()){
							brokenVehicles.get(0).makeClean();
						}
						else if(brokenVehicles.get(0).isClean()){
							brokenVehicles.get(0).makeDirty();
						}
						//A vehicle that becomes used has its sales price increased 50%. 
						brokenVehicles.get(0).SetSalesPrice(.5);
						
						//add fixed vehicle to the usedList, and remove it from the brokenList. 
						usedVehicles.add(brokenVehicles.get(0));
						brokenVehicles.remove(0);
					}
					else {
						if(brokenVehicles.get(0).isSparkling()){
							brokenVehicles.get(0).makeClean();
						}
						else if(brokenVehicles.get(0).isClean()){
							brokenVehicles.get(0).makeDirty();
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
						System.out.println("\t" + mechanics.get(i).GetName() + " just repaired " + usedVehicles.get(0).GetName() + " and made it Like New");
						mechanics.get(i).Bonus(usedVehicles.get(0));
						//bring vehicle down a level of cleanliness
						if(usedVehicles.get(0).isSparkling()){
							usedVehicles.get(0).makeClean();
						}
						else if(usedVehicles.get(0).isClean()){
							usedVehicles.get(0).makeDirty();
						}
						//A vehicle that becomes used has its sales price increased 25%. 
						usedVehicles.get(0).SetSalesPrice(.25);
						
						//add fixed vehicle to the likeNewVehicles, and remove it from the usedList. 
						likeNewVehicles.add(usedVehicles.get(0));
						usedVehicles.remove(0); 
					}
					else {
						if(usedVehicles.get(0).isSparkling()){
							usedVehicles.get(0).makeClean();
						}
						else if(usedVehicles.get(0).isClean()){
							usedVehicles.get(0).makeDirty();
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
			if(brokenVehicles.get(i).isPerformanceCar()) {
				performanceCars.add((PerformanceCars) brokenVehicles.get(i));
			}
			if(brokenVehicles.get(i).isCar()) {
				cars.add((Cars) brokenVehicles.get(i));
			}
			if(brokenVehicles.get(i).isPickup()) {
				pickups.add((Pickups) brokenVehicles.get(i));
			}
		}
		for(int a = 0; a < usedVehicles.size(); a++) {
			if(usedVehicles.get(a).isPerformanceCar()) {
				performanceCars.add((PerformanceCars) usedVehicles.get(a));
			}
			if(usedVehicles.get(a).isCar()) {
				cars.add((Cars) usedVehicles.get(a));
			}
			if(usedVehicles.get(a).isPickup()) {
				pickups.add((Pickups) usedVehicles.get(a));
			}
		}
		for(int b = 0; b < likeNewVehicles.size(); b++) {
			if(likeNewVehicles.get(b).isPerformanceCar()) {
				performanceCars.add((PerformanceCars) likeNewVehicles.get(b));
			}
			if(likeNewVehicles.get(b).isCar()) {
				cars.add((Cars) likeNewVehicles.get(b));
			}
			if(likeNewVehicles.get(b).isPickup()) {
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
			if(performanceCars.get(i).isBroken())
			{
				brokenPerf.add(performanceCars.get(i));
				performanceCars.remove(i);
			}
		}
		for ( int i = 0; i < cars.size(); i++) {
			if(cars.get(i).isBroken())
			{
				brokenCar.add(cars.get(i));
				cars.remove(i);
			}
		}
		for ( int i = 0; i < pickups.size(); i++) {
			if(pickups.get(i).isBroken())
			{
				brokenPickup.add(pickups.get(i));
				pickups.remove(i);
			}
		}
		Collections.sort(performanceCars, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
		Collections.sort(cars, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
		Collections.sort(pickups, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
		
		
		
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
						if(!performanceCars.get(a).isBroken()) {
						
							chance = buyers.get(i).GetChance();
							
							if(performanceCars.get(a).isSparkling()) {
								//chance of sales up 10%.
								chance = chance + 10.0;
							}
							if(performanceCars.get(a).isLikeNew()) {
								//chance of sales up 10%.
								chance = chance + 10.0;
							}
							//NOW SELL VEHICLE WITH UPDATED CHANCE. 
							var d = Math.random() * 100;
							if (d <= chance) {
								System.out.println( "\t" + salesPeople.get(randomNum2).GetName() + " just sold " + performanceCars.get(a).GetName() + " for " + performanceCars.get(a).GetSalesPrice());
								totalSalesPerDay = totalSalesPerDay + performanceCars.get(a).GetSalesPrice();
								salesPeople.get(randomNum2).Bonus(performanceCars.get(a));
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
					ArrayList<Vehicles> drivableVehicles = new ArrayList<Vehicles>();
					drivableVehicles.addAll(cars);
					drivableVehicles.addAll(pickups);
					Collections.sort(drivableVehicles, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
					//NOW SELL FIRST ELEMENT OF DRIVABLE VEHICLES. 
					
					chance = buyers.get(i).GetChance();
					
					if(drivableVehicles.get(0).isSparkling()) {
						//chance of sales up 10%.
						chance = chance + 10.0;
					}
					if(drivableVehicles.get(0).isLikeNew()) {
						//chance of sales up 10%.
						chance = chance + 10.0;
					}
					
					chance = chance - 20.0; // - 20% because it's not the preferred type of car. 
					
					//NOW SELL VEHICLE, BUT DECREASES SALES CHANCE BY 20%.
					var d = Math.random() * 100;
					if (d <= chance - 20.0) {
						for(int a = 0; a<drivableVehicles.size(); a++) {
							if(drivableVehicles.get(a).isCar() && !drivableVehicles.get(a).isBroken()) {
								
								Collections.sort(performanceCars, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
								System.out.println( "\t" + salesPeople.get(randomNum2).GetName() + " just sold " + drivableVehicles.get(a).GetName() + " for " + drivableVehicles.get(a).GetSalesPrice());
								cars.remove(a); 
								totalSalesPerDay = totalSalesPerDay + drivableVehicles.get(a).GetSalesPrice();
								salesPeople.get(randomNum2).Bonus(drivableVehicles.get(a));
								budget_ = budget_ + drivableVehicles.get(a).GetSalesPrice();
								soldVehicles.add( drivableVehicles.get(a) );
								break;
							}
							else if(drivableVehicles.get(a).isPickup()	 && !drivableVehicles.get(a).isBroken()) {
								
								Collections.sort(cars, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
								System.out.println( "\t" + salesPeople.get(randomNum2).GetName() + " just sold " + drivableVehicles.get(a).GetName() + " for " + drivableVehicles.get(a).GetSalesPrice());
								pickups.remove(a); 
								totalSalesPerDay = totalSalesPerDay + drivableVehicles.get(a).GetSalesPrice();
								salesPeople.get(randomNum2).Bonus(drivableVehicles.get(a));
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
						if(!cars.get(a).isBroken()) {
						
							chance = buyers.get(i).GetChance();
							
							if(cars.get(a).isSparkling()) {
								//chance of sales up 10%.
								chance = chance + 10.0;
							}
							if(cars.get(a).isLikeNew()) {
								//chance of sales up 10%.
								chance = chance + 10.0;
							}
							//NOW SELL VEHICLE WITH UPDATED CHANCE. 
							var d = Math.random() * 100;
							if (d <= chance) {
								System.out.println( "\t" + salesPeople.get(randomNum2).GetName() + " just sold " + cars.get(a).GetName() + " for " + cars.get(a).GetSalesPrice());
								totalSalesPerDay = totalSalesPerDay + cars.get(a).GetSalesPrice();
								salesPeople.get(randomNum2).Bonus(cars.get(a));
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
					ArrayList<Vehicles> drivableVehicles = new ArrayList<Vehicles>();
					drivableVehicles.addAll(performanceCars);
					drivableVehicles.addAll(pickups);
					Collections.sort(drivableVehicles, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
					//NOW SELL FIRST ELEMENT OF DRIVABLE VEHICLES. 
					
					chance = buyers.get(i).GetChance();
					
					if(drivableVehicles.get(0).isSparkling()) {
						//chance of sales up 10%.
						chance = chance + 10.0;
					}
					if(drivableVehicles.get(0).isLikeNew()) {
						//chance of sales up 10%.
						chance = chance + 10.0;
					}
					
					chance = chance - 20.0; // - 20% because it's not the preferred type of car. 
					
					//NOW SELL VEHICLE, BUT DECREASES SALES CHANCE BY 20%.
					var d = Math.random() * 100;
					
					if (d <= chance - 20.0) {
						for(int a = 0; a<drivableVehicles.size(); a++) {
							if(drivableVehicles.get(a).isPerformanceCar() && !drivableVehicles.get(a).isBroken()) {
								
								Collections.sort(performanceCars, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
								System.out.println( "\t" + salesPeople.get(randomNum2).GetName() + " just sold " + drivableVehicles.get(a).GetName() + " for " + drivableVehicles.get(a).GetSalesPrice());
								performanceCars.remove(a); 
								totalSalesPerDay = totalSalesPerDay + drivableVehicles.get(a).GetSalesPrice();
								salesPeople.get(randomNum2).Bonus(drivableVehicles.get(a));
								budget_ = budget_ + drivableVehicles.get(a).GetSalesPrice();
								soldVehicles.add( drivableVehicles.get(a) );
								break;
							}
							else if(drivableVehicles.get(a).isPickup() && !drivableVehicles.get(a).isBroken()) {
								Collections.sort(cars, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
								System.out.println( "\t" + salesPeople.get(randomNum2).GetName() + " just sold " + drivableVehicles.get(a).GetName() + " for " + drivableVehicles.get(a).GetSalesPrice());
								pickups.remove(a); 
								totalSalesPerDay = totalSalesPerDay + drivableVehicles.get(a).GetSalesPrice();
								salesPeople.get(randomNum2).Bonus(drivableVehicles.get(a));
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
						if(!pickups.get(a).isBroken()) {
						
							chance = buyers.get(i).GetChance();
							
							if(pickups.get(0).isSparkling()) {
								//chance of sales up 10%.
								chance = chance + 10.0;
							}
							if(pickups.get(0).isLikeNew()) {
								//chance of sales up 10%.
								chance = chance + 10.0;
							}
							//NOW SELL VEHICLE WITH UPDATED CHANCE. 
							var d = Math.random() * 100;
							if (d <= chance) {
								System.out.println( "\t" + salesPeople.get(randomNum2).GetName() + " just sold " + pickups.get(a).GetName() + " for " + pickups.get(a).GetSalesPrice());
								totalSalesPerDay = totalSalesPerDay + pickups.get(0).GetSalesPrice();
								salesPeople.get(randomNum2).Bonus(pickups.get(0));
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
					ArrayList<Vehicles> drivableVehicles = new ArrayList<Vehicles>();
					drivableVehicles.addAll(performanceCars);
					drivableVehicles.addAll(cars);
					Collections.sort(drivableVehicles, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
					//NOW SELL FIRST ELEMENT OF DRIVABLE VEHICLES. 
					
					chance = buyers.get(i).GetChance();
					
					if(drivableVehicles.get(0).isSparkling()) {
						//chance of sales up 10%.
						chance = chance + 10.0;
					}
					if(drivableVehicles.get(0).isLikeNew()) {
						//chance of sales up 10%.
						chance = chance + 10.0;
					}
					
					chance = chance - 20.0; // - 20% because it's not the preferred type of car. 
					
					//NOW SELL VEHICLE, BUT DECREASES SALES CHANCE BY 20%.
					var d = Math.random() * 100;
					if (d <= chance - 20.0) {
						for(int a = 0; a<drivableVehicles.size(); a++) {
							if(drivableVehicles.get(a).isPerformanceCar() && !drivableVehicles.get(a).isBroken()) {
								Collections.sort(performanceCars, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
								System.out.println( "\t" + salesPeople.get(randomNum2).GetName() + " just sold " + drivableVehicles.get(a).GetName() + " for " + drivableVehicles.get(a).GetSalesPrice());
								performanceCars.remove(a); 
								totalSalesPerDay = totalSalesPerDay + drivableVehicles.get(a).GetSalesPrice();
								salesPeople.get(randomNum2).Bonus(drivableVehicles.get(a));
								budget_ = budget_ + drivableVehicles.get(a).GetSalesPrice();
								soldVehicles.add( drivableVehicles.get(a) );
								break;
							}
							else if(drivableVehicles.get(a).isCar() && !drivableVehicles.get(a).isBroken()) {
								Collections.sort(cars, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
								System.out.println( "\t" + salesPeople.get(randomNum2).GetName() + " just sold " + drivableVehicles.get(a).GetName() + " for " + drivableVehicles.get(a).GetSalesPrice());
								cars.remove(a); 
								totalSalesPerDay = totalSalesPerDay + drivableVehicles.get(a).GetSalesPrice();
								salesPeople.get(randomNum2).Bonus(drivableVehicles.get(a));
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
