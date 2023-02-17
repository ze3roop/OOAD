import java.util.ArrayList;
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
	
	public String[] daysOfTheWeek_ = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	public int totalDayCount_;
	public int dayCount_; 
	public String day_; 
	public int daysToSimulate_;
	
	public FNCD(int daysToSimulate)
	{
		budget_ = (double) 500000; //INITIAL OPERATING BUDGET 
		totalDayCount_ = 0;
		dayCount_ = 1; 
		day_ = "Monday"; // gonna use something like, day = daysOfTheWeek[dayCount]. When dayCount gets to 6, we will make it equal to 0. But 
		//We will keep increasing totalDayCount without taking anything away, for the sake of a simulation duration tracker. 
		//Also when calling this FNCD object, we could instantiate with an argument for the amount of days it should run. Then, on open, 
		//we check if totalDayCount is equal to the argument. We can also call the next function in the class. so Opening() will call Cleaning() next,
		//and so on and so forth.
		daysToSimulate_ = daysToSimulate;
	}
	
	public Double GetBudget() {
		return budget_;
	}
	public void SetBudget( Double amount ) {
		budget_ = budget_ - amount;
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
		//These newly instantiated vehciles must be paid for, reducing the operating  budget by the cost of the Vehicle. The Vehicles will then be
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
		//Every working day, 0 to 5 buyers will arrive to buy a vehicle (2-8 buyers on Friday/Saturday) from a Salesperson.
		//Buyers buy from a Salesperson (randomly selected). Buyers are initialized randomly with one of three types of Buying
		//, each with a base chance of buying a vehicle: Just Looking (10%), Wants One (40%), Needs One (70%).
		//The buyers will have a type of vehicle they want (Performance Car, Car, Pickup) randomly determined.
		
		//A salesperson will always try to sell the most expensive vehicle of the type the Buyer wants that is in the inventory.
		//(Note that broken vehicles cannot be sold to buyers.) The chance of a sales increases by 10% if the vehicle is like new, and
		//another 10% if the vehicle is sparkling. If no vehicle of the Buyer's vehicle type choice are available, the Salesperson will
		//try to sell the most expensive vehicle lift in inventory at -20% chance for the sale. If the buy buys the vehicle, they will give
		// the FNCD the sales price (which goes into the operating budget) and the Salesperson will get a bonus by Vehicle Type;
		//the vehicle should be removed from the FNCD working inventory, but should be stored in a collection of Sold Vehicles for later review. 
		
		// 1. Instantiate a list of Buyers, with a random size in the range of 0 - 5. Check if Fri/Sat for a range of 2-8.
		//	  So, create a random int that creates a random number from 0 - 5.
		
		//if not friday || saturday
		int randomNum = ThreadLocalRandom.current().nextInt(0, 5 + 1); //the 5 isn't inclusive, so add 1. 
		
		ArrayList<Buyer> buyers = new ArrayList<Buyer>();
		for (int i = 0; i<=randomNum; i++)
		{
			buyers.add(new Buyer());
		}
		
		// 2. Since Buyers are initialized randomly with one of three types of buying, we can either put the random logic here, and pass it into a constructor.
		//	  OR, do the random logic in the constructor when initializing the Buyer.
		
			//  DID IN THE CONSTRUCTOR 
		
		// 3. The same thing will also be done with the type of vehicle that they want, which is also randomly determined.
		
			//  DID IN THE CONSTRUCTOR 
		
		// 4. Since a salesperson will always try to sell the most expensive vehicle of the type the Buyers wants that is in the inventory, 
		//	  we will have to check the list of the VehicleType that the Buyers wants, and find the one with the highest salesprice. 
		
		
		
		for(int i = 0; i<buyers.size(); i++) {
			int randomNum2 = ThreadLocalRandom.current().nextInt(0, 5); 
			salesPeople.get(randomNum2); // get a random salesPerson. 
			if(buyers.get(i).GetPreference() == "Performance Car") {
				if (performanceCars.size() > 0)
				{
					if (performanceCars.size() > 1)
					{
						int n, m;
						for (n = 0; i<performanceCars.size()-1; n++) {
							for(m = 0; m<performanceCars.size() - n - 1; m++) {
								if (performanceCars.get(m).GetSalesPrice() > performanceCars.get(m+1).GetSalesPrice()) {
									PerformanceCars temp = performanceCars.get(m);
									performanceCars.set(m, performanceCars.get(m+1)); 
									performanceCars.set(m+1, temp);
								}
							}
						}
						
					}
					//we have our performanceCar as the last element.
					//NOW CALCULATE CHANCE AND TRY TO SELL THE LAST ELEMENT OF THE LIST ( AKA ArrayList.get(ArrayList.size()) ) . 
					if(performanceCars.get(performanceCars.size()).GetCondition() == "Like New" ) {
						//chance of sale increases by 10% 
					}
					if(performanceCars.get(performanceCars.size()).GetCleanliness() == "sparkling" ) {
						//chance of sale increases by 10%
					}
					if(performanceCars.get(performanceCars.size()).GetCondition() == "Broken") {
						//can't sell this car
					}
	
				}
				else {
					//WE HAVE NO PERFORMANCE CARS, SO WE HAVE TO SELL ANY CAR TYPE WITH THE HIGHEST SALESPRICE. 
					ArrayList<Vehicles> allVehicles = new ArrayList<Vehicles>();
					for(int a = 0; a<cars.size(); a++) {
						allVehicles.add( cars.get(a));
					}
					for(int b = 0; b<pickups.size(); b++) {
						allVehicles.add( pickups.get(b));
					}
					if (allVehicles.size() > 1)
					{
						int n, m;
						for (n = 0; i<allVehicles.size()-1; n++) {
							for(m = 0; m<allVehicles.size() - n - 1; m++) {
								if (allVehicles.get(m).GetSalesPrice() > allVehicles.get(m+1).GetSalesPrice()) {
									Vehicles temp = allVehicles.get(m);
									allVehicles.set(m, allVehicles.get(m+1)); 
									allVehicles.set(m+1, temp);
								}
							}
						}
						
					}
					//HERE WE TRY TO SELL THE ALLVEHICLES CAR AT THE LAST ELEMENT WITH A -20% CHANCE OF SALE. 
				}	
			}
			
			else if (buyers.get(i).GetPreference() == "Car") {
				if (cars.size() > 0)
				{
					if (cars.size() > 1)
					{
						int n, m;
						for (n = 0; i<cars.size()-1; n++) {
							for(m = 0; m<cars.size() - n - 1; m++) {
								if (cars.get(m).GetSalesPrice() > cars.get(m+1).GetSalesPrice()) {
									Cars temp = cars.get(m);
									cars.set(m, cars.get(m+1)); 
									cars.set(m+1, temp);
								}
							}
						}
						
					}
					//we have our performanceCar as the last element.
					//NOW CALCULATE CHANCE AND TRY TO SELL THE LAST ELEMENT OF THE LIST ( AKA ArrayList.get(ArrayList.size()) ) . 
				}
				else {
					//WE HAVE NO PERFORMANCE CARS, SO WE HAVE TO SELL ANY CAR TYPE WITH THE HIGHEST SALESPRICE. 
					ArrayList<Vehicles> allVehicles = new ArrayList<Vehicles>();
					for(int a = 0; a<performanceCars.size(); a++) {
						allVehicles.add( performanceCars.get(a));
					}
					for(int b = 0; b<pickups.size(); b++) {
						allVehicles.add( pickups.get(b));
					}
					if (allVehicles.size() > 1)
					{
						int n, m;
						for (n = 0; i<allVehicles.size()-1; n++) {
							for(m = 0; m<allVehicles.size() - n - 1; m++) {
								if (allVehicles.get(m).GetSalesPrice() > allVehicles.get(m+1).GetSalesPrice()) {
									Vehicles temp = allVehicles.get(m);
									allVehicles.set(m, allVehicles.get(m+1)); 
									allVehicles.set(m+1, temp);
								}
							}
						}
						
					}
					//HERE WE TRY TO SELL THE ALLVEHICLES CAR AT THE LAST ELEMENT WITH A -20% CHANCE OF SALE. 
				}	
			}
			else if (buyers.get(i).GetPreference() == "Pickup") {
				if (pickups.size() > 0)
				{
					if (pickups.size() > 1)
					{
						int n, m;
						for (n = 0; i<pickups.size()-1; n++) {
							for(m = 0; m<pickups.size() - n - 1; m++) {
								if (pickups.get(m).GetSalesPrice() > pickups.get(m+1).GetSalesPrice()) {
									Pickups temp = pickups.get(m);
									pickups.set(m, pickups.get(m+1)); 
									pickups.set(m+1, temp);
								}
							}
						}
						
					}
					//we have our performanceCar as the last element.
					//NOW CALCULATE CHANCE AND TRY TO SELL THE LAST ELEMENT OF THE LIST ( AKA ArrayList.get(ArrayList.size()) ) . 
				}
				else {
					//WE HAVE NO PERFORMANCE CARS, SO WE HAVE TO SELL ANY CAR TYPE WITH THE HIGHEST SALESPRICE. 
					ArrayList<Vehicles> allVehicles = new ArrayList<Vehicles>();
					for(int a = 0; a<performanceCars.size(); a++) {
						allVehicles.add( performanceCars.get(a));
					}
					for(int b = 0; b<cars.size(); b++) {
						allVehicles.add( cars.get(b));
					}
					if (allVehicles.size() > 1)
					{
						int n, m;
						for (n = 0; i<allVehicles.size()-1; n++) {
							for(m = 0; m<allVehicles.size() - n - 1; m++) {
								if (allVehicles.get(m).GetSalesPrice() > allVehicles.get(m+1).GetSalesPrice()) {
									Vehicles temp = allVehicles.get(m);
									allVehicles.set(m, allVehicles.get(m+1)); 
									allVehicles.set(m+1, temp);
								}
							}
						}
						
					}
					//HERE WE TRY TO SELL THE ALLVEHICLES CAR AT THE LAST ELEMENT WITH A -20% CHANCE OF SALE. 
				}	
			}
		}
		
		
		
		// 5. Add logic to increase the chance of sales by 10% if the vehicle is like new, and/or 10% if the vehicle is sparkling. 
		// 6. If there isn't a single vehicle of the buyers vehicle type choice available (which is extremely rare, considering we have 4 of each at the start of the day)
		//	  we will have to look through the other two vehicles types for the most expensive one, but with salesprice decreased 20%.
		// 7. If the buyer buys the vehicle, the salesprice of the vehicle will go into the operating budget, and the salesperson will 
		//	  get a bonus by vehicle type. 
		// 8. The vehicle should then be removed from the FNCD working inventory, but should be stored in a collection (arrayList) of sold vehicles
		//	  for later review. 
		
	}
}	
