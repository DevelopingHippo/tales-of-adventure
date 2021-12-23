import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Creature implements AnimalActions {
    protected Animal(String creaturename) {super("animal", creaturename);}


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
    public Rabbit()
    {
        super("rabbit");
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
            creatureLoot.add(new RawRabbit());
        }
    }
}




class Wolf extends Animal
{
    public Wolf()
    {
        super("wolf");
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
            creatureLoot.add(new WolfFur());
        }
    }
}




class Deer extends Animal
{
    public Deer()
    {
        super("deer");
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
            creatureLoot.add(new RawVenison());
        }
    }
}