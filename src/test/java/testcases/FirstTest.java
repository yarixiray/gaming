package testcases;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.TestRunner;
import org.testng.annotations.Test;

import java.util.List;

public class FirstTest extends TestBase {

    @Test
    public void pushButton() {
        log.debug("First TEST !!!");
//        driver.findElement(By.xpath(OR.getProperty("cookieAccept"))).click();
        Assert.assertTrue(isElementPresent(By.id("cookieAccept")), "No such element");
        driver.findElement(By.id("cookieAccept")).click();

        Assert.assertTrue(isElementPresent(By.id("section-iframe__playbutton")), "No such element");
        driver.findElement(By.id("section-iframe__playbutton")).click();

        Assert.assertTrue(isElementPresent(By.id("age-notice-iframe-yes")), "No such element");
        Assert.assertTrue(isElementPresent(By.id("age-notice-iframe-no")), "No such element");
        driver.findElement(By.id("age-notice-iframe-yes")).click();

        WebDriverWait wait = new WebDriverWait(driver, 70);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("joinPlace"))));
        Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("joinPlace"))), "No such element");
        List<WebElement> listEl = driver.findElements(By.xpath(OR.getProperty("joinPlace")));
        listEl.get(0).click();

        log.debug("Login successfully executed");
        Reporter.log("Login successfully executed");
    }

}
