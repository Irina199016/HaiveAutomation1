import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;


// Declaring the CheckoutPage class, which extends the BasePage class.
// By extending BasePage, CheckoutPage inherits the WebDriver instance and the PageFactory initialization.
public class LoginPage extends BasePage {

    // Declaring a public WebDriverWait variable named 'wait'.
    // WebDriverWait is used for implementing explicit waits during interactions with web elements.
    public WebDriverWait wait;
    public SoftAssert softAssert;

    // Constructor for the CheckoutPage class that takes a WebDriver object as an argument.
    // This constructor calls the parent class (BasePage) constructor to initialize the WebDriver instance
    // and sets up the PageFactory to initialize the web elements on this page.
    public LoginPage(WebDriver driver) {
        // Calling the parent class (BasePage) constructor using 'super' to initialize the WebDriver.
        super(driver);

        // Initializing the WebDriverWait object with a 10-second timeout.
        // This will be used to wait for certain conditions or elements during test execution.
        wait = new WebDriverWait(driver, 10);
        softAssert = new SoftAssert();
    }

    // Locating the search bar element using the @FindBy annotation.
    // @FindBy is a Selenium annotation that helps locate elements on the web page.
    // Here, the element is being located by its 'id' attribute with the value "input-search".
    // Declare the WebElement as private to enforce encapsulation
    // This ensures that 'searchBar' cannot be accessed directly from outside this class
    @FindBy(id = "input-search")
    private WebElement searchBar;

    // A public method to set a value in the search bar.
    // This method interacts with the searchBar element and sends the text "mouse" to it.
    // Public method to interact with the private 'searchBar' element
    // Provides controlled access to the encapsulated WebElement
    public void setSearchBar() {
        // Typing the word "mouse" into the search bar.
        searchBar.sendKeys("mouse");
    }

    // Locating the search button element using the @FindBy annotation.
    // The element is being located by its CSS selector, which identifies elements based on their classes.
    // Here, the button has the classes "btn", "btn-light", and "btn-sm".
    @FindBy(linkText = "Autentificare")
    private WebElement loginButton;

    public void clickLoginButton() {
        loginButton.click();
    }

    @FindBy(name = "email")
    private WebElement userNameField;

    public void setUserNameField(String name) {
        userNameField.sendKeys(name);
    }
public WebElement getEmail(){
        return userNameField;
}
    @FindBy(name = "password")
    private WebElement passwordField;

    public void setPasswordField(String password) {
        passwordField.sendKeys(password);
    }

    @FindBy(xpath = "//button[text()='Autentificare']")
    private WebElement loginBtn;

    public void clickLoginBtn() {
        loginBtn.click();
    }

    @FindBy(linkText = "dino")
    private WebElement userLoggedIn;

    public WebElement getUserLoggedIn() {
        return userLoggedIn;
    }
    @FindBy(css = ".svg-inline--fa.fa-undo.fa-w-16")
    private WebElement resetButton;
    public void clickResetButton() {
        resetButton.click();
    }
    public void loginUser()  {
        clickLoginButton();
        wait.until(ExpectedConditions.visibilityOf(userNameField));
        setUserNameField("irinna_precup@yahoo.com");
        setPasswordField("Bucurie1IrinaAndrei");
        clickLoginBtn();
    }
    @FindBy(css=".sort-products-select.form-control.form-control-sm")
    private WebElement sortBar;
    public WebElement getSortBar(){
        return sortBar;
    }
    public void selectOption(WebElement element, String option){
        Select optionSelect = new Select(element);
        optionSelect.selectByVisibleText(option);
    }
    @FindBy(id="date")
    private WebElement datePicker;
    public void clickDate(){
        datePicker.click();
    }
    public WebElement date(){
        return datePicker;
    }
    @FindBy(xpath= "(//button[@name='day' and text()='1'])[1]")
    private WebElement day1;
    public void clickDay1(){
        day1.click();
    }
    @FindBy(xpath= "(//button[@name='day' and text()='3'])[1]")
    private WebElement day3;
    public void clickDay3(){
        day3.click();
    }
    @FindBy(name="previous-month")
    private WebElement prevMonth;
    public void clickPrevMonth(){
        prevMonth.click();
    }
    @FindBy(linkText = "Locații")
    private WebElement venuesLink;
    @FindBy(linkText = "Creați locație")
    private WebElement createLocationLink;

    @FindBy(name = "name")
    private WebElement nameInput;
    public WebElement getNameInput(){
        return nameInput;
    }

    @FindBy(name = "address")
    private WebElement addressInput;

    @FindBy(name = "city")
    private WebElement cityInput;

    @FindBy(xpath = "//button[text()='Creați locația']") // Locate the button by its text
    private WebElement createLocationButton;
    public void clickVenuesLink() {
        venuesLink.click();
    }

    // Method to click the "Creați locație" link
    public void clickCreateLocation() {
        createLocationLink.click();
    }

    // Method to send keys to the name input field
    public void enterName(String name) {
        nameInput.sendKeys(name);
    }

    // Method to send keys to the address input field
    public void enterAddress(String address) {
        addressInput.sendKeys(address);
    }

    // Method to send keys to the city input field
    public void enterCity(String city) {
        cityInput.sendKeys(city);
    }

    // Method to click the "Creați locația" button
    public void clickCreateLocationButton() {
        createLocationButton.click();
    }

    public WebElement getVenuesLink() {
        return venuesLink;
    }

    public WebElement getCreateLocationLink() {
        return createLocationLink;
    }
    public void clickWhenReady(WebElement locator){
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }
    public void sendKeysWhenReady(WebElement locator,String text){
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.sendKeys(text);
    }
    @FindBy(css=".text-xl.font-semibold.leading-none.tracking-tight")
    private WebElement nameOfLocation;
    public WebElement getNameOfLocation(){
        return nameOfLocation;
    }
    @FindBy(xpath="//button[@class='minimal-toolbar_iconButton__hBqdp']")
    private WebElement buttonNotNeeded;
    public void clickButtonNotNeeded(){
        buttonNotNeeded.click();
    }
}

