public abstract class Skill {

    // PUBLIC VARIABLES
    public final String skillType;

    // PRIVATE VARIABLES
    private int Level = 0;
    private int Exp = 0;

    protected Skill(String skillType) {
        this.skillType = skillType;
    }


    /*
    +------------------+
    | GETTER FUNCTIONS |
    +------------------+
    */
    public int getExp(){return this.Exp;}
    public int getLevel(){return this.Exp;}

    /*
    +------------------+
    | SETTER FUNCTIONS |
    +------------------+
    */
    public void addLevel(int levelIncrease){this.Level = this.Level + levelIncrease;}
    public void addExp(int expIncrease){this.Exp = this.Exp + expIncrease;}


    public abstract void activateSkill(String skill, boolean active);
    public abstract void printActiveSkills(Player PLAYER);
}