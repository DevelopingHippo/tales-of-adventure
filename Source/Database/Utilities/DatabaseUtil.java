import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;


public class DatabaseUtil {

    private final Connection conn;

    public DatabaseUtil(Connection dbConn)
    {
        conn = dbConn;
    }


    /*
    +---------------------------+
    | GENERAL PURPOSE FUNCTIONS |
    +---------------------------+
    */

    // Query Database
    public ResultSet queryDatabase(String sql)
    {
        Statement stmt;
        try
        {
            stmt = conn.createStatement();
            if(stmt.execute(sql))
            {
                return stmt.getResultSet();
            }
        }
        catch (SQLException e)
        {
            return null;
        }
        return null;
    }

    // Get Result of Query
    public String getResult(ResultSet results, String column)
    {
        try
        {
            if (results.next())
            {
                return results.getString(column);
            }
        }
        catch (SQLException e)
        {
            return null;
        }
        return null;
    }

    // Check if User is in Database
    public boolean checkUser(String username)
    {
        try
        {
            PreparedStatement sqlStmt = conn.prepareStatement("SELECT username FROM account WHERE username = ?");
            sqlStmt.setString(1, username);
            ResultSet results = sqlStmt.executeQuery();

            return results.isBeforeFirst();
        }
        catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return false;
    }

    public boolean checkCharacterName(String characterName)
    {
        try
        {
            PreparedStatement checkNameStmt = conn.prepareStatement("SELECT name FROM playerCharacter WHERE name = ?");
            checkNameStmt.setString(1, characterName);
            ResultSet results = checkNameStmt.executeQuery();
            return results.isBeforeFirst();
        }
        catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return false;
    }

    public void createNewCharacter(String username, String characterName)
    {
        try
        {
            PreparedStatement createCharStmt = conn.prepareStatement("INSERT INTO playerCharacter(username, name) VALUES (?, ?)");
            createCharStmt.setString(1, username);         // Username
            createCharStmt.setString(2, characterName);    // CharacterName
            createCharStmt.executeUpdate();

            PreparedStatement createMageStmt = conn.prepareStatement("INSERT INTO mageSkill(name) VALUES (?)");
            PreparedStatement createArcheryStmt = conn.prepareStatement("INSERT INTO archerSkill(name) VALUES (?)");
            PreparedStatement createKnightStmt = conn.prepareStatement("INSERT INTO knightSkill(name) VALUES (?)");
            PreparedStatement createRogueStmt = conn.prepareStatement("INSERT INTO rogueSkill(name) VALUES (?)");

            createMageStmt.setString(1, characterName);
            createArcheryStmt.setString(1, characterName);
            createKnightStmt.setString(1, characterName);
            createRogueStmt.setString(1, characterName);

            createMageStmt.executeUpdate();
            createArcheryStmt.executeUpdate();
            createKnightStmt.executeUpdate();
            createRogueStmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    // Login Hashing Function
    public String loginHash(String password)
    {
        String hashedPassword;
        MessageDigest digest = null;
        try
        {
            digest = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        assert digest != null;
        byte[] encodedHash = digest.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
        for (byte hash : encodedHash)
        {
            String hex = Integer.toHexString(0xff & hash);
            if (hex.length() == 1)
            {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        hashedPassword = hexString.toString();
        return hashedPassword;
    }


}