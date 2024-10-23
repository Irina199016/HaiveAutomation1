import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

// This annotation is used to specify that the ExtentTestNGITestListener class should be used to listen to the test events.
// The listener will generate reports based on the test results using the ExtentReports library.
@Listeners(ExtentTestNGITestListener.class)

// Declaring the CheckoutTest class, which extends the Hooks class.
// By extending Hooks, CheckoutTest inherits the setup and teardown methods for WebDriver.
public class LoginTest extends Hooks {

    // Declaring a public variable of type CheckoutPage named 'checkoutPage'.
    // This will be used to interact with the CheckoutPage object during the tests.
    public LoginPage loginPage;

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
        loginPage = new LoginPage(driver);

        // Initializing the WebDriverWait object with the current WebDriver instance and a timeout of 30 seconds.
        // This wait will be used to pause the execution until certain conditions are met or elements are found.
        wait = new WebDriverWait(driver, 30);
        softAssert = new SoftAssert();
    }
    @Test(description = "Login with a user")
    public void loginTest() {
        loginPage.clickLoginButton();
        loginPage.setUserNameField("dino");
        loginPage.setPasswordField("choochoo");
        loginPage.clickLoginBtn();
        assertEquals(loginPage.getUserLoggedIn().getText(), "dino");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The user 'dino' is logged in");
    }
@Test(description = "Sorting Test")
    public void sortTest() throws InterruptedException {
        loginPage.selectOption(loginPage.getSortBar(), "Sort by name (Z to A)");
        Thread.sleep(5000);
       // assertEquals();
}
    @Test(description = "Clicking Reset when a user is logged in")
    public void loginTested()  {
       loginPage.loginUser();
//       wait.until(ExpectedConditions.visibilityOf(loginPage.date()));
//       loginPage.clickDate();
        loginPage.clickWhenReady(loginPage.date());
        loginPage.clickDay1();
        loginPage.clickDay3();
        loginPage.clickPrevMonth();
    }
    @Test(description = "create new location")
    public void createLocation() throws InterruptedException {
        loginPage.loginUser();
        loginPage.clickWhenReady(loginPage.getVenuesLink());
        loginPage.clickWhenReady(loginPage.getCreateLocationLink());
        loginPage.sendKeysWhenReady(loginPage.getNameInput(), "Test Name");
        loginPage.enterAddress("123 Example St");
        loginPage.enterCity("Example City");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(580, 2800);");
        Thread.sleep(2000);
        loginPage.clickCreateLocationButton();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getNameOfLocation()));
        Thread.sleep(3000);
        assertEquals(loginPage.getNameOfLocation().getText(),"Test Name");

    }
@Test(description = "delete button not needed")
    public void deleteButton() {
    loginPage.loginUser();
    loginPage.clickButtonNotNeeded();
}
}


