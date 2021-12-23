public class Guard extends Npc
{


    public Guard(String npcname, String npctype,Core core) {
        super(npcname, "Guard", core);
    }
}

class TownGuard extends Guard
{

    public final Town townGuarded;

    public TownGuard(String guardName,Town town, Core core)
    {
        super(guardName, "Town Guard",core);
        townGuarded = town;
    }

}


class CityGuard extends Guard
{

    public final City cityGuarded;

    public CityGuard(String npcname, City city, Core core) {
        super(npcname, "City Guard", core);
        cityGuarded = city;
    }
}