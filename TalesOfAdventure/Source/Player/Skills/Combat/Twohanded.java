public class Twohanded extends Skill {

    public Twohanded()
    {
        super("twohanded");
    }

    public boolean heavySwingActive = false;

    @Override
    public void activateSkill(String skill, boolean active)
    {
        if(skill.equalsIgnoreCase("Heavy Swing"))
        {
            heavySwingActive = active;
        }
    }

    @Override
    public void printActiveSkills(Player PLAYER)
    {

        if(heavySwingActive)
        {
            PLAYER.getClient().msgClient("Heavy Swing: Unlocked");
        }

    }
}