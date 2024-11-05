package eu.europa.ec.digit.ams.commands.verify;

import eu.europa.ec.digit.ams.commands.Command;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VerifyElement extends Command {

    public static final String COMMAND_NAME = "Verify element with ";


    @Override
    public void execute(WebDriver driver, String commandLine) {
        String cmd = trimCommand(commandLine, COMMAND_NAME);
        String selector = "";
        By sel = null;
        if(cmd.startsWith("class")) {
            selector = removeQuotes(cmd.substring("class".length()).trim());
            sel = By.className(selector);
        }
        if(cmd.startsWith("label")) {
            selector = removeQuotes(cmd.substring("label".length()).trim());
            sel = By.xpath("//button[normalize-space()='" + selector + "']");
        }
        if(cmd.startsWith("id")) {
            selector = removeQuotes(cmd.substring("id".length()).trim());
            sel = By.id(selector);
        }

        try {
            if(sel != null && driver.findElement(sel).isDisplayed()){
                System.out.println("Element verification passed.");
            }

        } catch (Exception e) {
            System.out.println("Could not find field.");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isCommand(String commandLine) {
        return commandLine.startsWith(COMMAND_NAME);
    }
}
