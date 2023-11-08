import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

public class Database {

    private Core CORE;
    private Connection conn = null;
    private DatabaseUtil utility;
    private int logCount;

    /*
    +----------------------------+
    | START / CREATION FUNCTIONS |
    +----------------------------+
    */
    public Database(Core core)
    {
        CORE = core;
        // PRIVATE VARIABLES
        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://db_toa:3306/toa?" + "user=toa&password=P@ssw0rd123!");
            utility = new DatabaseUtil(conn);
            setupDatabase();
        }
        catch (SQLException e)
        {
            System.out.println("+------------------------------------------------+");
            System.out.println("| Terminal Error: Connection to Database Failed! |");
            System.out.println("+------------------------------------------------+");
            System.exit(0);
        }
    }
    // Setup DATABASE values
    public void setupDatabase()
    {
        ResultSet result = utility.queryDatabase("SELECT MAX(logID) AS logCount FROM log");
        logCount = Integer.parseInt((utility.getResult(result, "logCount")));
    }






    /*
    +---------------------------+
    | GENERAL PURPOSE FUNCTIONS |
    +---------------------------+
    */

    //Public function for creating password hashes
    public String hashPassword(String password){
        return utility.loginHash(password);
    }

    //User Login Authentication, Queries database and checks for user
    public boolean userAuthentication(String username, String password)
    {
        Log(("Login Attempted For: " + username), "account");
        if (utility.checkUser(username))
        {
            try
            {
                PreparedStatement sqlStmt = conn.prepareStatement("SELECT username FROM account WHERE username = ? AND password = ?");
                sqlStmt.setString(1, username);
                sqlStmt.setString(2, password);
                ResultSet results = sqlStmt.executeQuery();
                if (results.next())
                {
                    Log(("Login Successful: " + username), "account");
                    return true;
                }
                else
                {
                    Log(("Login Failed: " + username), "account");
                    return false;
                }
            }
            catch (SQLException e)
            {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
            }
        }
        Log(("Login Failed: " + username + " Does Not Exist"), "account");
        return false;
    }

    //Create New Account in Database
    public boolean createNewAccount(String username, String password)
    {
        try
        {
            if(utility.checkUser(username))
            {
                PreparedStatement sqlStmt = conn.prepareStatement("INSERT INTO account VALUES ( ? , ? )");
                sqlStmt.setString(1, username);
                sqlStmt.setString(2, password);
                sqlStmt.executeUpdate();
                if(utility.checkUser(username))
                {
                    Log(("New Account Created: " + username), "account");
                    return true;
                }
            }
            Log(("Failed to Create New Account: " + username), "account");
            return false;
        }
        catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        Log(("Failed to Create New Account: " + username), "account");
        return false;
    }





    /*
    +---------------------+
    | CHARACTER FUNCTIONS |
    +---------------------+
    */

    public void loadCharacterInfo(Player PLAYER)
    {
        if(utility.checkCharacterName(PLAYER.getPlayerInfo().getName()))
        {
            ResultSet results = utility.queryDatabase("SELECT * FROM playerCharacter WHERE name = '" + PLAYER.getPlayerInfo().getName() + "'");
            try
            {
                if(results.next())
                {
                    PLAYER.getPlayerInfo().addLevel(results.getInt("level"));
                    PLAYER.getPlayerInfo().addExp(results.getInt("totExp"));
                    PLAYER.getPlayerInfo().setWorldLocation(results.getString("worldLocation"));
                }
            }
            catch (SQLException e)
            {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
            }
        }
    }

    public void loadCharacterSkills(Player PLAYER)
    {
        if(utility.checkCharacterName(PLAYER.getPlayerInfo().getName()))
        {
            try
            {
                /*
                +---------------+
                | COMBAT SKILLS |
                +---------------+
                */

                // Onehanded Skills
                ResultSet onehandedResult = utility.queryDatabase("SELECT * FROM onehandedSkill WHERE name = '" + PLAYER.getPlayerInfo().getName() + "'");
                if(onehandedResult.next())
                {
                    PLAYER.getPlayerSkills().onehandedSkill.addLevel(onehandedResult.getInt("level"));
                    PLAYER.getPlayerSkills().onehandedSkill.addExp(onehandedResult.getInt("totExp"));
                    PLAYER.getPlayerSkills().onehandedSkill.activateSkill("Quick Stab", onehandedResult.getBoolean("quickStab"));
                }

                // Twohanded Skills
                ResultSet twohandedResults = utility.queryDatabase("SELECT * FROM twohandedSkill WHERE name = '" + PLAYER.getPlayerInfo().getName() + "'");
                if(twohandedResults.next())
                {
                    PLAYER.getPlayerSkills().twohandedSkill.addLevel(twohandedResults.getInt("level"));
                    PLAYER.getPlayerSkills().twohandedSkill.addExp(twohandedResults.getInt("totExp"));
                    PLAYER.getPlayerSkills().twohandedSkill.activateSkill("Heavy Swing", twohandedResults.getBoolean("heavySwing"));
                }

                // Ranged Skills
                ResultSet rangedResults = utility.queryDatabase("SELECT * FROM rangedSkill WHERE name = '" + PLAYER.getPlayerInfo().getName() + "'");
                if(rangedResults.next())
                {
                    PLAYER.getPlayerSkills().rangedSkill.addLevel(rangedResults.getInt("level"));
                    PLAYER.getPlayerSkills().rangedSkill.addExp(rangedResults.getInt("totExp"));
                    PLAYER.getPlayerSkills().rangedSkill.activateSkill("Double Shot", rangedResults.getBoolean("doubleShot"));
                }

                // Magick Skills
                ResultSet magickResults = utility.queryDatabase("SELECT * FROM magickSkill WHERE name = '" + PLAYER.getPlayerInfo().getName() + "'");
                if(magickResults.next())
                {
                    PLAYER.getPlayerSkills().magickSkill.addLevel(magickResults.getInt("level"));
                    PLAYER.getPlayerSkills().magickSkill.addExp(magickResults.getInt("totExp"));
                    PLAYER.getPlayerSkills().magickSkill.activateSkill("Fireball", magickResults.getBoolean("fireball"));
                }

                // Blocking Skills
                ResultSet blockResults = utility.queryDatabase("SELECT * FROM blockSkill WHERE name = '" + PLAYER.getPlayerInfo().getName() + "'");
                if(blockResults.next())
                {
                    PLAYER.getPlayerSkills().blockSkill.addLevel(blockResults.getInt("level"));
                    PLAYER.getPlayerSkills().blockSkill.addExp(blockResults.getInt("totExp"));
                    PLAYER.getPlayerSkills().blockSkill.activateSkill("Ready Block", blockResults.getBoolean("readyBlock"));
                }

                /*
                +------------------+
                | GATHERING SKILLS |
                +------------------+
                */

                // Fishing Skills
                ResultSet fishingResults = utility.queryDatabase("SELECT * FROM fishingSkill WHERE name = '" + PLAYER.getPlayerInfo().getName() + "'");
                if(fishingResults.next())
                {
                    PLAYER.getPlayerSkills().fishingSkill.addLevel(fishingResults.getInt("level"));
                    PLAYER.getPlayerSkills().fishingSkill.addExp(fishingResults.getInt("totExp"));
                    PLAYER.getPlayerSkills().fishingSkill.activateSkill("Fresh Water Fishing", fishingResults.getBoolean("freshWaterFishing"));
                }
            }
            catch (SQLException e)
            {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
            }
        }
    }

    public void createNewCharacter(Player PLAYER)
    {
        PLAYER.getClient().alertClient("New Character");
        PLAYER.getClient().msgClient("Name: ");
        String characterName = PLAYER.getClient().getStringInput();

        while(utility.checkCharacterName(characterName))
        {
            PLAYER.getClient().msgClient("Character Name Already Exists");
            PLAYER.getClient().msgClient("Try Again");
            PLAYER.getClient().msgClient("Name: ");
            characterName = PLAYER.getClient().getStringInput();
        }
        utility.createNewCharacter(PLAYER.getUsername(), characterName);
        PLAYER.getPlayerInfo().setName(characterName);
    }

    public LinkedList<String> getCharacterList(String username)
    {
        ResultSet results = utility.queryDatabase("SELECT name, level FROM playerCharacter WHERE username='" + username + "'");
        LinkedList<String> characterList = new LinkedList<>();
        try
        {
            while (results.next()) {
                characterList.add(results.getString("name"));
            }
        }
        catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return characterList;
    }





    /*
    +------------------+
    | SYSTEM FUNCTIONS |
    +------------------+
    */

    // Insert Log into Database
    public void Log(String logMessage, String type)
    {
        if(logMessage.length() <= 100 && type.length() <= 15)
        {
            int logNum = logCount++;
            try
            {
                PreparedStatement sqlStmt = conn.prepareStatement("INSERT INTO Log VALUES (?, ?, ?, ? , ?)");
                sqlStmt.setString(1, String.valueOf(logNum));
                sqlStmt.setString(2, String.valueOf(LocalDate.now()));
                sqlStmt.setString(3, String.valueOf(LocalTime.now()));
                sqlStmt.setString(4, type);
                sqlStmt.setString(5, logMessage);
                sqlStmt.executeUpdate();
            }
            catch (SQLException e)
            {
                if(e.getErrorCode() == 1062)
                {
                    this.Log(logMessage, type);
                }
                else
                {
                    System.out.println("SQLException: " + e.getMessage());
                    System.out.println("SQLState: " + e.getSQLState());
                    System.out.println("VendorError: " + e.getErrorCode());
                }
            }
        }
    }

    // Prints out Logs in the Database
    public void getLogs(String type, int logAmount)
    {
        ResultSet results = null;
        if (type == null || type.equalsIgnoreCase("all") || type.isEmpty())
        {
            results = utility.queryDatabase("SELECT * FROM log LIMIT " + logAmount);
        }
        else
        {
            results = utility.queryDatabase("SELECT * FROM log WHERE type='" + type + "' ORDER BY logID ASC LIMIT " + logAmount);
        }
        System.out.println("Showing Log Results: " + logAmount);
        while (true)
        {
            try
            {
                if (!results.next()) break;
                System.out.print("[" + results.getString("logID") + "]\t");
                System.out.print("[" + results.getString("type") + "]\t");
                System.out.print("[" + results.getString("time") +"]\t");
                System.out.println(results.getString("message"));
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    // Called during CORE Shutdown
    public void shutdown()
    {
        try
        {
            conn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}