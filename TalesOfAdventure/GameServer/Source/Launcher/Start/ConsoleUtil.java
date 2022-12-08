public class ConsoleUtil {


    public void printHelp(String help)
    {
        if(help.equalsIgnoreCase("log"))
        {
            printLogHelp();
        }
        else
        {
            printAllHelp();
        }
    }


    /*
    +----------------------+
    | PRINT HELP FUNCTIONS |
    +----------------------+
    */

    private void printAllHelp()
    {
        System.out.println("Log Commands: log /{option} {amount}");
    }

    private void printLogHelp()
    {
        System.out.println("Log Commands: log /{option} {amount}");
        System.out.println("option = [all, system, game, admin, account] (Default=ALL)");
        System.out.println("amount = Amount of Logs to Return (Default=50)");
    }


}
