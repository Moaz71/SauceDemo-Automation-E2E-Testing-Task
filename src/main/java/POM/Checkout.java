package POM;

import Actions.ElementActions;

public class Checkout {

    ElementActions act = new ElementActions();
    /**********************************Selectors*********************************************/
    String checkoutButton = "//a[@class='btn_action checkout_button']";
    String firstNameSelector = "//input[@id='first-name']";
    String lastNameSelector = "//input[@id='last-name']";
    String postalCodeSelector = "//input[@id='postal-code']";
    String continueButton = "//input[@class='btn_primary cart_button']";
    String headerTextSelector = "//div[@class='subheader' and text()='Checkout: Overview']";
    String finishButton = "//a[@class='btn_action cart_button']";
    String finishPageMsgSelector = "//h2[@class='complete-header']";

    /**********************************Methods*********************************************/
    //click on checkout
    public void clickOnCheckoutButton(){
        act.click(checkoutButton, ElementActions.Locators.Xpath,firstNameSelector, ElementActions.Locators.Xpath);

    }

    //click on continue button
    public void clickOnContinueButton(){
        act.click(continueButton, ElementActions.Locators.Xpath,headerTextSelector, ElementActions.Locators.Xpath);
    }

    //fill user and pass field
    public void enterFirstName(String firstName){
        act.typeText(firstNameSelector, ElementActions.Locators.Xpath,firstName);
    }

    public void enterLastName(String lastName){
        act.typeText(lastNameSelector, ElementActions.Locators.Xpath,lastName);
    }

    public void enterPostalCode(String postalCode){
        act.typeText(postalCodeSelector, ElementActions.Locators.Xpath,postalCode);
    }
    public String getoverviewHeader(){
        return act.getText(headerTextSelector,ElementActions.Locators.Xpath);
    }

    public void clickOnFinishButton(){
        act.click(finishButton, ElementActions.Locators.Xpath,finishPageMsgSelector, ElementActions.Locators.Xpath);
    }

    public String getFinishPageMsg(){
        return act.getText(finishPageMsgSelector,ElementActions.Locators.Xpath);
    }
}
