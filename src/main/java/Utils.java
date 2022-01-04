import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Properties;

public class Utils {
    static WebDriver driver;

    @BeforeClass
    public static void browserSetUp() throws Exception {
        Properties prop=new Properties();
        InputStream input=new FileInputStream("src/main/resources/config.properties");
        prop.load(input);

        String browser=prop.getProperty("browser");

        if(browser.equals("chrome")) {

            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(prop.getProperty("URL"));
        }
        if(browser.equals("edge")){
            System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
            driver = new EdgeDriver();
            Thread.sleep(10000);
            driver.manage().window().maximize();
            driver.get(prop.getProperty("URL"));
            System.out.println(driver.getCurrentUrl());
            System.out.println(driver.getTitle());
        }

    }

    public static void clickLocator(String str){
        driver.findElement(By.xpath(str)).click();
    }

    public static void hoverOver(WebElement we){
        Actions ac=new Actions(driver);
        ac.moveToElement(we).build().perform(); //hoverOver

    }

    public static void dragDrop(WebElement from, WebElement to){
        Actions ac=new Actions(driver);
        ac.dragAndDrop(from,to).build().perform();
        //Action dragdrop=ac.clickAndHold(from).moveToElement(to).release(to).build();
    }


    //@AfterTest
    public static void captureScreen() throws IOException {
        File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("src/main/resources"+driver.getTitle()+".jpg"));
    }

    public static void scrollToWebelement(WebElement we){
        JavascriptExecutor je=(JavascriptExecutor)driver;
        //je.executeScript("arguments[0].scrollIntoView();",we);
        je.executeScript("window.scrollTo(0,200)");
    }
}
