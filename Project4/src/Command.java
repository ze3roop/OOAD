// https://www.geeksforgeeks.org/command-pattern/
// An interface for command
interface Command
{
    public void execute();
}

class PrintInventory implements Command {
    FNCD dealership;

    PrintInventory(FNCD d){
        dealership = d;
    }

    @Override
    public void execute() {
        dealership.PrintInventory(null);
    }
}