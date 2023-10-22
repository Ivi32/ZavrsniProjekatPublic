package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOnePage extends BaseTest {
    public CheckoutOnePage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id ="cancel")
    public WebElement cancelButton;

    @FindBy(id = "continue")
    public WebElement continueButton;

    @FindBy(id = "first-name")
    public WebElement firstNameField;

    @FindBy(id ="last-name")
    public WebElement lastNameField;

    @FindBy(id = "postal-code")
    public WebElement postalCodeField;

    @FindBy(id = "shopping_cart_container")
    public WebElement cartIcon;

    @FindBy(css = ".error-message-container.error")
    public WebElement errorMessage;

    //-----------------------------------------------

    public void clickOnContinueButton(){
        continueButton.click();
    }

    public void clickOnCancelButton(){
        cancelButton.click();
    }

    public void inputfirstName(String firstName){
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void inputlastName(String lastName){
       lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void inputPostalCode(int postalCode){
        postalCodeField.clear();
        postalCodeField.sendKeys(String.valueOf(postalCode));
    }
}
