public class Staff extends Weapon {

    public Staff(String name){
        itemType = "staff";
        itemName = name;
    }

}






class FireStaff extends Staff {

    public FireStaff() {
        super("Fire Staff");
        dmg = 5;
    }
}