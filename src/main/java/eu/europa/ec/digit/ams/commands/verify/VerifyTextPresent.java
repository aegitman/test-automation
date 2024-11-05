package eu.europa.ec.digit.ams.commands.verify;

import eu.europa.ec.digit.ams.commands.Command;
import org.openqa.selenium.WebDriver;

public class VerifyTextPresent extends Command {
    public static final String COMMAND_NAME = "Verify text ";

    @Override
    public void execute(WebDriver driver, String commandLine) {
        String cmd = trimCommand(commandLine, COMMAND_NAME);
        cmd = cmd.replace("is present", "");
        cmd  = removeQuotes(cmd);

        if(driver.getPageSource().contains(cmd)){
            System.out.println("Text verification passed.");
        } else {
            System.out.println("Text verification failed. Expected: " + cmd);
        }
    }

    @Override
    public boolean isCommand(String commandLine) {
        return commandLine.startsWith(COMMAND_NAME);
    }
}
