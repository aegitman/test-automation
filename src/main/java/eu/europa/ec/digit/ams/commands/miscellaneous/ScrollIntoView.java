package eu.europa.ec.digit.ams.commands.miscellaneous;

import eu.europa.ec.digit.ams.commands.Command;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollIntoView extends Command {

    public static final String COMMAND_NAME = "Scroll into view element with ";

    @Override
    public void execute(WebDriver driver, String commandLine) {
        System.out.println(COMMAND_NAME + " ...");
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
            if(sel != null) {
                WebElement element = driver.findElement(sel);

                JavascriptExecutor js = (JavascriptExecutor)driver;
                js.executeScript("arguments[0].scrollIntoView({block: \"start\",inline: \"nearest\",behavior: \"instant\"});",element);

            }
        } catch (Exception e) {
            System.out.println("Could not find field.");
            System.out.println(e.getMessage());
        }

        if(cmd.startsWith("text")) {
            String jsLabel = removeQuotes(cmd.substring("text".length()).trim());
            try {
                var label = driver.findElements(By.tagName("a"));
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
        return commandLine.startsWith(COMMAND_NAME);
    }
}
