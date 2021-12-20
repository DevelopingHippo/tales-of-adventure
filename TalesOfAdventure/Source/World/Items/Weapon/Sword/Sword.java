public class Sword extends Item implements Weapon {

    public Sword(String name)
    {
        itemType = "sword";
        itemName = name;
    }

}


class LongSword extends Sword
{
    public LongSword()
    {
        super("Long Sword");
    }
}