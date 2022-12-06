import java.util.concurrent.ThreadLocalRandom;

public abstract class Minion extends Creature {
    protected Minion(String creaturename, Core core) {
        super("minion", creaturename, core);
    }


    @Override
    public void attack(Player PLAYER)
    {
        PLAYER.takeDamage(ThreadLocalRandom.current().nextInt((damage / 2), damage));
    }
}

class Skeleton extends Minion
{
    public Skeleton(Area area, Core core)
    {
        super("Skeleton", core);
        inArea = area;
        health = 25;
        stamina = 10;
        damage = 5;
        exp = 10;
    }

    @Override
    public void createCreatureLoot()
    {
        creatureLoot.add(new GoldCoins(ThreadLocalRandom.current().nextInt(0, 10), CORE));
    }
}

class Goblin extends Minion
{
    public Goblin(Area area, Core core)
    {
        super("Goblin", core);
        inArea = area;
        health = 35;
        stamina = 10;
        damage = 5;
        exp = 15;
    }

    @Override
    public void createCreatureLoot()
    {
        creatureLoot.add(new GoldCoins(ThreadLocalRandom.current().nextInt(0, 10), CORE));
    }
}