package hellocucumber.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryItemPage {
    static WebDriver driver;
    static WebDriverWait wait;
    String ButtonString="";

    public InventoryItemPage(WebDriver browserDriver){
        driver = browserDriver;
        wait= new WebDriverWait(driver,10);


    }

    public void addCartButtonClick(){
        WebElement addToCartButton = driver.findElement(By.xpath("//*[contains(@class,'btn_primary btn_inventory')]"));
        ButtonString= addToCartButton.getText();
        addToCartButton.click();
    }

    public void clickBackButton(){
        WebElement backButton = driver.findElement(By.className("inventory_details_back_button"));
        backButton.click();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public  String getButtonString() {
        return ButtonString;
    }
}
