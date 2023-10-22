package Tests;

import Base.BaseTest;
import Models.InputDataForCheckout;
import org.openqa.selenium.html5.WebStorage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutOneTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        logIn();
        goToCart();
        goToCheckout();
    }

    @AfterMethod
    public void pageReset(){
        clearSesionAndLocalStorage();
    }

    @Test(priority = 20)
    public void verifyUserCanCheckout(){
        enterDataCkeckout();
        checkoutOnePage.clickOnContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL + "checkout-step-two.html");
        Assert.assertTrue(elementIsDisplayed(checkoutTwoPage.finishButton));
    }

    @Test(priority = 10)
    public void verifyUserCannotCheckout(){
        //bug, dozvoljava da se predje na checkout kad se za postal code unese 0
        enterDataCkeckout(false);
        checkoutOnePage.clickOnContinueButton();

        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL + "checkout-step-one.html");
        Assert.assertTrue(elementIsDisplayed(checkoutOnePage.errorMessage));
    }

    @Test(priority = 30)
    public void verifyUserCanCancelCheckout(){
        enterDataCkeckout();
        checkoutOnePage.clickOnCancelButton();

        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL + "cart.html");
        Assert.assertFalse(elementIsDisplayed(checkoutOnePage.firstNameField));
    }



}
