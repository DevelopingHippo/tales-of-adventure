import java.util.ArrayList;

public class PlayerInfo {
    // Player Important Stats
    private String Name = "Not Loaded";
    private int Level = 0;
    private int Exp = 0;
    private String worldLocation;
    private Location loadedLocation;
    private Area loadedArea;
    // Player Stats
    private int health = 50;
    private int stamina = 20;
    private int maxHealth = 50;
    private int maxStamina = 20;


    // Item Containers
    private int goldAmount = 0;
    private final ArrayList<Item> playerLoot = new ArrayList<>();
    private final ArrayList<Item> activeItems = new ArrayList<>();
    private final ArrayList<Item> passiveItem = new ArrayList<>();
    private Weapon currentWeapon;

    /*
    +------------------+
    | GETTER FUNCTIONS |
    +------------------+
    */
    public String getName(){return this.Name;}
    public int getLevel(){return this.Level;}
    public int getExp(){return this.Exp;}
    public String getWorldLocation(){return this.worldLocation;}
    public Area getLoadedArea(){return this.loadedArea;}
    public int getHealth(){return this.health;}
    public int getStamina(){return this.stamina;}
    public int getGoldAmount(){return this.goldAmount;}
    public ArrayList<Item> getPlayerLoot(){return this.playerLoot;}
    public Location getLoadedLocation(){return this.loadedLocation;}
    /*
    +------------------+
    | SETTER FUNCTIONS |
    +------------------+
    */
    public void setWorldLocation(String location){this.worldLocation = location;}
    public void setLoadedLocation(Location location){this.loadedLocation = location;}
    public void setLoadedArea(Area area){this.loadedArea = area;}
    public void setName(String characterName){this.Name = characterName;}
    public void addLevel(int levelIncrease){this.Level = this.Level + levelIncrease;}
    public void addExp(int expIncrease){this.Exp = this.Exp + expIncrease;}
    public void removeHealth(int damageTaken){this.health = this.health - damageTaken;}
    public void removeStamina(int staminaUsed){this.stamina = this.stamina - staminaUsed;}
    public void addLootItem(Item newItem)
    {
        if(newItem instanceof GoldCoins)
        {
            GoldCoins coins = (GoldCoins)newItem;
            goldAmount = goldAmount + coins.goldAmount;
        }
        else if(newItem.battleItem)
        {
            this.playerLoot.add(newItem);
            this.activeItems.add(newItem);
        }
    }

    public void addHealth(int healthAdd)
    {
        health = health + healthAdd;
        if(health > maxHealth)
        {
            health = maxHealth;
        }
    }
    public void addStamina(int staminaAdd)
    {
        stamina = stamina + staminaAdd;
        if(stamina > maxStamina)
        {
            stamina = maxStamina;
        }
    }

    public int getDamage()
    {
        return currentWeapon.dmg;
    }

    public void respawnPlayer()
    {
        this.health = this.maxHealth;
        this.stamina = this.maxStamina;
    }

    public ArrayList<Item> getActiveItems()
    {
        return this.activeItems;
    }

    public void removeItem(Item item)
    {
        passiveItem.remove(item);
        activeItems.remove(item);
        playerLoot.remove(item);
    }

    public void setCurrentWeapon(Weapon weapon) {
        this.currentWeapon = weapon;
    }
}
