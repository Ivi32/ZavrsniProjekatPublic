package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage extends BaseTest {
    public CheckoutCompletePage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "back-to-products")
    public WebElement backButton;

    @FindBy(className = "pony_express")
    public WebElement picture;

    @FindBy(id = "shopping_cart_container")
    public WebElement cart;
    //-----------------------------

    public void clickOnBackButton(){
        backButton.click();
    }

    public void clickOnCart(){
        cart.click();
    }

    public boolean checkIfCartIsEmpty(){
        if(cart.getText().isEmpty())
            return true;
        else
            return false;
    }


}
