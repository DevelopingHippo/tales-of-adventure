public class PlayerInfo {

    private String Name = "Not Loaded";
    private int Level = 0;
    private int Exp = 0;
    private String worldLocation;


    private int health = 20;
    private int stamina = 20;

    //Item Containers


    /*
    +------------------+
    | GETTER FUNCTIONS |
    +------------------+
    */
    public String getName(){return this.Name;}
    public int getLevel(){return this.Level;}
    public int getExp(){return this.Exp;}
    public String getWorldLocation(){return this.worldLocation;}
    public int getHealth(){return this.health;}
    public int getStamina(){return this.stamina;}


    /*
    +------------------+
    | SETTER FUNCTIONS |
    +------------------+
    */
    public void setWorldLocation(String location){this.worldLocation = location;}
    public void setName(String characterName){this.Name = characterName;}
    public void addLevel(int levelIncrease){this.Level = this.Level + levelIncrease;}
    public void addExp(int expIncrease){this.Exp = this.Exp + expIncrease;}
    public void removeHealth(int damageTaken){this.health = this.health - damageTaken;}
    public void removeStamina(int staminaUsed){this.stamina = this.stamina - staminaUsed;}








}
