package hellocucumber.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    private By firstNameLocator = By.id("first-name");
    private By lastNameLocator = By.id("last-name");
    private By postalCodeLocator = By.id("postal-code");
    private By continueButtonLocator = By.xpath("//*[@id='checkout_info_container']/div/form/div[2]/input");

    public CheckoutPage(WebDriver browserDriver) {
        super(browserDriver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueButtonLocator));
    }

    public void setFirstName(String firstName){
        WebElement firstNameTextBox = driver.findElement(firstNameLocator);
        firstNameTextBox.sendKeys(firstName);
    }

    public void setLastName(String lastName){
        WebElement lastNameTextBox = driver.findElement(lastNameLocator);
        lastNameTextBox.sendKeys(lastName);
    }

    public void setPostCode(String postCode){
        WebElement postCodeTextBox = driver.findElement(postalCodeLocator);
        postCodeTextBox.sendKeys(postCode);
    }

    public void clickContinueButton(){
        WebElement continueButton = driver.findElement(continueButtonLocator);
        continueButton.click();
    }
}
