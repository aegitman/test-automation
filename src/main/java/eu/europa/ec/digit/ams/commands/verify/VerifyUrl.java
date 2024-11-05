package eu.europa.ec.digit.ams.commands.verify;

import eu.europa.ec.digit.ams.commands.Command;
import org.openqa.selenium.WebDriver;

public class VerifyUrl extends Command {
    public static final String COMMAND_NAME = "Verify url is ";

    @Override
    public void execute(WebDriver driver, String commandLine) {
        String cmd = trimCommand(commandLine, COMMAND_NAME);
        cmd = removeQuotes(cmd);

        String actualURL = driver.getCurrentUrl();
        if(!actualURL.equals(cmd)) {
            System.out.println("URL verification failed. Expected: " + cmd + ", but got: " + actualURL);
        }
    }

    @Override
    public boolean isCommand(String commandLine) {
        return commandLine.startsWith(COMMAND_NAME);
    }
}
