package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static pages.LoginPage.*;

/**
 * HomePageHelper contains all methods for HomePage class
 */
public class LoginPageHelper extends BasePageHelper {

    WebDriver driver;

    /**
     * Constructor of the class
     *
     * @param driver The WebDriver instance that is used to interact with the web browser.
     */
    public LoginPageHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    /**
     * Enters the given username into the username field.
     *
     * @param username The username to enter.
     */
    public void enterUsername(String username) {
        WebElement usernameElement = driver.findElement(USERNAME_FIELD);
        usernameElement.sendKeys(username);
    }

    /**
     * Enters the given password into the password field.
     * The password to enter.
     */
    public void enterPassword(String password) {
        WebElement passwordElement = driver.findElement(PASSWORD_FIELD);
        passwordElement.sendKeys(password);
    }

    /**
     * Clicks the login button.
     */
    public void clickLoginButton() {
        WebElement loginBtnElement = driver.findElement(LOGIN_BTN);
        loginBtnElement.click();
    }

    /**
     * Checks if the user is logged in.
     *
     * @return True if the logo is displayed in, false otherwise.
     */
    public boolean checkUserLoggedIn() {
        WebElement elem = driver.findElement(LOGO);
        return elem.isDisplayed();
    }

    /**
     * Returns the current URL of the page.
     *
     * @return The current URL.
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * This method is used to log out from the application.
     * It first waits for the menu button to be clickable and then clicks it.
     * After that, it waits for the logout button to be clickable and then clicks it.
     */
    public void logout() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(MENU_BTN));
        menuButton.click();
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_BTN));
        logoutButton.click();
    }
}