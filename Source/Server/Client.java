import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class Client extends Thread {

    // PRIVATE VARIABLES
    private final Core CORE;
    private Player PLAYER;
    private final Socket clientSocket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private BufferedReader reader;

    /*
    +----------------------------+
    | START / CREATION FUNCTIONS |
    +----------------------------+
    */
    public Client(Socket clientsocket, Core core) {
        CORE = core;
        clientSocket = clientsocket;
    }
    @Override
    public void run() {
        try
        {
            clientLogin();
        }
        catch (IOException e)
        {
            this.disconnect();
            CORE.DATABASE.Log("Client Disconnected " + clientSocket.getInetAddress() + ":" + clientSocket.getPort(), "server" );
        }
    }
    private void clientLogin() throws IOException
    {
        inputStream = clientSocket.getInputStream();
        outputStream = clientSocket.getOutputStream();
        reader = new BufferedReader(new InputStreamReader(inputStream));
        boolean loginStatus = false;
        String[] tokens = new String[2];
        String username = "notSet";
        for(int i = 0; i < 3; i++)
        {
            msgClient("+----------+");
            msgClient("| Username |");
            msgClient("+----------+");
            tokens[0] = reader.readLine();
            msgClient("+----------+");
            msgClient("| Password |");
            msgClient("+----------+");
            tokens[1] = reader.readLine();
            loginStatus = CORE.SERVER.handleClientLogin(tokens);
            if(loginStatus)
            {
                username = tokens[0];
                msgClient("+---------------+");
                msgClient("| Login Success |");
                msgClient("+---------------+");
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
        int intInput = Integer.parseInt(Objects.requireNonNull(getInput()));
        if(intInput < min && intInput > max)
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
    public void msgClient(String msg){try {outputStream.write( (msg +"\r\n").getBytes() );} catch (IOException e) {disconnect();}}
    private String getInput() {try {return reader.readLine();} catch (IOException e) {disconnect();} return null;}

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
        this.interrupt();
    }
}
