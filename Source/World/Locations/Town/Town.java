public class Town extends Location {

    public Town(String name, Core core)
    {
        locationType = "town";
        locationName = name;
        CORE = core;
    }
}

class OldRiften extends Town
{
    public OldRiften(Core CORE)
    {
        super("Old Riften", CORE);
    }
}