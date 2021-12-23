public class Food extends Consumable {

    public Food(String name)
    {
        itemType = "food";
        itemName = name;
        battleItem = false;
    }
}

class RoastedDeer extends Food
{
    public RoastedDeer()
    {
        super("Roasted Deer");
    }
}