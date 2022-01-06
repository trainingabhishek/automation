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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class FirstTest extends CreateAccountPage {
    CreateAccountPage pagefunc;


    //@BeforeClass
    public void setup() throws Exception {
        browserSetUp();
        pagefunc=new CreateAccountPage();
    }


    @Test
    public void createAnAccount() throws Exception {

        WebElement signIn= Utils.driver.findElement(By.xpath("//a[contains(.,'Sign in')]"));
        signIn.click();
        Utils.driver.findElement(By.id("email_create")).sendKeys("abc8225@abc.com");
        //driver.findElement(By.linkText("Forgot your password?")).click();
        Utils.driver.findElement(By.id("SubmitCreate")).click();
        Thread.sleep(7000);

        pagefunc.fillForm("John","Melroy","123456abc");


    }

    @Test
    public void readData() throws Exception {
        File file=new File("src/main/resources/data.xlsx");
        FileInputStream fis=new FileInputStream(file);
        XSSFWorkbook wb=new XSSFWorkbook(fis);
        XSSFSheet sheet=wb.getSheetAt(0);

        //Read data from row and column
        String value=sheet.getRow(0).getCell(0).getStringCellValue();
        System.out.println(value);

        //row Count
        int rowCount=sheet.getLastRowNum();
        //col Count
        Row r=sheet.getRow(0);
        int colCount=r.getLastCellNum();

        for(int i=0;i<=rowCount;i++){
            System.out.println("The value at index is : "+sheet.getRow(i).getCell(0).getStringCellValue());
        }



        wb.close();
    }
}
