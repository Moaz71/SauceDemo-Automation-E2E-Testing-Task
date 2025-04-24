package POM;

import Actions.ElementActions;

public class Login {

    ElementActions act =new ElementActions();

    /********************************Locators**********************************/
    String userNameSelector= "//input[@id='user-name']";
    String passwordSelector = "//input[@id='password']";
    String loginButton ="//input[@id='login-button']";
    String expectedElement = "//div[@class='product_label']";
    String errorMsgSelector = "//h3";

    //navigate
    public void navigateToLoginPage(String pageUrl){
        act.navigate(pageUrl,userNameSelector,ElementActions.Locators.Xpath);
    }

    //fill user and pass field
    public void enterUserName(String userName){
        act.setText(userNameSelector,ElementActions.Locators.Xpath,userName);
    }
    public void enterPassword(String userPassword){
        act.setText(passwordSelector,ElementActions.Locators.Xpath,userPassword);
    }

    //click on login button
    public void clickOnLoginButton(){
        act.click(loginButton,ElementActions.Locators.Xpath,expectedElement,ElementActions.Locators.Xpath);
    }
    //get current url
    public String getCurrentUrl(){
        return act.getCurrentUrl();
    }
    public String getErrorMsg(){
        return act.getText(errorMsgSelector,ElementActions.Locators.Xpath);
    }
}
