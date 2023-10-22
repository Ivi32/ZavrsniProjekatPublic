package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AboutPage extends BaseTest {

    public AboutPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html/body/div[1]/header/div/div/div[2]/div/div[1]/a/button")
    public WebElement signinButton;

    //-----------------------------------------------

}
