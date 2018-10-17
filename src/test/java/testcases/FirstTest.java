package testcases;

import base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest extends TestBase {

    @Test
    public void pushButton() {
        log.debug("First TEST !!!");
        driver.findElement(By.xpath(OR.getProperty("playBtn"))).click();
        Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("playBtn"))),"No such button");
        log.debug("Login successfully executed");
    }

}
