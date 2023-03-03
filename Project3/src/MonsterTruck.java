import java.util.ArrayList;

public class MonsterTruck extends Vehicle {

    static private ArrayList<String> stageNames = new ArrayList<String>();
    static private ArrayList<String> usedStageNames = new ArrayList<String>();
    static private int generation = 0;

    MonsterTruck() {
        super(); // invoking base-class Staff constructor.
		vehicleType = Types_of_Vehicles.monsterTruck; 

        GenerateName();
        
        cost = Helper.RandDouble(40000, 60000);
        DetermineCost();
        salesPrice = cost * 2.0;
    }


    // private functions

    private void GenerateName() {
        if (generation == 0){
            generation ++;
            stageNames.add("Air_Force_AfterBurner");
            stageNames.add("Avenger");
            stageNames.add("Bad_News_Travels_Fast");
            stageNames.add("Batman");
            stageNames.add("Backwards_Bob");
            stageNames.add("Bearfoot");
            stageNames.add("Bigfoot");
            stageNames.add("Black_Stallion");
            stageNames.add("Black_Smith");
            stageNames.add("Blue_Thunder");
            stageNames.add("Bounty_Hunter");
            stageNames.add("Brutus");
            stageNames.add("BullDozer");
            stageNames.add("Captains_Curse");
            stageNames.add("Cyborg");
            stageNames.add("El_Toro_Loco");
            stageNames.add("Grave_Digger");
            stageNames.add("Grinder");
            stageNames.add("Gunslinger");
            stageNames.add("Jurassic_Attack");
            stageNames.add("King_Krunch");
            stageNames.add("Lucas_Oil_Crusaider");
            stageNames.add("Madusa");
            stageNames.add("Max-D");
            stageNames.add("Mohawk_Warrior");
            stageNames.add("Monster_Mutt");
            stageNames.add("Predator");
            stageNames.add("Shell_Camino");
            stageNames.add("Raminator");
            stageNames.add("Snake_Bite");
            stageNames.add("Stone_Crusher");
            stageNames.add("Sudden_Impact");
            stageNames.add("Swamp_thing");
            stageNames.add("The_Destroyer");
            stageNames.add("The_Felon");
            stageNames.add("USA");
            stageNames.add("War_Wizard");
            stageNames.add("Nitro_Machine");
            stageNames.add("Zombie");
        }
		
        if (stageNames.isEmpty()){
            generation += 1;
            while(!usedStageNames.isEmpty()){
                stageNames.add(usedStageNames.get(0));
                usedStageNames.remove(0);
            }
            usedStageNames.clear();
        }
        
        int index = Helper.RandInt(0, stageNames.size() - 1);

        if (generation == 1){ name = "MonsterTruck_" + stageNames.get(index);}
        else { name = "MonsterTruck_" +  stageNames.get(index) + "_" + generation; }

        usedStageNames.add(stageNames.get(index));
        stageNames.remove(index);
    }
}
