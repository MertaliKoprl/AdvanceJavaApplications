package hellocucumber.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class InventoryPage {
    static WebDriver driver;
    static WebDriverWait wait;

    public InventoryPage(WebDriver browserDriver){
        driver = browserDriver;
        wait= new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name")));

    }

    public List<WebElement> getProductNameLinks(){
        List<WebElement> productLinks = driver.findElements(By.className("inventory_item_name"));
        return productLinks;
    }

    public void clickFilterButton(){
        WebElement filterButton = driver.findElement(By.className("product_sort_container"));
        filterButton.click();
    }

    public List<WebElement> getFilterOptions(){
        Select filter = new Select(driver.findElement(By.className("product_sort_container")));
        return filter.getOptions();
    }

    public List<WebElement> getProductImageLinks(){
        List<WebElement> productImageLinks = driver.findElements(By.className("inventory_item_img"));
        return productImageLinks;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
