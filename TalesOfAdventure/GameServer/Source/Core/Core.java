public class Core implements Runnable {

    // PUBLIC VARIABLES
    public Core CORE;
    public Server SERVER;
    public Database DATABASE;
    public final Game GAME = new Game(this);
    public final World WORLD = new World(this);
    volatile boolean shutdown = false;

    /*
    +----------------------------+
    | START / CREATION FUNCTIONS |
    +----------------------------+
    */
    public Core()
    {
        CORE = this;
        WORLD.buildWorld();
        DATABASE = new Database(CORE);
        SERVER = new Server(2777, this);
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