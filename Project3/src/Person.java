public class Person {
    private static Integer ID = 0;
    protected String name = "";
    
    Person() {
        Helper h = new Helper();

        for(int i = 0; i < 3; i++){
            int ranInt = h.randInt(0, 5);

            ID = ID + ranInt;

            name = name + ID;
        }
    }

    public String GetName() {return name;}
}
