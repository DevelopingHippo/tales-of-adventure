public class Wild extends Location {

    protected Wild(String name, Core core)
    {
        super("wild", name, core);
    }
}

class Forest extends Wild implements Runnable
{
    public Forest(Core core)
    {
        super("Forest", core);
    }
    @Override
    public void run()
    {
        manageForest();
    }

    private void manageForest()
    {

    }

    public void playForest(Player PLAYER)
    {

    }


}