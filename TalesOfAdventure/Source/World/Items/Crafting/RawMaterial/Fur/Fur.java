public abstract class Fur extends Item {
    public Fur(String name, Core core)
    {
        super(core);
        itemType = "fur";
        itemName = name;
    }

    @Override
    public void useItem(Player PLAYER)
    {

    }

}

class WolfFur extends Fur
{
    public WolfFur(Core core)
    {
        super("Wolf Fur", core);
    }
}