package View;

import Exceptions.CustomException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class TextMenu {
    private Map<String, Command> commands;

    public TextMenu() {
        commands = new HashMap<String, Command>();
    }

    public void addCommand(Command c) {
        commands.put(c.getKey(), c);
    }

    private void printMenu() {
        for (Command com : commands.values()) {
            String line = String.format("%4s : %s", com.getKey(), com.getDescription());
            System.out.println(line);
        }
    }

    public void show() throws CustomException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();

            // Get the option to be executed
            System.out.print("Input the option: ");
            String key = scanner.nextLine();



            Command com = commands.get(key);
            if (com == null) {
                System.out.println("Invalid Option");
                continue;
            }
            else if (com instanceof RunCommand) {
                // Get the name of the file to save the content of the PrgState
                System.out.print("Input the name of the file in which the execution steps will be logged (or leave empty for default option): ");
                String logFilePath = scanner.nextLine();

                RunCommand runCom = (RunCommand)com;

                if (!Objects.equals(logFilePath, "")) {
                    // change the default option
                    runCom.updateLogFilePath(logFilePath);
                }
            }

            // execute the command
            com.execute();

        }
    }
}
