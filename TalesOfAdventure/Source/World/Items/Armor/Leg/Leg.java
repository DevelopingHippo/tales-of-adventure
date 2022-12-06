public class Leg extends Armor {

    public Leg(String name, Core core)
    {
        super(core);
        itemType = "leg";
        itemName = name;
        battleItem = false;
    }
}

class LeatherPants extends Leg
{
    public LeatherPants(Core core)
    {
        super("Leather Pants", core);
    }
}