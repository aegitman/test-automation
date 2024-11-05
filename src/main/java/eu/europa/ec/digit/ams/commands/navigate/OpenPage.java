package eu.europa.ec.digit.ams.commands.navigate;

import eu.europa.ec.digit.ams.commands.Command;
import org.openqa.selenium.WebDriver;

public class OpenPage extends Command {

    public static final String COMMAND_NAME = "Open page";

    @Override
    public void execute(WebDriver driver, String cmd) {
        String trim = removeQuotes(trimCommand(cmd, COMMAND_NAME));
        System.out.println(COMMAND_NAME + " " + trim + " ...");
        driver.get(trim);
        try {
            Thread.sleep(WAIT_AFTER_NAV);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isCommand(String commandLine) {
         return commandLine.startsWith(COMMAND_NAME);
    }
}
