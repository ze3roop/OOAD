public class MonsterTruck extends Vehicle {

     // ------ Need to add unique features for the class

        MonsterTruck() {
            super(); // invoking base-class Staff constructor.
		    vehicleType = Types_of_Vehicles.monsterTruck; 
		
		    name = "MonsterTruck_" + Helper.GenerateUniqueID();
		
            cost = Helper.RandDouble(20000, 40000);
            DetermineCost();
            salesPrice = cost * 2.0;
        }
}
