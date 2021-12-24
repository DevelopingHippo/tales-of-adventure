public abstract class Boss extends Creature {

    protected Boss(String creaturename, Core core) {super("boss", creaturename, core);}

    @Override
    public void attack(Player PLAYER) {
        PLAYER.takeDamage(damage);
    }
}


class GaryTheSkeletonKnight extends Boss
{
    public GaryTheSkeletonKnight(Area area, Core core)
    {
        super("garytheskeletonknight", core);
        inArea = area;
        health = 75;
        stamina = 45;
        damage = 25;
        exp = 150;
    }

    @Override
    public void createCreatureLoot()
    {

    }
}
