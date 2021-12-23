public class Bow extends Weapon {

    public Bow(String name)
    {
        itemType = "bow";
        itemName = name;
    }

}

class LongBow extends Bow
{
    public LongBow()
    {
        super("Long Bow");
        dmg = 5;
    }
}