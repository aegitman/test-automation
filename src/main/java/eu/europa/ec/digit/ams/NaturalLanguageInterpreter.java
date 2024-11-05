package eu.europa.ec.digit.ams;

import eu.europa.ec.digit.ams.commands.miscellaneous.ExecuteJsScript;
import eu.europa.ec.digit.ams.commands.miscellaneous.ScrollIntoView;
import eu.europa.ec.digit.ams.commands.miscellaneous.TakeScreenShot;
import eu.europa.ec.digit.ams.commands.navigate.*;
import eu.europa.ec.digit.ams.commands.timing.WaitFor;
import eu.europa.ec.digit.ams.commands.verify.VerifyPageTitle;
import eu.europa.ec.digit.ams.commands.verify.VerifyUrl;
import eu.europa.ec.digit.ams.utils.CommandLineArgs;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NaturalLanguageInterpreter {

    private WebDriver driver;

    public static void main(String[] args) {
        CommandLineArgs bean = new CommandLineArgs();

        CmdLineParser parser = new CmdLineParser(bean);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }

        NaturalLanguageInterpreter interpreter = new NaturalLanguageInterpreter();
        interpreter.setBrowser("Firefox");
        interpreter.runInterpreter(bean.filePath);
    }

    public void runInterpreter(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // skip empty line or comments
                if(line.trim().isEmpty() || line.startsWith("#")) {
                    continue;
                }
                processCommand(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private void processCommand(String command) {
        OpenPage openPage = new OpenPage();
        WaitFor waitFor = new WaitFor();
        VerifyPageTitle verifyPageTitle = new VerifyPageTitle();
        FillFormField fillFormField = new FillFormField();
        ClickAction clickLink = new ClickAction();
        TakeScreenShot takeScreenShot = new TakeScreenShot();
        ExecuteJsScript executeJsScript = new ExecuteJsScript();
        ScrollIntoView scrollIntoView = new ScrollIntoView();
        VerifyUrl verifyUrl = new VerifyUrl();

        if (command.startsWith("Set browser to ")) {
            setBrowser(command);
        } else if (waitFor.isCommand(command)) {
            waitFor.execute(driver, command);
        } else if (openPage.isCommand(command)) {
            openPage.execute(driver, command);
        } else if (clickLink.isCommand(command)) {
            clickLink.execute(driver, command);
        } else if (verifyPageTitle.isCommand(command)) {
            verifyPageTitle.execute(driver, command);
        } else if (fillFormField.isCommand(command)) {
            fillFormField.execute(driver, command);
        } else if (takeScreenShot.isCommand(command)) {
            takeScreenShot.execute(driver, command);
        } else if(executeJsScript.isCommand(command)) {
            executeJsScript.execute(driver, command);
        } else if (scrollIntoView.isCommand(command)) {
            scrollIntoView.execute(driver, command);
        } else if (verifyUrl.isCommand(command)) {
            verifyUrl.execute(driver, command);
        } else {
            System.out.println("Unknown command: " + command);
        }
    }

    private void setBrowser(String command) {
        if (command.contains("Chrome")) {
            throw new IllegalArgumentException("Unsupported browser: " + command);
//            driver = new ChromeDriver();
        } else if (command.contains("Firefox")) {
            System.setProperty("webdriver.chrome.driver", "/home/gitmaal/Downloads/gekodriver");
            driver = new FirefoxDriver();
        } else if (command.contains("Safari")) {
            throw new IllegalArgumentException("Unsupported browser: " + command);
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + command);
        }
        driver.manage().window().maximize();
    }
}
