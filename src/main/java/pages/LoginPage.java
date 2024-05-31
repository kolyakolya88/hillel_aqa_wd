package pages;

import org.openqa.selenium.By;

/**
 * This class represents the login page.
 * It contains methods to interact with the login page elements.
 */
public class LoginPage {

    public static final By MENU_BTN = By.xpath("//button[@id='react-burger-menu-btn']");
    public static final By LOGOUT_BTN = By.xpath("//a[@id='logout_sidebar_link']");
    public static final By LOGIN_BTN = By.xpath("//input[@type='submit']");
    public static final By LOGO = By.xpath("//div[@class='app_logo']");
    public static final By PASSWORD_FIELD = By.xpath("//input[@id='password']");
    public static final By USERNAME_FIELD = By.xpath("//input[@placeholder='Username']");
}
