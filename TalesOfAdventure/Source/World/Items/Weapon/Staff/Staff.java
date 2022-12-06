public class Staff extends Weapon {

    public Staff(String name, Core core){
        super(core);
        itemType = "staff";
        itemName = name;
    }

}






class FireStaff extends Staff {

    public FireStaff(Core core) {
        super("Fire Staff", core);
        dmg = 5;
    }
}