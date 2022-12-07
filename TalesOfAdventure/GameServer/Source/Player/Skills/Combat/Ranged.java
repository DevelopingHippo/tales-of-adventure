public class Ranged extends Skill {

    public Ranged()
    {
        super("ranged");
    }

    public boolean doubleShotActive = false;

    @Override
    public void activateSkill(String skill, boolean active)
    {
        if(skill.equalsIgnoreCase("Double Shot"))
        {
            doubleShotActive = active;
        }
    }

    @Override
    public void printActiveSkills(Player PLAYER)
    {

        if(doubleShotActive)
        {
            PLAYER.getClient().msgClient("Double Shot: Unlocked");
        }

    }
}