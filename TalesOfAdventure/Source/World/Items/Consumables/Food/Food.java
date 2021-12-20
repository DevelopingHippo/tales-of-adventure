public class Food extends Item implements Consumable {

    public Food(String name)
    {
        itemType = "food";
        itemName = name;
    }

}

class RoastedDeer extends Food
{
    public RoastedDeer()
    {
        super("Roasted Deer");
    }
}