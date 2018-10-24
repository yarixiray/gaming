package testcases;

import base.TestBase;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class SitPlayerAndPlayTest extends TestBase {

    @Test()
    public void blackJackSonyaTest() {
        log.debug("blackJackSonyaTest is started");
        clickOn("cookieAcceptBtn_id");
        clickOn("playBtn_id");
        clickOn("ageNoticeYesBtn_id");
        switchToFrame("iFrame_xpath");
        takeFreePlace("joinAndTakeFreePlaceBtn_xpath");
        clickOn("betAmount_id");
        clickOn("dealBtn_xpath");
        verifyNotificationMessage("messageNotification_xpath",
                "happyMessage", "sadMessage");
        clickOn("leaveSeatBtn_xpath");
        log.debug("blackJackSonyaTest successfully executed");
        Reporter.log("Login successfully executed");
    }
}
