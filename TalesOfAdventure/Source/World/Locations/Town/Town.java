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
        new RiftenTownGate(this, CORE);
        new RiftenEntrance(this, CORE);
        new RiftenTownCenter(this, CORE);
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
        playersInLocation.put(PLAYER.getPlayerInfo().getName(), PLAYER);
        areaMap.get("Riften Town Gate").playerEnterArea(PLAYER, null);
    }


    /*
    +------------------+
    | Riften Town Gate |
    +------------------+
    */
    static class RiftenTownGate extends Area
    {
        public RiftenTownGate(Location parentLocation, Core core)
        {
            super("Riften Town Gate", parentLocation, core);
            parentLocation.areaMap.put(this.areaName, this);
        }

        @Override
        public void startArea(Player PLAYER)
        {
        }

    }


    /*
    +-----------------+
    | Riften Entrance |
    +-----------------+
    */
    static class RiftenEntrance extends Area
    {
        public RiftenEntrance(Location parentLocation, Core core)
        {
            super("Riften Entrance", parentLocation, core);
            parentLocation.areaMap.put(this.areaName, this);

        }

        @Override
        public void startArea(Player PLAYER)
        {

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
            parentLocation.areaMap.put(this.areaName, this);
        }

        @Override
        public void startArea(Player PLAYER)
        {

        }
    }
}




