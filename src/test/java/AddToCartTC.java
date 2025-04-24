import Actions.BrowserActions;
import POM.AddToCart;
import POM.Login;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class AddToCartTC {

    Login login ;
    /*******************************Test Data*******************************/
    String LoginUrl = "https://www.saucedemo.com/v1";
    String inventoryUrl = "https://www.saucedemo.com/v1/inventory.html";
    String standardUserName = "standard_user";
    String userPassword = "secret_sauce";


    @Parameters({"browser"})
    @BeforeTest
    public void setUp( String browser) {
        BrowserActions.initializeWebDriver(BrowserActions.Browser.valueOf(browser));
        BrowserActions.maximizeWindow();
    }
    @Test
    public void AddToCart() {

        loginHappyTest();

        //add item to cart
        AddToCart addToCart = new AddToCart();
        addToCart.clickOnBackPackButton();
        addToCart.clickOnOnesieButton();
        addToCart.clickOnTShirtButton();

        //Assert if the item added to cart successfully
        Assert.assertEquals(addToCart.getItemsCount(),3);

    }

    //remove item after Add
    @Test
    public void RemoveItem() {
        loginHappyTest();

        //add item to cart
        AddToCart addToCart = new AddToCart();
        addToCart.clickOnBackPackButton();
        addToCart.clickOnOnesieButton();
        addToCart.clickOnTShirtButton();

        //Assert if the item added to cart successfully
        Assert.assertEquals(addToCart.getItemsCount(),3);
        //remove item from cart
        addToCart.clickOnRemoveButton();

        //Assert if the item removed from cart successfully
        Assert.assertEquals(addToCart.getItemsCount(),2);
    }


    @AfterTest
    public void tearDown() {
        //BrowserActions.quitBrowser();
    }
    @AfterMethod
    public void screenShotOnFailure(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            BrowserActions.takeScreenShot(result.getName());
        }
    }

    public void loginHappyTest() {
        login = new Login();
        login.navigateToLoginPage(LoginUrl);
        login.enterUserName(standardUserName);
        login.enterPassword(userPassword);
        login.clickOnLoginButton();
        Assert.assertEquals(login.getCurrentUrl(),inventoryUrl);

    }
}
