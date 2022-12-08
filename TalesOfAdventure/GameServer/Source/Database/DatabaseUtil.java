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
            // CREATE CHARACTER
            PreparedStatement createCharStmt = conn.prepareStatement("INSERT INTO playerCharacter(username, name) VALUES (?, ?)");
            createCharStmt.setString(1, username);         // Username
            createCharStmt.setString(2, characterName);    // CharacterName
            createCharStmt.executeUpdate();

            // COMBAT SKILLS
            PreparedStatement createOnehandedStmt = conn.prepareStatement("INSERT INTO onehandedSkill(name) VALUES (?)");
            PreparedStatement createTwohandedStmt = conn.prepareStatement("INSERT INTO twohandedSkill(name) VALUES (?)");
            PreparedStatement createRangedStmt = conn.prepareStatement("INSERT INTO rangedSkill(name) VALUES (?)");
            PreparedStatement createMagickStmt = conn.prepareStatement("INSERT INTO magickSkill(name) VALUES (?)");
            PreparedStatement createBlockStmt = conn.prepareStatement("INSERT INTO blockSkill(name) VALUES (?)");
            //GATHERING SKILLS
            PreparedStatement createFishingStmt = conn.prepareStatement("INSERT INTO fishingSkill(name) VALUES (?)");

            //LOAD COMBAT STMT
            createOnehandedStmt.setString(1, characterName);
            createTwohandedStmt.setString(1, characterName);
            createRangedStmt.setString(1, characterName);
            createMagickStmt.setString(1, characterName);
            createBlockStmt.setString(1, characterName);
            //LOAD GATHERING STMT
            createFishingStmt.setString(1, characterName);

            // EXECUTE COMBAT STMT
            createOnehandedStmt.executeUpdate();
            createTwohandedStmt.executeUpdate();
            createRangedStmt.executeUpdate();
            createMagickStmt.executeUpdate();
            createBlockStmt.executeUpdate();
            // EXECUTE GATHERING STMT
            createFishingStmt.executeUpdate();
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