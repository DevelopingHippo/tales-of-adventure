public class Sword extends Weapon {

    public Sword(String name, Core core)
    {
        super(core);
        itemType = "sword";
        itemName = name;
    }

}


class LongSword extends Sword
{
    public LongSword(Core core)
    {
        super("Long Sword", core);
        dmg = 5;
    }
}