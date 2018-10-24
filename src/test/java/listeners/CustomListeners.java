package listeners;

import base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import utilities.TestUtil;

import java.io.IOException;

public class CustomListeners extends TestBase implements ITestListener {

    public void onTestStart(ITestResult iTestResult) {
    }

    public void onTestSuccess(ITestResult arg0) {
        test.log(LogStatus.PASS, arg0.getName().toUpperCase() + "PASS");
        rep.endTest(test);
        rep.flush();  //generate report
    }

    public void onTestFailure(ITestResult arg0) {

        System.setProperty("org.uncommons.reportng.escape-output", "false"); // convert String into html
        Reporter.log("Capturing screenshot");
        try {
            TestUtil.captureScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        test.log(LogStatus.FAIL, arg0.getName().toUpperCase() + "Failed with exception : " + arg0.getThrowable());
        test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));


        Reporter.log("Click to see Screenshot");
        Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + ">Screenshot</a>");
        Reporter.log("<br>");
        Reporter.log("<br>");
        Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName + " height=200 width=200></img></a>");
        rep.endTest(test);
        rep.flush();  //generate report
    }

    public void onTestSkipped(ITestResult iTestResult) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    public void onStart(ITestContext iTestContext) {
    }

    public void onFinish(ITestContext iTestContext) {
    }

}
