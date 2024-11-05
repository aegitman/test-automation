package eu.europa.ec.digit.ams.commands;

import org.openqa.selenium.WebDriver;

public abstract class Command {

    public static final Long WAIT_AFTER_NAV = 1000L;

    public void execute(WebDriver driver, String commandLine){}

    public boolean isCommand(String commandLine){
        return false;
    }

    public String trimCommand(String commandLine, String cmdName){
        return commandLine.replace(cmdName, "").trim();
    }

    public String removeQuotes(String commandLine){
        return commandLine.replace("\"", "");
    }
}
