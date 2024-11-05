package eu.europa.ec.digit.ams.commands.navigate;

import eu.europa.ec.digit.ams.commands.Command;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FillFormField extends Command {
    public static final String COMMAND_NAME = "Fill form field with label ";

    @Override
    public void execute(WebDriver driver, String commandLine) {
        String arg = super.trimCommand(commandLine, COMMAND_NAME);
        System.out.println(COMMAND_NAME + " " + arg + " ...");

        //  "Email" with "user@example.com"

        String [] keys = arg.split(" with ");
        keys[1] = removeQuotes(keys[1]);
        keys[0] = removeQuotes(keys[0]);
        try {
            var label = driver.findElements(By.tagName("label"));
            label.forEach(l -> {
                if(l.getText().trim().equalsIgnoreCase(keys[0].trim())) {
                    var input = l.getAttribute("for");
                    driver.findElement(By.id(input)).sendKeys(keys[1]);
                }
            });
        } catch (Exception e) {
            System.out.println("Could not find field with label: " + keys[0]);
            System.out.println(e.getMessage());
        }
    }

    public boolean isCommand(String commandLine){
        return commandLine.startsWith(COMMAND_NAME);
    }
}
