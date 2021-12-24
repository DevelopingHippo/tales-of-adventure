public class Player {

    private final Core CORE;
    private final Client CLIENT;
    private final PlayerUtil utility = new PlayerUtil();

    private final String Username;
    private final PlayerInfo playerInfo = new PlayerInfo();
    private final PlayerAction playerAction = new PlayerAction(this);
    private final PlayerSkills playerSkills = new PlayerSkills(this);

    private Battle activeBattle;
    private boolean inBattle = false;
    public boolean currentlyPlaying = true;




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
        playerInfo.setCurrentWeapon(new Fist(CORE));
        utility.loadCharacter(this);
        CORE.WORLD.playersInWorld.add(this);
        CORE.WORLD.playerLoadedArea.put(this, null);
        CORE.WORLD.playerLoadedLocation.put(this, null);
        playerInfo.setWorldLocation("Old Riften");
        CORE.GAME.StartGame(this);
    }


    public void printNext()
    {
        CLIENT.getStringInput();
    }
    public void sleep(int time)
    {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
    public Battle getActiveBattle(){return this.activeBattle;}

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


    public void useItem(Item item)
    {
        playerInfo.getPlayerLoot().remove(item);
    }


    public void startBattle(Battle currentBattle)
    {
        this.inBattle = true;
        this.activeBattle = currentBattle;
        while(true)
        {
            if(!this.activeBattle.getBattleState() || !this.inBattle)
            {
                break;
            }
            else
            {
                sleep(1000);
            }
        }
        this.activeBattle = null;

        if(playerInfo.getHealth() <= 0)
        {
            die();
        }
    }

    public void leaveBattle()
    {
        this.inBattle = false;
    }

    public void die()
    {
        CLIENT.alertClient("YOU HAVE DIED!");
        sleep(5000);
        playerInfo.respawnPlayer();
        playerInfo.setWorldLocation("Intro");
        CORE.GAME.loadLocation(this);
    }


    public void interact(Player player)
    {
        player.getClient().msgClient(this.getPlayerInfo().getName() + " | HP:" + this.playerInfo.getHealth() + " | LEVEL:" + this.playerInfo.getLevel());
        // MUCH MORE TO DO, THIS IS FOR WHEN A PLAYER INTERACTS WITH ANOTHER PLAYER IN AN AREA
    }
}
