public class Dungeon extends Location {

    public Dungeon(String name, Core core)
    {
        locationType = "dungeon";
        locationName = name;
        CORE = core;
    }
}

class OldCrypt extends Dungeon
{
    public OldCrypt(Core core)
    {
        super("Old Crypt", core);
    }
}