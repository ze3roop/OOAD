// a class of helper functions used throught the program
public class Helper {
    // random intiger with range pilled from https://www.geeksforgeeks.org/java-math-random-method-examples/
    public int randInt (int min, int max) {
        int range = max - min + 1;
        return (int)( Math.random() * range) + min;
    }

    public Double randDouble (Double min, Double max) {
        Double range = max - min + 1;
        return (Double) (Math.random() * range + min);
    }

    // returns true "passing" percent of the time else returns false
    public boolean percentChance (int passing) {
       return  randInt(1,100) <= passing;
    }

    // returns true if strings match
    public boolean compareString (String a, String b){
        a.toUpperCase();
        b.toUpperCase();

        return a == b;
    }
}
