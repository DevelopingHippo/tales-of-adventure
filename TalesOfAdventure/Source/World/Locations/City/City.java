public class City extends Location {
    protected City(String name, Core core){super("city", name, core);}

}

class HighHrothgar extends City
{
    public HighHrothgar(Core core) {super("High Hrothgar", core);}

    public void playHighHrothgar(Player PLAYER)
    {

    }
}