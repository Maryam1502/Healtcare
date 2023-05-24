/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package test.com;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author tkhes
 */
public class SpecialtyCareTest {

     private static WebDriver driver;
    
    private String baseUrl;

    public SpecialtyCareTest() {
    }

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
        System.setProperty("webdriver.chrome.driver", "c:\\data\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
    @Test
    public void testSpecialtyCare() throws Exception {
        driver.get("https://healthcare.ascension.org/");
        driver.findElement(By.xpath("//header/div[@id='header']/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/ul[1]/li[1]/div[1]/div[1]/div[1]/div[1]/div[1]")).click();
        String expectedtitle = "Specialty Care | Ascension";
        String actualtitle = driver.getTitle();
        Assert.assertEquals(actualtitle, expectedtitle);

        //searchbox on specialty care page
        driver.findElement(By.name("get-care-now-filter-location")).click();
        driver.findElement(By.name("get-care-now-filter-location")).clear();
        driver.findElement(By.name("get-care-now-filter-location")).sendKeys("post-acute care");
        driver.findElement(By.linkText("Post-acute care")).click();

        driver.findElement(By.xpath("//div[@id='content']/div/div[3]/div/div/div/div/div/div/div/p[5]/a")).click();
        driver.close();
    }

}