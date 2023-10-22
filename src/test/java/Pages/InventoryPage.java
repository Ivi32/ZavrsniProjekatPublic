package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InventoryPage extends BaseTest {
    public InventoryPage(){
        PageFactory.initElements(driver, this);
    }

    public ArrayList<String> listTabs = new ArrayList<>();

    @FindBy(id = "react-burger-menu-btn")
    public WebElement sidebarMenu;

    @FindBy(id ="inventory_sidebar_link")
    public WebElement linkToAllItems;

    @FindBy(id="about_sidebar_link")
    public WebElement linkToAboutPage;

    @FindBy(id ="logout_sidebar_link")
    public WebElement logout;

    @FindBy(xpath = "<a id=\"about_sidebar_link\" class=\"bm-item menu-item\" href=\"#\" tabindex=\"0\" style=\"display: block;\">Reset App State</a>")
    public WebElement linkToReset;

    @FindBy(className = "product_sort_container")
    public WebElement sorterMenu;

    @FindBy(className = "inventory_item")
    public List<WebElement> listOfItems;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> itemPrices;

    @FindBy(id ="shopping_cart_container")
    public WebElement cartIcon;

    @FindBy(linkText = "Twitter")
    public WebElement twitterIcon;

    @FindBy(linkText = "Facebook")
    public WebElement facebookIcon;

    @FindBy(linkText = "LinkedIn")
    public WebElement linkedinIcon;


    //--------------------------------

    public void clickOnTheSidebarMenu(){
        sidebarMenu.click();
    }

    public void clickOnLogout(){
        logout.click();
    }

    public void clickOnAbout(){
        linkToAboutPage.click();
    }

    public void clickOnAllItems(){
        linkToAllItems.click();
    }

    public void clickToReset(){
        linkToReset.click();
    }

    public void clickOnSorterMenu(){
        sorterMenu.click();
    }

    public void clickOnTwitterIcon(){
        twitterIcon.click();
    }

    public void clickOnFacebookIcon(){
        facebookIcon.click();
    }

    public void clickOnLinkedinIcon(){
        linkedinIcon.click();
    }

    public boolean allItemsDisplayed(){
        boolean b= true;
        for (WebElement item:listOfItems) {
            if(item.isDisplayed())
                continue;
            else
                b = false;
        }
      return b;
    }

    public boolean itemsSortedAlphabetically(List<WebElement> listOfItems){
        for (int i = 0; i < listOfItems.size()-1; i++) {
            if((listOfItems.get(i).getText().compareTo(listOfItems.get(i+1).getText()) > 0))
                return false;
        }
        return true;
    }

    public boolean itemsSortedByPrice(List<WebElement> itemPrices){
        for (int i = 0; i < itemPrices.size()-1; i++) {
            var item = itemPrices.get(i).getText().substring(1);
            var itemNext = itemPrices.get(i+1).getText().substring(1);
            if( Double.parseDouble(item)  > Double.parseDouble(itemNext) ) {
                return false;
            }
        }
        return true;
    }

    public void addAllItemsToCart(List<WebElement> listOfItems){
        for (WebElement item:listOfItems) {
            item.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).click();
        }
    }

    public void addAnyItemToCart(List<WebElement> listOfItems){
        Random randomizer = new Random();
        WebElement random = listOfItems.get(randomizer.nextInt(listOfItems.size()));
        random.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).click();
    }

    public boolean addToCartDisplayed(){
        boolean b = true;
        for (WebElement item:listOfItems) {
            if(item.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).isDisplayed())
                continue;
            else
                b = false;
        }
        return b;
    }

    public void removeAllItemsFromCart(List<WebElement> listOfItems){
        for (WebElement item:listOfItems) {
            item.findElement(By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory")).click();
        }
    }

    public boolean areAllItemsFromCurrentPageAddedToCart(){
        boolean b = true;
        for (WebElement item:listOfItems) {
            if(item.findElement(By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory")).isDisplayed())
                continue;
            else
                b = false;
            //TODO Proveri
            //break;
        }
        return b;
    }

    public boolean isAnyItemFromCurrentPageAddedToCart(){
        for (WebElement item:listOfItems) {
            try {
                if (item.findElement(By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory")).isDisplayed())
                    return true;
            } catch (Exception e) { }
        }
        return false;
    }

    public boolean checkIfCartIsEmpty(){
        return cartIcon.getText().isEmpty();
    }

    public boolean allItemsHavePictures(List<WebElement> listOfItems){
        boolean b = true;
        for (WebElement item:listOfItems) {
            if(item.findElement(By.className("inventory_item_img")).isDisplayed())
                continue;
            else
                b = false;
        }
        return b;
    }

    public boolean allItemsHaveNames(List<WebElement> listOfItems){
        boolean b = true;
        for (WebElement item:listOfItems) {
            if(item.findElement(By.className("inventory_item_name")).isDisplayed())
                continue;
            else
                b = false;
        }
        return b;
    }

    public boolean allItemsHaveDescription(List<WebElement> listOfItems){
        boolean b = true;
        for (WebElement item:listOfItems) {
            if(item.findElement(By.className("inventory_item_desc")).isDisplayed())
                continue;
            else
                b = false;
        }
        return b;
    }

    public boolean allItemsHaveAddtocartButton(List<WebElement> listOfItems){
        boolean b = true;
        for (WebElement item:listOfItems) {
            if(item.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).isDisplayed())
                continue;
            else
                b = false;
        }
        return b;
    }

    public boolean allItemsHavePrice(List<WebElement> listOfItems){
        boolean b = true;
        for (WebElement item:listOfItems) {
            if(item.findElement(By.className("inventory_item_price")).isDisplayed())
                continue;
            else
                b = false;
        }
        return b;
    }

    public void refreshListTabs() {
        listTabs = new ArrayList<>(driver.getWindowHandles());
    }


}

