public class Chest extends Item implements Armor {

    public Chest(String name)
    {
        itemType = "chest";
        itemName = name;
    }
}


class LeatherVest extends Chest {
    public LeatherVest()
    {
        super("Leather Vest");
    }
}