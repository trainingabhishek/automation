package testcases;

import com.utilities.CustomListener;
import com.utilities.Utils;
import locators.WindowPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Listeners(CustomListener.class)
public class SecondTest extends WindowPage {
    WindowPage pagefunc;

    @BeforeClass
    public void setup() throws Exception {
        browserSetUp();
        pagefunc=new WindowPage();
    }

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

    @Test
    public void windows() throws InterruptedException {
        //driver.findElement(By.xpath("//button[text()='    click   ']")).click();
        pagefunc.clickOpenWindow();
        //table.click();
        Thread.sleep(2000);
        String mainWindow=driver.getWindowHandle();
        System.out.println(driver.getTitle());
        Set<String> allWindows=driver.getWindowHandles();

        Iterator<String> iterator=allWindows.iterator();
        while (iterator.hasNext()){
            String childWin= iterator.next();
            if(!mainWindow.equalsIgnoreCase(childWin)){
                driver.switchTo().window(childWin);
                System.out.println(driver.getTitle());
            }
        }
    }
}
