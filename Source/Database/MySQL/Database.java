
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Database {

    // PRIVATE VARIABLES
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
        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/toa?" + "user=toa&password=P@ssw0rd123!");
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
        System.out.println();
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

    public boolean loadCharacterInfo(PlayerInfo playerInfo)
    {
        if(!utility.checkCharacterName(playerInfo.getName()))
        {
            ResultSet results = utility.queryDatabase("SELECT * FROM playerCharacter WHERE name = '" + playerInfo.getName() + "'");
            try
            {
                if(results.next())
                {
                    playerInfo.addLevel(Integer.parseInt(results.getString("level")));
                    playerInfo.addExp(Integer.parseInt(results.getString("totExp")));
                    return true;
                }
            }
            catch (SQLException e)
            {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
            }
        }
        return false;
    }

    public void createNewCharacter(Player PLAYER)
    {
        PLAYER.getClient().msgClient("+---------------+");
        PLAYER.getClient().msgClient("| New Character |");
        PLAYER.getClient().msgClient("+---------------+");
        PLAYER.getClient().msgClient("Name: ");
        String characterName = PLAYER.getClient().getStringInput();

        while(utility.checkCharacterName(characterName))
        {
            PLAYER.getClient().msgClient("Character Name Already Exists");
            PLAYER.getClient().msgClient("Try Again");
            PLAYER.getClient().msgClient("Name: ");
            characterName = PLAYER.getClient().getStringInput();
        }
        try
        {
            PreparedStatement createCharStmt = conn.prepareStatement("INSERT INTO playerCharacter VALUES (?, ?, ?, ?)");
            createCharStmt.setString(1, PLAYER.getUsername());
            createCharStmt.setString(2, characterName);
            createCharStmt.setString(3, "0");
            createCharStmt.setString(4, "0");
            createCharStmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        PLAYER.getPlayerInfo().setName(characterName);
        loadCharacterInfo(PLAYER.getPlayerInfo());
    }

    public ArrayList<String> getCharacterList(String username)
    {
        ResultSet results = utility.queryDatabase("SELECT name, level FROM playerCharacter WHERE username='" + username + "'");
        ArrayList<String> characterList = new ArrayList<>();
        while (true)
        {
            try
            {
                if (!results.next()) break;
                characterList.add(results.getString("name"));
            }
            catch (SQLException e)
            {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
            }
        }
        return characterList;
    }

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