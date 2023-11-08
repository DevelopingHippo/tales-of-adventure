import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;


public class Server implements Runnable {

    //PRIVATE VARIABLES
    private final Core CORE;
    private final int Port;
    private ServerSocket serverSocket;
    private final LinkedList<Client> clients = new LinkedList<>();
    volatile boolean shutdown = false;
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
        while(!shutdown)
        {
            serverStart();
        }
        System.out.println("Server Connection Closed");
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
                Thread clientThread = new Thread(client);
                clientThread.start();
            }
        }
        catch(IOException e)
        {
            System.out.println(e);
            CORE.initiateShutdown();
        }
    }



    /*
    +------------------+
    | GETTER FUNCTIONS |
    +------------------+
    */
    public LinkedList<Client> getActivePlayers() {return clients;}


    public void addClient(Client newClient) {clients.add(newClient);}
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

                if(CORE.DATABASE.userAuthentication(loginUser, loginPass))
                {
                    for(Client otherClients : clients)
                    {
                        if(loginUser.equalsIgnoreCase(otherClients.getPlayer().getUsername()))
                        {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }


    // Shutdown function for the SERVER
    public void serverShutdown()
    {
        while(!clients.isEmpty())
        {
            clients.get(0).alertClient("Server is Shutting Down");
            clients.get(0).disconnect();
        }
        try
        {
            serverSocket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        this.shutdown = true;
    }

}
