public class PerformanceCar extends Vehicle{
    static int num = 0; // end of name of car

    public PerformanceCar() {
        super();

        Helper h = new Helper();

        num = num + h.randInt(1, 1000);
        name = "PerformanceCar_" + num;

        cost = h.randDouble(20000.0, 40000.0); adjustCost();

        VehicleType = "PerformanceCar";
    }
}
