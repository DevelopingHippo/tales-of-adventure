public abstract class Location {

    public final String locationType;
    public final String locationName;
    public final Core CORE;


    protected Location(String locationtype, String locationname, Core core) {

        this.locationType = locationtype;
        this.locationName = locationname;
        this.CORE = core;
        this.CORE.WORLD.nameToLocation.put(this.locationName, this);
    }

    public void playerJoin(Player PLAYER)
    {
        PLAYER.getClient().alertClient("Entering " + locationName);
        PLAYER.getPlayerInfo().setLoadedLocation(this);
        startLocation(PLAYER);
    }

    private void startLocation(Player PLAYER)
    {
        if(this instanceof City)
        {
            if(this instanceof HighHrothgar highHrothgar)
            {
                highHrothgar.playHighHrothgar(PLAYER);
            }
        }
        else if(this instanceof Town)
        {
            if(this instanceof OldRiften oldRiften)
            {
                oldRiften.playOldRiften(PLAYER);
            }
        }
        else if(this instanceof Dungeon)
        {
            if(this instanceof OldCrypt oldCrypt)
            {
                oldCrypt.playOldCrypt(PLAYER);
            }
        }
        else if(this instanceof Wild)
        {
            if(this instanceof Forest forest)
            {
                forest.playForest(PLAYER);
            }
        }
        else if(this instanceof Zone)
        {
            if(this instanceof Intro intro)
            {
                intro.playIntro(PLAYER);
            }
        }
    }

}
