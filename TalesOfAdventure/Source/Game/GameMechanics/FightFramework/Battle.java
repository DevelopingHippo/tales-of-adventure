import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Battle implements Runnable {

    private final HashMap<String, Player> playersInBattle = new HashMap<>();
    private final ArrayList<String> playerNames = new ArrayList<>();

    private final HashMap<String, Player> playersJoining = new HashMap<>();

    private final HashMap<String, Creature> creaturesInBattle = new HashMap<>();
    private final ArrayList<String> creatureNames = new ArrayList<>();

    private final HashMap<String, Creature> joiningCreatures = new HashMap<>();
    private final ArrayList<String> battleAlerts = new ArrayList<>();

    @Override
    public void run()
    {
        startBattle();
    }

    public Battle(Player player, Creature creature)
    {
        addCreatureToBattle(creature);
        addPlayerToBattle(player);
        Thread battleThread = new Thread(this);
        battleThread.start();
    }

    public void playerJoinBattle(Player newPlayer)
    {
        playersJoining.put(newPlayer.getPlayerInfo().getName(), newPlayer);
        String allCreatures = creatureNames.toString().replaceAll("\\[", "").replaceAll("]","").replaceAll("\\{", "").replaceAll("}", "");
        newPlayer.getClient().alertClient("BATTLE STARTED: " + allCreatures);
    }
    public void creatureJoinBattle(Creature creature)
    {
        int creaturenameCount = 0;
        for(Creature existingCreature : joiningCreatures.values())
        {
            if(existingCreature.creatureName.equalsIgnoreCase(creature.creatureName))
            {
                creaturenameCount++;
            }
        }
        String addCreatureName;
        if(creaturenameCount == 0)
        {
            addCreatureName = creature.creatureName;
        }
        else
        {
            addCreatureName = creature.creatureName + creaturenameCount;
        }
        joiningCreatures.put(addCreatureName, creature);
    }



    private void addCreatureToBattle(Creature creature)
    {
        int creaturenameCount = 0;
        for(Creature existingCreature : creaturesInBattle.values())
        {
            if(existingCreature.creatureName.equalsIgnoreCase(creature.creatureName))
            {
                creaturenameCount++;
            }
        }
        String addCreatureName;
        if(creaturenameCount == 0)
        {
            addCreatureName = creature.creatureName;
        }
        else
        {
            addCreatureName = creature.creatureName + creaturenameCount;
        }

        creatureNames.add(addCreatureName);
        creaturesInBattle.put(addCreatureName, creature);
        battleAlerts.add(addCreatureName + " Joined The Battle!");
    }
    private void addPlayerToBattle(Player PLAYER)
    {
        playersInBattle.put(PLAYER.getPlayerInfo().getName(), PLAYER);
        playerNames.add(PLAYER.getPlayerInfo().getName());
        battleAlerts.add(PLAYER.getPlayerInfo().getName() + " Joined The Battle!");
    }
    private void addJoiningPlayersAndCreatures()
    {
        for(Player player : playersJoining.values())
        {
            addPlayerToBattle(player);
        }
        for(Creature creature : joiningCreatures.values())
        {
            addCreatureToBattle(creature);
        }
        joiningCreatures.clear();
        playersJoining.clear();
    }



    private void creatureDefeated(String creaturename)
    {
        creatureNames.remove(creaturename);
    }


    private void printBattleAlerts()
    {
        while(!battleAlerts.isEmpty())
        {
            for(Player player : playersInBattle.values())
            {
                player.getClient().alertClient(battleAlerts.get(0));
                battleAlerts.remove(0);
            }
        }
    }

    public void startBattle()
    {
        while(creaturesInBattle.size() > 0)
        {
            if(!joiningCreatures.isEmpty() || !playersJoining.isEmpty())
            {
                addJoiningPlayersAndCreatures();
            }
            if(!battleAlerts.isEmpty())
            {
                printBattleAlerts();
            }

            battleLoop();
        }
    }




    private void battleLoop()
    {
        for (Player PLAYER : playersInBattle.values())
        {
            PLAYER.getClient().msgClient("Your Turn!");
            for (Player otherPlayers : playersInBattle.values())
            {
                if(otherPlayers != PLAYER)
                {
                    otherPlayers.getClient().msgClient(PLAYER.getPlayerInfo().getName() + "'s Turn!");
                }
            }
            PLAYER.getPlayerAction().battleAction(playersInBattle, creaturesInBattle);
        }

        for(Creature CREATURE : creaturesInBattle.values())
        {

            Player targetPlayer = playersInBattle.get(playerNames.get(ThreadLocalRandom.current().nextInt(0, playersInBattle.size())));

            for(Player player : playersInBattle.values())
            {
                if(player == targetPlayer)
                {
                    player.getClient().msgClient(CREATURE.creatureName + " Attacks You!");
                }
                else
                {
                    player.getClient().msgClient(CREATURE.creatureName + " Attacks " + targetPlayer.getPlayerInfo().getName() + "!");
                }
            }

            CREATURE.attack(targetPlayer);

        }


    }



}
