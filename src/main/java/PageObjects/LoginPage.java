package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class represents the login page.
 * It contains methods to interact with the login page elements.
 */
public class LoginPage {
    private final WebDriver driver;

    private final By menuBtn = By.xpath("//button[@id='react-burger-menu-btn']");
    private final  By logoutBtn = By.xpath("//a[@id='logout_sidebar_link']");
    private final By passwordField = By.xpath("//input[@id='password']");
    private final By loginButton = By.xpath("//input[@id='login-button']");

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
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement usernameElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='user-name']")));
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
     * @param username The username to log in with.
     * @param password The password to log in with.
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    /**
     * This method is used to log out from the application.
     * It first waits for the menu button to be clickable and then clicks it.
     * After that, it waits for the logout button to be clickable and then clicks it.
     */
    public void logout() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(menuBtn));
        menuButton.click();
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(logoutBtn));
        logoutButton.click();
    }

    /**
     * Returns the current URL of the page.
     * @return The current URL.
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}