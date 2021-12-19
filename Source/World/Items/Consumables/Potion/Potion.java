public class Potion extends Item implements Consumable {

    public Potion(String name)
    {
        itemType = "potion";
        itemName = name;
    }

}

class SmallHealthPotion extends Potion
{
    public SmallHealthPotion()
    {
        super("Small Health Potion");
    }
}