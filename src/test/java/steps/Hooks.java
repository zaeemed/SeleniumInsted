package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import utils.ConfigLoader;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Hooks {
    private static WebDriver driver;
    ConfigLoader config = ConfigLoader.getInstance();

    @Before
    public void initializeBrowser() {

        if(Objects.equals(config.getProperty("browser"), "chrome")){
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
//        WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--silent");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            // Take screenshot only on failure
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotName = scenario.getName() + "-screenshot.png";
            FileHandler.copy(screenshot, new File("target/screenshots/" + screenshotName));
        }
        if (driver != null) {
            driver.quit();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
