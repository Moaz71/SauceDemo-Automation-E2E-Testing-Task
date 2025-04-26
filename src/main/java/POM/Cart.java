package POM;

import Actions.ElementActions;

public class Cart {

    ElementActions act = new ElementActions();

    /************************************Selectors*******************************************/
    String cartItemsCountSelector ="//div[@class='inventory_item_name']";
    String removeBtnSelector = "//div[@class='cart_item_label']/a/div[contains(text(), '%s')]/ancestor::div[@class='cart_item_label']/div[@class='item_pricebar']/button";
    String backPackRemoveSelector = "//a[@id='item_4_title_link']/ancestor::div[@class='cart_item']//button";
    String cartButton = "//a[@class='shopping_cart_link fa-layers fa-fw']";
    String cartExpectedElement = "//div[@class='subheader']";
    String checkoutButton = "//a[@class='btn_action checkout_button']";
    String firstNameSelector = "//input[@id='first-name']";

    // get item added count
    public int getItemsCount(){
        return act.getNumberOfElements(cartItemsCountSelector, ElementActions.Locators.Xpath);
    }
    public Cart removeProductsFromCart(String productName){
        act.click(String.format(removeBtnSelector, productName), ElementActions.Locators.Xpath, cartExpectedElement, ElementActions.Locators.Xpath,true);
        return this;
    }

    //remove item from cart
    public void clickOnRemoveButton(){
        act.click(backPackRemoveSelector,ElementActions.Locators.Xpath,cartButton,ElementActions.Locators.Xpath,true);
    }

    //click on checkout
    public Checkout clickOnCheckoutButton(){
        act.click(checkoutButton, ElementActions.Locators.Xpath,firstNameSelector, ElementActions.Locators.Xpath,true);
        return new Checkout();
    }

}
