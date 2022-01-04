import jdk.jshell.execution.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FirstTest extends Utils {
    public static void main(String[] args) throws Exception {
        Utils.browserSetUp();
        WebElement signIn=driver.findElement(By.xpath("//a[contains(.,'Sign in')]"));
        signIn.click();
        driver.findElement(By.id("email_create")).sendKeys("abc8225@abc.com");
        //driver.findElement(By.linkText("Forgot your password?")).click();
        driver.findElement(By.id("SubmitCreate")).click();
        Thread.sleep(7000);
        Utils.clickLocator("//label[text()='Title']//following::div[contains(.,'Mr.')]");
        driver.findElement(By.id("customer_firstname")).sendKeys("Ankit");
        driver.findElement(By.id("customer_lastname")).sendKeys("customer");
        System.out.println(driver.findElement(By.id("email")).getText());
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("dsjdjgcfuyed");

        Select selectDays = new Select(driver.findElement(By.id("days")));
        selectDays.selectByValue("27");

        Select selectMon = new Select(driver.findElement(By.id("months")));
        //selectMon.selectByValue("12");

        Select selectYear = new Select(driver.findElement(By.id("years")));
        selectYear.selectByValue("2021");

        List<WebElement> we=selectMon.getOptions();
        for(int i=0;i<we.size();i++){
            System.out.println(we.get(i).getText());
        }
        //driver.quit();

    }
}
