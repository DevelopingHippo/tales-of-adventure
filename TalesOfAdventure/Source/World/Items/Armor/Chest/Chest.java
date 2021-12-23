public abstract class Chest extends Armor {

    public Chest(String name)
    {
        itemType = "chest";
        itemName = name;
        battleItem = false;
    }
}


class LeatherVest extends Chest {
    public LeatherVest()
    {
        super("Leather Vest");
    }


}