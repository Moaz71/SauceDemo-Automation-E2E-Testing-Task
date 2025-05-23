package Actions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class BrowserActions {
    public static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    /* a new version of the initializeWebDriver method which handle unexpected popups issues*/
    public static void initializeWebDriver(Browser browserName) {
        switch (browserName) {
            case Chrome -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");
                drivers.set(new ChromeDriver(chromeOptions));
            }
            case firefox -> {

                drivers.set(new FirefoxDriver());
            }
            case Edge -> {
                drivers.set(new EdgeDriver());
            }
            default -> System.out.println("Unsupported browser: " + browserName);
        }
    }
    public static void initializeWebDriver_withOutHandleUnexpectedPopup (Browser browserName){

       switch (browserName)
       {
           //switch arrow syntax doesn’t fall through like traditional switch-case blocks.
           // Each case is already isolated so no need to break.

           case Chrome -> drivers.set(new ChromeDriver());
           case firefox -> drivers.set(new FirefoxDriver());
           case Edge -> drivers.set(new EdgeDriver());
           default -> System.out.println("Unsupported browser: " + browserName);
       }

    }

    public static void closeDriver(){
        drivers.get().close();
    }

    public static void maximizeWindow(){
        drivers.get().manage().window().maximize();

    }
    public static void quitBrowser(){
        drivers.get().quit();

    }
    public static void closeBrowser(){
        drivers.get().close();
    }

    public enum Browser {
        Chrome,
        firefox,
        Edge
    }
    public static void takeScreenShot(String screenshotName){

        Path dest = Paths.get("./Screenshots",screenshotName+".png");
        try {
            Files.createDirectories(dest.getParent());
            FileOutputStream out = new FileOutputStream(dest.toString());
            out.write(((TakesScreenshot) drivers.get()).getScreenshotAs(OutputType.BYTES));
            out.close();
        } catch (IOException e) {
            System.out.println("Excpetion while taking screenshot"+ e.getMessage());
        }
    }

    /******************************** implicitWait**********************************/
    public static void impliciWait(long Sec)
    {
        drivers.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(Sec));
    }
}
