public class Shield extends Weapon {

    public Shield(String name, Core core){
        super(core);
        itemType = "staff";
        itemName = name;
    }

}






class WoodenShield extends Shield {

    public WoodenShield(Core core) {
        super("Wooden Shield", core);
        dmg = 2;
    }
}