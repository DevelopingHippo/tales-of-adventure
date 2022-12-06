public class Onehanded extends Skill {

    public Onehanded()
    {
        super("onehanded");
    }

    public boolean quickStabActive = false;

    @Override
    public void activateSkill(String skill, boolean active)
    {
        if(skill.equalsIgnoreCase("Quick Stab"))
        {
            quickStabActive = active;
        }
    }


    @Override
    public void printActiveSkills(Player PLAYER)
    {

        if(quickStabActive)
        {
            PLAYER.getClient().msgClient("Quick Stab: Unlocked");
        }

    }
}