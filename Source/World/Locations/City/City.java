public class City extends Location {

    public City(String name, Core core)
    {
        locationType = "city";
        locationName = name;
        CORE = core;
    }
}

class HighHrothgar extends City
{
    public HighHrothgar(Core core)
    {
        super("High Hrothgar", core);
    }
}
