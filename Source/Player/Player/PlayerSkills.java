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
    public Onehanded onehandedSkill;
    public Twohanded twohandedSkill;
    public Ranged rangedSkill;
    public Magick magickSkill;
    public Blocking blockSkill;


    /*
    +------------------+
    | GATHERING SKILLS |
    +------------------+
    */
    public Fishing fishingSkill;

}
