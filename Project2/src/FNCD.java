import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class FNCD {
	public ArrayList<Salespeople> salesPeople = new ArrayList<Salespeople>();
	public ArrayList<Mechanics> mechanics = new ArrayList<Mechanics>();
	public ArrayList<Interns> interns = new ArrayList<Interns>();
	public Integer staffPerType = 3; 
	public ArrayList<PerformanceCars> performanceCars = new ArrayList<PerformanceCars>();
	public ArrayList<Cars> cars = new ArrayList<Cars>();
	public ArrayList<Pickups> pickups = new ArrayList<Pickups>();
	public Integer carAmount = 4;
	public Double budget_; //this is going to be private. 
	public ArrayList<Vehicles> soldVehicles = new ArrayList<Vehicles>();
	public ArrayList<Staff> departedStaff = new ArrayList<Staff>();
	
	public String[] daysOfTheWeek_ = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	public int totalDayCount_;
	public int dayCount_; 
	public String day_; 
	public int daysToSimulate_;
	public Double totalSalesPerDay;
	
	public FNCD(int daysToSimulate)
	{
		budget_ = (double) 500000; //INITIAL OPERATING BUDGET 
		totalDayCount_ = 0;
		dayCount_ = 1; 
		totalSalesPerDay = 0.0;
		day_ = "Monday"; // gonna use something like, day = daysOfTheWeek[dayCount]. When dayCount gets to 6, we will make it equal to 0. But 
		//We will keep increasing totalDayCount without taking anything away, for the sake of a simulation duration tracker. 
		//Also when calling this FNCD object, we could instantiate with an argument for the amount of days it should run. Then, on open, 
		//we check if totalDayCount is equal to the argument. We can also call the next function in the class. so Opening() will call Cleaning() next,
		//and so on and so forth.
		daysToSimulate_ = daysToSimulate;
		
		//initialize staff and vehicles
		for (int a = 0; a<staffPerType; a++) {
			interns.add( new Interns() );
		}
		for (int b = 0; b<staffPerType; b++) {
			mechanics.add( new Mechanics() );
		}
		for (int c = 0; c<staffPerType; c++) {
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
		for (int i = 0; i < daysToSimulate_; i++) {
			Opening();
			Washing();
			Repairing();
			Selling();
			Ending();
		}
	}
	
	public void Opening() {
		
		//THIS IS WHERE WE SHOULD CHECK WHAT DAY IT IS, AND ADD TO ALL OF THE ACCUMULATORS. 
		
		
		

		
		//NOTE: EVERY TIME MONEY IS TAKEN OUT OF THE BUDGET, CHECK IF BUDGET IS <= IF IT IS THEN ADD $250,000 TO THE OPERATING BUDGET, AND ANNOUNCE IT.
		
		
		//if interns are less than three interns, hire (e.g. add) new interns to the FNCD to bring the count back to three. 
		if(interns.size() < staffPerType) {
			for (int i = 0; i<staffPerType; i++) {
				interns.add( new Interns() );
			}
		}
		
		
		//There should be four of each type of Vehicle in inventory. If there are not for any reason, additional vehicles will be sent to the FNCD.
		//These newly instantiated vehicles must be paid for, reducing the operating  budget by the cost of the Vehicle. The Vehicles will then be
		//part of the FNCD inventory.
		
		if(performanceCars.size() < carAmount) {
			for (int i = 0; i<carAmount; i++) {
				performanceCars.add( new PerformanceCars() );
				//take money from operating budget. 
				//SetBudget( performanceCars just instantiated.getSalesPrice() ); 
			}
		}
		if(cars.size() < carAmount) {
			for (int i = 0; i<carAmount; i++) {
				cars.add( new Cars() );
				//take money from operating budget. 
				//SetBudget( performanceCars just instantiated.getSalesPrice() ); 
			}
		}
		if(pickups.size() < carAmount) {
			for (int i = 0; i<carAmount; i++) {
				pickups.add( new Pickups() );
				//take money from operating budget. 
				//SetBudget( pickup just instantiated.getSalesPrice() ); 
			}
		}
		
	}
	
	public void Washing() {
		ArrayList<Vehicles> dirtyVehicles = new ArrayList<Vehicles>();
		//Every working day the interns will wash vehicles. 
		//run a for loop through each type of car. 
		//if a vehicle is dirty, add to dirty vehicles array 
		for(int i = 0; i<carAmount; i++) {
			if(performanceCars.get(i).GetCondition() == "dirty") {
				dirtyVehicles.add( performanceCars.get(i) );
			}
			if(cars.get(i).GetCondition() == "dirty") {
				dirtyVehicles.add( cars.get(i) );
			}
			if(pickups.get(i).GetCondition() == "dirty") {
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
			if(performanceCars.get(i).GetCondition() == "clean") {
				cleanVehicles.add( performanceCars.get(i) );
			}
			if(cars.get(i).GetCondition() == "clean") {
				cleanVehicles.add( cars.get(i) );
			}
			if(pickups.get(i).GetCondition() == "clean") {
				cleanVehicles.add( pickups.get(i) );
			}
		}
		ArrayList<Vehicles> sparklingVehicles = new ArrayList<Vehicles>();
		//Every working day the interns will wash vehicles. 
		//run a for loop through each type of car. 
		//if a vehicle is dirty, add to dirty vehicles array 
		for(int i = 0; i<carAmount; i++) {
			if(performanceCars.get(i).GetCondition() == "sparkling") {
				sparklingVehicles.add( performanceCars.get(i) );
			}
			if(cars.get(i).GetCondition() == "sparkling") {
				sparklingVehicles.add( cars.get(i) );
			}
			if(pickups.get(i).GetCondition() == "sparkling") {
				sparklingVehicles.add( pickups.get(i) );
			}
		}
		
		for (int i = 0; i<staffPerType; i++) { //So, loop through every intern one at a time, if we have dirty vehicles, and the intern still has jobs to do, then iterate through the while loop.
			//Once either the intern has done all available jobs for himself, then we go to the next intern. 
			while(interns.get(i).GetJobsDone() > 0 || (dirtyVehicles.size() == 0 && cleanVehicles.size() == 0)) { //GROUP the logic that both dirtyVehicles and cleanVehicles lists need to be empty.
				if (dirtyVehicles.size() > 0) 
				{
					//Clean car, make a call to an intern. 
					//if car is cleaned. remove from dirtyVehicles array list and put into the cleanVehicles array list. 
					//if car is cleaned . remove from dirtyVehicles array list and put into the clean vehicles array list.
					//Add 1 to the amount of jobs an intern has done. 
					var d = Math.random() * 100;
					if (d <= 10) { //check this one first, since it is included in 80% chance. Whereas if I called 80% chance first and it was 10% it wouldn't be called. 
						// 10% chance
						dirtyVehicles.get(i).SetCondition("sparkling");
						interns.get(i).Bonus(dirtyVehicles.get(i).GetVehicleType());      //IF MAKES SPARKLING, INTERN GETS A BONUS BY TYPE OF VEHICLE. 
						sparklingVehicles.add(dirtyVehicles.get(i));
						dirtyVehicles.remove(i);
						
					}
					else if (d <= 80) {
						// 80% chance
						cleanVehicles.add(dirtyVehicles.get(i));
						dirtyVehicles.remove(i);
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
						cleanVehicles.get(i).SetCondition("dirty");
						dirtyVehicles.add(cleanVehicles.get(i));
						cleanVehicles.remove(i);
					}
					else if (d <= 30) {
						//30% chance of making the car sparkling.
						cleanVehicles.get(i).SetCondition("sparkling");
						interns.get(i).Bonus(dirtyVehicles.get(i).GetVehicleType());//IF MAKES SPARKLING, INTERN GETS A BONUS BY TYPE OF VEHICLE. 
						sparklingVehicles.add(dirtyVehicles.get(i));
						cleanVehicles.remove(i);
					}
					//how to go about cleaning a car. 
					interns.get(i).SetJobsDone(); //regardless of whether or not they cleaned a car add one to the amount of jobs the intern has done. 
				}
			}
			
		}
	}
	
	public void Repairing() {
		//Each mechanic can fix two vehicles per day. Broken vehicles that are fixed become Used.
		//Used Vehicles that are fixed become Like New.
		//Each mechanic has an 80% chance of fixing any Vehicle worked on. Whether fixed or not, any Vehicle worked on will go down one class
		//of cleanliness (if not already dirty).
		//A vehicle that becomes used has its sales price increased 50%.
		//A vehicle that becomes like new has its sales price increased 25%.
		//Mechanics receive a bonus from each successful repair by Vehicle type. 
		
		ArrayList<Vehicles> brokenVehicles = new ArrayList<Vehicles>();
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
		
		ArrayList<Vehicles> usedVehicles = new ArrayList<Vehicles>();
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
		
		ArrayList<Vehicles> likeNewVehicles = new ArrayList<Vehicles>();
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
		
		for (int i = 0; i<staffPerType; i++) {  
			while(mechanics.get(i).GetJobsDone() > 0 || (brokenVehicles.size() == 0 && usedVehicles.size() == 0)) {
				if (brokenVehicles.size() > 0) 
				{
					var d = Math.random() * 100;
					if (d <= 80) {
						//80% chance to fix a vehicle
						//give mechanic a bonus for fixing a vehicle by vehicle type
						mechanics.get(i).Bonus(brokenVehicles.get(i).GetVehicleType());
						//bring vehicle down a level of cleanliness
						if(brokenVehicles.get(i).GetCleanliness() == "sparkling"){
							brokenVehicles.get(i).SetCleanliness("clean");
						}
						else if(brokenVehicles.get(i).GetCleanliness() == "clean"){
							brokenVehicles.get(i).SetCleanliness("dirty");
						}
						//A vehicle that becomes used has its sales price increased 50%. 
						brokenVehicles.get(i).SetSalesPrice(.5);
						
						//add fixed vehicle to the usedList, and remove it from the brokenList. 
						usedVehicles.add(brokenVehicles.get(i));
						brokenVehicles.remove(i); 
					}
					else {
						if(brokenVehicles.get(i).GetCleanliness() == "sparkling"){
							brokenVehicles.get(i).SetCleanliness("clean");
						}
						else if(brokenVehicles.get(i).GetCleanliness() == "clean"){
							brokenVehicles.get(i).SetCleanliness("dirty");
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
						mechanics.get(i).Bonus(usedVehicles.get(i).GetVehicleType());
						//bring vehicle down a level of cleanliness
						if(usedVehicles.get(i).GetCleanliness() == "sparkling"){
							usedVehicles.get(i).SetCleanliness("clean");
						}
						else if(usedVehicles.get(i).GetCleanliness() == "clean"){
							usedVehicles.get(i).SetCleanliness("dirty");
						}
						//A vehicle that becomes used has its sales price increased 25%. 
						usedVehicles.get(i).SetSalesPrice(.25);
						
						//add fixed vehicle to the usedList, and remove it from the usedList. 
						likeNewVehicles.add(usedVehicles.get(i));
						usedVehicles.remove(i); 
					}
					else {
						if(usedVehicles.get(i).GetCleanliness() == "sparkling"){
							usedVehicles.get(i).SetCleanliness("clean");
						}
						else if(usedVehicles.get(i).GetCleanliness() == "clean"){
							usedVehicles.get(i).SetCleanliness("dirty");
						}
					}
					
					mechanics.get(i).SetJobsDone();
				}
			}
			
		}
	}
	
	public void Selling() {
		 
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
		ArrayList<PerformanceCars> drivablePerfCars	= performanceCars;
		ArrayList<Cars> drivableCars = cars;
		ArrayList<Pickups> drivablePickups = pickups; 
		
		
		
		for ( int i = 0; i < performanceCars.size(); i++) {
			if(drivablePerfCars.get(i).GetCondition() == "Broken")
			{
				drivablePerfCars.remove(i);
			}
		}
		for ( int i = 0; i < cars.size(); i++) {
			if(drivableCars.get(i).GetCondition() == "Broken")
			{
				drivableCars.remove(i);
			}
		}
		for ( int i = 0; i < pickups.size(); i++) {
			if(drivablePickups.get(i).GetCondition() == "Broken")
			{
				drivablePickups.remove(i);
			}
		}
		
		
		
		/*=== SORT ALLDRIVABLEVEHICLES IN DESCENDING ORDER OF SALESPRICE ===*/
		//Collections.sort(allDrivableVehicles, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
		
		/*=== CHECK BUYER PREFERENCE ===*/
		for (int i = 0; i<buyers.size(); i++) {
			//INSTANTIATE A CHANCE VARIABLE HERE
			Double chance; 
			
			//INSTANTIATE OUR SALESPERSON HERE AS WELL ( RANDOMLY )
			int randomNum2 = ThreadLocalRandom.current().nextInt(0, 5); 
			salesPeople.get(randomNum2); // get a random salesPerson. 
			
			if(buyers.get(i).GetPreference() == "Performance Car") {
				if(drivablePerfCars.size() > 1) {
					//sell the one in the first spot, since that is the highest priced one.	
					//BUT FIRST CHECK IF WE CAN INCREASE SALESCHANCE 
					//WE COULD UPDATE SALESCHANCE IN THE SUB CLASS, BUT WE WOULD HAVE TO UPDATE EACH ITERATION IN REPAIRING AND CLEANING. OR WE CAN JUST CHECK HERE
					
					chance = buyers.get(i).GetChance();
					
					if(drivablePerfCars.get(0).GetCleanliness() == "sparkling") {
						//chance of sales up 10%.
						chance = chance + 10.0;
					}
					if(drivablePerfCars.get(0).GetCondition() == "Like New") {
						//chance of sales up 10%.
						chance = chance + 10.0;
					}
					//NOW SELL VEHICLE WITH UPDATED CHANCE. 
					var d = Math.random() * 100;
					if (d <= chance) {
						totalSalesPerDay = totalSalesPerDay + drivablePerfCars.get(0).GetSalesPrice();
						salesPeople.get(randomNum2).Bonus(drivablePerfCars.get(0).GetVehicleType());
						budget_ = budget_ + drivablePerfCars.get(0).GetSalesPrice();
						soldVehicles.add( drivablePerfCars.get(0) );
						Collections.sort(performanceCars, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
						performanceCars.remove(0); 
						drivablePerfCars.remove(0);
					}
				}
				else {
					//WE DON'T HAVE ANY PERFORMANCE CARS, SO 
					ArrayList<Vehicles> drivableVehicles = new ArrayList<Vehicles>();
					drivableVehicles.addAll(drivableCars);
					drivableVehicles.addAll(drivablePickups);
					Collections.sort(drivableVehicles, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
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
						totalSalesPerDay = totalSalesPerDay + drivableVehicles.get(0).GetSalesPrice();
						salesPeople.get(randomNum2).Bonus(drivableVehicles.get(0).GetVehicleType());
						budget_ = budget_ + drivableVehicles.get(0).GetSalesPrice();
						soldVehicles.add( drivableVehicles.get(0) );
						if(drivableVehicles.get(0).GetVehicleType() == "Pickup") {
							Collections.sort(pickups, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
							pickups.remove(0); 
							drivableVehicles.remove(0);
						}
						else if(drivableVehicles.get(0).GetVehicleType() == "Cars") {
							Collections.sort(cars, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
							cars.remove(0); 
							drivableVehicles.remove(0);
						}				
					}
				}
			}
			else if(buyers.get(i).GetPreference() == "Car") {
				if(drivablePerfCars.size() > 1) {
					//sell the one in the first spot, since that is the highest priced one.	
					//BUT FIRST CHECK IF WE CAN INCREASE SALESCHANCE 
					//WE COULD UPDATE SALESCHANCE IN THE SUB CLASS, BUT WE WOULD HAVE TO UPDATE EACH ITERATION IN REPAIRING AND CLEANING. OR WE CAN JUST CHECK HERE
					
					chance = buyers.get(i).GetChance();
					
					if(drivableCars.get(0).GetCleanliness() == "sparkling") {
						//chance of sales up 10%.
						chance = chance + 10.0;
					}
					if(drivableCars.get(0).GetCondition() == "Like New") {
						//chance of sales up 10%.
						chance = chance + 10.0;
					}
					//NOW SELL VEHICLE WITH UPDATED CHANCE. 
					var d = Math.random() * 100;
					if (d <= chance) {
						totalSalesPerDay = totalSalesPerDay + drivableCars.get(0).GetSalesPrice();
						salesPeople.get(randomNum2).Bonus(drivableCars.get(0).GetVehicleType());
						budget_ = budget_ + drivableCars.get(0).GetSalesPrice();
						soldVehicles.add( drivableCars.get(0) );
						Collections.sort(cars, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
						cars.remove(0); 
						drivableCars.remove(0);
					}
				}
				else {		
					//WE DON'T HAVE ANY DRIVABLE CARS, SO SELL ONE OF THE PERFORMANCE CARS OR THE PICKUP, WHICH EVER IS THE HIGHEST PRICE. 
					ArrayList<Vehicles> drivableVehicles = new ArrayList<Vehicles>();
					drivableVehicles.addAll(drivablePerfCars);
					drivableVehicles.addAll(drivablePickups);
					Collections.sort(drivableVehicles, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
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
						totalSalesPerDay = totalSalesPerDay + drivableVehicles.get(0).GetSalesPrice();
						salesPeople.get(randomNum2).Bonus(drivableVehicles.get(0).GetVehicleType());
						budget_ = budget_ + drivableVehicles.get(0).GetSalesPrice();
						soldVehicles.add( drivableVehicles.get(0) );
						if(drivableVehicles.get(0).GetVehicleType() == "Performance Car") {
							Collections.sort(performanceCars, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
							performanceCars.remove(0); 
							drivableVehicles.remove(0);
						}
						else if(drivableVehicles.get(0).GetVehicleType() == "Pickup") {
							Collections.sort(pickups, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
							pickups.remove(0); 
							drivableVehicles.remove(0);
						}				
					}
				}
			}
			else if(buyers.get(i).GetPreference() == "Pickup") {
				
				if(drivablePickups.size() > 1) {
					//sell the one in the first spot, since that is the highest priced one.	
					//BUT FIRST CHECK IF WE CAN INCREASE SALESCHANCE 
					//WE COULD UPDATE SALESCHANCE IN THE SUB CLASS, BUT WE WOULD HAVE TO UPDATE EACH ITERATION IN REPAIRING AND CLEANING. OR WE CAN JUST CHECK HERE
					
					chance = buyers.get(i).GetChance();
					
					if(drivablePickups.get(0).GetCleanliness() == "sparkling") {
						//chance of sales up 10%.
						chance = chance + 10.0;
					}
					if(drivablePickups.get(0).GetCondition() == "Like New") {
						//chance of sales up 10%.
						chance = chance + 10.0;
					}
					//NOW SELL VEHICLE WITH UPDATED CHANCE. 
					var d = Math.random() * 100;
					if (d <= chance) {
						totalSalesPerDay = totalSalesPerDay + drivablePickups.get(0).GetSalesPrice();
						salesPeople.get(randomNum2).Bonus(drivablePickups.get(0).GetVehicleType());
						budget_ = budget_ + drivablePickups.get(0).GetSalesPrice();
						soldVehicles.add( drivablePickups.get(0) );
						Collections.sort(pickups, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
						pickups.remove(0); 
						drivablePickups.remove(0);
					}
				}
				else {		
					//WE DON'T HAVE ANY DRIVABLE CARS, SO SELL ONE OF THE PERFORMANCE CARS OR THE PICKUP, WHICH EVER IS THE HIGHEST PRICE. 
					ArrayList<Vehicles> drivableVehicles = new ArrayList<Vehicles>();
					drivableVehicles.addAll(drivablePerfCars);
					drivableVehicles.addAll(drivableCars);
					Collections.sort(drivableVehicles, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
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
						totalSalesPerDay = totalSalesPerDay + drivableVehicles.get(0).GetSalesPrice();
						salesPeople.get(randomNum2).Bonus(drivableVehicles.get(0).GetVehicleType());
						budget_ = budget_ + drivableVehicles.get(0).GetSalesPrice();
						soldVehicles.add( drivableVehicles.get(0) );
						if(drivableVehicles.get(0).GetVehicleType() == "Performance Car") {
							Collections.sort(performanceCars, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
							performanceCars.remove(0); 
							drivableVehicles.remove(0);
						}
						else if(drivableVehicles.get(0).GetVehicleType() == "Car") {
							Collections.sort(cars, Comparator.comparingDouble(Vehicles::GetSalesPrice).reversed());
							cars.remove(0); 
							drivableVehicles.remove(0);
						}				
					}
				}
				
				
			}
		}
		
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
		
		for (int i = 0; i < salesPeople.size(); i++) {
			salesPeople.get(i).SetDailySalary();
		}
		for (int i = 0; i < mechanics.size(); i++) {
			mechanics.get(i).SetDailySalary();
		}
		for (int i = 0; i < interns.size(); i++) {
			interns.get(i).SetDailySalary();
		}
		
		
		System.out.println("============== QUIT: ==============");
		int acc = 0;
		var salespeopleChance = Math.random() * 100;
		if (salespeopleChance <= 10) {
			int randomNum = ThreadLocalRandom.current().nextInt(0, 5);
			System.out.println("\t" + salesPeople.get(randomNum).GetName());
			departedStaff.add(salesPeople.get(randomNum));
			salesPeople.remove(randomNum);
			salesPeople.add( new Salespeople (interns.get(0).GetName() ) );
			interns.remove(0);
		}
		else { acc++; }
		var mechanicChance = Math.random() * 100;
		if (mechanicChance <= 10) {
			int randomNum = ThreadLocalRandom.current().nextInt(0, 5);
			System.out.println("\t" + mechanics.get(randomNum).GetName());
			departedStaff.add(mechanics.get(randomNum));
			mechanics.remove(randomNum);
			mechanics.add( new Mechanics (interns.get(0).GetName() ) );
			interns.remove(0);
		}
		else { acc++; }
		var internChance = Math.random() * 100;
		if (internChance <= 10) {
			int randomNum = ThreadLocalRandom.current().nextInt(0, 5);
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
			System.out.println("\t \t Pay: " + mechanics.get(i).GetTotalSalary());
		}
		System.out.println("===== INTERNS: =====");
		for (int i = 0; i < interns.size(); i++) {
			System.out.println("\t" + interns.get(i).GetName());
		}
		
		/* ============== REST OF THE REPORT ============ */
			//Inventory - List of all Vehicles with Name, Cost, Sale Price, Condition, Cleanliness, Sold or In Stock
			//Total $ in operating budget, total sales $ for the day.
		System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", "Name",
				"Cost", "Sale Price", "Condition", "Cleanliness", "Sold or In Stock");
		for(int i = 0; i < performanceCars.size(); i++) {
			System.out.printf("%-20s%-20f%-20f%-20s%-20s%-20s\n", performanceCars.get(i).GetName(), 
					performanceCars.get(i).GetCost(), performanceCars.get(i).GetSalesPrice(),
					performanceCars.get(i).GetCondition(), performanceCars.get(i).GetCleanliness(), "In Stock");
		}
		for(int i = 0; i < cars.size(); i++) {
			System.out.printf("%-20s%-20f%-20f%-20s%-20s%-20s\n", cars.get(i).GetName(), 
					cars.get(i).GetCost(), cars.get(i).GetSalesPrice(),
					cars.get(i).GetCondition(), cars.get(i).GetCleanliness(), "In Stock");
		}
		for(int i = 0; i < pickups.size(); i++) {
			System.out.printf("%-20s%-20f%-20f%-20s%-20s%-20s\n", pickups.get(i).GetName(), 
					pickups.get(i).GetCost(), pickups.get(i).GetSalesPrice(),
					pickups.get(i).GetCondition(), pickups.get(i).GetCleanliness(), "In Stock");
		}
		for(int i = 0; i < soldVehicles.size(); i++) {
			System.out.printf("%-20s%-20f%-20f%-20s%-20s%-20s\n", soldVehicles.get(i).GetName(), 
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
