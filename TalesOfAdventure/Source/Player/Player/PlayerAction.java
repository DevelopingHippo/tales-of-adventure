import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class PlayerAction {

    private final Player PLAYER;

    public PlayerAction(Player player)
    {
        this.PLAYER = player;
    }


    public void battleAction(HashMap<String, Player> playersInBattle, HashMap<String, Creature> creaturesInBattle)
    {
        PLAYER.getClient().msgClient(PLAYER.getPlayerInfo().getName() + "'s Health: " + PLAYER.getPlayerInfo().getHealth());

        ArrayList<String> possibleActions = new ArrayList<>();

        // Actions with Items
        for(Item item : PLAYER.getPlayerInfo().getActiveItems())
        {
           if(!possibleActions.contains(item.itemName))
           {
               possibleActions.add(item.itemName);
           }
        }

        // Actions on Other Players
        for(Player otherPlayers : playersInBattle.values())
        {
            if(otherPlayers != this.PLAYER)
            {
                possibleActions.add("Heal: " + otherPlayers.getPlayerInfo().getName() + " HP:" + otherPlayers.getPlayerInfo().getHealth());
            }
        }

        // Actions on Creatures
        for(Creature creature : creaturesInBattle.values())
        {
            possibleActions.add("Attack: " + creature.creatureName + " HP:" +  creaturesInBattle.get(creature.uniqueID).health);
        }

        possibleActions.add("Run Away");

        formatActions(possibleActions);

        int choice = this.PLAYER.getClient().getNumInput(0, possibleActions.size() - 1);

        if(possibleActions.get(choice).equalsIgnoreCase("Run Away"))
        {
            this.PLAYER.getActiveBattle().playerFlee(this.PLAYER);
        }
        else if(possibleActions.get(choice).contains("Heal:"))
        {
            for(String target : playersInBattle.keySet())
            {
                if(possibleActions.get(choice).contains(target))
                {
                    playersInBattle.get(target).getPlayerInfo().addHealth(10);
                }
            }
        }
        else if(possibleActions.get(choice).contains("Attack:"))
        {
            for(Creature target : creaturesInBattle.values())
            {
                if(possibleActions.get(choice).contains(target.creatureName))
                {
                    creaturesInBattle.get(target.uniqueID).takeDamage(PLAYER.getPlayerInfo().getDamage());
                    break;
                }
            }
        }
        else
        {
            for(Item item : PLAYER.getPlayerInfo().getActiveItems())
            {
                if(possibleActions.get(choice).contains(item.itemName))
                {
                    item.useItem(PLAYER);
                    PLAYER.getPlayerInfo().removeItem(item);
                    break;
                }
            }
        }

    }


    public void getInteraction(Area area)
    {
        ArrayList<String> possibleActions = new ArrayList<>();
        LinkedList<Player> playersInWorld =  this.PLAYER.getCore().WORLD.playersInWorld;

        possibleActions.add("");

        for (Npc npc : PLAYER.getCore().WORLD.npcsInWorld.values())
        {
            if(PLAYER.getCore().WORLD.npcLoadedArea.get(npc.uniqueID) == area)
            {
                possibleActions.add("Talk: " + npc.npcName);
            }
        }
        for (Creature creature : PLAYER.getCore().WORLD.creaturesInWorld.values())
        {
            if(PLAYER.getCore().WORLD.creatureLoadedArea.get(creature.uniqueID) == area)
            {
                possibleActions.add("Attack: " + creature.creatureName);
            }
        }
        for (Player player : playersInWorld)
        {
            if(PLAYER.getCore().WORLD.playerLoadedArea.get(player) == area)
            {
                if(player != PLAYER)
                {
                    possibleActions.add("Interact: " + player.getPlayerInfo().getName());
                }
            }
        }
        for (Area otherArea : area.area_NeighboringAreas.values())
        {
            possibleActions.add("Enter: " + otherArea.areaName);
        }

        formatActions(possibleActions);

        int choice = PLAYER.getClient().getNumInput(0, possibleActions.size() - 1);

        if(possibleActions.get(choice).contains("Talk: "))
        {
            for (Npc npc : PLAYER.getCore().WORLD.npcsInWorld.values())
            {
                if(possibleActions.get(choice).contains(npc.npcName))
                {
                    npc.interact(PLAYER);
                }
            }
        }
        else if(possibleActions.get(choice).contains("Attack: "))
        {
            for(Creature creature : PLAYER.getCore().WORLD.getCreaturesInArea(area))
            {
                if(possibleActions.get(choice).contains(creature.creatureName))
                {
                    PLAYER.startBattle(creature.battle(PLAYER));
                }
            }
        }
        else if(possibleActions.get(choice).contains("Interact: "))
        {
            for (Player player : playersInWorld)
            {
                if(possibleActions.get(choice).contains(player.getPlayerInfo().getName()))
                {
                    player.interact(PLAYER);
                }
            }
        }
        else if(possibleActions.get(choice).contains("Enter: "))
        {
            for(Area neighboringArea : area.area_NeighboringAreas.values())
            {
                if(possibleActions.get(choice).contains(neighboringArea.areaName))
                {
                    neighboringArea.playerEnterArea(PLAYER);
                }
            }
        }
        else
        {
            getInteraction(area);
        }
    }






    private void formatActions(ArrayList<String> actions)
    {
        StringBuilder actionLine = new StringBuilder("[" + 0 + "] " + actions.get(0)).append("\t");

        int actionCount = 0;

        if(actions.size() > 1)
        {
            for(int i = 1; i < actions.size(); i++)
            {
                if(actionCount > 2)
                {
                    this.PLAYER.getClient().msgClient(actionLine.toString());
                    actionLine = new StringBuilder();
                    actionCount = 0;
                }
                actionLine.append("[").append(i).append("] ").append(actions.get(i)).append("\t");
                actionCount++;
            }
        }
        this.PLAYER.getClient().msgClient(actionLine.toString());
    }



}
