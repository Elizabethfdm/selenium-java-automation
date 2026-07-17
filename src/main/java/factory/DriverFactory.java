package factory;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private static WebDriver driver;

    private DriverFactory() {
    }

    public static WebDriver iniciarDriver() {

        if (driver == null) {

            String navegador = ConfigReader
                    .getProperty("browser")
                    .toLowerCase();

            boolean headless = Boolean.parseBoolean(
                    ConfigReader.getProperty("headless")
            );

            String seleniumRemoteUrl =
                    System.getenv("SELENIUM_REMOTE_URL");

            // Execução via Docker/Selenium Grid
            if (seleniumRemoteUrl != null &&
                    !seleniumRemoteUrl.isBlank()) {

                driver = iniciarDriverRemoto(
                        navegador,
                        seleniumRemoteUrl
                );

            } else {

                // Execução Local
                switch (navegador) {

                    case "chrome":
                        driver = iniciarChrome(headless);
                        break;

                    case "firefox":
                        driver = iniciarFirefox(headless);
                        break;

                    case "edge":
                        driver = iniciarEdge(headless);
                        break;

                    default:
                        throw new IllegalArgumentException(
                                "Navegador não suportado: " + navegador
                        );
                }
            }

            if (!headless) {
                driver.manage().window().maximize();
            }
        }

        return driver;
    }

    private static WebDriver iniciarChrome(boolean headless) {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        if (headless || System.getenv("CI") != null) {

            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        return new ChromeDriver(options);
    }

    private static WebDriver iniciarFirefox(boolean headless) {

        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();

        if (headless || System.getenv("CI") != null) {

            options.addArguments("-headless");
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
        }

        return new FirefoxDriver(options);
    }

    private static WebDriver iniciarEdge(boolean headless) {

        WebDriverManager.edgedriver().setup();

        EdgeOptions options = new EdgeOptions();

        if (headless || System.getenv("CI") != null) {

            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        return new EdgeDriver(options);
    }

    private static WebDriver iniciarDriverRemoto(
            String navegador,
            String seleniumRemoteUrl) {

        try {

            switch (navegador) {

                case "chrome":
                    return new RemoteWebDriver(
                            new URL(seleniumRemoteUrl),
                            new ChromeOptions());

                case "firefox":
                    return new RemoteWebDriver(
                            new URL(seleniumRemoteUrl),
                            new FirefoxOptions());

                case "edge":
                    return new RemoteWebDriver(
                            new URL(seleniumRemoteUrl),
                            new EdgeOptions());

                default:
                    throw new IllegalArgumentException(
                            "Navegador remoto não suportado: " + navegador);
            }

        } catch (MalformedURLException e) {

            throw new RuntimeException(
                    "URL do Selenium Grid inválida.",
                    e);
        }
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