public class Town extends Location {

    protected Town(String name, Core core)
    {
        super("town", name, core);
    }
}

class OldRiften extends Town
{
    public OldRiften(Core CORE)
    {
        super("Old Riften", CORE);
    }

    public void playOldRiften(Player PLAYER)
    {

    }
}