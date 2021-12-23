import java.util.ArrayList;
import java.util.Collection;

public abstract class Creature {

    public final String creatureType;
    public final String creatureName;
    public final ArrayList<Item> creatureLoot = new ArrayList<>();

    private Battle currentBattle;
    private boolean battleState = false;

    public int exp;
    public int health = 1;
    public int stamina = 1;
    public int damage = 1;

    public abstract void createCreatureLoot();
    protected Creature(String creaturetype, String creaturename)
    {
        this.creatureType = creaturetype;
        this.creatureName = creaturename;
        createCreatureLoot();
    }

    public Battle battle(Player PLAYER)
    {
        if(!this.battleState)
        {
            this.battleState = true;
            this.currentBattle = new Battle(PLAYER, this);
        }
        else
        {
            currentBattle.playerJoinBattle(PLAYER);
        }
        return this.currentBattle;
    }
    public void joinBattle(Creature joinCreature)
    {
        joinCreature.battleState = true;
        joinCreature.currentBattle = this.currentBattle;
        this.currentBattle.creatureJoinBattle(joinCreature);
    }

    public void leaveBattle()
    {
        this.currentBattle = null;
    }

    public void takeDamage(int attackDmg) {this.health = this.health - attackDmg;}

    public abstract void attack(Player PLAYER);

    public ArrayList<Item> getLoot()
    {
        return creatureLoot;
    }
}
