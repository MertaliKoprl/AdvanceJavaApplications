package hellocucumber.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private By userNameLocator = By.id("user-name");
    private By passwordLocator = By.id("password");
    private By loginButtonLocator = By.id("login-button");

    public LoginPage (WebDriver browserDriver){
        super(browserDriver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
    }

    public void setUserName(String userName){
        WebElement userNameTextBox = driver.findElement(userNameLocator);
        userNameTextBox.sendKeys(userName);
    }

    public void setPassword(String password){
        WebElement passwordTextBox = driver.findElement(passwordLocator);
        passwordTextBox.sendKeys(password);
    }

    public void clickLoginButton(){
        WebElement loginButton = driver.findElement(loginButtonLocator);
        loginButton.click();
    }




}
