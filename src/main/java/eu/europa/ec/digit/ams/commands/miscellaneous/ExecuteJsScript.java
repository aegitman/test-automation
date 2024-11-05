package eu.europa.ec.digit.ams.commands.miscellaneous;

import eu.europa.ec.digit.ams.commands.Command;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ExecuteJsScript extends Command {

    public static final String COMMAND_NAME = "Execute JS script ";

    @Override
    public void execute(WebDriver driver, String commandLine) {
        System.out.println(COMMAND_NAME + " ...");
        String cmd = trimCommand(commandLine, COMMAND_NAME);

        cmd = removeQuotes(cmd);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript(cmd, "");
    }

    @Override
    public boolean isCommand(String commandLine) {
        return commandLine.startsWith(COMMAND_NAME);
    }
}
