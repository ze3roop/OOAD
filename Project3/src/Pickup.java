public class Pickup extends Vehicle{
    static int num = 0; // end of name of car

    public Pickup() {
        super();

        Helper h = new Helper();

        num = num + h.randInt(1, 1000);
        name = "Car_" + num;

        cost = h.randDouble(10000.0, 40000.0); adjustCost();

        VehicleType = "Pickup";
    }
}
