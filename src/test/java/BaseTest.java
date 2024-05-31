import helpers.BasePageHelper;
import helpers.InventoryPageHelper;
import helpers.LoginPageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.ConfigReader;
import utils.Constants;
import utils.WebDriverFactory;
import java.util.Properties;

/**
 * Class Base test implements general functionality for all test classes
 */

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected LoginPageHelper loginPageHelper;
    protected BasePageHelper basePageHelper;
    protected InventoryPageHelper inventoryPageHelper;

    protected Properties testConfig;


    /**
     * Method to set up the WebDriver, WebDriverWait, and page objects
     * @param browser The browser to use for the test
     */
    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser){
        String browserName = ConfigReader.getBrowserProperty();
        System.out.println("Browser: " + browserName);
        long waitInSeconds = Constants.DEFAULT_WAITER;
        driver = WebDriverFactory.getDriver(browserName);
        wait = new WebDriverWait(driver, waitInSeconds);
        loginPageHelper = new LoginPageHelper(driver);
        basePageHelper = new BasePageHelper(driver);
        inventoryPageHelper = new InventoryPageHelper(driver);
        this.testConfig = ConfigReader.loadProperties();
        driver.get(ConfigReader.getURLProperty());
    }

    /**
     * Method to close the WebDriver
     */
    @AfterClass(alwaysRun = true)
    public void tearDown(){
        WebDriverFactory.closeDriver();
    }


}