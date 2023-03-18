public class Moped extends Motorcycle {
    Moped(){
        super(); // invoking base-class Staff constructor. (This will call the createName() function
		vehicleType = Types_of_Vehicles.moped; 
		name = "Moped_" + Helper.GenerateUniqueID(); 
		cost = Helper.RandDouble(700, 3000); //700 - 3000
		engineSize = Helper.randDouble_Gaus(51.0,Double.MAX_VALUE,200.0, 100.0);
		
		DetermineCost();
		
		salesPrice = cost * 2.0; // randomly select a value between $20,000 and $40,0000.
    }
}
