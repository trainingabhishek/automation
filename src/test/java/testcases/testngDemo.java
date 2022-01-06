package testcases;

import org.testng.annotations.*;

public class testngDemo {

    @BeforeSuite
    public void setUp(){
        System.out.println("set up completed ");
    }
    @BeforeTest
    public void launchBrowser(){
        System.out.println("Browser launched");
    }
    @BeforeClass
    public void login(){
        System.out.println("Login successful");
    }
    @BeforeMethod
    public void goToHomePage(){
        System.out.println("Home Page");
    }
    @Test
    public void takeFormShot(){
        System.out.println("Screenshot taken");
    }

}
