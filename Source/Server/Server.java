import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server extends Thread {

    private final Core CORE;
    private final int Port;
    private ServerSocket serverSocket;
    private ArrayList<Client> Clients = new ArrayList<>();

    public Server(int port, Core core)
    {
        Port = port;
        CORE = core;
    }

    @Override
    public void run() {
        try
        {
            serverSocket = new ServerSocket(Port);
            while(!serverSocket.isClosed()) // Always Running and Accepting new Connections
            {
                System.out.println("SERVER: Accepting Clients\n");
                Socket clientSocket = serverSocket.accept();
                System.out.println("\nSERVER: Accepted Client From " + clientSocket + "\n");
                Client client = new Client(clientSocket, CORE);
                Clients.add(client);
                client.start();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public boolean handleClientLogin(String[] loginTokens)
    {
        if(loginTokens != null)
        {
            if(loginTokens.length == 2)
            {
                String loginUser = loginTokens[0];
                String loginPass = CORE.DATABASE.hashPassword(loginTokens[1]);
                System.out.println("SERVER: Handling Login " + loginUser);
                return CORE.DATABASE.userAuthentication(loginUser, loginPass);
            }
        }
        return false;
    }

    public void shutdown()
    {
        for(Client client : Clients)
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
