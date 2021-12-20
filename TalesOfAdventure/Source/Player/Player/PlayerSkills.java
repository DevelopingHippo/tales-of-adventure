public class PlayerSkills {
    private final Player PLAYER;
    public PlayerSkills(Player player)
    {
        PLAYER = player;
    }


    /*
    +---------------+
    | COMBAT SKILLS |
    +---------------+
    */
    public Onehanded onehandedSkill = new Onehanded();
    public Twohanded twohandedSkill = new Twohanded();
    public Ranged rangedSkill = new Ranged();
    public Magick magickSkill = new Magick();
    public Blocking blockSkill = new Blocking();


    /*
    +------------------+
    | GATHERING SKILLS |
    +------------------+
    */
    public Fishing fishingSkill = new Fishing();

}
