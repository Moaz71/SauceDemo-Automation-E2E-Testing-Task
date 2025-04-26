import Actions.BrowserActions;
import POM.AddToCart;
import POM.Cart;
import POM.Checkout;
import POM.Login;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class E2ETest {


    /*******************************Test Data*******************************/
    String loginUrl = "https://www.saucedemo.com/v1";
    String inventoryUrl = "https://www.saucedemo.com/v1/inventory.html";
    String standardUserName = "standard_user";
    String userPassword = "secret_sauce";
    String[] productNames = {"Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)"};
    String firstName = "moaz";
    String lastName = "mosa";
    String postalCode = "123";
    String finishPageMsg = "THANK YOU FOR YOUR ORDER";
    String lockedOutUserName = "locked_out_user";
    String problemUserName = "problem_user";
    String performanceGlitchUserName = "performance_glitch_user";
    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(String browser) {
        BrowserActions.initializeWebDriver(BrowserActions.Browser.valueOf(browser));
        BrowserActions.maximizeWindow();
    }
    @AfterMethod
    public void tearDown() {
        BrowserActions.closeBrowser();
    }
     @Test
    public void standarUserE2ETC() {
        //login
         Login login = new Login();
         AddToCart addToCart = new AddToCart();
         Cart cart = new Cart();
         Checkout checkout = new Checkout();

         login.navigateToLoginPage(loginUrl).enterUserName(standardUserName).enterPassword(userPassword).clickOnLoginButton(true);
         Assert.assertEquals(login.getCurrentUrl(), inventoryUrl,"Login Failed");
         addToCart.addProductsToCart(productNames[0]).addProductsToCart(productNames[1]).addProductsToCart(productNames[2]).addProductsToCart(productNames[3]).addProductsToCart(productNames[4]);
         Assert.assertEquals(addToCart.getItemsCount(),5,"Failed to add items to cart");
         Assert.assertEquals(addToCart.getBtnText(productNames[0]),"REMOVE","Faild to add BackPack to cart");

         addToCart.removeProductsFromCart(productNames[3]).removeProductsFromCart(productNames[4]);
         Assert.assertEquals(addToCart.getItemsCount(),3,"Failed to remove items from cart");

         addToCart.ckickOnCartButton();
         Assert.assertEquals(cart.getItemsCount(),3,"Failed to add items to cart");
         cart.removeProductsFromCart(productNames[2]);
         Assert.assertEquals(cart.getItemsCount(),2,"Failed to remove items from cart");
         cart.clickOnCheckoutButton();

         checkout.enterFirstName(firstName).enterLastName(lastName).enterPostalCode(postalCode).clickOnContinueButton();
         double totalPrice = checkout.getProductPrice(productNames[0]) + checkout.getProductPrice(productNames[1]);
         Assert.assertEquals(checkout.getTotalPrice(),totalPrice,"Failed to calculate total price");
         checkout.clickOnFinishButton();
         Assert.assertEquals(checkout.getFinishPageMsg(),finishPageMsg,"Failed to finish checkout");
     }

     @Test
    public void lockedOutUserE2ETC() {

        Login login = new Login();


         login.navigateToLoginPage(loginUrl).enterUserName(lockedOutUserName).enterPassword(userPassword).clickOnLoginButton(false);
         Assert.assertEquals(login.getErrorMsg(), "Epic sadface: Sorry, this user has been locked out.","Login Failed");

     }

     @Test
    public void problemUserE2ETC() {
        SoftAssert softAssert = new SoftAssert();
         //login
         Login login = new Login();
         AddToCart addToCart = new AddToCart();
         Cart cart = new Cart();
         Checkout checkout = new Checkout();

         login.navigateToLoginPage(loginUrl).enterUserName(problemUserName).enterPassword(userPassword).clickOnLoginButton(true);
         Assert.assertEquals(login.getCurrentUrl(), inventoryUrl,"Login Failed");
         addToCart.addProductsToCart(productNames[0]).addProductsToCart(productNames[1]).addProductsToCart(productNames[2]).addProductsToCart(productNames[3]).addProductsToCart(productNames[4]);
         softAssert.assertEquals(addToCart.getItemsCount(),5,"Failed to add items to cart");
         softAssert.assertEquals(addToCart.getBtnText(productNames[2]),"REMOVE","Faild to add Bolt T-Shirt to cart");
         softAssert.assertEquals(addToCart.getBtnText(productNames[3]),"REMOVE","Faild to add Fleece Jacket to cart");

         addToCart.removeProductsFromCart(productNames[3]).removeProductsFromCart(productNames[4]);
         Assert.assertEquals(addToCart.getItemsCount(),3,"Failed to remove items from cart");
         softAssert.assertEquals(addToCart.getBtnText(productNames[4]),"ADD TO CART","Faild to remove Onesie from cart");
         softAssert.assertEquals(addToCart.getBtnText(productNames[3]),"ADD TO CART","Faild to remove Fleece Jacket form cart");
         addToCart.ckickOnCartButton();
         softAssert.assertEquals(cart.getItemsCount(),3,"Failed to add items to cart");
         cart.removeProductsFromCart(productNames[4]);
         softAssert.assertEquals(cart.getItemsCount(),2,"Failed to remove items from cart");
         cart.clickOnCheckoutButton();

         checkout.enterFirstName(firstName).enterLastName(lastName).enterPostalCode(postalCode);
         softAssert.assertEquals(checkout.getFirstNameTXT(),firstName,"Failed to enter first name");
         checkout.clickOnContinueButton();
         double totalPrice = checkout.getProductPrice(productNames[0]) + checkout.getProductPrice(productNames[1]);
         softAssert.assertEquals(checkout.getTotalPrice(),totalPrice,"Failed to calculate total price");
         checkout.clickOnFinishButton();
         softAssert.assertEquals(checkout.getFinishPageMsg(),finishPageMsg,"Failed to finish checkout");

         softAssert.assertAll();
    }

    @Test
    public void performanceGlitchUserE2ETC() {
        //login
        Login login = new Login();
        AddToCart addToCart = new AddToCart();
        Cart cart = new Cart();
        Checkout checkout = new Checkout();

        login.navigateToLoginPage(loginUrl).enterUserName(performanceGlitchUserName).enterPassword(userPassword).clickOnLoginButton(true);
        Assert.assertEquals(login.getCurrentUrl(), inventoryUrl,"Login Failed");
        addToCart.addProductsToCart(productNames[0]).addProductsToCart(productNames[1]).addProductsToCart(productNames[2]).addProductsToCart(productNames[3]).addProductsToCart(productNames[4]);
        Assert.assertEquals(addToCart.getItemsCount(),5,"Failed to add items to cart");
        Assert.assertEquals(addToCart.getBtnText(productNames[0]),"REMOVE","Faild to add BackPack to cart");

        addToCart.removeProductsFromCart(productNames[3]).removeProductsFromCart(productNames[4]);
        Assert.assertEquals(addToCart.getItemsCount(),3,"Failed to remove items from cart");

        addToCart.ckickOnCartButton();
        Assert.assertEquals(cart.getItemsCount(),3,"Failed to add items to cart");
        cart.removeProductsFromCart(productNames[2]);
        Assert.assertEquals(cart.getItemsCount(),2,"Failed to remove items from cart");
        cart.clickOnCheckoutButton();

        checkout.enterFirstName(firstName).enterLastName(lastName).enterPostalCode(postalCode).clickOnContinueButton();
        double totalPrice = checkout.getProductPrice(productNames[0]) + checkout.getProductPrice(productNames[1]);
        Assert.assertEquals(checkout.getTotalPrice(),totalPrice,"Failed to calculate total price");
        checkout.clickOnFinishButton();
        Assert.assertEquals(checkout.getFinishPageMsg(),finishPageMsg,"Failed to finish checkout");
    }
}


