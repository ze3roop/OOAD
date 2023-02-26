import java.util.Random;

public class Helper {
    static int uniqueID = 0;
    static int RandInt(int min, int max){
        Random rn = new Random();
        return rn. nextInt(max - min + 1) + min;
    }
    static boolean PercentChance(int chance_to_pass){
        return RandInt(1, 100) <= chance_to_pass;
    }
    static int GenerateUniqueID () {
        uniqueID += RandInt(1, 9);
        return uniqueID;
    }
}