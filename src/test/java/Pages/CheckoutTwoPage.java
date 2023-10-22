package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutTwoPage extends BaseTest {
    public CheckoutTwoPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "finish")
    public WebElement finishButton;

    @FindBy(id = "cancel")
    public WebElement cancelButton;

    @FindBy( css = ".summary_info_label.summary_total_label")
    public WebElement totalSum;

    @FindBy( className = "summary_tax_label")
    public WebElement taxes;

    @FindBy(className = "cart_item")
    public List<WebElement> itemsInCart;

    //-----------------------------------

    public void clickOnCancelButton(){
        cancelButton.click();
    }

    public void clickOnFinishButton(){
        finishButton.click();
    }

    public double totalCartPrice(List<WebElement> itemsInCart){
        double sum = 0;
        for (int i = 0; i < itemsInCart.size() ; i++) {
            int quantity = Integer.parseInt(itemsInCart.get(i).findElement(By.className("cart_quantity")).getText());
            double itemPrice = Double.parseDouble(itemsInCart.get(i).findElement(By.className("inventory_item_price")).getText().substring(1));
            sum+= itemPrice*quantity;
        }
        return sum;
    }

    public boolean checkPriceForPayment(){
        double tax = Double.parseDouble(taxes.getText().substring(6));
        double expectedPrice = totalCartPrice(itemsInCart) + tax;
        double actualPrice = Double.parseDouble(totalSum.getText().substring(8));
        if(expectedPrice==actualPrice)
            return true;
        else
            return false;
    }
}
