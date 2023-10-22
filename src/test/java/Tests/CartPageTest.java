package Tests;

import Base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class CartPageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        logIn();
        goToCart();

    }

    @AfterMethod
    public void pageReset() {
        clearSesionAndLocalStorage();
    }



    @Test
    public void verifyProductsAreInTheCart() {
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL + "cart.html");
        Assert.assertEquals(cartPage.subheaderMessage.getText(), "Your Cart");
        Assert.assertEquals(String.valueOf(cartPage.itemsInTheCart.size()), cartPage.cart.getText());
        Assert.assertTrue(elementIsDisplayed(cartPage.checkoutButton));
        Assert.assertTrue(elementIsDisplayed(cartPage.continueShoppingButton));
    }

    @Test
    public void verifyQuantityOfProductsInTheCart() {
        int a = 1;
        for (int i = 0; i < cartPage.itemsInTheCart.size(); i++) {
            Assert.assertEquals(String.valueOf(cartPage.itemsInTheCart.get(i).findElement(By.className("cart_quantity")).getText()), String.valueOf(a));
        }
    }

    //@Test
    public void verifyUserCanChangeQuantity() {
        //ovo je bug, ne dozvoljava promenu kolicine proizvoda, ocekujem da ce test da padne
        int a = 2;
        for (int i = 0; i < cartPage.itemsInTheCart.size(); i++) {
            cartPage.itemsInTheCart.get(i).findElement(By.className("cart_quantity")).sendKeys(String.valueOf(a));
            Assert.assertEquals(String.valueOf(cartPage.itemsInTheCart.get(i).findElement(By.className("cart_quantity")).getText()), String.valueOf(a));
        }
    }

    @Test
    public void verifyUserCanContinueShopping(){
        cartPage.clickOnContinueShoppingButton();
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL + "inventory.html");
        Assert.assertFalse(elementIsDisplayed(cartPage.checkoutButton));
    }


    @Test
    public void verifyUserCanProceedToCheckout(){
        cartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL + "checkout-step-one.html");
        Assert.assertFalse(elementIsDisplayed(cartPage.continueShoppingButton));
    }


}