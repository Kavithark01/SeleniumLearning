import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SeleniumBasic {
    public static void main(String[] args)
    {
        WebDriver driver;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().window().maximize();

        // Locate link by partialLinkText
        String PartialLinkText_Text = driver.findElement(By.partialLinkText("Orange")).getText();
        System.out.println("Text of PartialLinkText Locator is ->" + PartialLinkText_Text);

        // Locate FirstName by name
        driver.findElement(By.name("username")).sendKeys("Admin");

        // Locate LastName by id
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
        // Locate options of DropDown by tagName
        List<WebElement> linkName = driver.findElements(By.tagName("a"));
        System.out.println("List of Links:");
        for (WebElement link : linkName) {
            System.out.println(link.getText() + " - " +link.getDomAttribute("href"));
        }
        TakesScreenshot ts=(TakesScreenshot)driver;
        File screenshot=ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot,new File("./Screenshot/new"+System.currentTimeMillis()+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // driver.close();
    }

}
