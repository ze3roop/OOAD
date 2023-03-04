class Intern extends Staff
{
	Algorithm alg = null;

	public Intern() {
		super(); // invoking base-class Staff constructor. (This will call the createName() function
		dailySalary = 100.0; // whatever the base salary for Interns is 
		name = "Intern_" + Helper.GenerateUniqueID();
		JobsDone = 0; // start with 0 jobsdone
		totalSalary = 0.0;
		bonus_ = 0.0;
		int rand = Helper.RandInt(0, 2);
		if(rand == 0){
			alg = new Algorithm(new Chemical());
		}
		else if (rand == 1){
			alg = new Algorithm(new ElbowGrease());
		}
		else{
			alg = new Algorithm(new Detailed());
		}
		
	}
	public void Washing(Vehicle vehicle){
		alg.executeStrategy(vehicle);
	}
}
//Credit: https://www.tutorialspoint.com/design_pattern/strategy_pattern.htm
interface Strategy{
	public void doOperation(Vehicle vehicle); 
}
/* 
Chemical:
	o Dirty Vehicle: 80% chance of Clean, 10% chance of Sparkling
	o Clean Vehicle: 10% chance of Dirty, 20% chance of Sparkling
	o In all cases, a 10% chance of Vehicle becoming Broken (if not already) 
*/
class Chemical implements Strategy{
	public void doOperation(Vehicle vehicle){
		if(vehicle.isDirty()){
			//do percent chance in here
			boolean chance_of_clean = Helper.PercentChance(80);
			boolean chance_of_sparkling = Helper.PercentChance(10); 
			if(chance_of_sparkling){
				vehicle.makeSparkling();
			}
			else if (chance_of_clean){
				vehicle.makeClean();
			}
		}
		else if(vehicle.isClean()){
			//do percent chance in here
			boolean chance_of_dirty = Helper.PercentChance(10);
			boolean chance_of_sparkling = Helper.PercentChance(20); 
			if(chance_of_dirty){
				vehicle.makeDirty();
			}
			else if (chance_of_sparkling){
				vehicle.makeSparkling();
			}
		}
		//10% chance to make broken regardless of whether or not was cleaned, unless it is already broken
		if(!vehicle.isBroken()){
			boolean chance_of_broken = Helper.PercentChance(10);
			if(chance_of_broken){
				vehicle.makeBroken();
			}
		}
	}	
}

/*  
Elbow Grease:
	o Dirty Vehicle: 70% chance of Clean, 5% chance of Sparkling
	o Clean Vehicle: 15% chance of Dirty, 15% chance of Sparkling
	o In all cases, 10% chance of Vehicle becoming Like New (if not already)
*/
class ElbowGrease implements Strategy{
	public void doOperation(Vehicle vehicle){
		if(vehicle.isDirty()){
			//do percent chance in here
			boolean chance_of_clean = Helper.PercentChance(70);
			boolean chance_of_sparkling = Helper.PercentChance(5); 
			if(chance_of_sparkling){
				vehicle.makeSparkling();
			}
			else if (chance_of_clean){
				vehicle.makeClean();
			}
		}
		else if(vehicle.isClean()){
			//do percent chance in here
			boolean chance_of_dirty = Helper.PercentChance(15);
			boolean chance_of_sparkling = Helper.PercentChance(15); 
			if(chance_of_dirty){
				vehicle.makeDirty();
			}
			else if (chance_of_sparkling){
				vehicle.makeSparkling();
			}
		}
		//10% chance to make like new regardless of whether or not was cleaned, unless it is already like new
		if(!vehicle.isLikeNew()){
			boolean chance_of_like_new = Helper.PercentChance(10);
			if(chance_of_like_new){
				vehicle.makeLikeNew();
			}
		}
	}
}
/*
Detailed:
	o Dirty Vehicle: 60% chance of Clean, 20% chance of Sparkling
	o Clean Vehicle: 5% chance of Dirty, 40% chance of Sparkling
	o No special effects as above methods 
*/
class Detailed implements Strategy{
	public void doOperation(Vehicle vehicle){
		if(vehicle.isDirty()){
			//do percent chance in here
			boolean chance_of_clean = Helper.PercentChance(60);
			boolean chance_of_sparkling = Helper.PercentChance(20); 
			if(chance_of_sparkling){
				vehicle.makeSparkling();
			}
			else if (chance_of_clean){
				vehicle.makeClean();
			}
		}
		else if(vehicle.isClean()){
			//do percent chance in here
			boolean chance_of_dirty = Helper.PercentChance(5);
			boolean chance_of_sparkling = Helper.PercentChance(40); 
			if(chance_of_dirty){
				vehicle.makeDirty();
			}
			else if (chance_of_sparkling){
				vehicle.makeSparkling();
			}
		}
		//No special effects as above methods
	}
}
class Algorithm{
	private Strategy strategy;

	public Algorithm(Strategy strategy){
		this.strategy = strategy;
	}
	public void executeStrategy(Vehicle vehicle){
		strategy.doOperation(vehicle);
	}
}

