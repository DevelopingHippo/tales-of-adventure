public class Launcher {

    public static void main(String[] args)
    {
        Core CORE = new Core();

        Console sysConsole = new Console(CORE);
        sysConsole.startConsole();

        // 10 Second Counter while Shutting Down
        for(int i = 10; i >= 0; i--)
        {
            System.out.print(i + " ");
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("+----------+");
        System.out.println("| SHUTDOWN |");
        System.out.println("+----------+");
    }

}