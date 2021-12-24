public class Food extends Consumable {

    public Food(String name, Core core)
    {
        super(core);
        itemType = "food";
        itemName = name;
        battleItem = false;
    }
}

class RoastedDeer extends Food
{
    public RoastedDeer(Core core)
    {
        super("Roasted Deer", core);
    }
}