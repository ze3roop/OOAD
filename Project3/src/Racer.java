import java.util.function.ToIntFunction;

public class Racer {
    private String name = "Racer_" + Helper.GenerateUniqueID();
    public Vehicle vehicle;
    public int position = -1;
    public boolean isFNCD = false;
    public int FNCD_InventoryID = -1;
    public int FNCD_DriverID = -1;
    public boolean isWinner = false;
    public boolean isInjured = false;

    public Racer(Vehicle.Types_of_Vehicles vehicle_type) {
        if (vehicle_type == Vehicle.Types_of_Vehicles.monsterTruck)        { vehicle = new MonsterTruck();}
		else if (vehicle_type == Vehicle.Types_of_Vehicles.motorcycle)     { vehicle = new Motorcycle();}
		else if (vehicle_type == Vehicle.Types_of_Vehicles.performanceCar) { vehicle = new PerformanceCar();}
		else if (vehicle_type == Vehicle.Types_of_Vehicles.pickup)         { vehicle = new Pickup();}
    }

    public Racer(Vehicle v, Boolean isFNCD_, String name_, int vehicle_ID, int driver_ID){
        vehicle = v;
        isFNCD = isFNCD_;
        name = name_;
        FNCD_InventoryID = vehicle_ID;
        FNCD_DriverID = driver_ID;
    }

    public String getName() { return name;}

    public int getPosition() { return position; }
}
