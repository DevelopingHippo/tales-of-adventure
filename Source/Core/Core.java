public class Core extends Thread {

    public Core CORE;
    public Server SERVER;
    public Database DATABASE;

    public Core()
    {
        CORE = this;
        SERVER = new Server(1234, this);
        SERVER.start();
        DATABASE = new Database();

    }

    public void run()
    {
        DATABASE.Log("CORE is Starting" ,"system");
        startCore();
    }


    public void startCore()
    {
        System.out.println("Starting Core\n");
    }

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