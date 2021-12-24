public abstract class RawMeat extends Cooking {

    public RawMeat(String name, Core core)
    {
        super(core);
        itemType = "meat";
        itemName = name;
    }



}


class RawRabbit extends RawMeat {
    public RawRabbit(Core core) {
        super("Raw Rabbit", core);
    }
}


class RawVenison extends RawMeat {
    public RawVenison(Core core) {
        super("Raw Venison", core);
    }

}
