import com.swag.labs.utils.BaseWebDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public class BaseTest {

    protected WebDriver driver;
    protected SoftAssert softAssert;

    @BeforeClass
    public void setUpBeforeClass() {
        this.driver = BaseWebDriver.getDriver();
        this.softAssert = new SoftAssert();
    }

    @AfterClass
    public void tearDown() {
        BaseWebDriver.quit();
    }
}
