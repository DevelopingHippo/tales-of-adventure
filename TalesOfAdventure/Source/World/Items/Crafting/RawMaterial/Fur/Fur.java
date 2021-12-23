public abstract class Fur extends Item {
    public Fur(String name)
    {
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
    public WolfFur()
    {
        super("Wolf Fur");
    }
}