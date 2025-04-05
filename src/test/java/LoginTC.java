import Actions.BrowserActions;
import POM.Login;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTC {

    Login login ;
    /*******************************Test Data*******************************/
    String LoginUrl = "https://www.saucedemo.com/v1";
    String inventoryUrl = "https://www.saucedemo.com/v1/inventory.html";
    String standardUserName = "standard_user";
    String userPassword = "secret_sauce";
    String wrongUserName = "moaz";
    String wrongPassword ="123";

    @BeforeTest
    public void setUp() {BrowserActions.initializeWebDriver(BrowserActions.Browser.Chrome);
        BrowserActions.maximizeWindow();
    }
    @Test
    public void loginHappyTest() {

        login = new Login();
        login.navigateToLoginPage(LoginUrl);
        login.enterUserName(standardUserName);
        login.enterPassword(userPassword);
        login.clickOnLoginButton();
        Assert.assertEquals(login.getCurrentUrl(),inventoryUrl);
    }
    @Test
    public void loginWithoutUserName(){
        login = new Login();
        login.navigateToLoginPage(LoginUrl);
        login.enterPassword(userPassword);
        login.clickOnLoginButton();
        Assert.assertEquals(login.getErrorMsg(),"Epic sadface: Username is required");

    }
    @Test
    public void loginWithoutPassword(){
        login = new Login();
        login.navigateToLoginPage(LoginUrl);
        login.enterUserName(standardUserName);
        login.clickOnLoginButton();
        // assert the error msg
        Assert.assertEquals(login.getErrorMsg(),"Epic sadface: Password is required");

    }

    //login with wrong user name
    @Test
    public void loginWithWrongUserName(){
        login = new Login();
        login.navigateToLoginPage(LoginUrl);
        login.enterUserName(wrongUserName);
        login.enterPassword(userPassword);
        login.clickOnLoginButton();
        Assert.assertEquals(login.getErrorMsg(),"Epic sadface: Username and password do not match any user in this service");
    }

    //login with wrong password
    @Test
    public void loginWithWrongPassword(){
        login = new Login();
        login.navigateToLoginPage(LoginUrl);
        login.enterUserName(standardUserName);
        login.enterPassword(wrongPassword);
        login.clickOnLoginButton();
        Assert.assertEquals(login.getErrorMsg(),"Epic sadface: Username and password do not match any user in this service");
    }

    @AfterTest
    public void tearDown() {
        //BrowserActions.quitBrowser();
    }
}
