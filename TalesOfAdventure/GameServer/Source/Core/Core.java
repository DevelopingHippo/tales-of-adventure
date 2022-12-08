public class Core implements Runnable {

    // PUBLIC VARIABLES
    public Core CORE;
    public Server SERVER;
    public Database DATABASE;
    public final Game GAME = new Game(this);
    public final World WORLD = new World(this);
    volatile boolean shutdown = false;

    // Create new Database, Build World, and Start Server Listening on 2777 and create new thread
    public Core()
    {
        CORE = this;
        WORLD.buildWorld();
        DATABASE = new Database(CORE);
        SERVER = new Server(2777, this);
        Thread coreThread = new Thread(this);
        coreThread.start();
    }

    // Start Thread which runs startCore()
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

    // Shutdown Initiated by Console Shutdown command
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