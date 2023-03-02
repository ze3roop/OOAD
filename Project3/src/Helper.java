import java.util.Random;

public class Helper {
    static public int uniqueID = 0;
    static public enum Names_of_Day {Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday}
    static public Names_of_Day[] Week = {Names_of_Day.Sunday, Names_of_Day.Monday, Names_of_Day.Tuesday, Names_of_Day.Wednesday, Names_of_Day.Thursday, Names_of_Day.Friday, Names_of_Day.Saturday};

    // returns random int between min and max (inclusive)
    static int RandInt(int min, int max){
        Random rn = new Random();
        return rn.nextInt(max - min + 1) + min;
    }

    // returns random double between min and max (inclusive)
    static double RandDouble(double min, double max){
        Random rn = new Random();
        return rn.nextDouble(max - min + 1) + min;
    }

    // retuns random intiger folloing clipped gausian deviation based on given range(inclusive), standard deviation, and mean
    // source: https://stackoverflow.com/questions/58453886/is-there-a-way-in-java-to-generate-random-numbers-following-fixed-mean-and-stand    

    public static double randDouble_Gaus(double min, double max, double mean, double standard_deveation) {
        Random rand = new Random();
        double randomValue;

        do {
            randomValue = rand.nextGaussian() * standard_deveation + mean;
        } while (randomValue <= min && randomValue >= max); // run this loop again if this falls outside of the range

        return randomValue;
    }

    // returns true chance_to_pass% of the time
    static boolean PercentChance(int chance_to_pass){
        return RandInt(1, 100) <= chance_to_pass;
    }

    //generates a unique intiger ID every time it is caled
    static int GenerateUniqueID () {
        uniqueID += RandInt(1, 9);
        return uniqueID;
    }
}