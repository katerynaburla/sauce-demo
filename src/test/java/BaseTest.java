import com.swag.labs.utils.BaseWebDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUpBeforeClass() {
        this.driver = BaseWebDriver.getDriver();
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        BaseWebDriver.quitDriver();
    }
}
