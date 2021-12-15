
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Database {

    private Connection conn = null;
    private DatabaseUtils utility;
    private int logCount;

    public Database()
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/toa?" + "user=toa&password=P@ssw0rd123!");
            utility = new DatabaseUtils(conn);
            setupDatabase();
        }
        catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }
    // Setup DATABASE values
    public void setupDatabase()
    {
        ResultSet result = utility.queryDatabase("SELECT MAX(logID) AS logCount FROM log");
        logCount = Integer.parseInt((utility.getResult(result, "logCount")));
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

    public boolean createNewAccount(String username, String password)
    {
        try
        {
            if(!utility.checkUser(username))
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

    public boolean createNewCharacter()
    {

        return false;
    }

    public void Log(String logMessage, String type)
    {
        if(logMessage.length() <= 100 && type.length() <= 15)
        {
            logCount++;
            try
            {
                PreparedStatement sqlStmt = conn.prepareStatement("INSERT INTO Log VALUES (?, ?, ?, ? , ?)");
                sqlStmt.setString(1, Integer.toString(logCount));
                sqlStmt.setString(2, String.valueOf(LocalDate.now()));
                sqlStmt.setString(3, String.valueOf(LocalTime.now()));
                sqlStmt.setString(4, type);
                sqlStmt.setString(5, logMessage);
                sqlStmt.executeUpdate();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

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
                System.out.print("[" + results.getString("logID") + "] ");
                System.out.print("[" + results.getString("type") + "] ");
                System.out.print("[" + results.getString("time") +"] ");
                System.out.println(results.getString("message"));
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}