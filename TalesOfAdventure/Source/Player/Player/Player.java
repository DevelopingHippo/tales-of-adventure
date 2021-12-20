public class Player {

    private final Core CORE;
    private final Client CLIENT;
    private final PlayerUtil utility = new PlayerUtil();

    private final String Username;
    private final PlayerInfo playerInfo = new PlayerInfo();
    private final PlayerAction playerAction = new PlayerAction(this);
    private final PlayerSkills playerSkills = new PlayerSkills(this);








    /*
    +----------------------------+
    | START / CREATION FUNCTIONS |
    +----------------------------+
    */
    public Player(Core core, Client client, String username)
    {
        this.CORE = core;
        this.CLIENT = client;
        this.Username = username;
    }
    public void startPlayer()
    {
        utility.loadCharacter(this);

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
    public PlayerAction getPlayerAction(){return this.playerAction;}


    /*
    +---------------------------+
    | GENERAL PURPOSE FUNCTIONS |
    +---------------------------+
    */
    public void takeDamage(int damageReceived)
    {
        this.playerInfo.removeHealth(damageReceived);
    }
    public void useStamina(int staminaUsed)
    {
        this.playerInfo.removeStamina(staminaUsed);
    }



}
