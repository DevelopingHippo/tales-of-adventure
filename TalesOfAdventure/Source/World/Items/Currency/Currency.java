public abstract class Currency extends Item {
    public Currency(String name)
    {
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

    public GoldCoins(int coinAmount)
    {
        super("Gold Coins");
        goldAmount = coinAmount;
    }

}
