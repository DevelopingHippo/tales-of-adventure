public class Leg extends Armor {

    public Leg(String name)
    {
        itemType = "leg";
        itemName = name;
        battleItem = false;
    }
}

class LeatherPants extends Leg
{
    public LeatherPants()
    {
        super("Leather Pants");
    }
}