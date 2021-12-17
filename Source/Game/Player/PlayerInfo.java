public class PlayerInfo {

    private String Name = "Not Loaded";
    private int Level = 0;
    private int Exp = 0;

    /*
    +------------------+
    | GETTER FUNCTIONS |
    +------------------+
    */
    public String getName(){return this.Name;}
    public int getLevel(){return this.Level;}
    public float getExp(){return this.Exp;}




    /*
    +------------------+
    | SETTER FUNCTIONS |
    +------------------+
    */
    public void setName(String characterName){this.Name = characterName;}
    public void addLevel(int levelIncrease){this.Level = this.Level + levelIncrease;}
    public void addExp(int expIncrease){this.Exp = this.Exp + expIncrease;}
}
