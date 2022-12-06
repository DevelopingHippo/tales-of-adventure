public class Dungeon extends Location {
    protected Dungeon(String name, Core core) {super("dungeon", name, core);}

}

class OldCrypt extends Dungeon
{
    public OldCrypt(Core core) {super("Old Crypt", core);}
    public void playOldCrypt(Player PLAYER)
    {

    }
}