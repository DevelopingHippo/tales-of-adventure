public class Magick extends Skill {

    public Magick()
    {
        super("magick");
    }

    public boolean fireballActive = false;

    @Override
    public void activateSkill(String skill, boolean active)
    {
        if(skill.equalsIgnoreCase("Fireball"))
        {
            fireballActive = active;
        }
    }

    @Override
    public void printActiveSkills(Player PLAYER)
    {

        if(fireballActive)
        {
            PLAYER.getClient().msgClient("Fireball: Unlocked");
        }

    }

}