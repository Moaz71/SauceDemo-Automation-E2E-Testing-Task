package POM;

import Actions.ElementActions;

public class Checkout {

    ElementActions act = new ElementActions();
    /**********************************Selectors*********************************************/

    String firstNameSelector = "//input[@id='first-name']";
    String lastNameSelector = "//input[@id='last-name']";
    String postalCodeSelector = "//input[@id='postal-code']";
    String continueButton = "//input[@class='btn_primary cart_button']";
    String headerTextSelector = "//div[@class='subheader' and text()='Checkout: Overview']";
    String finishButton = "//a[@class='btn_action cart_button']";
    String finishPageMsgSelector = "//h2[@class='complete-header']";
    String checkoutItemsCountSelector ="//div[@class='inventory_item_name']";
    String itemPriceSelector = "//div[@class='inventory_item_name' and contains(text(),'%s')]/ancestor::div[@class='cart_item_label']/div[@class='inventory_item_price']";
    String totalPriceSelector = "//div[@class='summary_subtotal_label']";
    /**********************************Methods*********************************************/
    //fill user and pass field
    public Checkout enterFirstName(String firstName){
        act.setText(firstNameSelector, ElementActions.Locators.Xpath,firstName);
        return this;
    }

    public Checkout enterLastName(String lastName){
        act.setText(lastNameSelector, ElementActions.Locators.Xpath,lastName);
        return this;
    }

    public Checkout enterPostalCode(String postalCode){
        act.setText(postalCodeSelector, ElementActions.Locators.Xpath,postalCode);
        return this;
    }
    //click on continue button
    public Checkout clickOnContinueButton(){
        act.click(continueButton, ElementActions.Locators.Xpath,headerTextSelector, ElementActions.Locators.Xpath,true);
        return this;
    }
    public String getoverviewHeader(){
        return act.getText(headerTextSelector,ElementActions.Locators.Xpath);
    }

    public void clickOnFinishButton(){
        act.click(finishButton, ElementActions.Locators.Xpath,finishPageMsgSelector, ElementActions.Locators.Xpath,true);
    }

    public String getFinishPageMsg(){
        return act.getText(finishPageMsgSelector,ElementActions.Locators.Xpath);
    }

    public int getItemsCount(){
        return act.getNumberOfElements(checkoutItemsCountSelector, ElementActions.Locators.Xpath);
    }
    public double  getProductPrice(String productName){
        return Double.parseDouble(act.getText(String.format(itemPriceSelector,productName),ElementActions.Locators.Xpath).split("\\$")[1].trim());
    }
    public double getTotalPrice(){
        return Double.parseDouble(act.getText(totalPriceSelector,ElementActions.Locators.Xpath).split("\\$")[1].trim());
    }
    public String getFirstNameTXT(){
        return act.getAttributeValue(firstNameSelector,ElementActions.Locators.Xpath, ElementActions.Attribute.VALUE);
    }
}
