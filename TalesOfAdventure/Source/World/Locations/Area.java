import java.util.HashMap;

public abstract class Area
{
    public final String areaName;
    public final Location parentLocation;
    public final Core CORE;
    public Area(String areaname, Location parentlocation, Core core)
    {
        this.areaName = areaname;
        this.parentLocation = parentlocation;
        this.CORE = core;
    }

    public HashMap<String, Player> playersInArea = new HashMap<>();
    public HashMap<String, Npc> npcsInArea = new HashMap<>();
    public HashMap<String, Creature>  creaturesInArea = new HashMap<>();



    public abstract void startArea(Player PLAYER);




    public void playerEnterArea(Player PLAYER, Area oldArea)
    {
        if(oldArea != null)
        {
            oldArea.playerLeaveArea(PLAYER);
        }
        playersInArea.put(PLAYER.getPlayerInfo().getName(), PLAYER);
        parentLocation.playerLoadedArea.put(PLAYER, this);
        PLAYER.getClient().alertClient("Entering " + areaName);
        startArea(PLAYER);
    }
    public void playerLeaveArea(Player PLAYER)
    {
        playersInArea.remove(PLAYER.getPlayerInfo().getName());
    }

    public void creatureEnterArea(Creature CREATURE)
    {
        creaturesInArea.put(CREATURE.creatureName, CREATURE);
    }
    public void creatureLeaveArea(Creature CREATURE)
    {
        creaturesInArea.remove(CREATURE.creatureName, CREATURE);
    }

    public void npcEnterArea(Npc NPC)
    {
        npcsInArea.put(NPC.npcName, NPC);
    }
    public void npcLeaveArea(Npc NPC)
    {
        npcsInArea.remove(NPC.npcName);
    }


}
