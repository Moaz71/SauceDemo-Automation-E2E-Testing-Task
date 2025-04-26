package POM;

import Actions.ElementActions;
import org.testng.Assert;

public class AddToCart {
    ElementActions act = new ElementActions();
    //div[@class='inventory_item_label']/a/div[contains(text(), 'Backpack')]
    /**********************************Selectors*********************************************/
    String productNameSelector = "//div[@class='inventory_item_label']/a/div[contains(text(), '%s')]/ancestor::div[@class='inventory_item']/div[@class='pricebar']/button";
    String backPackSelector = "//a[@id='item_4_title_link']/ancestor::div[@class='inventory_item']//button[contains(text(), 'ADD TO CART')]";
    String backPackRemoveSelector = "//a[@id='item_4_title_link']/ancestor::div[@class='inventory_item']//button[contains(text(), 'REMOVE')]";

    String onesieSelector = "//a[@id='item_2_title_link']/ancestor::div[@class='inventory_item']//button[contains(text(), 'ADD TO CART')]";
    String tShirtSelector = "//a[@id='item_3_title_link']/ancestor::div[@class='inventory_item']//button[contains(text(), 'ADD TO CART')]";
    String cartButton = "//a[@class='shopping_cart_link fa-layers fa-fw']";
    String selectedItemsCountSelector = "//button[contains(text(), 'REMOVE')]";
    String checkoutButton = "//a[@class='btn_action checkout_button']";
    /**********************************Methods*********************************************/
    public AddToCart addProductsToCart(String productName){
        //here u can make switch case for all products
        //or u can use dynamic xpath
        String btnText = act.getText(String.format(productNameSelector,productName),ElementActions.Locators.Xpath);
        if(btnText.equals("ADD TO CART")) {
            act.click(String.format(productNameSelector, productName), ElementActions.Locators.Xpath, cartButton, ElementActions.Locators.Xpath,true);
        }else {
            System.out.println("Product already added to cart");
            Assert.fail("Product already added to cart: "+productName);
        }
        return this;
    }

    public AddToCart removeProductsFromCart(String productName){
        String btnText = act.getText(String.format(productNameSelector,productName),ElementActions.Locators.Xpath);
        if(btnText.equals("REMOVE")) {
            act.click(String.format(productNameSelector, productName), ElementActions.Locators.Xpath, cartButton, ElementActions.Locators.Xpath,true);
        }else {
            System.out.println("Product already removed from cart: "+productName);
            //Assert.fail("Product already removed from cart: "+productName);
        }
        return this;
    }
    //Click on cart button
    public Cart ckickOnCartButton(){
        act.click(cartButton,ElementActions.Locators.Xpath,checkoutButton,ElementActions.Locators.Xpath,true);
        return new Cart();
    }
    public String getBtnText(String productName){
        return act.getText(String.format(productNameSelector,productName),ElementActions.Locators.Xpath);
    }

    public void clickOnBackPackButton(){
        act.click(backPackSelector,ElementActions.Locators.Xpath,cartButton,ElementActions.Locators.Xpath,true);
    }

    public void clickOnOnesieButton(){
        act.click(onesieSelector,ElementActions.Locators.Xpath,cartButton,ElementActions.Locators.Xpath,true);
    }

    public void clickOnTShirtButton(){
        act.click(tShirtSelector,ElementActions.Locators.Xpath,cartButton,ElementActions.Locators.Xpath,true);
    }


    // get item added count
    public int getItemsCount(){
        return act.getNumberOfElements(selectedItemsCountSelector,ElementActions.Locators.Xpath);
    }

    //remove item from cart
    public void clickOnRemoveButton(){
        act.click(backPackRemoveSelector,ElementActions.Locators.Xpath,cartButton,ElementActions.Locators.Xpath,true);
    }

}
