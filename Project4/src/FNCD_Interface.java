import java.util.ArrayList;
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
                arr.get(0).Ending();
                System.out.println("-----------------" + FNCD_Name.get(1) + "-----------------");
                arr.get(1).Ending();
        }

        System.out.println("=====================================================");
		System.out.println("================= End of Simulation =================");
		System.out.println("=====================================================");

		try {
			// Reset System.out to print to terminal
			System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
		}
		catch (Exception e) {
			e.printStackTrace();
        }


        int choice = 0;
        do{

            System.out.print(">");

        }while (choice != 0);

        System.out.println("Exiting program");

    }
}
