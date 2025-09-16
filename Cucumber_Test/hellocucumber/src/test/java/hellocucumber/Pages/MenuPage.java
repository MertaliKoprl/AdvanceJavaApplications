package hellocucumber.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MenuPage {
    WebDriver driver;
    WebDriverWait wait;


    public MenuPage(WebDriver browserDriver){
        driver = browserDriver;
        WebElement menu = driver.findElement(By.cssSelector("div.bm-burger-button"));
        menu.click();
    }

    public void Logout(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
        logoutButton.click();
    }
}
