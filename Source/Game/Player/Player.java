public class Player {

    private final Core CORE;
    private final Client CLIENT;
    private final PlayerUtil utility = new PlayerUtil();

    private final String Username;
    private String CharacterName = "Not Loaded";
    private final PlayerInfo playerInfo = new PlayerInfo();
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
        CORE.GAME.startGame(this);
    }



    /*
    +------------------+
    | GETTER FUNCTIONS |
    +------------------+
    */
    public String getCharacterName(){return this.CharacterName;}
    public Client getClient(){return this.CLIENT;}
    public Core getCore(){return this.CORE;}
    public String getUsername(){return this.Username;}
    public PlayerInfo getPlayerInfo(){return this.playerInfo;}




    /*
    +---------------------------+
    | GENERAL PURPOSE FUNCTIONS |
    +---------------------------+
    */


}
