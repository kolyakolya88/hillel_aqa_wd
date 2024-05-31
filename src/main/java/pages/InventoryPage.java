package pages;

import org.openqa.selenium.By;

/**
 * This class represents the inventory page.
 * It contains methods to interact with the inventory page elements.
 */
public class InventoryPage {
    public static final By INVENTORY_ITEM = By.xpath("//div[@class='inventory_item_desc']");
    public static final By FILTER_DROPDOWN = By.xpath("//select[@class='product_sort_container']");
    public static final By PRICE_ELEMENT = By.xpath("//div[@class='inventory_item_price']");
    public static final By FIRST_INVENTORY_ITEM = By.xpath("//div[@class='inventory_item_name '][1]");
    public static final By ITEM_NAME_ELEMENT = By.xpath("//div[@class='inventory_item_name']");


}