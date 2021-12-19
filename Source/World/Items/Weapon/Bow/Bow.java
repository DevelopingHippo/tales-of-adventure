public class Bow extends Item implements Weapon {

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
    }
}