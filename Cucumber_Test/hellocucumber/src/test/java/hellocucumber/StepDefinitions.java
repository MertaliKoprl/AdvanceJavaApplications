package hellocucumber;


import hellocucumber.Pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {

    static WebDriver driver;
    static String currentURL = "";
    static WebDriverWait wait;
    static LoginPage loginPage;
    static InventoryPage inventoryPage;
    static InventoryItemPage inventoryItemPage;
    static ShoppingCartPage shoppingCartPage;
    static WebElement nameOfItem;
    static int numberOfProducts;
    static CheckoutPage checkoutPage;
    static CheckoutOverviewPage checkoutOverviewPage;
    static MenuPage menuPage; //Checkout Complete Page

///////////////////////LOGIN//////////////////////

    @Given("I am on the Login Page of Sauce demo")
    public void i_am_on_the_login_page_of_sauce_demo() {
        // Write code here that turns the phrase above into concrete actions
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/index.html");


    }


    @When("I enter valid username and password")
    public void i_enter_valid_username_and_password() {
        // Write code here that turns the phrase above into concrete actions
        loginPage= new LoginPage(driver);
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();

    }

    @Then("I should be taken to Inventory Page")
    public void i_should_be_taken_to_inventory_page() {
        // Write code here that turns the phrase above into concrete actions
        currentURL = loginPage.getDriver().getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html", currentURL); // Confirms that we have successfully Loged in.

    }

    //////////////////////SELECT FIRST ITEM////////////////////


    @Given("I am on the Inventory Page of Sauce demo")
    public void i_am_on_the_inventory_page_of_sauce_demo() {
        inventoryPage= new InventoryPage(driver);
        currentURL = inventoryPage.getDriver().getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html", currentURL);

    }

    @When("I select first item on the list")
    public void i_select_first_item_on_the_list() {

        List<WebElement> products = inventoryPage.getProductNameLinks();
        products.get(0).click();// Click First Item.

    }

    @Then("I am on the item Page Successfully")
    public void i_am_on_the_item_page_successfully() {
        currentURL = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory-item.html?id=4", currentURL); // CHECK IF WE ARE ON CORRECT PAGE !

    }

    //////////////////////ADDD ITEM TO SHOPPING CART////////////////////////

    @Given("I am on the Item Page of source demo")
    public void i_am_on_the_item_page_of_source_demo() {
        // Write code here that turns the phrase above into concrete actions
        inventoryItemPage= new InventoryItemPage(driver);
        currentURL = inventoryItemPage.getDriver().getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory-item.html?id=4", currentURL); // CHECK IF WE ARE ON CORRECT PAGE !
    }

    @When("I click addToCart Button")
    public void i_click_add_to_cart_button() {
        inventoryItemPage.addCartButtonClick();
    }

    @Then("Added successfully")
    public void added_successfully() {
        // Write code here that turns the phrase above into concrete actions
        WebElement addToCartButton = driver.findElement(By.xpath("//*[contains(@class,'btn_secondary btn_inventory')]"));
        assertEquals("REMOVE", addToCartButton.getText()); // If the Text on the button is 'REMOVE' So means that it added successsfully.
        //SoftAssert'te kullanılabilir.

    }

    ////////////////////////////GO TO SHOPPING CART//////////////////////////////////////
    @Given("I have successfully add Item to ShoppingCart")
    public void i_have_successfully_add_item_to_shopping_cart() {
        // Write code here that turns the phrase above into concrete actions

        WebElement addToCartButton = driver.findElement(By.xpath("//*[contains(@class,'btn_secondary btn_inventory')]"));
        assertEquals("REMOVE", addToCartButton.getText()); // If the Text on the button is 'REMOVE' So means that it added successsfully.

    }

    @When("I click ShoppingCart button")
    public void i_click_shopping_cart_button() {
        shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.getClass();
        shoppingCartPage.clickCheckoutButton();

    }

    @Then("I should be in ShoppingCart Page")
    public void i_should_be_in_shopping_cart_page() {
        // Write code here that turns the phrase above into concrete actions
        currentURL = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/checkout-step-one.html", currentURL); // CHECK IF WE ARE ON CORRECT PAGE !

    }
    ////////////////////////////CHECK ITEM ON THE LIST//////////////////////////////////////

    @Given("I am on the Shopping Cart Page of Sauce demo")
    public void i_am_on_the_shopping_cart_page_of_sauce_demo() {
        shoppingCartPage = new ShoppingCartPage(driver);
        currentURL = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/cart.html", currentURL); // CHECK IF WE ARE ON CORRECT PAGE !
    }

    @When("I check the name of the item on the list")
    public void i_check_the_name_of_the_item_on_the_list() {
        shoppingCartPage.getClass();
        numberOfProducts = shoppingCartPage.getNumberOfItems();
        nameOfItem = shoppingCartPage.getNameOfItem();
    }

    @Then("I check the item on the list successfully")
    public void i_check_the_item_on_the_list_successfully() {
        assertEquals(numberOfProducts,1); // CHECK IF THE NUMBER OF ITEM IS CORRECT !
        assertEquals(nameOfItem.getText(), "Sauce Labs Backpack"); // CHECK IF THE NAME OF THE ITEM IS CORRECT !
    }
    ////////////////////////////PROCEED TO CHECKOUT//////////////////////////////////////

    @Given("I am on the Shopping Cart Page")
    public void i_am_on_the_shopping_cart_page() {
        shoppingCartPage = new ShoppingCartPage(driver);
        currentURL = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/cart.html", currentURL); // CHECK IF WE ARE ON CORRECT PAGE !
    }

    @When("I click Checkout Button")
    public void i_click_checkout_button() {
        shoppingCartPage.clickCheckoutButton();
    }

    @Then("I should be taken to Checkout Page of Sauce demo")
    public void i_should_be_taken_to_checkout_page_of_sauce_demo() {
        currentURL = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/checkout-step-one.html", currentURL); // CHECK IF WE ARE ON CORRECT PAGE !
    }

    ////////////////////////////COMPLETE CHECKOUT//////////////////////////////////////

    @Given("I am on the Checkout Page of Sauce demo")
    public void i_am_on_the_checkout_page_of_sauce_demo() {
        checkoutPage = new CheckoutPage(driver);
        currentURL = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/checkout-step-one.html", currentURL); // CHECK IF WE ARE ON CORRECT PAGE !
    }

    @When("I enter valid firstname, lastname and zip code and click continue button")
    public void i_enter_valid_firstname_lastname_and_zip_code_and_click_continue_button() {
        checkoutPage.setFirstName("Ozay");
        checkoutPage.setLastName("Ezerceli");
        checkoutPage.setPostCode("34980");
        checkoutPage.clickContinueButton();
    }

    @Then("I should be taken to Checkout Overview Page")
    public void i_should_be_taken_to_checkout_overview_page() {
        currentURL = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/checkout-step-two.html", currentURL); // CHECK IF WE ARE ON CORRECT PAGE !
    }

    ////////////////////////////FINISH CHECKOUT//////////////////////////////////////

    @Given("I am on the Checkout Overview Page of Sauce demo")
    public void i_am_on_the_checkout_overview_page_of_sauce_demo() {
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        currentURL = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/checkout-step-two.html", currentURL); // CHECK IF WE ARE ON CORRECT PAGE !
    }

    @When("I click finish button")
    public void i_click_finish_button() {
        checkoutOverviewPage.clickFinishButton();
    }

    @Then("I should be taken to Checkout Complete Page of Sauce demo")
    public void i_should_be_taken_to_checkout_complete_page_of_sauce_demo() {
        currentURL = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/checkout-complete.html", currentURL); // CHECK IF WE ARE ON CORRECT PAGE !
    }

    ////////////////////////////LOGOUT//////////////////////////////////////
    @Given("I am on the Checkout Complete Page of Sauce demo")
    public void i_am_on_the_checkout_complete_page_of_sauce_demo() {
        currentURL = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/checkout-complete.html", currentURL); // CHECK IF WE ARE ON CORRECT PAGE !
    }

    @When("I click Open Menu button and select logout option")
    public void i_click_open_menu_button_and_select_logout_option() {
        menuPage = new MenuPage(driver);
        menuPage.Logout();
    }

    @Then("I should be taken to Login Page of Sauce demo")
    public void i_should_be_taken_to_login_page_of_sauce_demo() {
        currentURL = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/index.html", currentURL); // CHECK IF WE ARE ON CORRECT PAGE !
    }
}


