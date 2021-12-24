public class Potion extends Consumable {

    public Potion(String name, Core core) {
        super(core);
        itemType = "potion";
        itemName = name;
        battleItem = true;
    }
}

class SmallHealthPotion extends Potion
{
    public SmallHealthPotion(Core core)
    {
        super("Small Health Potion", core);
        ishealthReplenish = true;
        healthReplenish = 10;
    }
}

class SmallStaminaPotion extends Potion
{
    public SmallStaminaPotion(Core core)
    {
        super("Small Stamina Potion", core);
        isstaminaReplenish = true;
        staminaReplenish = 10;
    }
}