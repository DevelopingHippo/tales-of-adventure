import java.util.HashMap;

public abstract class Area
{
    public final String areaName;
    private final Core CORE;

    public Area(String areaname, Location parentLocation, Core core)
    {
        this.areaName = areaname;
        this.CORE = core;
        this.CORE.WORLD.areaParentLocation.put(this, parentLocation);
        this.CORE.WORLD.locationChildArea.put(parentLocation, this);
    }

    public HashMap<String, Area> area_NeighboringAreas = new HashMap<>();




    public abstract void startArea(Player PLAYER);
    public void addNeighboringArea(Area neighboringArea)
    {
        area_NeighboringAreas.put(neighboringArea.areaName, neighboringArea);
    }

    public void playerEnterArea(Player PLAYER)
    {
        PLAYER.getPlayerInfo().setLoadedArea(this);
        PLAYER.getClient().alertClient("Entering " + areaName);
        startArea(PLAYER);
    }
    public void creatureEnterArea(Creature CREATURE)
    {
        CREATURE.inArea = this;
    }
    public void npcEnterArea(Npc NPC)
    {
        NPC.inArea = this;
    }


}
