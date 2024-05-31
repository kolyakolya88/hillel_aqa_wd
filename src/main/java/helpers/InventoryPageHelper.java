package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;


import java.util.ArrayList;
import java.util.List;

import static pages.InventoryPage.*;

/**
 * InventoryPageHelper for the inventory page "https://www.saucedemo.com/inventory.html"
 */
public class InventoryPageHelper extends BasePageHelper {

    Actions actions;

    /**
     * Constructor for the InventoryPageHelper class.
     *
     * @param driver
     */
    public InventoryPageHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    /**
     * Checks if the inventory item is displayed.
     *
     * @return true if the inventory item is displayed, false otherwise.
     */
    public boolean isInventoryItemDisplayed() {
        WebElement elem = driver.findElement(INVENTORY_ITEM);
        return elem.isDisplayed();
    }

    /**
     * Selects the given sort option from the sort dropdown.
     *
     * @param option The sort option to select.
     */
    public void selectSortOption(String option) {
        WebElement dropdownElement = driver.findElement(FILTER_DROPDOWN);
        actions.moveToElement(dropdownElement).click().perform();
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(option);
    }

    /**
     * Returns the prices of all items on the page.
     *
     * @return A list of item prices.
     */
    public List<Double> getItemPrices() {
        List<WebElement> priceElements = driver.findElements(PRICE_ELEMENT);
        List<Double> prices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            prices.add(price);
        }
        return prices;
    }


    /**
     * Returns the names of all items on the page.
     *
     * @return A list of item names.
     */
    public List<String> getItemNames() {
        List<WebElement> nameElements = driver.findElements(ITEM_NAME_ELEMENT);
        List<String> names = new ArrayList<>();
        for (WebElement nameElement : nameElements) {
            names.add(nameElement.getText());
        }
        return names;
    }

    /**
     * Hovers over the title of the first inventory item on the page and returns its color.
     *
     * @return The color of the title after hover.
     */
    public String hoverAndGetTitleColor() {
        WebElement titleElement = driver.findElement(FIRST_INVENTORY_ITEM);
        Actions actions = new Actions(driver);
        actions.moveToElement(titleElement).perform();
        String colorValue = titleElement.getCssValue("color");
        String colorAsHex = Color.fromString(colorValue).asHex();
        return colorAsHex;
    }
}
