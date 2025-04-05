package Actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserActions {
    public static WebDriver driver;
    public static void initializeWebDriver(Browser browserName){

       switch (browserName)
       {
           //switch arrow syntax doesn’t fall through like traditional switch-case blocks.
           // Each case is already isolated.
           case Chrome -> driver =new ChromeDriver();
           case firefox -> driver =new FirefoxDriver();
           case Edge -> driver = new EdgeDriver();
           default -> System.out.println("Unsupported browser: " + browserName);
       }

    }

    public static void maximizeWindow(){
        driver.manage().window().maximize();

    }
    public static void quitBrowser(){
        driver.quit();

    }
    public static void closeBrowser(){
        driver.close();
    }

    public enum Browser {
        Chrome,
        firefox,
        Edge
    }
}
