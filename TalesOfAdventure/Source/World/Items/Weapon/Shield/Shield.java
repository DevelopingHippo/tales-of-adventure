public class Shield extends Weapon {

    public Shield(String name){
        itemType = "staff";
        itemName = name;
    }

}






class WoodenShield extends Shield {

    public WoodenShield() {
        super("Wooden Shield");
        dmg = 2;
    }
}