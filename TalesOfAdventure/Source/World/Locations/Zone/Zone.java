public class Zone extends Location {
    protected Zone(String name, Core core) {super("zone", name, core);}

}

class Intro extends Zone
{
    public Intro(Core core) {super("Intro", core);}

    public void playIntro(Player PLAYER)
    {

        PLAYER.getClient().msgClient("Welcome To Tales of Adventure!");
        PLAYER.getClient().msgClient("This is a Text-Based RPG with Client -> Server Communication");
        PLAYER.getClient().msgClient("to allow for a connected World and Player Experience!");
        PLAYER.sleep(2000);

        PLAYER.getClient().msgClient("Your first lesson will be learning how to battle monsters.");
        PLAYER.sleep(2000);

        Skeleton skeleton = new Skeleton();
        PLAYER.startBattle(skeleton.battle(PLAYER));

        PLAYER.getClient().msgClient("Congrats! You have defeated your first Monster.");
        PLAYER.getClient().msgClient("However, now your health is low, and another Monster is on its way!");

        PLAYER.sleep(2000);

        PLAYER.getClient().msgClient("Quick, here's a HEALTH POTION, try and use it in the battle!");
        PLAYER.getPlayerInfo().addLootItem(new SmallHealthPotion());

        PLAYER.sleep(1000);

        Goblin goblin = new Goblin();
        PLAYER.startBattle(goblin.battle(PLAYER));


        PLAYER.getPlayerInfo().setWorldLocation("Old Riften");

    }

}


