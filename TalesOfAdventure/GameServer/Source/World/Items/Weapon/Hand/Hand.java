public abstract class Hand extends Weapon{

    public Hand(Core core)
    {
        super(core);
        itemType = "Hand";
    }

}



class Fist extends Hand
{

    public Fist(Core core)
    {
        super(core);
        itemName = "Fists";
        dmg = 5;
    }


}

