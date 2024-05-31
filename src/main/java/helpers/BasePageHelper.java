package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Contains general methods to use on different pages
 */
public class BasePageHelper {

    WebDriver driver;

    public BasePageHelper(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Clicks on a web element
     *
     * @param webElement WebElement to be clicked
     */
    public void clickOnWebElement(WebElement webElement) {
        webElement.click();
    }
}