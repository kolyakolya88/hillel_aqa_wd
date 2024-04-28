import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tests {
    WebDriver driver;
    SoftAssert softAssert;
    WebDriverWait wait;


    static final String URL = "https://www.saucedemo.com/";
    static final By inventoryItem = By.xpath("//div[@class='inventory_item_desc']");
    static final By logo = By.xpath("//div[@class=\"app_logo\"]");
    static final By logoLogin = By.xpath("//div[@class=\"login_logo\"]");
    static final By loginInput = By.xpath("//input[@id='user-name']");
    static final By passwordInput = By.xpath("//input[@id='password']");
    static final By loginBtn = By.xpath("//input[@id='login-button']");
    static final By priceElem = By.xpath("//div[@class='inventory_item_price']");
    static final By itemName = By.xpath("//div[@class='inventory_item_name ']");
    static final By filterDropdown = By.xpath("//select[@class='product_sort_container']");
    static final By brokenLocator = By.xpath("//select[@class='super-puper-locator']");


    @BeforeTest
    public void setup() {
        softAssert = new SoftAssert();
        System.setProperty("chromedriver", "../chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get(URL);
        WebElement elem = wait.until(ExpectedConditions.visibilityOfElementLocated(logoLogin));
        elem.isDisplayed();
        WebElement loginElement = wait.until(ExpectedConditions.visibilityOfElementLocated(loginInput));
        loginElement.sendKeys("standard_user");
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passwordElement.sendKeys("secret_sauce");
        WebElement loginBtnElement = wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtn));
        loginBtnElement.click();
    }

    @Test
    @Description("Authorization test")
    @Severity(SeverityLevel.CRITICAL)
    public void Authorization() {
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertEquals(currentUrl, URL + "inventory.html", "Authorization failed");
        softAssert.assertAll();
    }

    @Test
    @Description("Checking that items are displayed")
    @Severity(SeverityLevel.NORMAL)
    public void isItemsDisplayed() {
        WebElement elem = driver.findElement(inventoryItem);
        wait.until(ExpectedConditions.visibilityOf(elem));
        softAssert.assertTrue(elem.isDisplayed());
        softAssert.assertAll();
    }

    @Test
    @Description("Checking that price is sorted from low to high")
    @Severity(SeverityLevel.MINOR)
    public void checkingSortingLowToHigh() {
        Select dropdown = new Select(driver.findElement(filterDropdown));
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterDropdown));
        dropdown.selectByVisibleText("Price (low to high)");
        List<WebElement> priceElements = driver.findElements(priceElem);
        List<Double> prices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            prices.add(price);
        }
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        softAssert.assertEquals(prices, sortedPrices, "Prices are not sorted from low to high");
        softAssert.assertAll();
    }

    @Test
    @Description("Checking that price is sorted from high to low")
    @Severity(SeverityLevel.MINOR)
    public void checkingSortingHighToLowPrice() {
        Select dropdown = new Select(driver.findElement(filterDropdown));
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterDropdown));
        dropdown.selectByVisibleText("Price (high to low)");
        List<WebElement> priceElements = driver.findElements(priceElem);
        wait.until(ExpectedConditions.visibilityOfAllElements(priceElements));
        List<Double> prices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            prices.add(price);
        }
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices, Collections.reverseOrder());
        softAssert.assertEquals(prices, sortedPrices, "Prices are not sorted from high to low");
        softAssert.assertAll();
    }

    @Test
    @Description("Checking that items are sorted from A to Z")
    @Severity(SeverityLevel.MINOR)
    public void checkingSortingFromAToZ() {
        List<WebElement> items = driver.findElements(itemName);
        List<String> currentNames = new ArrayList<>();
        for (WebElement item : items) {
            String itemName = item.getText();
            currentNames.add(itemName);
        }
        Select dropdown = new Select(driver.findElement(filterDropdown));
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterDropdown));
        dropdown.selectByVisibleText("Name (A to Z)");
        items = driver.findElements(itemName);
        wait.until(ExpectedConditions.visibilityOfAllElements(items));
        List<String> sortedNames = new ArrayList<>();
        for (WebElement item : items) {
            String itemName = item.getText();
            sortedNames.add(itemName);
        }
        softAssert.assertEquals(sortedNames, currentNames, "Items are not sorted from A to Z");
        softAssert.assertAll();
    }

    @Test(priority = 100) // This test is broken and will fail, priority added to handle executing this test at the start, before other tests. Cuz it will fail and other tests will not be executed.
    @Description("One broken test for allure")
    @Severity(SeverityLevel.MINOR)
    public void checkRetrogradniyMercury() {
        List<WebElement> items = driver.findElements(brokenLocator);
        List<String> currentNames = new ArrayList<>();
        for (WebElement item : items) {
            String itemName = item.getText();
            currentNames.add(itemName);
        }
        Select dropdown = new Select(driver.findElement(filterDropdown));
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterDropdown));
        dropdown.selectByVisibleText("Name (A to Z)");
        items = driver.findElements(itemName);
        wait.until(ExpectedConditions.visibilityOfAllElements(items));
        List<String> sortedNames = new ArrayList<>();
        for (WebElement item : items) {
            String itemName = item.getText();
            sortedNames.add(itemName);
        }
        softAssert.assertTrue(items.isEmpty());
        softAssert.assertAll("Retrogradniy Mercury is not present :D");
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}