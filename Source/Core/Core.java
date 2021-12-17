public class Core extends Thread {

    // PUBLIC VARIABLES
    public Core CORE;
    public Server SERVER;
    public Database DATABASE;
    public Game GAME = new Game(this);

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

        this.start();
    }
    public void run()
    {
        startCore();
    }
    public void startCore()
    {
        DATABASE.Log("CORE is Starting" ,"system");
        SERVER.start();
    }




    /*
    +---------------------------+
    | GENERAL PURPOSE FUNCTIONS |
    +---------------------------+
    */
    public void initiateShutdown()
    {
        DATABASE.shutdown();
        SERVER.shutdown();
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        this.interrupt();
    }
}