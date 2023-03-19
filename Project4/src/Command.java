import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Scanner;

// https://www.geeksforgeeks.org/command-pattern/
// An interface for command
interface Command {
    public void execute();
}

class PrintInventory implements Command {
    FNCD dealership;

    PrintInventory(FNCD d){
        dealership = d;
    }

    @Override
    public void execute() {
       ArrayList <Vehicle> i = new ArrayList<Vehicle> (dealership.GetInventory());
       System.out.printf("%-40s%-20s\n", "Name" , "Price");
       for (Vehicle v : i){
        if(!v.isBroken()){
            System.out.printf("%-40s%-20.2f\n",v.GetName(),v.GetSalesPrice());
        }
       }
    }
}

class BuyCar implements Command {
    FNCD dealership;

    BuyCar(FNCD d){
        dealership = d;
    }

    @Override
    public void execute(){

    }

    public void run(String name, Scanner scnr){
        ArrayList <Vehicle> i = new ArrayList<Vehicle> (dealership.GetInventory());

        boolean found = false;

        for (int c = 0; c < i.size(); c++){
            if (i.get(c).GetName().equals(name)){
                found = true;
                double cost = i.get(c).GetSalesPrice();
                double warenty = cost * .2;
                double undercoat = cost * .05;
                double roadside = cost * .02;
                double radio = cost * 0.05;

                System.out.println("Would you like to add an Extended warenty for $" + Math.round(warenty* 100) / 100.0 + "?");
                System.out.print(">");
                if (userWants(scnr.nextLine())){
                    cost += Math.round(warenty* 100) / 100;
                }
                System.out.println("Would you like to add an undercoat for $" + Math.round(undercoat* 100) / 100.0 + "?");
                System.out.print(">");
                if (userWants(scnr.nextLine())){
                    cost += Math.round(undercoat* 100) / 100;
                }
                System.out.println("Would you like to add road side assistance for $" + Math.round(roadside* 100) / 100.0 + "?");
                System.out.print(">");
                if (userWants(scnr.nextLine())){
                    cost += Math.round(roadside* 100) / 100;
                }
                System.out.println("Would you like to add a Satellite Radio for $" + Math.round(radio* 100) / 100.0 + "?");
                System.out.print(">");
                if (userWants(scnr.nextLine())){
                    cost += Math.round(roadside* 100) / 100;
                }
                System.out.println("Your total comes to $" + Math.round(cost* 100) / 100.0 + ".");
                System.out.println("Would you like to purchase the " + name + "?");
                System.out.print(">");
                if (userWants(scnr.nextLine())){
                    i.remove(c);
                }
            }
        }

        if (!found){
            System.out.println("Sorry, we do not have \"" + name + "\" in stock");
        }
    }

    private Boolean userWants(String rawInput){
        return rawInput.contains("y") || rawInput.contains("Y");
    }

}

class PrintInfo implements Command {
    
    FNCD dealership;

    PrintInfo(FNCD d){
        dealership = d;
    }

    @Override
    public void execute() {

    }

    public void run(String name) {
        ArrayList <Vehicle> i = new ArrayList<Vehicle> (dealership.GetInventory());

        boolean found = false;

        for (int c = 0; c < i.size(); c++){
            if (i.get(c).GetName().equals(name)){
                found = true;
                System.out.println("Name: " + i.get(c).GetName());
                System.out.println("Price: $" + i.get(c).GetSalesPrice());
                String condition = "";
                    if (i.get(c).isBroken()) {condition = "Broken";}
                    else if (i.get(c).isUsed()) {condition = "Used";}
                    else if (i.get(c).isLikeNew()) {condition = "Like New";}
                System.out.println("Condition: " + condition);
                String cleanleness = "";
                    if (i.get(c).isDirty()) {cleanleness = "Dirty";}
                    else if (i.get(c).isClean()) {cleanleness = "Clean";}
                    else if (i.get(c).isSparkling()) {cleanleness = "Sparkling";}
                System.out.println("Cleanleness: " + cleanleness);
                System.out.println("Races Won: " + i.get(c).racesWon);
                String range = "N/A";
                if (i.get(c).isElectricCar()){range = String.valueOf(i.get(c).GetRange());}
                System.out.println("Range: " + range);
                String Engine = "N/A";
                if (i.get(c).isMotorcycle()){Engine = String.valueOf(i.get(c).getEngine());}
                System.out.println("Engine Size: " + Engine);
                String Faction = "N/A";
                if (i.get(c).isTransformer()){Faction = i.get(c).getFaction_String();}
                System.out.println("Faction: " + Faction);

                c = i.size();
            }
        }
        if (!found){
            System.out.println("Sorry, we do not have \"" + name + "\" in stock");
        }
    }   
}

class FNCD_SalesPerson{

    private String name;
    private FNCD dealership_;
    
    private PrintInfo Info;
    private Command print;

    private BuyCar buy;

    // I am implementing the Controler to be programed upon instantiation because I do not want to dynamicaly change what the salesperson does
    FNCD_SalesPerson(FNCD dealership){
        name = dealership.salesPeople.get(Helper.RandInt(0, dealership.salesPeople.size()-1)).GetName();
        print = new PrintInventory(dealership);
        dealership_ = dealership;
        Info = new PrintInfo(dealership);
        buy = new BuyCar(dealership);
    }

    public void PrintSellableInventory(){
        print.execute();
    }

    public void GetInfo(String S){
        Info.run(S);
    }
    
    public void Buy(String s, Scanner S){
        buy.run(s, S);
    }
    public void ChangeSalesPerson(){
        System.out.println("Okay, I will go get another sakles person for you");
        name = dealership_.salesPeople.get(Helper.RandInt(0, dealership_.salesPeople.size()-1)).GetName();
    }

    public void AskName(){
        System.out.println("My name is " + name);
    }

    // https://www.javatpoint.com/java-get-current-date
    public void AskTime(){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        System.out.println(dtf.format(now)); 
    }
}