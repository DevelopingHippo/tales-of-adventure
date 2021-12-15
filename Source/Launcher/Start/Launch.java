import java.util.Scanner;

public class Launch {

    public static void main(String[] args)
    {
        Core CORE = new Core();
        CORE.start();
        try
        {
            Thread.sleep(500);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

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
        System.out.println("\n\n************");
        System.out.println("| SHUTDOWN |");
        System.out.println("************");
    }

}