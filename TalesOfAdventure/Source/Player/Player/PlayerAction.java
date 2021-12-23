import java.util.ArrayList;
import java.util.HashMap;

public class PlayerAction {


    private Player PLAYER;
    public PlayerAction(Player player)
    {
        this.PLAYER = player;
    }


    public void battleAction(HashMap<String, Player> playersInBattle, HashMap<String, Creature> creaturesInBattle)
    {



        PLAYER.getClient().msgClient(PLAYER.getPlayerInfo().getName() + "'s Health: " + PLAYER.getPlayerInfo().getHealth());

        ArrayList<String> possibleActions = new ArrayList<>();

        for(Item item : PLAYER.getPlayerInfo().getActiveItems())
        {
           if(!possibleActions.contains(item.itemName))
           {
               possibleActions.add(item.itemName);
           }
        }


        for(Player otherPlayers : playersInBattle.values())
        {
            if(otherPlayers != this.PLAYER)
            {
                possibleActions.add("Heal: " + otherPlayers.getPlayerInfo().getName() + " HP:" + otherPlayers.getPlayerInfo().getHealth());
            }
        }

        for(String creature : creaturesInBattle.keySet())
        {
            possibleActions.add("Attack: " + creature + " HP:" +  creaturesInBattle.get(creature).health);
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
            for(String target : creaturesInBattle.keySet())
            {
                if(possibleActions.get(choice).contains(target))
                {
                    creaturesInBattle.get(target).takeDamage(PLAYER.getPlayerInfo().getDamage());
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
