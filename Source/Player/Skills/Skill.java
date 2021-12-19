public abstract class Skill {

    // PUBLIC VARIABLES
    public String skillType;

    // PRIVATE VARIABLES
    private int Level = 0;
    private int Exp = 0;





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

}