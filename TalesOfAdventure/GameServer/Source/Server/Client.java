import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class Client implements Runnable {

    // PRIVATE VARIABLES
    private final Core CORE;
    private Player PLAYER;
    private final Socket clientSocket;
    private OutputStream outputStream;
    private BufferedReader reader;
    volatile boolean shutdown = false;
    /*
    +----------------------------+
    | START / CREATION FUNCTIONS |
    +----------------------------+
    */
    public Client(Socket clientsocket, Core core)
    {
        CORE = core;
        clientSocket = clientsocket;
    }
    public void run() {
        try
        {
            while(!shutdown)
            {
                clientLogin();
            }
        }
        catch (IOException e)
        {
            this.disconnect();
            CORE.DATABASE.Log("Client Disconnected " + clientSocket.getInetAddress() + ":" + clientSocket.getPort(), "server" );
        }
    }
    private void clientLogin() throws IOException
    {
        InputStream inputStream = clientSocket.getInputStream();
        outputStream = clientSocket.getOutputStream();
        reader = new BufferedReader(new InputStreamReader(inputStream));
        boolean loginStatus = false;
        String[] tokens = new String[2];
        String username = "notSet";
        for(int i = 0; i < 3; i++)
        {
            alertClient("Username");
            tokens[0] = reader.readLine();
            alertClient("Password");
            tokens[1] = reader.readLine();
            loginStatus = CORE.SERVER.handleClientLogin(tokens);
            if(loginStatus)
            {
                username = tokens[0];
                alertClient("Login Success");
                break;
            }
            else
            {
                msgClient("Login Failed: Try Again");
            }
        }
        if(loginStatus)
        {
            PLAYER = new Player(CORE, this, username);
            CORE.SERVER.addClient(this);
            PLAYER.startPlayer();
        }
        this.disconnect();
    }






    /*
    +------------------+
    | GETTER FUNCTIONS |
    +------------------+
    */
    public Player getPlayer() {return this.PLAYER;}
    public int getNumInput(int min, int max){
        int intInput = -25;
        try
        {
            try
            {
                intInput = Integer.parseInt(Objects.requireNonNull(getInput()));
            }
            catch(NumberFormatException nfe)
            {
                msgClient("BAD INPUT!");
                intInput = getNumInput(min, max);
            }
        }
        catch(NullPointerException npe)
        {
            this.disconnect();
        }

        if(intInput < min || intInput > max)
        {
            msgClient("BAD INPUT!");
            intInput = getNumInput(min, max);
        }
        return intInput;
    }


    public String getStringInput(){return getInput();}







    /*
    +---------------------------+
    | GENERAL PURPOSE FUNCTIONS |
    +---------------------------+
    */
    public void alertClient(String msg)
    {
        int msgSize = msg.length();
        int borderSize = msgSize + 2;

        String border = "+" + "-".repeat(borderSize) + "+";
        msg = "| " + msg + " |";

        msgClient(border);
        msgClient(msg);
        msgClient(border);


    }
    public void msgClient(String msg)
    {
        try
        {
        outputStream.write( (msg +"\r\n").getBytes() );
        }
        catch (IOException e)
        {
            disconnect();
        }
    }
    private String getInput() {
        try
        {
            String input = reader.readLine();

            if(input == null)
            {
                disconnect();
            }
            return input;
        }
        catch (IOException e)
        {
            disconnect();
        }
        return null;
    }

    public void disconnect()
    {
        CORE.SERVER.removeClient(this);
        try
        {
            clientSocket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        this.shutdown = true;
    }
}
