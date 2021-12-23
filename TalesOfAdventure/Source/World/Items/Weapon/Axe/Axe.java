public class Axe extends Weapon {
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
        dmg = 5;
    }
}
