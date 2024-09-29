package Entrata;

import io.github.bonigarcia.wdm.WebDriverManager;


import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EntrataTests {



        private WebDriver driver;
        private WebDriverWait wait;

        @BeforeMethod
        public void setUp() {
            // Set the path for the ChromeDriver
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get("https://www.entrata.com/");
        }

        @Test
        public void testHomePageTitle() {
            // Navigate to the Entrata homepage


            // Validate the title of the home page
            String title = driver.getTitle();

            Assert.assertTrue(title.contains("Entrata"),"Title should contain 'Entrata'");
        }

    @Test
    public void fillingWatchDemoForm() {
        // Navigate to the Entrata homepage

        WebElement ScheduleDemo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Watch Demo']")));
        ScheduleDemo.click();
        driver.findElement(By.id("FirstName")).sendKeys("Manvi");
        driver.findElement(By.id("LastName")).sendKeys("Nigam");
        driver.findElement(By.id("Email")).sendKeys("manvinigam31@gmail.com");
        driver.findElement(By.id("Company")).sendKeys("IBM");
        driver.findElement(By.id("Phone")).sendKeys("6266496240");
        driver.findElement(By.id("Phone")).sendKeys("6266496240");
        driver.findElement(By.id("Title")).sendKeys("Automation Tester");

        Select unit_count = new Select(driver.findElement(By.id("Unit_Count__c")));
        Select I_am = new Select(driver.findElement(By.id("demoRequest")));

        unit_count.selectByVisibleText("1 - 10");
        I_am.selectByVisibleText("a Resident");

      WebElement watchdemo = driver.findElement(By.xpath("//*[text()='WATCH DEMO']"));
        Assert.assertTrue(watchdemo.isEnabled() , "Watch Demo button is disabled");
    }


    @Test
        public void navigateToPropertyManagement() {




        WebElement hoverElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Products']")));

        // Create an Actions object
        Actions actions = new Actions(driver);

        // Hover over the element and click on the required element


        WebElement clickElement = driver.findElement(By.xpath("//*[text()='ResidentPay']"));
        actions.moveToElement(hoverElement).perform();

        clickElement.click();




        // Optional: Verify something after clicking
        Assert.assertTrue(clickElement.isDisplayed(),"Resident Pay page is not displayed");

           }



        @Test
        public void validateSummitPage() {

            driver.findElement(By.linkText("Watch Demo")).click();
            Set<String> winIds = driver.getWindowHandles();

            String originalWindow = driver.getWindowHandle();



                for (String windowHandle : winIds) {
                    if (!windowHandle.equals(originalWindow)) {
                        driver.switchTo().window(windowHandle);
                        break; // Exit the loop once switched to the new tab
                    }
                }

            WebElement BreakoutSessions = driver.findElement(By.xpath("//*[text()='BREAKOUT']"));
            WebElement MainStageSessions = driver.findElement(By.xpath("//*[text()='Main stage']"));
            WebElement Days = driver.findElement(By.xpath("//*[text()='Days']"));

            Assert.assertTrue(BreakoutSessions.isDisplayed() , "Break out session counter is displayed");
            Assert.assertTrue(MainStageSessions.isDisplayed() , "MainStage session counter is displayed");
            Assert.assertTrue(Days.isDisplayed() , "Days counter is displayed");
            }



        @AfterMethod
        public void tearDown() {
            // Close the browser
            if (driver != null) {
                driver.quit();
            }
        }
    }

