public abstract class Creature {

    public final String creatureType;
    public final String creatureName;

    private Battle currentBattle;
    private boolean battleState = false;


    public int health = 1;
    public int stamina = 1;
    public int damage = 1;


    protected Creature(String creaturetype, String creaturename)
    {
        this.creatureType = creaturetype;
        this.creatureName = creaturename;
    }

    public void battle(Player PLAYER)
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
    }
    public void joinBattle(Creature joinCreature)
    {
        joinCreature.battleState = true;
        joinCreature.currentBattle = this.currentBattle;
        this.currentBattle.creatureJoinBattle(joinCreature);
    }

    public void takeDamage(int attackDmg) {this.health = this.health - attackDmg;}

    public abstract void attack(Player PLAYER);
}
