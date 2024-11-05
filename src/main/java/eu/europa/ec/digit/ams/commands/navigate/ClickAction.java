package eu.europa.ec.digit.ams.commands.navigate;

import eu.europa.ec.digit.ams.commands.Command;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClickAction extends Command {

    public static final String COMMAND_LINK = "Click link with ";
    public static final String COMMAND_BUTTON = "Click button with ";
    public static final String COMMAND_ELEMENT = "Click element with ";

    @Override
    public void execute(WebDriver driver, String commandLine) {
        String cmd = trimCommand(commandLine, COMMAND_LINK);
        cmd = trimCommand(cmd, COMMAND_BUTTON);
        cmd = trimCommand(cmd, COMMAND_ELEMENT);
        // class is "className"

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
        if(cmd.startsWith("xpath")) {
            selector = removeQuotes(cmd.substring("xpath".length()).trim());
            sel = By.xpath(selector);
        }


        try {
            if(sel != null){
                driver.findElement(sel).click();
                Thread.sleep(WAIT_AFTER_NAV);
            }

        } catch (Exception e) {
            System.out.println("Could not find field.");
            System.out.println(e.getMessage());
        }

        if(cmd.startsWith("text")) {
            String jsLabel = removeQuotes(cmd.substring("text".length()).trim());
            try {
                var label = driver.findElements(By.tagName("span"));
                boolean found = false;
                for (var l : label) {
                    if(l.getText().trim().equalsIgnoreCase(jsLabel) && !found) {
                        l.click();
                        break;
                    }
                }
                Thread.sleep(WAIT_AFTER_NAV);
            } catch (Exception e) {
                System.out.println("Could not find field with text: " + jsLabel);
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public boolean isCommand(String commandLine) {
        return commandLine.startsWith(COMMAND_LINK) || commandLine.startsWith(COMMAND_BUTTON) || commandLine.startsWith(COMMAND_ELEMENT);
    }
}
