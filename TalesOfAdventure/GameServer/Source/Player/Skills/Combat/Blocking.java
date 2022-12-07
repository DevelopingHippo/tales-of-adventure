public class Blocking extends Skill {
    public Blocking()
    {
        super("blocking");
    }

    public boolean readyBlockActive = false;

    @Override
    public void activateSkill(String skill, boolean active)
    {

        if(skill.equalsIgnoreCase("Ready Block"))
        {
            readyBlockActive = active;
        }

    }

    @Override
    public void printActiveSkills(Player PLAYER)
    {

        if(readyBlockActive)
        {
            PLAYER.getClient().msgClient("Ready Block: Unlocked");
        }

    }


}