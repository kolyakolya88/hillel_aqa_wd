package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    public static WebDriver createDriver(String browserType) {
        WebDriver driver;

        switch (browserType.toLowerCase()) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "/firefoxDriver");
                driver = new FirefoxDriver();
                break;
            case "chrome":
            default:
                System.setProperty("webdriver.chrome.driver", "chromedriver");
                driver = new ChromeDriver();
                break;
        }

        return driver;
    }
}