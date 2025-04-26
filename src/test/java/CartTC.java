import Actions.BrowserActions;
import POM.AddToCart;
import POM.Cart;
import POM.Login;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CartTC {

    Login login ;
    AddToCart addToCart;
    Cart cart;
    /*******************************Test Data*******************************/
    String LoginUrl = "https://www.saucedemo.com/v1";
    String inventoryUrl = "https://www.saucedemo.com/v1/inventory.html";
    String standardUserName = "standard_user";
    String userPassword = "secret_sauce";


    @BeforeTest
    public void setUp() {
        BrowserActions.initializeWebDriver(BrowserActions.Browser.Chrome);
        BrowserActions.maximizeWindow();
    }
    @Test
    public void countCartItems() {

        //login
        loginHappyTest();
        Assert.assertEquals(login.getCurrentUrl(),inventoryUrl);
        //add item to cart
        addItemToCart();
        //Assert if the item added to cart successfully
        Assert.assertEquals(addToCart.getItemsCount(),3);

        //open cart
        cart = new Cart();
        //Assert if the item add to cart successfully
        Assert.assertEquals(cart.getItemsCount(),3);

    }

    //remove item after Add
    @Test
    public void RemoveItem() {
        //login
        loginHappyTest();
        Assert.assertEquals(login.getCurrentUrl(),inventoryUrl);
        //add item to cart
        addItemToCart();
        //Assert if the item added to cart successfully
        Assert.assertEquals(addToCart.getItemsCount(),3);

        //open cart
        cart = new Cart();
        //Assert if the item add to cart successfully
        Assert.assertEquals(cart.getItemsCount(),3);

        //Remove item from cart
        cart.clickOnRemoveButton();
        //Assert if the item removed from cart successfully
        Assert.assertEquals(cart.getItemsCount(),2);
    }


    @AfterTest
    public void tearDown() {
        //BrowserActions.quitBrowser();
    }

    public void loginHappyTest() {
        login = new Login();
        login.navigateToLoginPage(LoginUrl);
        login.enterUserName(standardUserName);
        login.enterPassword(userPassword);
        login.clickOnLoginButton(true);

    }
    public void addItemToCart() {
        addToCart = new AddToCart();
        addToCart.clickOnBackPackButton();
        addToCart.clickOnOnesieButton();
        addToCart.clickOnTShirtButton();
        addToCart.ckickOnCartButton();

    }
}
