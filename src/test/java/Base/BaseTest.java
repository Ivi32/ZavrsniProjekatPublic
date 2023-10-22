package Base;

import Helpers.DataReader;
import Models.InputDataForCheckout;
import Models.LoginData;
import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class BaseTest {
    public static final String BASE_URL = "https://www.saucedemo.com/";

    public static WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;
    public DataReader dataReader;
    public InventoryPage inventoryPage;
    public AboutPage aboutPage;
    public ItemPage itemPage;
    public CartPage cartPage;
    public CheckoutOnePage checkoutOnePage;
    public CheckoutTwoPage checkoutTwoPage;
    public CheckoutCompletePage checkoutCompletePage;


    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        dataReader = new DataReader();
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        aboutPage = new AboutPage();
        itemPage = new ItemPage();
        cartPage = new CartPage();
        checkoutOnePage = new CheckoutOnePage();
        checkoutTwoPage = new CheckoutTwoPage();
        checkoutCompletePage = new CheckoutCompletePage();
    }

    public void logIn(){
        logIn(null);
    }

    public void logIn(String userTypeParam){
        goToStartUrl();
        LoginData loginData = dataReader.getLoginData(userTypeParam);

        loginPage.inputUsername(loginData.getUserName());
        loginPage.inputPassword(loginData.getPassword());
        loginPage.clickOnLoginButton();
    }

    public void goToCart(){
        inventoryPage.addAnyItemToCart(inventoryPage.listOfItems);
        inventoryPage.cartIcon.click();
    }
    public void goToCheckout(){
        cartPage.clickOnCheckoutButton();
    }
    public void enterDataCkeckout() {
        enterDataCkeckout(true);
    }

    public void enterDataCkeckout(boolean usePostalCode){
        InputDataForCheckout inputDataForCheckout = dataReader.getInputDataForCheckout(usePostalCode);
        fillCheckoutOnePage(inputDataForCheckout);

    }

    private void fillCheckoutOnePage(InputDataForCheckout inputDataForCheckout) {
        checkoutOnePage.inputfirstName(inputDataForCheckout.getFirstName());
        checkoutOnePage.inputlastName(inputDataForCheckout.getLastName());
        checkoutOnePage.inputPostalCode(inputDataForCheckout.getPostalCode());
    }

    public void finishCheckout(){
        checkoutOnePage.clickOnContinueButton();
        checkoutTwoPage.clickOnFinishButton();
    }

    public void clearSesionAndLocalStorage(){
        if (!(driver instanceof WebStorage)) {
            throw new IllegalArgumentException("This test expects the driver to implement WebStorage");
        }
        WebStorage webStorage = (WebStorage)driver;
        webStorage.getSessionStorage().clear();
        webStorage.getLocalStorage().clear();
    }

    public boolean elementIsDisplayed(WebElement element) {
        boolean isDisplayed = false;
        try {
            isDisplayed = element.isDisplayed();
        } catch (Exception e) {
            System.out.println(e);
        }
        return isDisplayed;
    }

    public void goToStartUrl()
    {
        driver.navigate().to(BASE_URL);
    }

    @AfterMethod
    public void createScreenShot(ITestResult testResult) throws IOException, InterruptedException {
        if (testResult.getStatus() == ITestResult.FAILURE || testResult.getStatus() == ITestResult.SKIP) {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("c:\\tmp\\"+testResult.getTestClass().getName()+"\\" + testResult.getName() + "-" + System.currentTimeMillis()+".png"));

        }
    }

    @AfterClass
    public void tearDown(){
        //driver.quit();
    }
}
