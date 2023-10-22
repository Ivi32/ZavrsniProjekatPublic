package Tests;

import Base.BaseTest;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginLogoutTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        goToStartUrl();
    }

    @Test(priority = 20)
    public void verifyThatUserCanLogin() throws InterruptedException {
        logIn();

        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL + "inventory.html");

        inventoryPage.clickOnTheSidebarMenu();
        Thread.sleep(2000);
        Assert.assertTrue(elementIsDisplayed(inventoryPage.logout));
    }

    @Test(priority = 10)
    public void verifyThatUserCannotLogin(){
        logIn("locked_out_user");

        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);
        Assert.assertTrue(elementIsDisplayed(loginPage.loginErrorMessage));
    }

    @Test(priority = 30)
    public void verifyThatUserCanLogout() throws InterruptedException {

        logIn();

        inventoryPage.clickOnTheSidebarMenu();
        Thread.sleep(2000);
        inventoryPage.clickOnLogout();

        Assert.assertTrue(elementIsDisplayed(loginPage.loginButton));
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);
    }

    @Test(priority = 40)
    public void verifyLoginWithCookies() throws InterruptedException {

        Cookie cookie = new Cookie("session-username", "standard_user");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();

        driver.navigate().to(BASE_URL + "inventory.html");
        driver.navigate().refresh();

        inventoryPage.clickOnTheSidebarMenu();
        Thread.sleep(2000);
        Assert.assertTrue(elementIsDisplayed(inventoryPage.logout));
        Assert.assertTrue(elementIsDisplayed(inventoryPage.sorterMenu));
    }

}
