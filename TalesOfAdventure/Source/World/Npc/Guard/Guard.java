public abstract class Guard extends Npc
{


    public Guard(String npcname, String npctype, Core core) {
        super(npcname, npctype, core);
    }


}

class TownGuard extends Guard
{

    public final Town townGuarded;

    public TownGuard(String npcname, Town town, Area area, Core core)
    {
        super(npcname, "Town Guard",core);
        townGuarded = town;
        area.npcEnterArea(this);
    }

    @Override
    public void interact(Player PLAYER)
    {
        if(CORE.WORLD.npcLoadedArea.get(uniqueID) instanceof OldRiften.RiftenTownGate)
        {
            PLAYER.getClient().msgClient("Hello " + PLAYER.getPlayerInfo().getName() + ", welcome to " + townGuarded.locationName + ". We love new travelers, please enter.");
            PLAYER.printNext();
        }
        else if(CORE.WORLD.npcLoadedArea.get(uniqueID) instanceof OldRiften.RiftenEntrance)
        {
            PLAYER.getClient().msgClient("Welcome visitor, this is Old Riften, home to many of this Worlds misfits and misplaced, we hope you find your place.");
            PLAYER.printNext();
        }
    }
}


class CityGuard extends Guard
{

    public final City cityGuarded;

    public CityGuard(String npcname, City city, Area area, Core core) {
        super(npcname, "City Guard", core);
        cityGuarded = city;
        area.npcEnterArea(this);
    }

    @Override
    public void interact(Player PLAYER)
    {

    }
}