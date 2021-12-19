import java.util.Objects;

public class Player {

    private final Core CORE;
    private final Client CLIENT;
    private final PlayerUtil utility = new PlayerUtil();

    private final String Username;
    private final PlayerInfo playerInfo = new PlayerInfo();
    private final PlayerSkills playerSkills = new PlayerSkills(this);

    /*
    +----------------------------+
    | START / CREATION FUNCTIONS |
    +----------------------------+
    */
    public Player(Core core, Client client, String username)
    {
        CORE = core;
        CLIENT = client;
        Username = username;
    }
    public void startPlayer()
    {
        utility.loadCharacter(this);

        System.out.println(playerInfo.getName());
        System.out.println(playerInfo.getLevel());
        System.out.println(playerInfo.getExp());
        System.out.println(playerInfo.getWorldLocation());

        if(playerInfo.getWorldLocation().equalsIgnoreCase("Intro"))
        {
            CORE.GAME.playIntro(this);
        }
        CORE.GAME.loadLocation(this);
    }



    /*
    +------------------+
    | GETTER FUNCTIONS |
    +------------------+
    */
    public Client getClient(){return this.CLIENT;}
    public Core getCore(){return this.CORE;}
    public String getUsername(){return this.Username;}
    public PlayerInfo getPlayerInfo(){return this.playerInfo;}
    public PlayerSkills getPlayerSkills(){return this.playerSkills;}



    /*
    +---------------------------+
    | GENERAL PURPOSE FUNCTIONS |
    +---------------------------+
    */


}
