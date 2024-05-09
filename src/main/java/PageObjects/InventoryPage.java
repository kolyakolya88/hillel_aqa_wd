package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the inventory page.
 * It contains methods to interact with the inventory page elements.
 */
public class InventoryPage {
    private WebDriver driver;
    private final By inventoryItem = By.xpath("//div[@class='inventory_item_desc']");
    private final By filterDropdown = By.xpath("//select[@class='product_sort_container']");
    private final By priceElem = By.xpath("//div[@class='inventory_item_price']");
    private final By firstInventoryItemTitle = By.xpath("//div[@class='inventory_item_name '][1]");



    /**
     * Constructor for InventoryPage.
     * @param driver WebDriver instance.
     */
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Checks if the inventory item is displayed.
     * @return true if the inventory item is displayed, false otherwise.
     */
    public boolean isInventoryItemDisplayed() {
        WebElement elem = driver.findElement(inventoryItem);
        return elem.isDisplayed();
    }

    /**
     * Selects the given sort option from the sort dropdown.
     * @param option The sort option to select.
     */
    public void selectSortOption(String option) {
        Select dropdown = new Select(driver.findElement(filterDropdown));
        dropdown.selectByVisibleText(option);
    }

    /**
     * Returns the prices of all items on the page.
     * @return A list of item prices.
     */
    public List<Double> getItemPrices() {
        List<WebElement> priceElements = driver.findElements(priceElem);
        List<Double> prices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            prices.add(price);
        }
        return prices;
    }

    private final By itemNameElem = By.xpath("//div[@class='inventory_item_name']");

    /**
     * Returns the names of all items on the page.
     * @return A list of item names.
     */
    public List<String> getItemNames() {
        List<WebElement> nameElements = driver.findElements(itemNameElem);
        List<String> names = new ArrayList<>();
        for (WebElement nameElement : nameElements) {
            names.add(nameElement.getText());
        }
        return names;
    }

    /**
     * Hovers over the title of the first inventory item on the page and returns its color.
     * @return The color of the title after hover.
     */
    public String hoverAndGetTitleColor() {
        WebElement titleElement = driver.findElement(firstInventoryItemTitle);
        Actions actions = new Actions(driver);
        actions.moveToElement(titleElement).perform();
        String colorValue = titleElement.getCssValue("color");
        String colorAsHex = Color.fromString(colorValue).asHex();
        return colorAsHex;
    }

}