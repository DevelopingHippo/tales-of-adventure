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

        ArrayList<String> possibleActions = new ArrayList<>();

        for(Player otherPlayers : playersInBattle.values())
        {
            if(otherPlayers != this.PLAYER)
            {
                possibleActions.add("Heal: " + otherPlayers.getPlayerInfo().getName());
            }
        }

        for(String creature : creaturesInBattle.keySet())
        {
            possibleActions.add("Attack: " + creature);
        }

        formatActions(possibleActions);

        int choice = this.PLAYER.getClient().getNumInput(0, possibleActions.size() - 1);


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
        PLAYER.getClient().msgClient(actionLine.toString());
    }



}
