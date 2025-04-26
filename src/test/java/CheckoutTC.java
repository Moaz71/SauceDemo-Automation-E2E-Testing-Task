import Actions.BrowserActions;
import POM.AddToCart;
import POM.Cart;
import POM.Checkout;
import POM.Login;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CheckoutTC {
    Login login ;
    AddToCart addToCart;
    Cart cart;
    Checkout checkout;
    /*******************************Test Data*******************************/
    String LoginUrl = "https://www.saucedemo.com/v1";
    String inventoryUrl = "https://www.saucedemo.com/v1/inventory.html";
    String standardUserName = "standard_user";
    String userPassword = "secret_sauce";
    String firstName = "moaz";
    String lastName = "mosa";
    String postalCode = "123";


    @BeforeTest
    public void setUp() {
        BrowserActions.initializeWebDriver(BrowserActions.Browser.Chrome);
        BrowserActions.maximizeWindow();
    }
    @Test
    public void checkoutHappyTest() {

        //login
        loginHappyTest();
        Assert.assertEquals(login.getCurrentUrl(),inventoryUrl);
        //add item to cart
        addItemToCart();
        //Assert if the item added to cart successfully
        Assert.assertEquals(addToCart.getItemsCount(),3);

        //open cart
        openCart();
        //Assert if the item add to cart successfully
        Assert.assertEquals(cart.getItemsCount(),3);
        cart.clickOnCheckoutButton();

        //click on checkout button
        checkout = new Checkout();
        //enter user information
        checkout.enterFirstName(firstName);
        checkout.enterLastName(lastName);
        checkout.enterPostalCode(postalCode);

        //click on continue button
        checkout.clickOnContinueButton();

        //Assert if redirected to overview page successfully
        Assert.assertEquals(checkout.getoverviewHeader(), "Checkout: Overview");
        //click on finish button
        checkout.clickOnFinishButton();

        //Assert if redirected to complete page successfully
        Assert.assertEquals(checkout.getFinishPageMsg(), "THANK YOU FOR YOUR ORDER");




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
        openCart();
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
        login.clickOnLoginButton(false);

    }
    public void addItemToCart() {
        addToCart = new AddToCart();
        addToCart.clickOnBackPackButton();
        addToCart.clickOnOnesieButton();
        addToCart.clickOnTShirtButton();

    }

    public void openCart() {
        addToCart.ckickOnCartButton();
    }
}
