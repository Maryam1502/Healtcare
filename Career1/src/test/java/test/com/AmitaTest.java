/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.com;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author kohli
 */
public class AmitaTest {

  
    private static WebDriver driver;

    public AmitaTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @BeforeClass
    public static void setUpClass() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1400,800");
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        driver.quit();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        
    }

    @Test (priority =2)
    public void testBillPay() throws InterruptedException {

        driver.get("https://healthcare.ascension.org/");
        driver.findElement(By.linkText("Bill Pay")).click();

//        System.out.println("Before switchint title is =" + driver.getTitle());
        driver.findElement(By.linkText("PAY A HOSPITAL BILL - VISITPAY PORTAL")).click();

        Set<String> handler = driver.getWindowHandles();
        Iterator<String> it = handler.iterator();

        String parentWindowId = it.next();
//        System.out.println("parent window id"+parentWindowId);

        String childWindowId = it.next();
//        System.out.println("child window id"+childWindowId);

        driver.switchTo().window(childWindowId);
        System.out.println("chid window Title" + driver.getTitle());

        driver.findElement(By.xpath("//a[contains(text(),'Make a one-time payment')]")).click();

        driver.findElement(By.xpath("//input[@id='AuthenticationId0']")).click();
        driver.findElement(By.id("AuthenticationId0")).sendKeys("test");
        driver.findElement(By.id("LastName")).click();
        driver.findElement(By.id("LastName")).sendKeys("test1234");
        Select mon = new Select(driver.findElement(By.id("DateOfBirthMonth")));
        mon.selectByVisibleText("Jan");
        driver.findElement(By.id("DateOfBirthDay")).sendKeys("01");
        driver.findElement(By.id("DateOfBirthYear")).sendKeys("1986");
        driver.findElement(By.xpath("//label[@id='rbNotPatientLabel']")).click();
        Select month = new Select(driver.findElement(By.id("PatientDateOfBirthMonth")));
        month.selectByVisibleText("Jul");
        driver.findElement(By.id("PatientDateOfBirthDay")).sendKeys("04");
        driver.findElement(By.id("PatientDateOfBirthYear")).sendKeys("2000");

        driver.findElement(By.xpath("//label[@id='GuestPayAgreeTermsOfUseLabel']")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("guestAuthenticationSubmitButton")).click();

    }

    @Test(priority = 1)
    public void testFindLocation() throws InterruptedException {
//        driver.get("https://healthcare.ascension.org/");
//        driver.findElement(By.xpath("//a[@title='Find a Location']")).click();
//        driver.findElement(By.xpath("//input[@class='location-type-ahead-input is-valid']")).click();
//        driver.findElement(By.xpath("//input[@class='location-type-ahead-input is-valid']")).clear();
//
//        driver.findElement(By.xpath("//input[@class='location-type-ahead-input is-valid']")).sendKeys("Carol Stream, IL 60188");
//
////Thread.sleep(2000);
////driver.findElement(By.xpath("//*[@id=\"locationsSearchContainer\"]/div/div[2]/div[1]/div/div/div/div/div[3]/div/input")).click();
//
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,1000)");
////        Thread.sleep(2000);
////WebElement SubmitButton=(new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Ascension Medical Group Illinois - Heart & Vascula')]")));
//        driver.findElement(By.xpath("//a[contains(text(),'Ascension Medical Group Illinois - Heart & Vascula')]")).click();
//        String title = driver.getTitle();
//        System.out.println(title);
System.out.println("");
        System.out.println("🧪 Find A Location is Being Tested 🧪");
        System.out.println("");

        driver.get("https://healthcare.ascension.org/locations");
        driver.findElement(By.xpath("//input[@class='location-type-ahead-input is-valid' and @placeholder='City and State, or ZIP Code']")).click();
        driver.findElement(By.xpath("//input[@class='location-type-ahead-input is-valid' and @placeholder='City and State, or ZIP Code']")).clear();
        driver.findElement(By.xpath("//input[@class='location-type-ahead-input is-valid' and @placeholder='City and State, or ZIP Code']")).sendKeys(Keys.chord(Keys.COMMAND, "a"));
        driver.findElement(By.xpath("//input[@class='location-type-ahead-input is-valid' and @placeholder='City and State, or ZIP Code']")).sendKeys(Keys.DELETE);
        driver.findElement(By.xpath("//input[@class='location-type-ahead-input is-valid' and @placeholder='City and State, or ZIP Code']")).sendKeys("Carol Stream, IL");
        driver.findElement(By.xpath("//input[@class='location-type-ahead-input is-valid' and @placeholder='City and State, or ZIP Code']")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//input[@type='submit' and @class='fal-search-button']")).click();

        driver.findElement(By.linkText("Ascension Medical Group Illinois - Heart & Vascular Care Bartlett")).click();

        System.out.println("");
        System.out.println("✅ Find a Location is Tested! ✅");
        System.out.println("");

    }
}
