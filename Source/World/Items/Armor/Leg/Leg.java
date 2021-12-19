public class Leg extends Item implements Armor {

    public Leg(String name)
    {
        itemType = "leg";
        itemName = name;
    }
}

class LeatherPants extends Leg
{
    public LeatherPants()
    {
        super("Leather Pants");
    }
}