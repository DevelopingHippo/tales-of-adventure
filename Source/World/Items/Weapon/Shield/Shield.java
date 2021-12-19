public class Shield extends Item implements Weapon {

    public Shield(String name){
        itemType = "staff";
        itemName = name;
    }

}






class WoodenShield extends Staff {

    public WoodenShield() {
        super("Wooden Shield");
    }
}