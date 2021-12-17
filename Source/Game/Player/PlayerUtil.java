import java.util.ArrayList;

public class PlayerUtil {


    public void loadCharacter(Player PLAYER)
    {
        PLAYER.getClient().msgClient("+----------------+");
        PLAYER.getClient().msgClient("| Load Character |");
        PLAYER.getClient().msgClient("+----------------+");

        ArrayList<String> characterList = PLAYER.getCore().DATABASE.getCharacterList(PLAYER.getUsername());


        PLAYER.getClient().msgClient("[0] Create New Character");

        int characterCount = 0;
        for(String character : characterList)
        {
            characterCount++;
            PLAYER.getClient().msgClient("[" + characterCount + "] " + character);
        }

        int characterChoice = PLAYER.getClient().getNumInput(0, characterList.size() - 1);
        /*
            +---------------+
            | TEST FUNCTION |
            +---------------+
         */
        if(characterChoice == 0)
        {
            PLAYER.getCore().DATABASE.createNewCharacter(PLAYER);
        }
        else
        {
            String characterName = characterList.get(characterChoice - 1);
            PLAYER.getPlayerInfo().setName(characterName);
            PLAYER.getCore().DATABASE.loadCharacterInfo(PLAYER.getPlayerInfo());
        }
    }
}
