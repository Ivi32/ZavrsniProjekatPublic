package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

public class InventoryPageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        logIn();
    }

    @AfterMethod
    public void pageReset(){
        clearSesionAndLocalStorage();

    }

    @Test
    public void verifySortingItemsByName(){
        Select drpCountry = new Select(inventoryPage.sorterMenu);
        drpCountry.selectByValue("az");
        Assert.assertTrue(inventoryPage.itemsSortedAlphabetically(inventoryPage.listOfItems));

        drpCountry.selectByValue("za");
        Assert.assertFalse(inventoryPage.itemsSortedAlphabetically(inventoryPage.listOfItems));
    }

    @Test
    public void verifySortingItemsByPrice(){
        Select drpCountry = new Select(inventoryPage.sorterMenu);
        drpCountry.selectByValue("lohi");
        Assert.assertTrue(inventoryPage.itemsSortedByPrice(inventoryPage.itemPrices));

        drpCountry.selectByValue("hilo");
        Assert.assertFalse(inventoryPage.itemsSortedByPrice(inventoryPage.itemPrices));
    }

    @Test
    public void verifySidebarMenuLinks(){
        inventoryPage.sidebarMenu.click();
        //inventoryPage.clickToReset(); test ovde pada, ovo je bug, nije clickable
        //logout link isproban u Login/Logout testu
        inventoryPage.clickOnAllItems();
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL +"inventory.html");
        Assert.assertTrue(inventoryPage.allItemsDisplayed());

        inventoryPage.clickOnAbout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
        Assert.assertTrue(elementIsDisplayed(aboutPage.signinButton));
    }

    @Test(priority = 10)
    public void verifyUserCanAddRemoveAllItemsToCart(){
        Assert.assertTrue(inventoryPage.checkIfCartIsEmpty());
        inventoryPage.addAllItemsToCart(inventoryPage.listOfItems);
        Assert.assertEquals(String.valueOf(inventoryPage.listOfItems.size()), inventoryPage.cartIcon.getText());
        Assert.assertTrue(inventoryPage.areAllItemsFromCurrentPageAddedToCart());

        inventoryPage.removeAllItemsFromCart(inventoryPage.listOfItems);
        Assert.assertTrue(inventoryPage.checkIfCartIsEmpty());
        Assert.assertTrue(inventoryPage.addToCartDisplayed());
    }

    @Test
    public void verifyUserCanAddAnyItemToCart(){

        Assert.assertTrue(inventoryPage.checkIfCartIsEmpty());
        inventoryPage.addAnyItemToCart(inventoryPage.listOfItems);
        Assert.assertEquals(String.valueOf(1), inventoryPage.cartIcon.getText());
        Assert.assertTrue(inventoryPage.isAnyItemFromCurrentPageAddedToCart());
    }

    @Test
    public void verifyUserCanAddAllItemsToCart(){
        Assert.assertTrue(inventoryPage.checkIfCartIsEmpty());
        inventoryPage.addAllItemsToCart(inventoryPage.listOfItems);
        Assert.assertEquals(String.valueOf(inventoryPage.listOfItems.size()), inventoryPage.cartIcon.getText());
        Assert.assertTrue(inventoryPage.areAllItemsFromCurrentPageAddedToCart());
    }

    @Test
    public void verifyUserCanRemoveAllItemsFromCart(){
        if(inventoryPage.checkIfCartIsEmpty())
            System.out.println("Cart is empty, nothing to remove");
        else
            inventoryPage.removeAllItemsFromCart(inventoryPage.listOfItems);
        Assert.assertTrue(inventoryPage.checkIfCartIsEmpty());
        Assert.assertTrue(inventoryPage.addToCartDisplayed());
    }

    //asertovati da su svi elementi prikazani na stranici
    @Test
    public void verifyAllItemsAreProperlyDisplayed(){
        Assert.assertTrue(inventoryPage.allItemsHavePictures(inventoryPage.listOfItems));
        Assert.assertTrue(inventoryPage.allItemsHaveNames(inventoryPage.listOfItems));
        Assert.assertTrue(inventoryPage.allItemsHaveDescription(inventoryPage.listOfItems));
        Assert.assertTrue(inventoryPage.allItemsHaveAddtocartButton(inventoryPage.listOfItems));
        Assert.assertTrue(inventoryPage.allItemsHavePrice(inventoryPage.listOfItems));
    }

    @Test(priority = 100)
    public void verifySocialNetworkAccess() {

        inventoryPage.clickOnFacebookIcon();
        inventoryPage.refreshListTabs();
        driver.switchTo().window(inventoryPage.listTabs.get(1));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/saucelabs");
        driver.switchTo().window(inventoryPage.listTabs.get(0));

        inventoryPage.clickOnTwitterIcon();
        inventoryPage.refreshListTabs();
        driver.switchTo().window(inventoryPage.listTabs.get(2));
        Assert.assertEquals(driver.getCurrentUrl(), "https://twitter.com/saucelabs");
        System.out.println(driver.getTitle());
        driver.switchTo().window(inventoryPage.listTabs.get(0));

        inventoryPage.clickOnLinkedinIcon();
        inventoryPage.refreshListTabs();
        driver.switchTo().window(inventoryPage.listTabs.get(3));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/company/sauce-labs/");
    }

}
