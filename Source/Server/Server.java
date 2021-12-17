import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;


public class Server extends Thread {

    //PRIVATE VARIABLES
    private final Core CORE;
    private final int Port;
    private ServerSocket serverSocket;
    private final ArrayList<Client> clients = new ArrayList<>();

    /*
    +----------------------------+
    | START / CREATION FUNCTIONS |
    +----------------------------+
    */
    public Server(int port, Core core)
    {
        Port = port;
        CORE = core;
    }
    @Override
    public void run() {
        serverStart();
    }
    private void serverStart(){
        try
        {
            serverSocket = new ServerSocket(Port);
            CORE.DATABASE.Log("SERVER is Starting", "server");
            while(!serverSocket.isClosed()) // Always Running and Accepting new Connections
            {
                Socket clientSocket = serverSocket.accept();
                CORE.DATABASE.Log("Accepted Client From " + clientSocket.getInetAddress() + ":" + clientSocket.getPort(), "server" );
                Client client = new Client(clientSocket, CORE);
                client.start();
            }
        }
        catch(IOException e)
        {
            System.out.println("Server Connection Closed");
        }
    }



    /*
    +------------------+
    | GETTER FUNCTIONS |
    +------------------+
    */
    public ArrayList<Client> getActivePlayers() {return clients;}


    public void addClient(Client newClient) {System.out.println(newClient.getPlayer().getUsername());clients.add(newClient);}
    public void removeClient(Client removeClient)
    {

        clients.remove(removeClient);


//        for(int i = 0; i < clients.size(); i++)
//        {
//            Client checkClient = clients.get(i);
//            if((checkClient.getPlayer().getUsername().equalsIgnoreCase(removeClient.getPlayer().getUsername())))
//            {
//                clients.remove(i);
//                break;
//            }
//        }
    }



    /*
    +---------------------------+
    | GENERAL PURPOSE FUNCTIONS |
    +---------------------------+
    */
    // Checks if USER exists in DATABASE
    public boolean handleClientLogin(String[] loginTokens)
    {
        if(loginTokens != null)
        {
            if(loginTokens.length == 2)
            {
                String loginUser = loginTokens[0];
                String loginPass = CORE.DATABASE.hashPassword(loginTokens[1]);
                CORE.DATABASE.Log("SERVER: Handling Login " + loginUser, "server");
                return CORE.DATABASE.userAuthentication(loginUser, loginPass);
            }
        }
        return false;
    }


    // Shutdown function for the SERVER
    public void shutdown()
    {
        for(Client client : clients)
        {
            client.msgClient("***************************");
            client.msgClient("| Server is Shutting Down |");
            client.msgClient("***************************");
            client.disconnect();
        }
        try
        {
            serverSocket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        this.interrupt();
    }

}
