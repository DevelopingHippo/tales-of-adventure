public class Animal extends Creature implements AnimalActions {
    protected Animal(String creaturename) {super("animal", creaturename);}

    @Override
    public void attack(Player PLAYER)
    {
        PLAYER.takeDamage(damage);
    }
}


class Rabbit extends Animal
{
    public Rabbit()
    {
        super("rabbit");
        health = 10;
        stamina = 15;
    }

}

class Wolf extends Animal
{
    public Wolf()
    {
        super("wolf");
        health = 10;
        stamina = 15;
    }

}

class Deer extends Animal
{
    public Deer()
    {
        super("deer");
        health = 5;
        stamina = 20;
    }

}