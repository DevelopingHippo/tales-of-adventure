public class Drink extends Consumable {

    public Drink(String name)
    {
        itemType = "drink";
        itemName = name;
        battleItem = false;
    }
}

class HoneyDew extends Drink
{
    public HoneyDew(){
        super("Honey Dew");
    }
}