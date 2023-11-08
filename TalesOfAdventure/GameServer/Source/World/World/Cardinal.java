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

    // Runs CardinalCheck 24/7
    private void cardinalCheck()
    {
        do {
            checkPlayers();
            checkCreatures();
            checkNPCs();
        } while (!this.CORE.shutdown);
    }

    // Checks Buffers if Creatures need to be Created/Removed
    private void checkCreatures()
    {
        // Checks if Creatures are waiting in Create Buffer
        if(this.CORE.WORLD.creatureCreateBuffer.size() > 0)
        {
            this.CORE.WORLD.creaturesInWorld.putAll(this.CORE.WORLD.creatureCreateBuffer);
            this.CORE.WORLD.creatureCreateBuffer.clear();
        }

        // Checks if Creatures are waiting in RemoveBuffer
        if(this.CORE.WORLD.creatureRemoveBuffer.size() > 0)
        {
            for(String creatureID : this.CORE.WORLD.creatureRemoveBuffer)
            {
                this.CORE.WORLD.creaturesInWorld.remove(creatureID);
                this.CORE.WORLD.creatureLoadedArea.remove(creatureID);
            }
            this.CORE.WORLD.creatureRemoveBuffer.clear();
        }

        // Moves Creature to Correct World Location
        for(String creatureID : this.CORE.WORLD.creaturesInWorld.keySet())
        {
            if(this.CORE.WORLD.creatureLoadedArea.get(creatureID) != this.CORE.WORLD.creaturesInWorld.get(creatureID).inArea)
            {
                this.CORE.WORLD.creatureLoadedArea.replace(creatureID, this.CORE.WORLD.creaturesInWorld.get(creatureID).inArea);
            }
        }
    }

    // Checks Buffers if NPCs need to be Created/Removed
    private void checkNPCs()
    {
        // Checks if NPCs are waiting in Create Buffer
        if(this.CORE.WORLD.npcCreateBuffer.size() > 0)
        {
            this.CORE.WORLD.npcsInWorld.putAll(this.CORE.WORLD.npcCreateBuffer);
            this.CORE.WORLD.npcCreateBuffer.clear();
        }

        // Checks if NCPs are waiting in RemoveBuffer
        if(this.CORE.WORLD.npcRemoveBuffer.size() > 0)
        {
            for(String npcID : this.CORE.WORLD.npcRemoveBuffer)
            {
                this.CORE.WORLD.npcsInWorld.remove(npcID);
                this.CORE.WORLD.npcLoadedArea.remove(npcID);
            }
            this.CORE.WORLD.npcRemoveBuffer.clear();
        }

        // Moves NPCs to Correct World Location
        for(String npcID : this.CORE.WORLD.npcsInWorld.keySet())
        {
            if(this.CORE.WORLD.npcLoadedArea.get(npcID) != this.CORE.WORLD.npcsInWorld.get(npcID).inArea)
            {
                this.CORE.WORLD.npcLoadedArea.replace(npcID, this.CORE.WORLD.npcsInWorld.get(npcID).inArea);
            }
        }
    }
    private void checkPlayers()
    {
        // Updates Player/Creature World Current and Loaded World position and adjusts accordingly
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
    }

    // Generates ID value for NPCs/Items/Creatures
    public String generateID(String type)
    {
        if(type.equalsIgnoreCase("npc"))
        {
            this.npcsCreated++;
            return "22" + this.npcsCreated;
        }
        if(type.equalsIgnoreCase("item"))
        {
            this.itemsCreated++;
            return "11" + this.itemsCreated;
        }
        if(type.equalsIgnoreCase("creature"))
        {
            this.creaturesCreated++;
            return "33" + this.creaturesCreated;
        }
        return null;
    }
}