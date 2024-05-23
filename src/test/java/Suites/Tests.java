package Suites;

import pages.InventoryPage;
import pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.DataProvider;
import utils.ConfigReader;
import utils.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Tests {
    WebDriver driver;
    SoftAssert softAssert;
    WebDriverWait wait;


    static final String URL = "https://www.saucedemo.com/";
    static final By logo = By.xpath("//div[@class=\"login_logo\"]");

    @BeforeTest
    public void setup() {
        ConfigReader configReader = new ConfigReader();
        String username = configReader.getProperty("username");
        String password = configReader.getProperty("password");
        softAssert = new SoftAssert();
        driver = WebDriverFactory.createDriver("chrome");
        wait = new WebDriverWait(driver, 10);
        driver.get(URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(logo));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }

    @Test
    @Description("Authorization test")
    @Severity(SeverityLevel.CRITICAL)
    public void authorization() {
        LoginPage loginPage = new LoginPage(driver);
        String currentUrl = loginPage.getCurrentUrl();
        wait.until(ExpectedConditions.urlToBe(URL + "inventory.html"));
        softAssert.assertEquals(currentUrl, URL + "inventory.html", "Authorization failed");
        softAssert.assertAll();
    }

    @Test
    @Description("Checking that items are displayed")
    @Severity(SeverityLevel.NORMAL)
    public void isItemsDisplayed() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        boolean isDisplayed = inventoryPage.isInventoryItemDisplayed();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inventory_item_desc']")));
        softAssert.assertTrue(isDisplayed);
        softAssert.assertAll();
    }

    @Test
    @Description("Checking that price is sorted from low to high")
    @Severity(SeverityLevel.MINOR)
    public void checkingSortingLowToHigh() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.selectSortOption("Price (low to high)");
        List<Double> prices = inventoryPage.getItemPrices();
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        assertThat(prices).isEqualTo(sortedPrices);// as you wish "цікаві ассерти", сподіваюсь цього достатньо :)
        assertThat(prices.get(0)).isBetween(7.99, 50.00);

    }

    @Test
    @Description("Checking that price is sorted from high to low")
    @Severity(SeverityLevel.MINOR)
    public void checkingSortingHighToLowPrice() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.selectSortOption("Price (high to low)");
        List<Double> prices = inventoryPage.getItemPrices();
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices, Collections.reverseOrder());
        assertThat(prices).isEqualTo(sortedPrices);
        assertThat(prices.get(0)).isBetween(7.99, 50.00);
    }

    @Test
    @Description("Checking that items are sorted from A to Z")
    @Severity(SeverityLevel.MINOR)
    public void checkingSortingFromAToZ() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.selectSortOption("Name (A to Z)");
        List<String> names = inventoryPage.getItemNames();
        List<String> sortedNames = new ArrayList<>(names);
        Collections.sort(sortedNames);
        softAssert.assertEquals(names, sortedNames, "Items are not sorted from A to Z");
        softAssert.assertAll();
    }

    @Test
    @Description("Hover over the title of the first inventory item and check the color change")
    @Severity(SeverityLevel.MINOR)
    public void hoverOverFirstInventoryItemTitleAndCheckColor() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        String expectedColor = "#3ddc91";
        String actualColor = inventoryPage.hoverAndGetTitleColor();
        assertThat(actualColor).isEqualTo(expectedColor);
    }

    @DataProvider(name = "users")
    public static Object[][] users() {
        return new Object[][] {
                {"standard_user"},
                {"visual_user"},
                {"problem_user"},
                {"performance_glitch_user"},
                {"error_user"}
        };
    }

    @Test(dataProvider = "users", description = "Authorization test for multiple users")
    @Severity(SeverityLevel.CRITICAL)
    public void CheckingPlatformWithDifferentUserRoles(String username) {
        ConfigReader configReader = new ConfigReader();
        String password = configReader.getProperty("password");
        WebDriver localDriver = WebDriverFactory.createDriver("chrome");
        WebDriverWait localWait = new WebDriverWait(localDriver, 10);
        localDriver.get(URL);
        localWait.until(ExpectedConditions.visibilityOfElementLocated(logo));
        LoginPage loginPage = new LoginPage(localDriver);
        loginPage.login(username, password);
         authorization();
         isItemsDisplayed();
         checkingSortingLowToHigh();
         checkingSortingHighToLowPrice();
         checkingSortingFromAToZ();
         hoverOverFirstInventoryItemTitleAndCheckColor();
         loginPage.logout();
         localDriver.quit();
        }

    @Test(priority = 100) // This test is broken and will fail, priority added to handle executing this test at the start, before other tests. Cuz it will fail and other tests will not be executed.
    @Description("One broken test for allure")
    @Severity(SeverityLevel.MINOR)
    public void checkRetrogradniyMercury() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.selectSortOption("Name (A to Z)");
        List<String> names = inventoryPage.getItemNames().contains("Retrogradniy Mercury") ? new ArrayList<>() : new ArrayList<>(List.of("Retrogradniy Mercury"));
        softAssert.assertTrue(names.isEmpty(), "Retrogradniy Mercury is not present :D");
        softAssert.assertAll();
    }

    @AfterTest
    public void teardown() {
        driver.quit();
        try {
            Runtime.getRuntime().exec("allure serve");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}