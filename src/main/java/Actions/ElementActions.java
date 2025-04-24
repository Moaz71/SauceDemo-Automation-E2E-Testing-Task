package Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

//import static Actions.BrowserActions.driver;

public class ElementActions {
    WebDriver driver;
    public ElementActions(){
        this.driver=BrowserActions.drivers.get();
    }
    public enum Locators{
        Xpath,
        CSS,
        id
    }
    /********************************* returnBy method ****************************************************/
    public By returnElementLocatorBy (String selector, Locators l){
        switch (l){
            case id : return By.id(selector);
            case Xpath: return By.xpath(selector);
            case CSS: return By.cssSelector(selector);
            default: return null;
        }

    }

    /**************************** validateOnElementV2 method Version 2 ***********************************************/
    public enum ElementCondition{
        PRESENCE,
        VISIBILITY,
        CLICKABLE
    }
    public boolean validateOnElement(By element, ElementCondition conditionType){
        ExpectedCondition<WebElement> condition = null;
        switch (conditionType){
            case PRESENCE:  condition= ExpectedConditions.presenceOfElementLocated(element);
                break;
            case VISIBILITY:  condition= ExpectedConditions.visibilityOfElementLocated(element);
                break;
            case CLICKABLE:  condition= ExpectedConditions.elementToBeClickable(element);
                break;
            default: System.out.println("Unsupported condition type: " + conditionType);
                return false;
        }
        try {
            new WebDriverWait(driver, Duration.ofSeconds(1)).until(condition);
            return true;
        } catch (Exception e) {
            printTheExceptionErrorMsg(e,conditionType);
            return false;
        }
    }
    /**************************** navigate method ***********************************************/
     public void navigate(String url ,String expectedElement , Locators l)
     {
        driver.get(url);
        By element =returnElementLocatorBy(expectedElement,l);

        try {
            if(validateOnElement(element,ElementCondition.VISIBILITY));

        } catch (Exception e) {
            System.out.println("Failed to navigate to page ");

        }

     }

    /*********************************** click method - version 3 **********************************/
    public boolean click(String selector, Locators l1, String expectedSelector, Locators l2) {
        By element = returnElementLocatorBy(selector, l1);
        By expected = returnElementLocatorBy(expectedSelector, l2);

        return tryClick(element, expected)
                || tryHoverClick(element, expected)
                || tryEnter(element, expected)
                || tryDoubleClick(element, expected);
    }


    private boolean tryClick(By element, By expected) {
        if (validateOnElement(element, ElementCondition.CLICKABLE)) {
                driver.findElement(element).click();
                System.out.println("Tried: Click");
                if (validateOnElement(expected, ElementCondition.PRESENCE)) {
                    return true;
                } else {
                    System.out.println("Click failed");
                    return false;
                }
            }else{
                System.out.println("Click failed:Element isn't clickable ");
                return false;
            }
        }


    private boolean tryHoverClick(By element, By expected) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(element)).perform();
        driver.findElement(element).click();
        System.out.println("Tried: Hover + Click");

        if (validateOnElement(expected, ElementCondition.PRESENCE)){
            return true;
        }else{
            System.out.println("Hover click failed");
            return false;
        }

    }

    private boolean tryEnter(By element, By expected) {

        driver.findElement(element).sendKeys(Keys.ENTER);
        System.out.println("Tried: Enter");

        if (validateOnElement(expected, ElementCondition.PRESENCE)){
            return true;
        }else{
            System.out.println("Enter failed");
            return false;
        }

    }

    private boolean tryDoubleClick(By element, By expected) {

        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(element)).perform();
        System.out.println("Tried: DoubleClick");
        if (validateOnElement(expected, ElementCondition.PRESENCE)){
            return true;
        }else{
            System.out.println("Double Click failed");
            return false;
        }

    }

    /***************************** TypeText method ************************************/
    public boolean setText(String selector, Locators l, String text) {
        By element = returnElementLocatorBy(selector, l);
        if (validateOnElement(element, ElementCondition.CLICKABLE)) {
            driver.findElement(element).sendKeys(text);
            System.out.println("Tried: TypeText");
            return true;
        } else {
            System.out.println("TypeText failed");
            return false;
        }
    }

    /***************************** getText method ************************************/
    public String getText(String selector, Locators l) {
        By element = returnElementLocatorBy(selector, l);
        if (validateOnElement(element, ElementCondition.VISIBILITY)) {
            String text = driver.findElement(element).getText();
            System.out.println("Tried: GetText");
            return text;
        } else {
            System.out.println("GetText failed");
            return null;
        }
    }
    /**************************** getCurrentUrl method ********************************/
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    /************************** getNumberOfElements method ****************************/
     public int getNumberOfElements(String selector,Locators l){
        By element = returnElementLocatorBy(selector, l);

         return driver.findElements(element).size();
     }
     /***************************** selectSpecificElementList******************************/
     public void selectSpecificElementFromList(String selector,Locators l, int slectedElementIndex){
         By element = returnElementLocatorBy(selector,l);
         //implement wait util list is loaded
         if(validateOnElement(element,ElementCondition.VISIBILITY)){
             List<WebElement> elementList =driver.findElements(element);
             System.out.println("Total elements found: " + elementList.size());
             for (WebElement el : elementList) {
                 System.out.println("Element text: " + el.getText());
             }
             elementList.get(slectedElementIndex).click();
         }else{
             System.out.println("The list is fail to load");
         }

     }
    /*********************************** PrintTheExceptionErrorMsg ***************************/
    private void printTheExceptionErrorMsg(Exception e, ElementCondition conditionType)
    {
        String fullErrorMsg=e.getMessage();
        String []lines=fullErrorMsg.split("\n");
        if (lines.length >= 2) {
            System.out.println("Validation failed: " + conditionType + " - " + lines[0]);
            System.out.println(lines[1]);
        } else {
            System.out.println("Validation failed: " + conditionType + " - " + fullErrorMsg);
        }
    }
}
