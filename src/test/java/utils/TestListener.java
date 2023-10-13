package utils;

import lombok.extern.log4j.Log4j2;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

@Log4j2
public class TestListener implements ITestListener {

    public void onTestFailure(ITestResult iTestResult) {
        log.info(String.format("==================== FAILED TEST %s Duration: %ss ====================", iTestResult
                .getName(), getExecutionTime(iTestResult)));
        AllureUtils.takeScreenshot();
    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }
}
