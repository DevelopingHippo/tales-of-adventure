public class Minion extends Creature implements MinionActions {
    protected Minion(String creaturename) {super("minion", creaturename);}



    @Override
    public void attack(Player PLAYER)
    {
        PLAYER.takeDamage(damage);
    }
}

class Skeleton extends Minion
{
    public Skeleton()
    {
        super("skeleton");
        health = 25;
        stamina = 10;
        damage = 5;
    }
}

class Goblin extends Minion
{
    public Goblin()
    {
        super("goblin");
        health = 35;
        stamina = 10;
        damage = 5;
    }
}