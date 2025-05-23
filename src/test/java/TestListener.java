import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

@Slf4j
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        log.error("Test started: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.error("Test passed: {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test failed: {}", result.getName());
        WebDriver driver = ((BaseTest) result.getInstance()).driver;

        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.attachment(result.getName(), new ByteArrayInputStream(screenshotBytes));
    }
}