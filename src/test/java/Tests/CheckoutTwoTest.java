package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTwoTest extends BaseTest {
    @BeforeMethod
    public void pageSetUp(ITestContext context){
        logIn();
        goToCart();
        goToCheckout();
        enterDataCkeckout();
    }

    @AfterMethod
    public void pageReset(){
        clearSesionAndLocalStorage();
    }

    @Test
    public void verifyTotalPrice(){
        checkoutOnePage.clickOnContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL + "checkout-step-two.html");
        Assert.assertTrue(checkoutTwoPage.checkPriceForPayment());
    }

    @Test
    public void verifyUserCanCancelCheckout() throws InterruptedException {
        checkoutOnePage.clickOnContinueButton();
        checkoutTwoPage.clickOnCancelButton();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(),  BASE_URL + "inventory.html");
        Assert.assertFalse(elementIsDisplayed(checkoutTwoPage.finishButton));
    }

    @Test
    public void verifyUserCanFinishCheckout(){
        finishCheckout();
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL + "checkout-complete.html");
        Assert.assertFalse(elementIsDisplayed(checkoutTwoPage.cancelButton));
    }
}
