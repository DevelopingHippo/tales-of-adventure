public class Zone extends Location {
    protected Zone(String name, Core core) {super("zone", name, core);}

}

class Intro extends Zone
{
    public Intro(Core core) {super("Intro", core);}

    public void playIntro(Player PLAYER)
    {
        Skeleton skeleton1 = new Skeleton();

        skeleton1.battle(PLAYER);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Skeleton skeleton2 = new Skeleton();
        Goblin goblin = new Goblin();
        Rabbit rabbit = new Rabbit();
        Wolf wolf = new Wolf();

        skeleton1.joinBattle(goblin);
        skeleton1.joinBattle(skeleton2);
        skeleton1.joinBattle(rabbit);
        skeleton1.joinBattle(wolf);


        while(true)
        {

        }
    }

}


