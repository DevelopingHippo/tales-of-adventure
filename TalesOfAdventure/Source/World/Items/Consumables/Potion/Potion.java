public class Potion extends Consumable {

    public Potion(String name) {
        itemType = "potion";
        itemName = name;
        battleItem = true;
    }
}

class SmallHealthPotion extends Potion
{
    public SmallHealthPotion()
    {
        super("Small Health Potion");
        ishealthReplenish = true;
        healthReplenish = 10;
    }
}

class SmallStaminaPotion extends Potion
{
    public SmallStaminaPotion()
    {
        super("Small Stamina Potion");
        isstaminaReplenish = true;
        staminaReplenish = 10;
    }
}