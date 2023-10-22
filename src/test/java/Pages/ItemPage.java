package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage extends BaseTest {

    public ItemPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="back-to-products")
    public WebElement backToProducts;

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    public WebElement addToCartButton;

    @FindBy(id = "shopping_cart_container")
    public WebElement shoppingCartIcon;

    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory")
    public WebElement removeButton;

    //---------------------------
    public void clickOnBackToProducts(){
        backToProducts.click();
    }

    public void clickOnAddToCartButton(){
        addToCartButton.click();
    }

    public void clickOnRemoveButton(){
        removeButton.click();
    }

}
