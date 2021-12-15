public class ConsoleUtility {


    public void printHelp(String help)
    {

        if(help.isEmpty())
        {
            //DEFAULT HELP PRINT
        }
        else if(help.equalsIgnoreCase("log"))
        {
            System.out.println("Log Commands: log /{option} {amount}");
            System.out.println("option = [all, system, game, admin, account] (Default=ALL)");
            System.out.println("amount = Amount of Logs to Return (Default=50)");
        }


    }


}
