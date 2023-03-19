import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class test {

    public Vehicle VehicleFactory(Vehicle.Types_of_Vehicles type){
        Vehicle v = new Vehicle();
        v = Vehicle.Create(type);
        return v;
    }

    //testing factory implemenation for Vehicle and Staff
    @Test
    public void TestVehicleFactory(){
        //car
        Vehicle.Types_of_Vehicles type1 = Vehicle.Types_of_Vehicles.car;
        Car c1 = new Car();
        assertEquals(VehicleFactory(type1).GetType(),c1.GetType());

        Vehicle.Types_of_Vehicles type2 = Vehicle.Types_of_Vehicles.pickup;
        Pickup c2 = new Pickup();
        assertEquals(VehicleFactory(type2).GetType(),c2.GetType());

        Vehicle.Types_of_Vehicles type3 = Vehicle.Types_of_Vehicles.performanceCar;
        PerformanceCar c3 = new PerformanceCar();
        assertEquals(VehicleFactory(type3).GetType(),c3.GetType());

        Vehicle.Types_of_Vehicles type4 = Vehicle.Types_of_Vehicles.electricCar;
        ElectricCar c4 = new ElectricCar();
        assertEquals(VehicleFactory(type4).GetType(),c4.GetType());

        Vehicle.Types_of_Vehicles type5 = Vehicle.Types_of_Vehicles.motorcycle;
        Motorcycle c5 = new Motorcycle();
        assertEquals(VehicleFactory(type5).GetType(),c5.GetType());

        Vehicle.Types_of_Vehicles type6 = Vehicle.Types_of_Vehicles.monsterTruck;
        MonsterTruck c6 = new MonsterTruck();
        assertEquals(VehicleFactory(type6).GetType(),c6.GetType());

        Vehicle.Types_of_Vehicles type7 = Vehicle.Types_of_Vehicles.transformer;
        Transformer c7 = new Transformer();
        assertEquals(VehicleFactory(type7).GetType(),c7.GetType());

        Vehicle.Types_of_Vehicles type8 = Vehicle.Types_of_Vehicles.semiTruck;
        SemiTruck c8 = new SemiTruck();
        assertEquals(VehicleFactory(type8).GetType(),c8.GetType());
    }


    //testing implementation of Staff Factory
    public Staff StaffFactory(Staff.Staff_Types type){
        Staff s = new Staff();
        s = Staff.Create(type);
        return s;
    }

    @Test
    public void TestStaffFactory(){
        Staff.Staff_Types type1 = Staff.Staff_Types.intern;
        Intern s1 = new Intern();
        assertEquals(StaffFactory(type1).getClass(), s1.getClass());

        Staff.Staff_Types type2 = Staff.Staff_Types.mechanic;
        Mechanic s2 = new Mechanic();
        assertEquals(StaffFactory(type2).getClass(), s2.getClass());

        Staff.Staff_Types type3 = Staff.Staff_Types.salesperson;
        SalesPerson s3 = new SalesPerson();
        assertEquals(StaffFactory(type3).getClass(), s3.getClass());

        Staff.Staff_Types type4 = Staff.Staff_Types.driver;
        Driver s4 = new Driver();
        assertEquals(StaffFactory(type4).getClass(), s4.getClass());
    }


    //testing 
    @Test
    public void TestVehicleValue(){
        //test that the value of the car is always within its range
        for(int d = 0; d <= 10000; d++){
            Car c = new Car(); //10,000 - 20,000
            double i = 9999;
            double t = c.GetCost();
            if(t > i && t < 20001){
                assertTrue(true);
            }
            else{
                assertFalse(false);
            }
        }
        for(int d = 0; d <= 10000; d++){
            //test tranformer cost is always within range
            Transformer c = new Transformer(); //1000000 - 1000000000;
            double min = 999999;
            double max = 1000000001;
            double t = c.GetCost();
            if(t > min && t < max){
                assertTrue(true);
            }
            else{
                assertFalse(false);
            }
        }     
    }

    @Test
    public void TestStrategyForInterns(){
        for(int i = 0; i < 10000; i++){
            Intern intern_ = new Intern();
            if(intern_.algType == "Chemical" || intern_.algType == "ElbowGrease" || intern_.algType == "Detailed"){
                assertTrue("Intern is one of the algorithm defined types", true);
            }
            else{
                assertFalse("not one of the types", false);
            }
        }
    }
}
