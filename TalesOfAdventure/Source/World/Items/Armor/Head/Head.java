public class Head extends Armor {

    public Head(String name)
    {
        itemType = "head";
        itemName = name;
        battleItem = false;
    }
}


class LeatherHelmet extends Head {
    public LeatherHelmet()
    {
        super("Leather Helmet");
    }
}