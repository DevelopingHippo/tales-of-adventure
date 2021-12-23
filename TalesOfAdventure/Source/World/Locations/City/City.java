public class City extends Location {
    protected City(String name, Core core){super("city", name, core);}

}

class HighHrothgar extends City implements Runnable
{
    public HighHrothgar(Core core) {super("High Hrothgar", core);}
    @Override
    public void run()
    {
        manageHHrothgar();
    }

    private void manageHHrothgar()
    {

    }




    public void playHighHrothgar(Player PLAYER)
    {

    }


}