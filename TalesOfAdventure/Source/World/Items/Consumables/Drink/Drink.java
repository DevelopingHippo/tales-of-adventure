public class Drink extends Item implements Consumable {

    public Drink(String name)
    {
        itemType = "drink";
        itemName = name;
    }

}

class HoneyDew extends Drink
{
    public HoneyDew(){
        super("Honey Dew");
    }
}