package Suites;

import dataproviders.LoginPageDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import utils.WebDriverFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Class Tests contains all test methods
 */
public class Tests extends BaseTest {

    SoftAssert softAssert = new SoftAssert();
    Actions actions;

    private void authorization(String username) {
        loginPageHelper.enterUsername(username);
        loginPageHelper.enterPassword(ConfigReader.getPasswordProperty());
        loginPageHelper.clickLoginButton();
        softAssert.assertTrue(loginPageHelper.checkUserLoggedIn());
        softAssert.assertAll();
    }

    @BeforeMethod
    public void setupActions() {
        actions = new Actions(driver);
    }

    @Test(priority = -1)
    @Description("Checking that items are displayed")
    @Severity(SeverityLevel.NORMAL)
    public void isItemsDisplayed() {
        authorization(ConfigReader.getUsernameProperty());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inventory_item_desc']")));
        softAssert.assertTrue(inventoryPageHelper.isInventoryItemDisplayed());
        softAssert.assertAll();
    }

    @Test
    @Description("Checking that price is sorted from low to high")
    @Severity(SeverityLevel.MINOR)
    public void checkingSortingLowToHigh() {
        inventoryPageHelper.selectSortOption("Price (low to high)");
        List<Double> prices = inventoryPageHelper.getItemPrices();
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        assertThat(prices).isEqualTo(sortedPrices);
        assertThat(prices.get(0)).isBetween(7.99, 50.00);
    }

    @Test
    @Description("Checking that price is sorted from high to low")
    @Severity(SeverityLevel.MINOR)
    public void checkingSortingHighToLowPrice() {
        inventoryPageHelper.selectSortOption("Price (high to low)");
        List<Double> prices = inventoryPageHelper.getItemPrices();
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices, Collections.reverseOrder());
        assertThat(prices).isEqualTo(sortedPrices);
        assertThat(prices.get(0)).isBetween(7.99, 50.00);
    }

    @Test
    @Description("Checking that items are sorted from Z to A")
    @Severity(SeverityLevel.MINOR)
    public void checkingSortingFromAToZ() {
        inventoryPageHelper.selectSortOption("Name (Z to A)");
        List<String> names = inventoryPageHelper.getItemNames();
        List<String> sortedNames = new ArrayList<>(names);
        Collections.sort(sortedNames);
        softAssert.assertEquals(names, sortedNames, "Items are not sorted from A to Z");
        softAssert.assertAll();
    }

    @Test
    @Description("Hover over the title of the first inventory item and check the color change")
    @Severity(SeverityLevel.MINOR)
    public void hoverOverFirstInventoryItemTitleAndCheckColor() {
        String expectedColor = "#3ddc91";
        String actualColor = inventoryPageHelper.hoverAndGetTitleColor();
        assertThat(actualColor).isEqualTo(expectedColor);
    }


    @Test(dataProvider = "users", dataProviderClass = LoginPageDataProvider.class, description = "Authorization test for multiple users", priority = -2)
    @Severity(SeverityLevel.CRITICAL)
    public void CheckingPlatformWithDifferentUserRoles(String username) {
        authorization(username);
        softAssert.assertTrue(loginPageHelper.checkUserLoggedIn());
        loginPageHelper.logout();
        softAssert.assertAll();
    }

    @Test(priority = 100)
    // This test is broken and will fail, priority added to handle executing this test at the start, before other tests. Cuz it will fail and other tests will not be executed.
    @Description("One broken test for allure")
    @Severity(SeverityLevel.MINOR)
    public void checkRetrogradniyMercury() {
        inventoryPageHelper.selectSortOption("Name (A to Z)");
        List<String> names = inventoryPageHelper.getItemNames().contains("Retrogradniy Mercury") ? new ArrayList<>() : new ArrayList<>(List.of("Retrogradniy Mercury"));
        softAssert.assertTrue(names.isEmpty(), "Retrogradniy Mercury is not present :D");
        softAssert.assertAll();
    }

    @AfterTest
    public void teardown() {
        WebDriverFactory.closeDriver();
        try {
            Runtime.getRuntime().exec("allure serve");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
