package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutCompleteTest extends BaseTest {
    @BeforeMethod
    public void pageSetUp(){
        logIn();
        goToCart();
        goToCheckout();
        enterDataCkeckout();
        finishCheckout();
    }

    @Test
    public void verifyUserCompletedCheckout(){
        Assert.assertTrue(elementIsDisplayed(checkoutCompletePage.backButton));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");
        Assert.assertTrue(elementIsDisplayed(checkoutCompletePage.picture));
    }

    @Test
    public void verifyUserCanGoBackToHomepage(){
        checkoutCompletePage.clickOnBackButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertFalse(elementIsDisplayed(checkoutCompletePage.picture));
    }

    @Test
    public void verifyCartIsEmptiedAfterShopping(){
        Assert.assertTrue(checkoutCompletePage.checkIfCartIsEmpty());
        checkoutCompletePage.clickOnCart();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertTrue(elementIsDisplayed(cartPage.checkoutButton));
    }
}
