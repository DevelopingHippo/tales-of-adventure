public abstract class Chest extends Armor {

    public Chest(String name, Core core)
    {
        super(core);
        itemType = "chest";
        itemName = name;
        battleItem = false;
    }
}


class LeatherVest extends Chest {
    public LeatherVest(Core core)
    {
        super("Leather Vest", core);
    }


}