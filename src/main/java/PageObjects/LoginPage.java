package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class represents the login page.
 * It contains methods to interact with the login page elements.
 */
public class LoginPage {
    private WebDriver driver;

    private By usernameField = By.xpath("//input[@id='user-name']");
    private By passwordField = By.xpath("//input[@id='password']");
    private By loginButton = By.xpath("//input[@id='login-button']");

    /**
     * Constructor for LoginPage.
     * @param driver WebDriver instance.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;

    }

    /**
     * Enters the given username into the username field.
     * @param username The username to enter.
     */
    public void enterUsername(String username) {
        WebElement usernameElement = driver.findElement(usernameField);
        usernameElement.sendKeys(username);
    }

    /**
     * Enters the given password into the password field.
     * @param password The password to enter.
     */
    public void enterPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordField);
        passwordElement.sendKeys(password);
    }

    /**
     * Clicks the login button.
     */
    public void clickLoginButton() {
        WebElement loginBtnElement = driver.findElement(loginButton);
        loginBtnElement.click();
    }

    /**
     * Performs a login with the given username and password.
     * @param username The username to login with.
     * @param password The password to login with.
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    /**
     * Returns the current URL of the page.
     * @return The current URL.
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}