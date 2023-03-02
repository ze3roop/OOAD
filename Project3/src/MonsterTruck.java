import java.util.ArrayList;

public class MonsterTruck extends Vehicle {

    static ArrayList<String> stageNames = new ArrayList<String>();
    static ArrayList<String> usedStageNames = new ArrayList<String>();
    static int generation = 0;

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
            stageNames.add("Air Force AfterBurner");
            stageNames.add("Avenger");
            stageNames.add("Bad News Travels Fast");
            stageNames.add("Batman");
            stageNames.add("Backwards Bob");
            stageNames.add("Bearfoot");
            stageNames.add("Bigfoot");
            stageNames.add("Black Stallion");
            stageNames.add("Black Smith");
            stageNames.add("Blue Thunder");
            stageNames.add("Bounty Hunter");
            stageNames.add("Brutus");
            stageNames.add("BullDozer");
            stageNames.add("Captains Curse");
            stageNames.add("Cyborg");
            stageNames.add("El Toro Loco");
            stageNames.add("Grave Digger");
            stageNames.add("Grinder");
            stageNames.add("Gunslinger");
            stageNames.add("Jurassic Attack");
            stageNames.add("King Krunch");
            stageNames.add("Lucas Oil Crusaider");
            stageNames.add("Madusa");
            stageNames.add("Max-D");
            stageNames.add("Mohawk Warrior");
            stageNames.add("Monster Mutt");
            stageNames.add("Predator");
            stageNames.add("Shell Camino");
            stageNames.add("Raminator");
            stageNames.add("Snake Bite");
            stageNames.add("Stone Crusher");
            stageNames.add("Sudden Impact");
            stageNames.add("Swamp thing");
            stageNames.add("The Destroyer");
            stageNames.add("The Felon");
            stageNames.add("USA");
            stageNames.add("War Wizard");
            stageNames.add("Nitro Machine");
            stageNames.add("Zombie");
        }
		if (stageNames.isEmpty()){
            generation += 1;
            stageNames = usedStageNames;
            usedStageNames.clear();
        }
        
        int index = Helper.RandInt(0, stageNames.size() - 1);

        if (generation == 1){ name = stageNames.get(index);}
        else { name = stageNames.get(index) + "_" + generation; }

        usedStageNames.add(stageNames.get(index));
        stageNames.remove(index);
    }
}
