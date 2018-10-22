package testcases;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.TestRunner;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class FirstTest extends TestBase {

    WebElement element;

    @Test()
    public void iframePlaybutton() {
        log.debug("First TEST !!!");
        Assert.assertTrue(isElementPresent(By.id("cookieAccept")), "No such element");
        driver.findElement(By.id("cookieAccept")).click();
        log.debug("cookieAccept !!!");

        Assert.assertTrue(isElementPresent(By.id("section-iframe__playbutton")), "No such element");
        driver.findElement(By.id("section-iframe__playbutton")).click();
        log.debug("iframe__playbutton !!!");

        Assert.assertTrue(isElementPresent(By.id("age-notice-iframe-yes")), "No such element");
        driver.findElement(By.id("age-notice-iframe-yes")).click();
        log.debug("age-notice-iframe-yes !!!");

        int number = findFrameNumber(By.tagName("iframe"));
        driver.switchTo().frame(number);
        driver.findElement(By.xpath("//div[@class='hover']")).click();

        //WebDriverWait wait = new WebDriverWait(driver, 70);
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'JOIN')]")));

        Actions actions = new Actions(driver);
        List<WebElement> ob = new ArrayList();


        ob = driver.findElements(By.xpath("//div[contains(text(),'JOIN')]"));
        if (ob.size() > 0) {
            ob.get(0).click();
        } else {
            System.out.println("-----------------------------------------------------");
            System.out.println("No free places !!! Please try again later...........");
            System.out.println("-----------------------------------------------------");
        }
        actions.moveToElement(ob.get(0)).click();

        WebDriverWait waitMoney = new WebDriverWait(driver, 50);
        waitMoney.until(ExpectedConditions.visibilityOfElementLocated(By.id("50")));
        driver.findElement(By.id("50")).click();
        System.out.println("------------------------------------------------");
        WebDriverWait waitDEAL = new WebDriverWait(driver, 50);
        waitDEAL.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'DEAL')]")));
        driver.findElement(By.xpath("//div[contains(text(),'DEAL')]")).click();

        System.out.println("---------------------000---------------------------");

        WebDriverWait waitStand = new WebDriverWait(driver, 50);
        waitStand.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'STAND')]")));
        driver.findElement(By.xpath("//div[contains(text(),'STAND')]")).click();
        System.out.println("--------------------------111----------------------");

        WebDriverWait waitLeaveSeat = new WebDriverWait(driver, 50);
        waitLeaveSeat.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'LEAVE SEAT')]")));
        boolean exist = driver.findElement(By.xpath("//div[contains(text(),'LEAVE SEAT')]")).isDisplayed();
        System.out.println(exist);
        driver.findElement(By.xpath("//div[contains(text(),'LEAVE SEAT')]")).click();


        System.out.println("--------------------------222----------------------");

        log.debug("Login successfully executed");
        Reporter.log("Login successfully executed");
    }

}
