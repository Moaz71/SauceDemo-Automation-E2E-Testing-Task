package POM;

import Actions.ElementActions;

public class Cart {

    ElementActions act = new ElementActions();

    /************************************Selectors*******************************************/
    String cartItemsCountSelector ="//div[@class='inventory_item_name']";
    String backPackRemoveSelector = "//a[@id='item_4_title_link']/ancestor::div[@class='cart_item']//button";
    String cartButton = "//a[@class='shopping_cart_link fa-layers fa-fw']";

    //Click on cart button
    public void ckickOnCartButton(){
        act.click(cartButton,ElementActions.Locators.Xpath,backPackRemoveSelector,ElementActions.Locators.Xpath);
    }
    // get item added count
    public int getItemsCount(){
        return act.getNumberOfElements(cartItemsCountSelector, ElementActions.Locators.Xpath);
    }

    //remove item from cart
    public void clickOnRemoveButton(){
        act.click(backPackRemoveSelector,ElementActions.Locators.Xpath,cartButton,ElementActions.Locators.Xpath);
    }
}
