public class Transformer extends Vehicle{
    private static int nameID = 0;

    Transformer(){
		super();

		name = "Transformer_" + GenerateName();
		
		cost = Helper.RandDouble(1000000, 1000000000);
		DetermineCost();
		salesPrice = cost * 2.0;

        
        vehicleType = Types_of_Vehicles.transformer;

        while (Faction == Transformer_Faction.NILL){ 
            Faction = Transformer_Faction.values()[Helper.RandInt(0, Transformer_Faction.values().length - 1)];
        }
    }

    private String GenerateName(){
        String ret = ListOfNames[nameID];
        nameID++;

        int numNames = ListOfNames.length;

        if (nameID == numNames){
            nameID = 0;
            for (int i = 0; i < numNames; i++){
                ListOfNames[i] += "I";
            }
        }
        return ret;
    }

    private static String[] ListOfNames = {
        "BumbleBee",
        "Optimus_Prime",
        "Megatron",
        "Starscream",
        "Grimlock",
        "Thundercracker",
        "Rodimus_Prime",
        "Arcee",
        "Jazz",
        "Ratchet",
        "Bonecrusher",
        "Wheeljack",
        "Grapple",
        "Bombshell",
        "Crunk",
        "Thrust"
    };

}
