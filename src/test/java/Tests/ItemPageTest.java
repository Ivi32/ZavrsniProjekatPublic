package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ItemPageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        logIn();
    }

    @Test
    public void verifyItemAddRemove() throws InterruptedException {
        int x = 1;
        for (int i = 0; i < inventoryPage.listOfItems.size(); i++) {
            inventoryPage.listOfItems.get(i).findElement(By.className("inventory_item_img")).click();
            itemPage.clickOnAddToCartButton();
            Assert.assertEquals(String.valueOf(itemPage.shoppingCartIcon.getText()), String.valueOf(x));
            Assert.assertTrue(elementIsDisplayed(itemPage.removeButton));

            itemPage.clickOnRemoveButton();
            Assert.assertTrue(elementIsDisplayed(itemPage.addToCartButton));
            Assert.assertEquals(String.valueOf(itemPage.shoppingCartIcon.getText()), "");

            itemPage.clickOnBackToProducts();
            Assert.assertEquals(driver.getCurrentUrl(), BASE_URL + "inventory.html");
        }
    }





}
