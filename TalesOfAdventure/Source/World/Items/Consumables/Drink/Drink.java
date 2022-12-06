public class Drink extends Consumable {

    public Drink(String name, Core core)
    {
        super(core);
        itemType = "drink";
        itemName = name;
        battleItem = false;
    }
}

class HoneyDew extends Drink
{
    public HoneyDew(Core core){
        super("Honey Dew", core);
    }
}