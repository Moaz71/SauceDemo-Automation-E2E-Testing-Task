package POM;

import Actions.ElementActions;

public class AddToCart {
    ElementActions act = new ElementActions();

    /**********************************Selectors*********************************************/
    String backPackSelector = "//a[@id='item_4_title_link']/ancestor::div[@class='inventory_item']//button[contains(text(), 'ADD TO CART')]";
    String onesieSelector = "//a[@id='item_2_title_link']/ancestor::div[@class='inventory_item']//button[contains(text(), 'ADD TO CART')]";
    String tShirtSelector = "//a[@id='item_3_title_link']/ancestor::div[@class='inventory_item']//button[contains(text(), 'ADD TO CART')]";
    String cartButton = "//a[@class='shopping_cart_link fa-layers fa-fw']";
    String cartItemsCountSelector = "//button[contains(text(), 'REMOVE')]";
    /**********************************Methods*********************************************/
    public void clickOnBackPackButton(){
        act.click(backPackSelector,ElementActions.Locators.Xpath,cartButton,ElementActions.Locators.Xpath);
    }

    public void clickOnOnesieButton(){
        act.click(onesieSelector,ElementActions.Locators.Xpath,cartButton,ElementActions.Locators.Xpath);
    }

    public void clickOnTShirtButton(){
        act.click(tShirtSelector,ElementActions.Locators.Xpath,cartButton,ElementActions.Locators.Xpath);
    }

    public String getButtonText(){
        return act.getText(backPackSelector,ElementActions.Locators.Xpath);
    }

    // get item added count
    public int getItemsCount(){
        return act.getNumberOfElements(cartItemsCountSelector,ElementActions.Locators.Xpath);
    }

}
