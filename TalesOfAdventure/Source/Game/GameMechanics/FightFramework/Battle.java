import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Battle implements Runnable {
    Thread battleThread;


    // General Battle Information
    private boolean battleState = true;
    private final ArrayList<String> battleAlerts = new ArrayList<>();

    // Loot
    private final ArrayList<Item> lootPool = new ArrayList<>();
    private int expPool = 0;
    private final HashMap<Player, Item> specialRewards = new HashMap<>();

    // Players & Creatures Queued to join the Battle
    private final HashMap<String, Player> playersJoining = new HashMap<>();
    private final HashMap<String, Creature> joiningCreatures = new HashMap<>();

    // Active Players & Creatures in the Battle
    private final ArrayList<String> playerNames = new ArrayList<>();
    private final ArrayList<String> creatureNames = new ArrayList<>();
    private final HashMap<String, Player> playersInBattle = new HashMap<>();
    private final HashMap<String, Creature> creaturesInBattle = new HashMap<>();

    // Players & Creatures in Queue to Leave
    private final ArrayList<String> playersLeaving = new ArrayList<>();
    private final ArrayList<String> creaturesLeaving = new ArrayList<>();

    /*
    +----------------------------+
    | START / CREATION FUNCTIONS |
    +----------------------------+
    */
    public Battle(Player player, Creature creature)
    {
        addCreatureToBattle(creature);
        addPlayerToBattle(player);
        battleThread = new Thread(this);
        battleThread.start();
    }
    @Override
    public void run()
    {   // Initiates new Thread for Battle
        startBattle();
    }


    /*
    +------------------+
    | GETTER FUNCTIONS |
    +------------------+
    */
    public boolean getBattleState() {return this.battleState;}



    /*
    +----------------------------+
    | JOINING / ADDING FUNCTIONS |
    +----------------------------+
    */

    // Called by Other Players when they start attacking a Creature that someone else is already battling
    public void playerJoinBattle(Player newPlayer)
    {
        playersJoining.put(newPlayer.getPlayerInfo().getName(), newPlayer);
        String allCreatures = creatureNames.toString().replaceAll("\\[", "").replaceAll("]","").replaceAll("\\{", "").replaceAll("}", "");
        newPlayer.getClient().alertClient("BATTLE STARTED: " + allCreatures);
    }
    // Adds PLAYER to playersInBattle list, adds message to BattleAlerts queue
    private void addPlayerToBattle(Player PLAYER)
    {
        playersInBattle.put(PLAYER.getPlayerInfo().getName(), PLAYER);
        playerNames.add(PLAYER.getPlayerInfo().getName());
        battleAlerts.add(PLAYER.getPlayerInfo().getName() + " Joined The Battle!");
    }

    // Called by Other Creatures when they attack a Player already in Battle
    public void creatureJoinBattle(Creature creature)
    {   // If multiple of one monster, add variance to their name (creatureName + (count))
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

    // Adds creature to creaturesInBattle, adds message to BattleAlerts queue
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





    /*
    +----------------+
    | FLEE FUNCTIONS |
    +----------------+
    */

    // Called when PLAYER chooses to Flee the Battle, adds PLAYER to playersLeaving queue
    public void playerFlee(Player PLAYER)
    {
        playersLeaving.add(PLAYER.getPlayerInfo().getName());
    }





    /*
    +------------------+
    | BATTLE FUNCTIONS |
    +------------------+
    */

    // Battle Check Loop, exits when either 0 Players OR 0 Creatures in the Battle
    public void startBattle()
    {
        while (creaturesInBattle.size() > 0 && playersInBattle.size() > 0)
        {
            if (!joiningCreatures.isEmpty() || !playersJoining.isEmpty())
            {
                addJoiningPlayersAndCreatures();
            }
            if (!battleAlerts.isEmpty())
            {
                printBattleAlerts();
            }
            if (!playersLeaving.isEmpty())
            {
                removePlayers();
            }
            if(creaturesInBattle.size() > 0 && playersInBattle.size() > 0)
            {
                battleLoop();
            }
            else
            {
                break;
            }
        }
        // PLAYERS won trigger Victory
        if (creaturesInBattle.isEmpty())
        {
            battleVictory();
        }
        // END BATTLE
        endBattle();
    }

    // Adds PLAYERS and CREATURES in join Queue to the Battle
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

    // Print out Battle Alerts to all PLAYERS
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

    // Loop through PLAYERS and CREATURES, taking turns doing PlayerActions and CreatureActions
    private void battleLoop()
    {
        checkDeathStatus();
        // PLAYERS Turn
        for (Player PLAYER : playersInBattle.values())
        {
            if(!creaturesInBattle.isEmpty())
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
            checkDeathStatus();
            removeCreatures();
            printBattleAlerts();
        }

        printBattleAlerts();

        // CREATURES Turn
        for(Creature CREATURE : creaturesInBattle.values())
        {
            if(!playersInBattle.isEmpty())
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
            checkDeathStatus();
            removePlayers();
            printBattleAlerts();
        }
        checkDeathStatus();
        printBattleAlerts();
    }

    private void checkDeathStatus()
    {
        for(String creaturename : creaturesInBattle.keySet())
        {
            Creature creature = creaturesInBattle.get(creaturename);
            if(creature.health <= 0)
            {
                creatureDefeated(creaturename);
            }
        }
        for(Player PLAYER : playersInBattle.values())
        {
            if(PLAYER.getPlayerInfo().getHealth() <= 0)
            {
                playerDefeated(PLAYER);
            }
        }
        removeCreatures();
        removePlayers();
    }

    private void playerDefeated(Player PLAYER)
    {
        battleAlerts.add(PLAYER.getPlayerInfo().getName() + " was Killed!");
        playersLeaving.add(PLAYER.getPlayerInfo().getName());
    }

    // Remove CREATURE from Battle when Defeated, add their loot to the LootPool
    private void creatureDefeated(String removeCreaturename)
    {
        battleAlerts.add(removeCreaturename + " was Killed!");
        expPool = expPool + creaturesInBattle.get(removeCreaturename).exp;
        lootPool.addAll(creaturesInBattle.get(removeCreaturename).getLoot());
        creaturesLeaving.add(removeCreaturename);
    }

    // Remove PLAYERS from playersLeaving queue from the playersInBattle
    private void removePlayers()
    {
        for(String playername : playersLeaving)
        {
            playersInBattle.get(playername).leaveBattle();
            playersInBattle.remove(playername);
            playerNames.remove(playername);
        }
        playersLeaving.clear();
    }
    private void removeCreatures()
    {
        for(String creaturename : creaturesLeaving)
        {
            creaturesInBattle.get(creaturename).leaveBattle();
            creaturesInBattle.remove(creaturename);
            creatureNames.remove(creaturename);
        }
        creaturesLeaving.clear();
    }

    // Called When all CREATURES ARE DEFEATED
    private void battleVictory()
    {
        battleAlerts.add("VICTORY");
        printBattleAlerts();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        battleRewards();
    }
    private void battleRewards()
    {
        for(Item rewardItem : lootPool)
        {
            Player rewardPlayer = playersInBattle.get(playerNames.get(ThreadLocalRandom.current().nextInt(0, playersInBattle.size())));
            rewardPlayer.getPlayerInfo().addLootItem(rewardItem);
            rewardPlayer.getClient().msgClient("Reward: " + rewardItem.itemName);
        }
        lootPool.clear();
    }
    public void endBattle()
    {
        playersLeaving.addAll(playersInBattle.keySet());
        if(!playersLeaving.isEmpty())
        {
            removePlayers();
        }
        creaturesInBattle.clear();
        creatureNames.clear();
        battleState = false;
        battleThread.interrupt();
    }

}