import java.util.HashMap;

public class World {

    private final Core CORE;
    private final HashMap<String, Location> worldLocations = new HashMap<>();

    public World(Core core)
    {
        CORE = core;
        buildWorld();
    }
    private void buildWorld()
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
    }




    public Location getLocation(String locationName) {return worldLocations.get(locationName);}
}
