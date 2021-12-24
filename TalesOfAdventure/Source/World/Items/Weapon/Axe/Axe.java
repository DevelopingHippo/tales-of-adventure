public class Axe extends Weapon {
    public Axe(String name, Core core)
    {
        super(core);
        itemType = "axe";
        itemName = name;
    }
}


class BronzeAxe extends Axe {
    public BronzeAxe(Core core)
    {
        super("Bronze Axe", core);
        dmg = 5;
    }
}
