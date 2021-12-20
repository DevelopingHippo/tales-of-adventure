public class Game {

    private Core CORE;

    public Game(Core core)
    {
        this.CORE = core;
    }


    public void playIntro(Player PLAYER)
    {
        PLAYER.getClient().msgClient("Welcome To Tales of Adventure!");
        PLAYER.getClient().msgClient("This is a Text-Based RPG with Client -> Server Communication");
        PLAYER.getClient().msgClient("to allow for a connected World and Player Experience!");
    }
    public void loadLocation(Player PLAYER)
    {
        CORE.WORLD.getLocation(PLAYER.getPlayerInfo().getWorldLocation()).playerJoin(PLAYER);
    }

}
