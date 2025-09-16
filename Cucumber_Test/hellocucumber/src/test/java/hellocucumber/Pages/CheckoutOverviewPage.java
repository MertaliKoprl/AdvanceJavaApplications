package hellocucumber.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CheckoutOverviewPage {
    WebDriver driver;

    public CheckoutOverviewPage(WebDriver browserDriver){
        driver = browserDriver;
    }

    public void clickFinishButton(){
        WebElement finishButton = driver.findElement(By.xpath("//*[@id='checkout_summary_container']/div/div[2]/div[8]/a[2]"));
        finishButton.click();
    }
}
