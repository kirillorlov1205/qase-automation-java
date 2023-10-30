package utils;

import lombok.extern.log4j.Log4j2;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

@Log4j2
public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info(String.format("==================== TEST STARTED %s Duration: %ss ====================", iTestResult
                .getName(), getExecutionTime(iTestResult)));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info(String.format("==================== TEST FAILED %s Duration: %ss ====================", iTestResult
                .getName(), getExecutionTime(iTestResult)));
        AllureUtils.takeScreenshot();
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info(String.format("==================== TEST SUCCESS %s Duration: %ss ====================", iTestResult
                .getName(), getExecutionTime(iTestResult)));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info(String.format("==================== TEST SKIPPED %s Duration: %ss ====================", iTestResult
                .getName(), getExecutionTime(iTestResult)));
    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }
}
