import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {

    private final Core CORE;
    private Player PLAYER;
    private final Socket clientSocket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private BufferedReader reader;


    public Client(Socket clientsocket, Core core)
    {
        CORE = core;
        clientSocket = clientsocket;
    }

    // Creates Thread for ClientInstance and goes straight to handleClientSocket
    @Override
    public void run() {
        try {
            System.out.println("Handling Socket");
            handleClientSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Sets outputStream and reader(inputStream) for client, continuously checks reader
    // for information, takes input and splits into tokens and checks tokens
    private void handleClientSocket() throws IOException {
        inputStream = clientSocket.getInputStream();
        outputStream = clientSocket.getOutputStream();
        reader = new BufferedReader(new InputStreamReader(inputStream));
        boolean loginStatus = false;
        String line;
        String[] tokens;

        msgClient("***********************");
        msgClient("| Username & Password |");
        msgClient("***********************");

        while (!loginStatus) {
            line = reader.readLine();
            tokens = StringUtils.split(line);
            loginStatus = CORE.SERVER.handleClientLogin(tokens);
        }
        msgClient("Successful Login");
    }

    // Send Message To Client
    public void msgClient(String msg){
        try {
            outputStream.write( ("\t" + msg +"\r\n").getBytes() );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Disconnect Client From Server
    public void disconnect() {
        msgClient("Disconnecting!");
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.interrupt();
    }
}
