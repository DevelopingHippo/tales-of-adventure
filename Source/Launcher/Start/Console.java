import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class Console {

    private final Core CORE;
    private final ConsoleUtility utility = new ConsoleUtility();

    public Console(Core core) {
        CORE = core;
    }

    //Always running and checking user input
    public void startConsole()
    {
        // Always On CMD Line for Server Administrator
        System.out.println("******************");
        System.out.println("| System Command |");
        System.out.println("******************");
        Scanner scan = new Scanner(System.in);
        String cmd = null;
        while (true)
        {
            System.out.print("\nCMD: ");
            cmd = scan.nextLine();
            if(cmd.equalsIgnoreCase("quit") || cmd.equalsIgnoreCase("exit"))
            {
                CORE.initiateShutdown();
                System.out.println("\n***********************");
                System.out.println("| Initiating Shutdown |");
                System.out.println("***********************");
                break;
            }
            else if(!cmd.isEmpty())
            {
                checkCommand(cmd);
            }
        }
    }

    //Checks input for command
    private void checkCommand(String input)
    {
        String[] cmdLine = StringUtils.split(input);
        String cmd = cmdLine[0];
        String option1 = null;
        String option2 = null;
        String option3 = null;

        if(cmdLine.length > 1)
        {
            option1 = cmdLine[1];
        }
        if(cmdLine.length > 2)
        {
            option2 = cmdLine[2];
        }
        if(cmdLine.length > 3)
        {
            option3 = cmdLine[3];
        }

        // Checks for different Types of Commands
        if(cmd.equalsIgnoreCase("log"))
        {
            logCommand(option1, option2);
        }
    }


    private void logCommand(String option, String amount)
    {
        int logAmount;
        try
        {
            logAmount = Integer.parseInt(amount);
            if (!(logAmount > 0 && logAmount < 500))
            {
                logAmount = 50;
            }
        }
        catch (NumberFormatException e)
        {
            logAmount = 50;
        }

        if (option == null || option.equalsIgnoreCase("/help"))
        {
            utility.printHelp("log");
        }
        else if (option.equalsIgnoreCase("/all"))
        {
            CORE.DATABASE.getLogs("all", logAmount);
        }
        else if (option.equalsIgnoreCase("/account"))
        {
            CORE.DATABASE.getLogs("account", logAmount);
        }
        else if (option.equalsIgnoreCase("/system"))
        {
            CORE.DATABASE.getLogs("system", logAmount);
        }
        else if (option.equalsIgnoreCase("/game"))
        {
            CORE.DATABASE.getLogs("game", logAmount);
        }
        else if (option.equalsIgnoreCase("/admin"))
        {
            CORE.DATABASE.getLogs("admin", logAmount);
        }
    }




}