import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Creature {
    protected Animal(String creaturename, Core core) {super("animal", creaturename, core);}


    @Override
    public void attack(Player PLAYER)
    {
        PLAYER.takeDamage(damage);
    }
    @Override
    public abstract void createCreatureLoot();
}





class Rabbit extends Animal
{
    public Rabbit(Area area, Core core)
    {
        super("rabbit", core);
        inArea = area;
        health = 10;
        stamina = 15;
        damage = 2;
        exp = 4;
    }

    @Override
    public void createCreatureLoot()
    {
        for(int i = 0; i < ThreadLocalRandom.current().nextInt(0, 3); i++)
        {
            creatureLoot.add(new RawRabbit(CORE));
        }
    }
}




class Wolf extends Animal
{
    public Wolf(Area area, Core core)
    {
        super("wolf", core);
        inArea = area;
        health = 10;
        stamina = 15;
        damage = 5;
        exp = 10;
    }

    @Override
    public void createCreatureLoot()
    {
        for(int i = 0; i < ThreadLocalRandom.current().nextInt(0, 3); i++)
        {
            creatureLoot.add(new WolfFur(CORE));
        }
    }
}




class Deer extends Animal
{
    public Deer(Area area, Core core)
    {
        super("deer", core);
        inArea = area;
        health = 5;
        stamina = 20;
        damage = 3;
        exp = 5;
    }

    @Override
    public void createCreatureLoot()
    {
        for(int i = 0; i < ThreadLocalRandom.current().nextInt(0, 3); i++)
        {
            creatureLoot.add(new RawVenison(CORE));
        }
    }
}