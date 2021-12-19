public class Wild extends Location {

    public Wild(String name, Core core)
    {
        locationType = "wild";
        locationName = name;
        CORE = core;
    }
}

class Forest extends Wild
{
    public Forest(Core core)
    {
        super("Forest", core);
    }
}