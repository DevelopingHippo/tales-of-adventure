import java.util.HashMap;

public abstract class Location {

    public final String locationType;
    public final String locationName;
    public final Core CORE;
    public final HashMap<String, Player> playersInLocation = new HashMap<>();

    protected Location(String locationtype, String locationname, Core core) {
        this.locationType = locationtype;
        this.locationName = locationname;
        CORE = core;
    }

    public void playerJoin(Player PLAYER)
    {
        playersInLocation.put(PLAYER.getUsername(), PLAYER);
        PLAYER.getClient().alertClient("Entering " + locationName);
        startLocation(PLAYER);
    }
    public void playerLeave(Player PLAYER)
    {
        playersInLocation.remove(PLAYER.getUsername());
    }


    private void startLocation(Player PLAYER)
    {
        if(this instanceof City)
        {
            if(this instanceof HighHrothgar)
            {
                HighHrothgar highHrothgar = (HighHrothgar)this;
                highHrothgar.playHighHrothgar(PLAYER);
            }
        }
        else if(this instanceof Town)
        {
            if(this instanceof OldRiften)
            {
                OldRiften oldRiften = (OldRiften)this;
                oldRiften.playOldRiften(PLAYER);
            }
        }
        else if(this instanceof Dungeon)
        {
            if(this instanceof OldCrypt)
            {
                OldCrypt oldCrypt = (OldCrypt)this;
                oldCrypt.playOldCrypt(PLAYER);
            }
        }
        else if(this instanceof Wild)
        {
            if(this instanceof Forest)
            {
                Forest forest = (Forest)this;
                forest.playForest(PLAYER);
            }
        }
        else if(this instanceof Zone)
        {
            if(this instanceof Intro)
            {
                Intro intro = (Intro)this;
                intro.playIntro(PLAYER);
            }
        }
    }

}
