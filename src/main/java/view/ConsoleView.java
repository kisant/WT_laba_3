package view;

import controller.Controller;
import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * The type Console view.
 */
public class ConsoleView {

    private static final Logger LOGGER = Logger.getLogger(ConsoleView.class);
    private static final String EXIT_COMMAND = "exit";

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Controller controller = Controller.getInstance();

        Scanner scanner = new Scanner(System.in);
        boolean isWork = true;
        String command = "";
        while (isWork) {
            if (scanner.hasNextLine()) {
                command = scanner.nextLine();
            }
            if (command.equals(EXIT_COMMAND)) {
                LOGGER.info("End of work");
                isWork = false;
                System.out.println("Good buy");
            } else {
                LOGGER.info(command + " start execute");
                System.out.println(controller.executeCommand(command));
                LOGGER.info(command + " end execute");
            }
        }
    }

}