public class Dev extends Location
{

    protected Dev(Core core)
    {
        super("Dev", "Dev", core);
        Temp temp = new Temp(this, core);
        core.WORLD.nameToArea.put(temp.areaName, temp);
    }
}


class Temp extends Area
{

    public Temp(Location parentLocation, Core core) {
        super("temp", parentLocation, core);
    }

    @Override
    public void startArea(Player PLAYER)
    {

    }
}
