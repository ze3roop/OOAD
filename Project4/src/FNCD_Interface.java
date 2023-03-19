import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class FNCD_Interface {

    ArrayList<FNCD> arr = new ArrayList<FNCD>();
    ArrayList<String> FNCD_Name = new ArrayList<String>();

    FNCD_Interface(int daysToSimulate){
        arr.add(new FNCD());
        FNCD_Name.add("North");

        arr.add(new FNCD());
        FNCD_Name.add("South");

        try {
			// Redirect System.out to a file
			FileOutputStream fos = new FileOutputStream("SimResults.txt");
			PrintStream ps = new PrintStream(fos);
			System.setOut(ps);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

        for(int i = 0; i < daysToSimulate; i++){
            Boolean isRaceDay = false;

            System.out.println("\n\n******************************************************************************\n");
            System.out.println("---- " + Helper.Week[i % 6] + " - FNCD " + FNCD_Name.get(0) + " DAY " + i + " ----");
            isRaceDay = arr.get(0).Opening();
            arr.get(0).ResetTotalSalesPerDay();
            System.out.println("---- " + Helper.Week[i % 6] + " - FNCD " + FNCD_Name.get(1) + " DAY " + i + " ----");
            arr.get(1).Opening();
            arr.get(1).ResetTotalSalesPerDay();

            if (isRaceDay){
                System.out.println("-----------------" + FNCD_Name.get(0) + "-----------------");
                arr.get(0).Racing();
                System.out.println("-----------------" + FNCD_Name.get(1) + "-----------------");
                arr.get(1).Racing();
            }
            else{
                System.out.println("-----------------" + FNCD_Name.get(0) + "-----------------");
                arr.get(0).Washing();
                System.out.println("-----------------" + FNCD_Name.get(1) + "-----------------");
                arr.get(1).Washing();
                System.out.println("-----------------" + FNCD_Name.get(0) + "-----------------");
                arr.get(0).Repairing();
                System.out.println("-----------------" + FNCD_Name.get(1) + "-----------------");
                arr.get(1).Repairing();
                System.out.println("-----------------" + FNCD_Name.get(0) + "-----------------");
                arr.get(0).Selling();
                System.out.println("-----------------" + FNCD_Name.get(1) + "-----------------");
                arr.get(1).Selling();
            }

            System.out.println("-----------------" + FNCD_Name.get(0) + "-----------------");
                arr.get(0).Ending("North");
                System.out.println("-----------------" + FNCD_Name.get(1) + "-----------------");
                arr.get(1).Ending("South");
        }

        System.out.println("=====================================================");
		System.out.println("================= End of Simulation =================");
		System.out.println("=====================================================");

        arr.get(0).tracker.display();
        arr.get(1).tracker.display();

		try {
			// Reset System.out to print to terminal
			System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
		}
		catch (Exception e) {
			e.printStackTrace();
        }


        boolean quit = false;
        Scanner scnr = new Scanner(System.in);

        FNCD_SalesPerson N = new FNCD_SalesPerson(arr.get(0));
        FNCD_SalesPerson S = new FNCD_SalesPerson(arr.get(1));

        do{

            System.out.print("\n>");

            String rawUserInput = scnr.nextLine();
            String[] userInput = rawUserInput.split("\\s+");

            String commandString = userInput[0];
            String flag = "";
            String input = "";

            if (userInput.length > 1){
                flag = userInput[1];
            }
            if (userInput.length > 2){
                input = userInput[2];
            }

            switch(commandString){
                case "Help":
                case "help":
                case "h":
                case "H":
                    printCommands();
                    break;
                case "Time":
                case "time":
                case "T":
                case "t":
                    N.AskTime();
                    break;
                case "quit":
                case "Quit":
                case "Q":
                case "q":
                    quit = true;
                    break;
                case "buy":
                case "Buy":
                case "b":
                case "B":
                    if (validFlag(commandString,flag)){
                        switch (flag){
                            case "-N":
                            case "-n":
                                N.Buy(input, scnr);
                                break;
                            case "-S":
                            case "-s":
                                S.Buy(input, scnr);
                                break;
                            default:
                                System.out.println("Huh... IDK how this happened : 168");
                                System.out.println("\"" + flag + "\"");
                        }
                    }
                    break;
                case "Change":
                case "change":
                case "c":
                case "C":
                if (validFlag(commandString,flag)){
                    switch (flag){
                        case "-N":
                        case "-n":
                            N.ChangeSalesPerson();
                            break;
                        case "-S":
                        case "-s":
                            S.ChangeSalesPerson();
                            break;
                        default:
                            System.out.println("Huh... IDK how this happened : 147");
                            System.out.println("\"" + flag + "\"");
                    }
                }
                    break;
                case "Details":
                case "details":
                case "D":
                case "d":
                if (validFlag(commandString,flag)){
                    switch (flag){
                        case "-N":
                        case "-n":
                            N.GetInfo(input);
                            break;
                        case "-S":
                        case "-s":
                            S.GetInfo(input);
                            break;
                        default:
                            System.out.println("Huh... IDK how this happened : 168");
                            System.out.println("\"" + flag + "\"");
                    }
                }
                    break;
                case "Inventory":
                case "inventory":
                case "I":
                case "i":
                if (validFlag(commandString,flag)){
                    switch (flag){
                        case "-N":
                        case "-n":
                            N.PrintSellableInventory();
                            break;
                        case "-S":
                        case "-s":
                            S.PrintSellableInventory();
                            break;
                        default:
                            System.out.println("Huh... IDK how this happened : 171");
                            System.out.println("\"" + flag + "\"");
                    }
                }
                    break;
                case "Name":
                case "name":
                case "N":
                case "n":
                if (validFlag(commandString,flag)){
                    switch (flag){
                        case "-N":
                        case "-n":
                            N.AskName();
                            break;
                        case "-S":
                        case "-s":
                            S.AskName();
                            break;
                        default:
                            System.out.println("Huh... IDK how this happened : 178");
                            System.out.println("\"" + flag + "\"");
                    }
                }
                    break;
                default:
                    System.out.println("Sorry command:\"" + commandString + "\" with flag:\"" + flag + "\" is not a recognized command");
                    System.out.println("Type h for a list of commands");
            }

        }while (!quit);

        scnr.close();

        System.out.println("Exiting program");

    }

    public void printCommands(){
        System.out.println("---------------------------Commands----------------------------------------------------------------------");
        System.out.println("All of these commands can be instantiated with the full string or the first chatacter of the string.");
        System.out.println("The first character in either case can be either upper case or lower case");
        System.out.println("-----------------------------------------------");
        System.out.println("(H)elp                      : Print this Menu");
        System.out.println("(T)ime                      : Print the current system time");
        System.out.println("(Q)uit                      : Exit the program\n");

        System.out.println("(B)uy [N/S/n/s] <name>      : buy the vehicle <name> from the Northern/Southern FNCD");
        System.out.println("(C)hange  [-N/S/n/s]        : ask for a different sales person at FNCD Northern/Southern");
        System.out.println("(D)etails [-N/S/n/s] <name> : ask for the details of the vehicle <name> at FNCD Northern/Southern");
        System.out.println("(I)nventory [-N/S/n/s]      : print a list of possible cars to purchace at FNCD Northern/Southern");
        System.out.println("(N)ame [-N/S/n/s]           : Ask the salesperson at the Northern/Southern FNCD for their name");
    }

    public void notImplemented(){
        System.out.println("Sorry, this command has not been implemented yet.");
    }

    public boolean validFlag(String commandString, String flag){
        switch (flag){
            case "-n":
            case "-N":
            case "-S":
            case "-s":
                return true;
            case "":
                System.out.println("Sorry \"" + commandString + "\" should be followed by a FNCD location flag");
                break;
            default:
                System.out.println("Sorry \"" + flag + "\" is not a valid flag for \"" + commandString + "\"");
        }

        return false;
        
    }
}
