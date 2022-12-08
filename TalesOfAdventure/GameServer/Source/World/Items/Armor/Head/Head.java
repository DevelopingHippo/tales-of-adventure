public class Head extends Armor {

    public Head(String name, Core core)
    {
        super(core);
        itemType = "head";
        itemName = name;
        battleItem = false;
    }
}


class LeatherHelmet extends Head {
    public LeatherHelmet(Core core)
    {
        super("Leather Helmet", core);
    }
}