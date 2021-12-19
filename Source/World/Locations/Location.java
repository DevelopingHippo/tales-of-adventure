import java.util.HashMap;

public abstract class Location {

    public String locationType;
    public String locationName;
    public HashMap<String, Player> playersInLocation = new HashMap<>();
    public Core CORE;

    public void playerJoin(Player PLAYER)
    {
        playersInLocation.put(PLAYER.getUsername(), PLAYER);
        PLAYER.getClient().alertClient("Entering " + locationName);
    }
    public void playerLeave(Player PLAYER)
    {
        playersInLocation.remove(PLAYER.getUsername());
    }


}
