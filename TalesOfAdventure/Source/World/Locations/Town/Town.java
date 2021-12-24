public class Town extends Location {

    protected Town(String name, Core core)
    {
        super("town", name, core);
    }
}



class OldRiften extends Town implements Runnable
{
    public OldRiften(Core CORE)
    {
        super("Old Riften", CORE);

        // Created Each Area
        RiftenTownGate riftenTownGate = new RiftenTownGate(this, CORE);
        RiftenEntrance riftenEntrance = new RiftenEntrance(this, CORE);
        RiftenTownCenter riftenTownCenter = new RiftenTownCenter(this, CORE);

        // Add Areas to World Map
        CORE.WORLD.nameToArea.put(riftenTownGate.areaName, riftenTownGate);
        CORE.WORLD.nameToArea.put(riftenEntrance.areaName, riftenEntrance);
        CORE.WORLD.nameToArea.put(riftenTownCenter.areaName, riftenTownCenter);

        // Add Areas to their Neighboring Areas

        // Town Gate
        riftenTownGate.addNeighboringArea(riftenEntrance);

        // Town Entrance
        riftenEntrance.addNeighboringArea(riftenTownGate);
        riftenEntrance.addNeighboringArea(riftenTownCenter);

        // Town Center
        riftenTownCenter.addNeighboringArea(riftenEntrance);

    }
    @Override
    public void run()
    {
        manageOldRiften();
    }
    private void manageOldRiften()
    {

    }

    public void playOldRiften(Player PLAYER)
    {
        CORE.WORLD.nameToArea.get("Riften Town Gate").startArea(PLAYER);
    }


    /*
    +------------------+
    | Riften Town Gate |
    +------------------+
    */
    public static class RiftenTownGate extends Area
    {
        public RiftenTownGate(Location parentLocation, Core core)
        {
            super("Riften Town Gate", parentLocation, core);
            new TownGuard("Lionel the Small", (Town)parentLocation,this, core);
            new Goblin(this, core);
        }

        @Override
        public void startArea(Player PLAYER)
        {
            PLAYER.getPlayerInfo().setLoadedArea(this);
            while(PLAYER.getPlayerInfo().getLoadedArea() == this)
            {
                PLAYER.getPlayerAction().getInteraction(this);
            }
        }
    }


    /*
    +-----------------+
    | Riften Entrance |
    +-----------------+
    */
    public static class RiftenEntrance extends Area
    {
        public RiftenEntrance(Location parentLocation, Core core)
        {
            super("Riften Entrance", parentLocation, core);
            new TownGuard("Jimmy the Large", (Town)parentLocation,this, core);
        }

        @Override
        public void startArea(Player PLAYER)
        {
            PLAYER.getPlayerInfo().setLoadedArea(this);
            while(PLAYER.getPlayerInfo().getLoadedArea() == this)
            {
                PLAYER.getPlayerAction().getInteraction(this);
            }
        }
    }


    /*
    +--------------------+
    | Riften Town Center |
    +--------------------+
    */
    static class RiftenTownCenter extends Area
    {
        public RiftenTownCenter(Location parentLocation, Core core)
        {
            super("Riften Town Center", parentLocation, core);
            new TownGuard("Terry the Medium", (Town)parentLocation,this, core);
        }

        @Override
        public void startArea(Player PLAYER)
        {
            PLAYER.getPlayerInfo().setLoadedArea(this);
            while(PLAYER.getPlayerInfo().getLoadedArea() == this)
            {
                PLAYER.getPlayerAction().getInteraction(this);
            }

        }
    }
}




