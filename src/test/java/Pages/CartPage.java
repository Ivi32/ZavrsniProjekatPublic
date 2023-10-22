package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BaseTest {
    public CartPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "cart_item")
    public List<WebElement> itemsInTheCart;

    @FindBy(id = "shopping_cart_container")
    public WebElement cart;

    @FindBy(className = "header_secondary_container")
    public WebElement subheaderMessage;

    @FindBy(id ="continue-shopping")
    public WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    public WebElement checkoutButton;

    //--------------------------------------------------

    public void clickOnContinueShoppingButton(){
        continueShoppingButton.click();
    }

    public void clickOnCheckoutButton(){
        checkoutButton.click();
    }
}
