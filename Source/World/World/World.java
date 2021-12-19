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

        // Add Cities to worldLocations
        HighHrothgar highHrothgar = new HighHrothgar(CORE);
        worldLocations.put(highHrothgar.locationName, highHrothgar);

        // Add Dungeons to worldLocations
        OldCrypt oldCrypt = new OldCrypt(CORE);
        worldLocations.put(oldCrypt.locationName, highHrothgar);

        // Add Wilds to worldLocations
        Forest forest = new Forest(CORE);
        worldLocations.put(forest.locationName, forest);
    }




    public Location getLocation(String locationName) {return worldLocations.get(locationName);}
}
