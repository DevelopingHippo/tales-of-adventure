public class Cardinal implements Runnable {

    private final Core CORE;

    private int itemsCreated = 0;
    private int creaturesCreated = 0;
    private int npcsCreated = 0;


    public Cardinal(Core core)
    {
        this.CORE = core;
        Thread cardinalThread = new Thread(this);
        cardinalThread.start();
    }
    @Override
    public void run()
    {
        cardinalCheck();
    }




    private void cardinalCheck()
    {
        while(true)
        {
            if(this.CORE.WORLD.creatureCreateBuffer.size() > 0)
            {
                this.CORE.WORLD.creaturesInWorld.putAll(this.CORE.WORLD.creatureCreateBuffer);
                this.CORE.WORLD.creatureCreateBuffer.clear();
            }
            if(this.CORE.WORLD.npcCreateBuffer.size() > 0)
            {
                this.CORE.WORLD.npcsInWorld.putAll(this.CORE.WORLD.npcCreateBuffer);
                this.CORE.WORLD.npcCreateBuffer.clear();
            }
            if(this.CORE.WORLD.creatureRemoveBuffer.size() > 0)
            {
                for(String creatureID : this.CORE.WORLD.creatureRemoveBuffer)
                {
                    this.CORE.WORLD.creaturesInWorld.remove(creatureID);
                    this.CORE.WORLD.creatureLoadedArea.remove(creatureID);
                }
                this.CORE.WORLD.creatureRemoveBuffer.clear();
            }
            if(this.CORE.WORLD.npcRemoveBuffer.size() > 0)
            {
                for(String npcID : this.CORE.WORLD.npcRemoveBuffer)
                {
                    this.CORE.WORLD.npcsInWorld.remove(npcID);
                    this.CORE.WORLD.npcLoadedArea.remove(npcID);
                }
                this.CORE.WORLD.npcRemoveBuffer.clear();
            }




            for(int i = 0; i < this.CORE.WORLD.playersInWorld.size(); i++)
            {
                Player player = this.CORE.WORLD.playersInWorld.get(i);
                if(player.getPlayerInfo().getLoadedArea() != this.CORE.WORLD.playerLoadedArea.get(player))
                {
                    this.CORE.WORLD.playerLoadedArea.replace(player, player.getPlayerInfo().getLoadedArea());
                }
                if(player.getPlayerInfo().getLoadedLocation() != this.CORE.WORLD.playerLoadedLocation.get(player))
                {
                    this.CORE.WORLD.playerLoadedLocation.replace(player, player.getPlayerInfo().getLoadedLocation());
                }
            }
            for(String npcID : this.CORE.WORLD.npcsInWorld.keySet())
            {
                if(this.CORE.WORLD.npcLoadedArea.get(npcID) != this.CORE.WORLD.npcsInWorld.get(npcID).inArea)
                {
                    this.CORE.WORLD.npcLoadedArea.replace(npcID, this.CORE.WORLD.npcsInWorld.get(npcID).inArea);
                }
            }
            for(String creatureID : this.CORE.WORLD.creaturesInWorld.keySet())
            {
                if(this.CORE.WORLD.creatureLoadedArea.get(creatureID) != this.CORE.WORLD.creaturesInWorld.get(creatureID).inArea)
                {
                    this.CORE.WORLD.creatureLoadedArea.replace(creatureID, this.CORE.WORLD.creaturesInWorld.get(creatureID).inArea);
                }
            }
        }
    }

    public String generateID(String type)
    {
        if(type.equalsIgnoreCase("npc"))
        {
            this.npcsCreated++;
            return "22" + this.npcsCreated;
        }
        else if(type.equalsIgnoreCase("item"))
        {
            this.itemsCreated++;
            return "11" + this.itemsCreated;
        }
        else if(type.equalsIgnoreCase("creature"))
        {
            this.creaturesCreated++;
            return "33" + this.creaturesCreated;
        }
        return null;
    }



}
