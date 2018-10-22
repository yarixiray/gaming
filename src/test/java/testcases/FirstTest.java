package testcases;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class FirstTest extends TestBase {

    @Test()
    public void blackJackSonyaTest() {
        log.debug("First TEST !!!");
        driver.findElement(By.id("cookieAccept")).click();
        log.debug("cookieAccept !!!");
        driver.findElement(By.id("section-iframe__playbutton")).click();
        log.debug("iframe__playbutton !!!");
        driver.findElement(By.id("age-notice-iframe-yes")).click();
        log.debug("age-notice-iframe-yes !!!");
        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));

        WebDriverWait wait = new WebDriverWait(driver, 80);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("joinBtn")));

        List<WebElement> ob = new ArrayList();
        ob = driver.findElements(By.xpath("joinBtn"));
        ob.get(0).click();

        WebDriverWait waitMoney = new WebDriverWait(driver, 80);
        waitMoney.until(ExpectedConditions.elementToBeClickable(By.id("50")));
        driver.findElement(By.id("50")).click();

        WebDriverWait waitDEAL = new WebDriverWait(driver, 80);
        waitDEAL.until(ExpectedConditions.elementToBeClickable(By.xpath("dealBtn")));
        driver.findElement(By.xpath("dealBtn")).click();

        WebDriverWait waitMessage = new WebDriverWait(driver, 80);
        waitMessage.until(ExpectedConditions.elementToBeClickable(By.xpath("message")));

        Assert.assertTrue(driver.findElement(By.xpath("message_")).isDisplayed());

        WebDriverWait waitLeaveSeat = new WebDriverWait(driver, 80);
        waitLeaveSeat.until(ExpectedConditions.elementToBeClickable(By.xpath("leaveBtn")));
        driver.findElement(By.xpath("leaveBtn")).click();

        driver.switchTo().defaultContent();

        System.out.println("--------------------------333----------------------");

        log.debug("Login successfully executed");
        Reporter.log("Login successfully executed");
    }

}
