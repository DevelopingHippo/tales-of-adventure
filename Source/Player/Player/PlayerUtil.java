import java.util.LinkedList;

public class PlayerUtil {







    public void loadCharacter(Player PLAYER)
    {
        PLAYER.getClient().alertClient("Load Character");

        LinkedList<String> characterList = PLAYER.getCore().DATABASE.getCharacterList(PLAYER.getUsername());
        PLAYER.getClient().msgClient("[0] Create New Character");
        int characterCount = 0;
        for(String character : characterList)
        {
            characterCount++;
            PLAYER.getClient().msgClient("[" + characterCount + "] " + character);
        }

        int characterChoice = PLAYER.getClient().getNumInput(0, characterList.size() - 1);

        if(characterChoice == 0)
        {
            PLAYER.getCore().DATABASE.createNewCharacter(PLAYER);
        }
        else
        {
            String characterName = characterList.get(characterChoice - 1);
            PLAYER.getPlayerInfo().setName(characterName);
        }
        PLAYER.getCore().DATABASE.loadCharacterInfo(PLAYER);
        PLAYER.getCore().DATABASE.loadCharacterSkills(PLAYER);
    }




}