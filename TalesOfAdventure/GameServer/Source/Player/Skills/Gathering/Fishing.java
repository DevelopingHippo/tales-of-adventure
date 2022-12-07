public class Fishing extends Skill {


    public Fishing()
    {
        super("fishing");
    }

    public boolean freshWaterFishing = false;

    @Override
    public void activateSkill(String skill, boolean active)
    {
        if(skill.equalsIgnoreCase("Fresh Water Fishing"))
        {
            freshWaterFishing = active;
        }
    }


    @Override
    public void printActiveSkills(Player PLAYER)
    {

        if(freshWaterFishing)
        {
            PLAYER.getClient().msgClient("Fresh Water Fishing: Unlocked");
        }

    }
}