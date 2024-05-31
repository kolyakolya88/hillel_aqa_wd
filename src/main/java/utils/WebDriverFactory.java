package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static utils.Constants.DEFAULT_WAITER;

public class WebDriverFactory {


    private static WebDriver driver;

    private WebDriverFactory() {
    }

    /**
     * Method returns instance of {@link WebDriver},
     * Method maximize window,
     * @return {@link WebDriver}
     */
    public static WebDriver getDriver(String browser) {
        if (driver == null) {
            synchronized (WebDriver.class) {
                if (driver == null) {
                    switch (browser.toLowerCase()) {
                        case "firefox":
                            driver = new FirefoxDriver();
                            break;
                        case "chrome":
                            driver = new ChromeDriver();
                            break;
                    }
                    driver.manage().timeouts().implicitlyWait(DEFAULT_WAITER, TimeUnit.SECONDS);
                }
            }
        }
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Method closes all open driver sessions
     */
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}