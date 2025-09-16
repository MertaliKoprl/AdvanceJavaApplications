package hellocucumber.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ShoppingCartPage {
    WebDriver driver;
    WebDriverWait wait;
    public ShoppingCartPage(WebDriver browserDriver){
        driver = browserDriver;
        wait= new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'svg-inline--fa fa-shopping-cart fa-w-18 fa-3x ')]")));
        WebElement shoppingCart = driver.findElement(By.xpath("//*[contains(@class,'svg-inline--fa fa-shopping-cart fa-w-18 fa-3x ')]"));
        shoppingCart.click();
    }

    public int getNumberOfItems(){
        List<WebElement> numberOfItems = driver.findElements(By.className("cart_item"));
        return numberOfItems.size();
    }

    public WebElement getNameOfItem(){
        WebElement nameOfItem = driver.findElement(By.className("inventory_item_name"));
        return nameOfItem;
    }

    public void clickCheckoutButton(){
        WebElement checkoutButton = driver.findElement(By.xpath("//*[@id='cart_contents_container']/div/div[2]/a[2]"));
        checkoutButton.click();
    }
}
