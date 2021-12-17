public class Game {

    private Core CORE;

    public Game(Core core)
    {
        this.CORE = core;
    }



    public void startGame(Player PLAYER){

        PLAYER.getClient().msgClient("Name: " + PLAYER.getPlayerInfo().getName());
        PLAYER.getClient().msgClient("Level: " + PLAYER.getPlayerInfo().getLevel());
        PLAYER.getClient().msgClient("Exp: " + PLAYER.getPlayerInfo().getExp());


        while(true)
        {
            String input = PLAYER.getClient().getStringInput();
            if(input.equalsIgnoreCase("quit"))
            {
                break;
            }
            else
            {
                System.out.println(input);
            }
        }

    }



}
