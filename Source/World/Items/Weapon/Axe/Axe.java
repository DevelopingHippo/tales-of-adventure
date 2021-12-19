public class Axe extends Item implements Weapon {
    public Axe(String name)
    {
        itemType = "axe";
        itemName = name;
    }
}


class BronzeAxe extends Axe {
    public BronzeAxe()
    {
        super("Bronze Axe");
    }
}
