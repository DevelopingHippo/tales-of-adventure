public class Head extends Item implements Armor {

    public Head(String name)
    {
        itemType = "head";
        itemName = name;
    }
}


class LeatherHelmet extends Head {
    public LeatherHelmet()
    {
        super("Leather Helmet");
    }
}