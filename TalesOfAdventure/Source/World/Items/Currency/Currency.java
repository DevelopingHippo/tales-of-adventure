public abstract class Currency extends Item {
    public Currency(String name, Core core)
    {
        super(core);
        itemType = "currency";
        itemName = name;
    }

    @Override
    public void useItem(Player PLAYER)
    {

    }

}


class GoldCoins extends Currency
{

    int goldAmount;

    public GoldCoins(int coinAmount, Core core)
    {
        super("Gold Coins", core);
        goldAmount = coinAmount;
    }

}
