import com.swag.labs.utils.BaseWebDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public synchronized void setUpBeforeClass() {
        this.driver = BaseWebDriver.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public synchronized void tearDown() {
        BaseWebDriver.quitDriver();
    }
}
