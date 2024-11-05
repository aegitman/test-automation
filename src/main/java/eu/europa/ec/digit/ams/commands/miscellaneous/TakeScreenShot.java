package eu.europa.ec.digit.ams.commands.miscellaneous;

import eu.europa.ec.digit.ams.commands.Command;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class TakeScreenShot extends Command {
    public static final String COMMAND_NAME = "Take screenshot";

    @Override
    public void execute(WebDriver driver, String commandLine) {
        System.out.println(COMMAND_NAME + " ...");

        try {
            // Create a Robot object to capture the screen
            Robot robot = new Robot();

            // Create a Rectangle object to define the area of the screen to capture
            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

            // Use the robot.createScreenCapture() method to capture the screen as a BufferedImage object
            BufferedImage image = robot.createScreenCapture(rectangle);

            // Save the BufferedImage object to a file using the ImageIO.write() method
            File file = new File("screenshot.png");
            ImageIO.write(image, "png", file);
        }catch (Exception e) {
            System.out.println("Could not take screenshot, command interrupted");
            e.printStackTrace();
        }
    }

    @Override
    public boolean isCommand(String commandLine) {
        return commandLine.startsWith(COMMAND_NAME);
    }
}
