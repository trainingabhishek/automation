import jdk.jshell.execution.Util;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.sql.SQLOutput;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Listeners(CustomListener.class)
public class SecondTest extends Utils{

    @Test
    public void progressBarValidation() throws Exception {
        //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebDriverWait wait=new WebDriverWait(driver,30);
        WebElement from=driver.findElement(By.xpath("//b[text()='DRAG ME TO MY TARGET!']"));
        WebElement to=driver.findElement(By.xpath("//b[text()='DROP HERE!']"));
        Utils.dragDrop(from,to);
    }

    @Test
    //@Parameters({"address"})
    public void progressBarValidation2() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Assert.assertTrue(false);
        driver.findElement(By.xpath("(//label[text()='Address']//following::textarea)[1]")).sendKeys("address");
        WebElement we = driver.findElement(By.xpath("//a[text()='Interactions ']"));
        Utils.hoverOver(we);

    }
    @Test
    public void webTable(){
        WebElement table=driver.findElement(By.xpath("//tbody"));
        List<WebElement> row= table.findElements(By.tagName("tr"));
        int rowSize=row.size();
        System.out.println(rowSize);
        for(int i=0;i<rowSize;i++){
            List<WebElement> column=row.get(i).findElements(By.tagName("td"));
            int columnSize=column.size();
            for(int j=0;j<columnSize;j++){
                String cellValue=column.get(j).getText();
                System.out.print(cellValue +" ");
            }
            System.out.println("");
        }
    }

    @Test
    public void dynamicWebtable() throws InterruptedException {
        WebElement we=driver.findElement(By.xpath("//strong[text()='The Bahamas']"));
        Utils.scrollToWebelement(we);
        Thread.sleep(10000);
    }
}
