package eu.europa.ec.digit.ams.commands.verify;

import eu.europa.ec.digit.ams.commands.Command;
import org.openqa.selenium.WebDriver;

public class VerifyPageTitle extends Command {

    public static final String COMMAND_NAME = "Verify page title is";

    @Override
    public void execute(WebDriver driver, String commandLine) {
        String expectedTitle = trimCommand(commandLine, COMMAND_NAME);

        String actualTitle = driver.getTitle();
        if (!actualTitle.equals(expectedTitle)) {
            System.out.println("Page title verification failed. Expected: " + expectedTitle + ", but got: " + actualTitle);
        } else {
            System.out.println("Page title verification passed.");
        }
    }

    @Override
    public boolean isCommand(String commandLine) {
        return commandLine.startsWith(COMMAND_NAME);
    }
}
