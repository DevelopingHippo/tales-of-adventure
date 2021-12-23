public abstract class RawMeat extends Cooking {

    public RawMeat(String name)
    {
        itemType = "meat";
        itemName = name;
    }



}


class RawRabbit extends RawMeat {
    public RawRabbit() {
        super("Raw Rabbit");
    }
}


class RawVenison extends RawMeat {
    public RawVenison() {
        super("Raw Venison");
    }

}
