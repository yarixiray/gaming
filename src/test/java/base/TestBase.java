package base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utilities.ExtentManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    public static Logger log = Logger.getLogger("devpinoyLogger");
    public static ExtentTest test;
    public static ExtentReports rep = ExtentManager.getInstance();
    public static List<WebElement> freePlaces = new ArrayList();

    @BeforeSuite
    public void setUp() {
        if (driver == null) {
            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                config.load(fis);
                log.debug("Config file loaded !!!");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                OR.load(fis);
                log.debug("OR file loaded !!!");
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
                driver = new ChromeDriver();
                log.debug("Chrome Launched !!!");
            } else if (config.getProperty("browser").equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
                driver = new FirefoxDriver();
                log.debug("Firefox Launched !!!");
            }

            driver.get(config.getProperty("testsiteurl"));
            log.debug("Navigated to : " + config.getProperty("testsiteurl"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
            driver.manage().deleteAllCookies();
        }

    }

    public void clickOn(String value) {
        WebDriverWait wait = new WebDriverWait(driver, 80);
        if (value.endsWith("_xpath")) {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(value))));
            driver.findElement(By.xpath(OR.getProperty(value))).click();
        } else if (value.endsWith("_id")) {
            wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty(value))));
            driver.findElement(By.id(OR.getProperty(value))).click();
        }
        log.debug("The button " + value + " is found and clicked");
    }

    public void switchToFrame(String attributeValue) {
        driver.switchTo().frame(driver.findElement(By.xpath(OR.getProperty(attributeValue))));
        log.debug("Switch To Frame: " + attributeValue);
    }

    public void takeFreePlace(String attrValue) {
        WebDriverWait wait = new WebDriverWait(driver, 80);
        wait.until(ExpectedConditions.elementToBeSelected(By.xpath(OR.getProperty(attrValue))));
        freePlaces = driver.findElements(By.xpath(OR.getProperty(attrValue)));
        freePlaces.get(0).click();
        log.debug("Sit on free place");
    }

    public void verifyNotificationMessage(String attrValue, String happyMessage, String sadMessage) {
        WebDriverWait waitMessage = new WebDriverWait(driver, 80);
        waitMessage.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(attrValue))));

        if (driver.findElement(By.xpath(OR.getProperty(attrValue))).getText()
                .equalsIgnoreCase(OR.getProperty(happyMessage))) {
            Assert.assertEquals(driver.findElement(By.xpath(OR.getProperty(attrValue))).getText(), happyMessage);
            log.debug(happyMessage);
        } else if (driver.findElement(By.xpath(OR.getProperty(attrValue))).getText()
                .equalsIgnoreCase(OR.getProperty(sadMessage))) {
            Assert.assertEquals(driver.findElement(By.xpath(OR.getProperty(attrValue))).getText(), sadMessage);
            log.debug(sadMessage);
        } else {
            log.debug("Your result is " + driver.findElement(By.xpath(OR.getProperty(attrValue))).getText());
        }
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
        log.debug("Test execution completed !!!");
    }
}
