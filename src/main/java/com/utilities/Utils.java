package com.utilities;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static WebDriver driver;


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

    public static void readDataExcel() throws Exception {
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

    public static Object[][] getData(String sheetname) throws Exception {
        File file=new File("src/main/resources/data.xlsx");
        FileInputStream fis=new FileInputStream(file);
        XSSFWorkbook wb=new XSSFWorkbook(fis);
        XSSFSheet sheet=wb.getSheet(sheetname);

        //row Count
        int rowCount=sheet.getLastRowNum();
        //col Count
        Row r=sheet.getRow(0);
        int colCount=r.getLastCellNum();

        Object[][] data=new Object[rowCount][colCount];
        for(int i=0;i<rowCount;i++){
            for(int j=0;j<colCount;j++){
                data[i][j]=sheet.getRow(i+1).getCell(j).toString();
            }
        }
        return data;
    }
}
