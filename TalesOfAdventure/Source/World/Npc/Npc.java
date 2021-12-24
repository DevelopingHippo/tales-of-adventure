public abstract class Npc
{
    public final String uniqueID;
    public String npcName;
    public String npcType;
    public Core CORE;
    public Area inArea;

    public Npc(String npcname, String npctype, Core core)
    {
        this.npcName = npcname;
        this.npcType = npctype;
        this.CORE = core;
        this.uniqueID = this.CORE.WORLD.cardinal.generateID("npc");
        this.CORE.WORLD.npcCreateBuffer.put(uniqueID, this);
        this.CORE.WORLD.npcLoadedArea.put(uniqueID, null);
    }

    public abstract void interact(Player PLAYER);
}
