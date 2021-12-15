import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;


public class DatabaseUtils {

    private final Connection conn;

    public DatabaseUtils(Connection dbConn)
    {
        conn = dbConn;
    }


    public ResultSet queryDatabase(String sql)
    {
        Statement stmt = null;
        ResultSet results = null;
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

    public boolean checkUser(String username)
    {
        try
        {
            PreparedStatement sqlStmt = conn.prepareStatement("SELECT username FROM account WHERE username = ?");
            sqlStmt.setString(1, username);
            ResultSet results = sqlStmt.executeQuery();

            return results.next();
        }
        catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return false;
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
        hashedPassword = bytesToHex(encodedHash);
        return hashedPassword;
    }

    private String bytesToHex(byte[] Hash)
    {
        StringBuilder hexString = new StringBuilder(2 * Hash.length);
        for (int i = 0; i < Hash.length; i++)
        {
            String hex = Integer.toHexString(0xff & Hash[i]);
            if(hex.length() == 1)
            {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }



}