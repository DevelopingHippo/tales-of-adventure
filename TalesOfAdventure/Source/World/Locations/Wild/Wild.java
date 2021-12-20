public class Wild extends Location {

    protected Wild(String name, Core core)
    {
        super("wild", name, core);
    }
}

class Forest extends Wild
{
    public Forest(Core core)
    {
        super("Forest", core);
    }

    public void playForest(Player PLAYER)
    {

    }

}