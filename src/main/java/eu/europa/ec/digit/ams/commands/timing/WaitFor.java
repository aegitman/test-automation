package eu.europa.ec.digit.ams.commands.timing;

import eu.europa.ec.digit.ams.commands.Command;
import org.openqa.selenium.WebDriver;

public class WaitFor extends Command {

    public static final String COMMAND_NAME = "Wait for";

    @Override
    public void execute(WebDriver driver, String cmd) {
        String time = super.trimCommand(cmd, COMMAND_NAME);
        System.out.println(COMMAND_NAME + " " + time + " ...");
        long timeInMillis = 0L;
        if(time.endsWith(" seconds") || time.endsWith(" second")) {
            timeInMillis = 1000L;
        }
        if(time.endsWith(" miliseconds")) {
            timeInMillis = 1L;
        }

        time = time.replace(" seconds", "").replace("miliseconds", "").trim();

        try {
            Thread.sleep(Integer.parseInt(time) * timeInMillis);
        } catch (InterruptedException e) {
            System.out.println("Could not wait, command interrupted");
            e.printStackTrace();
        }
    }

    @Override
    public boolean isCommand(String commandLine) {
        return commandLine.startsWith(COMMAND_NAME);
    }
}
