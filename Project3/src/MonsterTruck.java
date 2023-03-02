public class MonsterTruck extends Vehicle {

        MonsterTruck() {
            super(); // invoking base-class Staff constructor.
		    vehicleType = Types_of_Vehicles.monsterTruck; 
		
		    name = "MonsterTruck_" + Helper.GenerateUniqueID();
		
            cost = Helper.RandDouble(20000, 40000);
            DetermineCost();
            salesPrice = cost * 2.0;
        }
}
