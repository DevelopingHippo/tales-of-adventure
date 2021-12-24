import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class World {
    // System DATA
    private final Core CORE;
    public final Cardinal cardinal;

    // World DATA
    public final WorldTime worldTime;
    private final HashMap<String, Location> worldLocations = new HashMap<>();


    // Location DATA
    public final HashMap<Location, Area> locationChildArea = new HashMap<>();
    public final HashMap<String, Location> nameToLocation = new HashMap<>();

    // Area DATA
    public final HashMap<Area, Location> areaParentLocation = new HashMap<>();
    public final HashMap<String, Area> nameToArea = new HashMap<>();


    // Player World DATA
    public final LinkedList<Player> playersInWorld = new LinkedList<>();
    public final HashMap<Player, Location> playerLoadedLocation = new HashMap<>();
    public final HashMap<Player, Area> playerLoadedArea = new HashMap<>();



    // Npc World DATA
    public final HashMap<String, Npc> npcsInWorld = new HashMap<>();
    public final HashMap<String, Area> npcLoadedArea = new HashMap<>();
    public HashMap<String, Npc> npcCreateBuffer = new HashMap<>();
    public LinkedList<String> npcRemoveBuffer = new LinkedList<>();

    // Creature World DATA
    public final HashMap<String, Creature> creaturesInWorld = new HashMap<>();
    public final HashMap<String, Area> creatureLoadedArea = new HashMap<>();
    public HashMap<String, Creature> creatureCreateBuffer = new HashMap<>();
    public LinkedList<String> creatureRemoveBuffer = new LinkedList<>();


    // Item World DATA
    public final HashMap<String, Item> itemsInWorld = new HashMap<>();



    public World(Core core)
    {
        this.CORE = core;
        this.worldTime = new WorldTime();
        cardinal = new Cardinal(this.CORE);
    }
    public void buildWorld()
    {

        // Add Towns to worldLocations
        OldRiften oldRiften = new OldRiften(CORE);
        worldLocations.put(oldRiften.locationName, oldRiften);
        Thread oldRiftenThread = new Thread(oldRiften);
        oldRiftenThread.start();

        // Add Cities to worldLocations
        HighHrothgar highHrothgar = new HighHrothgar(CORE);
        worldLocations.put(highHrothgar.locationName, highHrothgar);
        Thread highHrothgarThread = new Thread(highHrothgar);
        highHrothgarThread.start();

        // Add Wilds to worldLocations
        Forest forest = new Forest(CORE);
        worldLocations.put(forest.locationName, forest);
        Thread forestThread = new Thread(forest);
        forestThread.start();

        // Add Dungeons to worldLocations
        OldCrypt oldCrypt = new OldCrypt(CORE);
        worldLocations.put(oldCrypt.locationName, highHrothgar);

        // Add Zones to worldLocations
        Intro intro = new Intro(CORE);
        worldLocations.put(intro.locationName, intro);

        // Add Dev Location
        Dev dev = new Dev(CORE);
        worldLocations.put(dev.locationName, dev);

    }


    public Location getLocation(String locationName) {
        return worldLocations.get(locationName);
    }

    public ArrayList<Creature> getCreaturesInArea(Area area)
    {
        ArrayList<Creature> areaCreatures = new ArrayList<>();

        for(String creatureID : creaturesInWorld.keySet())
        {
            if(creatureLoadedArea.get(creatureID) == area)
            {
                areaCreatures.add(creaturesInWorld.get(creatureID));
            }
        }
        return areaCreatures;
    }

    public void removeCreature(Creature creature)
    {
        creatureRemoveBuffer.add(creature.uniqueID);
    }
    public void removeNpc(Npc npc)
    {
        npcRemoveBuffer.add(npc.uniqueID);
    }
}
