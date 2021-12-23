public abstract class Consumable extends Item {


    public boolean ishealthReplenish;
    public int healthReplenish;

    public boolean isstaminaReplenish;
    public int staminaReplenish;

    public boolean isHealthBoost;
    public int healthBoost;

    public boolean isstaminaBoost;
    public int staminaBoost;




    @Override
    public void useItem(Player PLAYER)
    {
        PLAYER.getClient().msgClient("Using " + itemName);

        if(ishealthReplenish)
        {
            PLAYER.getPlayerInfo().addHealth(healthReplenish);
        }
        if(isstaminaReplenish)
        {
            PLAYER.getPlayerInfo().addStamina(staminaReplenish);
        }
    }

}
