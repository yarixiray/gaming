package testcases;

import base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class FirstTest extends TestBase {

    @Test
    public void pushButton() {
        log.debug("First TEST !!!");
        driver.findElement(By.xpath(OR.getProperty("playBtn"))).click();
        log.debug("Login successfully executed");
    }

}
