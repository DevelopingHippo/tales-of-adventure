public abstract class Npc
{

    public String npcName;
    public String npcType;
    public Core CORE;

    public Npc(String npcname, String npctype, Core core)
    {
        this.npcName = npcname;
        this.npcType = npctype;
        this.CORE = core;
    }

}
