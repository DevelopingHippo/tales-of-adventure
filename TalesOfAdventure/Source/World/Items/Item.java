public abstract class Item {

    public String itemType;
    public String itemName;
    public boolean battleItem;
    public final Core CORE;
    public final String uniqueID;

    public Item(Core core)
    {
        this.CORE = core;
        this.uniqueID = this.CORE.WORLD.cardinal.generateID("item");
        this.CORE.WORLD.itemsInWorld.put(this.uniqueID, this);
    }


    public abstract void useItem(Player PLAYER);
}
