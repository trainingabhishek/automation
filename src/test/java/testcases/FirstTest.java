package testcases;

import com.utilities.Utils;
import jdk.jshell.execution.Util;
import locators.CreateAccountPage;
import locators.WindowPage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class FirstTest extends CreateAccountPage {
    CreateAccountPage pagefunc;


    @BeforeClass
    public void setup() throws Exception {
        browserSetUp();
        pagefunc=new CreateAccountPage();
    }

    @DataProvider
    public Object[][] getDataForTest() throws Exception {
        Object data[][]=Utils.getData("DataSheet");
        return data;
    }


    @Test(dataProvider = "getDataForTest",groups={"smoke","sanity"})
    public void createAnAccount(String firstName,String lastName,String password) throws Exception {

        WebElement signIn= driver.findElement(By.xpath("//a[contains(.,'Sign in')]"));
        signIn.click();
        Utils.driver.findElement(By.id("email_create")).sendKeys("abc8225@abc.com");
        //driver.findElement(By.linkText("Forgot your password?")).click();
        Utils.driver.findElement(By.id("SubmitCreate")).click();
        Thread.sleep(7000);

        pagefunc.fillForm(firstName,lastName,password);


    }

    @Test
    public void readData() throws Exception {
        readDataExcel();
    }
}
