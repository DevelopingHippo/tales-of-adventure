public abstract class Boss extends Creature implements BossActions {

    protected Boss(String creaturename) {super("boss", creaturename);}

    @Override
    public void attack(Player PLAYER) {
        PLAYER.takeDamage(damage);
    }
}


class GaryTheSkeletonKnight extends Boss
{
    public GaryTheSkeletonKnight()
    {
        super("garytheskeletonknight");
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
