import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static java.time.Duration.ofMillis;
import static org.testng.Assert.*;

// This annotation is used to specify that the ExtentTestNGITestListener class should be used to listen to the test events.
// The listener will generate reports based on the test results using the ExtentReports library.
@Listeners(ExtentTestNGITestListener.class)

// Declaring the CheckoutTest class, which extends the Hooks class.
// By extending Hooks, CheckoutTest inherits the setup and teardown methods for WebDriver.
public class CheckoutTest extends Hooks {

    // Declaring a public variable of type CheckoutPage named 'checkoutPage'.
    // This will be used to interact with the CheckoutPage object during the tests.
    public CheckoutPage checkoutPage;

    // Declaring a public variable of type WebDriverWait named 'wait'.
    // WebDriverWait is used to explicitly wait for certain conditions or elements during test execution.
    public WebDriverWait wait;
    public SoftAssert softAssert;

    // Method annotated with @BeforeMethod, indicating that it will run before each test method.
    // This method is used to set up the page objects and other necessary components before each test.
    @BeforeMethod
    public void SetupPageObject() {

        // Initializing the checkoutPage object with the current WebDriver instance.
        // This allows the test methods to interact with elements on the checkout page.
        checkoutPage = new CheckoutPage(driver);

        // Initializing the WebDriverWait object with the current WebDriver instance and a timeout of 30 seconds.
        // This wait will be used to pause the execution until certain conditions are met or elements are found.
        wait = new WebDriverWait(driver, 30);
        softAssert = new SoftAssert();
    }


    @Test(description = "Tests the search functionality by searching for the keyword 'mouse'")
    public void searchTest() throws InterruptedException {
        checkoutPage.setSearchBar();
        Thread.sleep(2000);
        checkoutPage.clickSearchButton();
        Thread.sleep(2000);
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The search engine is looking up for the keyword 'mouse'");
    }

    @Test(description = "Purchasing a simple product from a guest user")
    public void checkoutTest() throws InterruptedException {
        checkoutPage.clickAwesomeGraniteChipsLink();
        Thread.sleep(2000);
        checkoutPage.clickCartIcon();
        Thread.sleep(2000);
        checkoutPage.clickShoppingCartIcon();
        Thread.sleep(2000);
        checkoutPage.clickCheckooutButton();
        Thread.sleep(2000);
        checkoutPage.setFirstNameField();
        Thread.sleep(2000);
        checkoutPage.setLastNameField();
        Thread.sleep(2000);
        checkoutPage.setAddressField();
        Thread.sleep(2000);
        checkoutPage.clickContinueCheckout();
        Thread.sleep(2000);
        checkoutPage.clickCompleteYourOrder();
        Thread.sleep(2000);
        assertEquals(checkoutPage.getSuccessMessage().getText(), "Order complete");

    }

    @Test(description = "Adding  a product to wishlist")
    public void wishlistTest() {
        checkoutPage.addProductToWishlist();
        assertEquals(checkoutPage.getAwesomeChipsProduct().getText(), "Awesome Granite Chips");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "Awesome Granite Chips product was found in the Wishlist");
        softAssert.assertAll();
    }
    @Test(description = "Removing  a product to wishlist")
    public void removeItemFromWishlist() {
        checkoutPage.addProductToWishlist();
        checkoutPage.clickBrokenHeartIcon();
        try {
            driver.findElement(By.linkText("Awesome Granite Chips"));
            Assert.fail("Element is still present.");
        }catch (NoSuchElementException e){

            Assert.assertTrue(true, "Element is not present as expected.");
        }

    }

    @Test(description = "Increase the amount of a product")
    public void increasedAmountTest() throws InterruptedException {
        Thread.sleep(5000);

        ExtentTestNGITestListener.getTest().log(Status.INFO, "The price of the product is:+" + checkoutPage.productPrice());
        double expectedTotal = checkoutPage.productPrice() * 2;
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The price of the product is:+" + expectedTotal);
        checkoutPage.clickPlusOne();
        assertEquals(checkoutPage.productPrice(), expectedTotal);
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The price of the product matches the expected total" + checkoutPage.productPrice() + "=" + expectedTotal);
    }
    @Test(description = "Calculate the total price for a product")
    public void totalPriceForAProduct(){
        checkoutPage.addProductToCart();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The price of product is"+checkoutPage.productPrice());
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The tax price  is"+checkoutPage.taxPrice());
        double expectedTotal = checkoutPage.totalPrice()+checkoutPage.taxPrice();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The actual price  is"+checkoutPage.totalPrice());
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The expected price  is"+expectedTotal);
        assertEquals(checkoutPage.totalPrice(),expectedTotal);

    }

    @Test(description = "Tests the deleting button")
    public void deleteProduct() throws InterruptedException {
        checkoutPage.clickAwesomeMetalChair();
        Thread.sleep(2000);
        checkoutPage.clickChartIconForMetalChair();
        Thread.sleep(2000);
        checkoutPage.clickCounterShoppingCartBadge();
        Thread.sleep(2000);
        checkoutPage.clickDeleteOneProduct();
        assertEquals(checkoutPage.getDeleteMessage().getText(), "How about adding some products in your cart?");
    }

    @Test(description = "Tests the login")
    public void loginAction() throws InterruptedException {
        checkoutPage.clickLoginButton();
        Thread.sleep(2000);
        checkoutPage.setUserNameField();
        Thread.sleep(2000);
        checkoutPage.setPasswordField();
        Thread.sleep(2000);
        checkoutPage.clickBlueLoginButton();
    }
    @Test(description = "Add location of the restaurant")
    public void addTheRestaurant() throws InterruptedException {
        checkoutPage.clickButtonLogin();
        Thread.sleep(5000);
        checkoutPage.setUserNameEmail("irinna_precup@yahoo.com");
        Thread.sleep(5000);
        checkoutPage.setPasswordFieldEmail("");
        Thread.sleep(5000);
        checkoutPage.clickLoginBtn();
        Thread.sleep(5000);
       assertEquals(checkoutPage.getUserLoggedIn().getText(), "Irina Precup");
       ExtentTestNGITestListener.getTest().log(Status.PASS, "The user 'Irina Precup' is logged in");

    }
    
}

