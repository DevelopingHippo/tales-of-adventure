public class Game {

    private final Core CORE;

    public Game(Core core)
    {
        this.CORE = core;
    }


    public void StartGame(Player PLAYER)
    {

        while(PLAYER.currentlyPlaying)
        {
            CORE.WORLD.getLocation(PLAYER.getPlayerInfo().getWorldLocation()).playerJoin(PLAYER);
        }

    }

    public void loadLocation(Player PLAYER)
    {
        CORE.WORLD.getLocation(PLAYER.getPlayerInfo().getWorldLocation()).playerJoin(PLAYER);
    }

}
