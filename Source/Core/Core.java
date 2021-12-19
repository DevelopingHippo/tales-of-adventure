public class Core implements Runnable {

    // PUBLIC VARIABLES
    public Core CORE;
    public Server SERVER;
    public Database DATABASE;
    public Game GAME = new Game(this);
    public World WORLD = new World(this);
    volatile boolean shutdown = false;

    /*
    +----------------------------+
    | START / CREATION FUNCTIONS |
    +----------------------------+
    */
    public Core()
    {
        CORE = this;
        DATABASE = new Database(CORE);
        SERVER = new Server(1234, this);
        Thread coreThread = new Thread(this);
        coreThread.start();
    }
    public void run()
    {
        startCore();
    }
    public void startCore()
    {
        DATABASE.Log("CORE is Starting" ,"system");
        Thread serverThread = new Thread(SERVER);
        serverThread.start();
    }




    /*
    +---------------------------+
    | GENERAL PURPOSE FUNCTIONS |
    +---------------------------+
    */
    public void initiateShutdown()
    {
        DATABASE.shutdown();
        SERVER.serverShutdown();
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}