package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static WebDriver driver;

    private DriverFactory() {
    }

    public static WebDriver iniciarDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            if (System.getenv("CI") != null) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
            }

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }

        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void encerrarDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}