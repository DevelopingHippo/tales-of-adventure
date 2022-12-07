public class Bow extends Weapon {

    public Bow(String name, Core core)
    {
        super(core);
        itemType = "bow";
        itemName = name;
    }

}

class LongBow extends Bow
{
    public LongBow(Core core)
    {
        super("Long Bow", core);
        dmg = 5;
    }
}