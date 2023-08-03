package tests;

import api_tests.BaseApiTest;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;
import utils.InvokedListener;
import utils.PropertyReader;

import java.time.Duration;

@Log4j2
@Listeners({InvokedListener.class})
public abstract class BaseTest extends BaseApiTest {
    protected static final String BASE_URL = PropertyReader.getProperty("base_url");
    protected static final String USERNAME = PropertyReader.getProperty("username");
    protected static final String PASSWORD = PropertyReader.getProperty("password");
    protected final static String WRONG_USERNAME = "ayqa245@mailinator.com";
    protected final static String WRONG_PASSWORD = "Ayqa2414!";
    protected final static String NAME = "TestProject";
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected LoginPage loginPage;
    protected AllProjectsPage allProjectsPage;
    protected AddTestCasePage addTestCasePage;
    protected ProjectPage projectPage;
    protected AddedTestCasePage addedTestCasePage;
    protected TestCaseInfoPage testCaseInfoPage;
    protected DashboardPage dashboardPage;
    protected CreateProjectPage createProjectPage;
    protected AdministrationPage administrationPage;
    protected TestCasesTab testCasesTab;
    protected MilestonesTab milestonesTab;
    static Faker faker = new Faker();

    @Parameters({"browserName"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browserName, ITestContext context) throws Exception {
        if (browserName.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equals("safari")) {
            driver = new SafariDriver();
        } else {
            throw new Exception("Unsupported browser");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        context.setAttribute("driver", driver);
        loginPage = new LoginPage(driver);
        allProjectsPage = new AllProjectsPage(driver);
        projectPage = new ProjectPage(driver);
        addTestCasePage = new AddTestCasePage(driver);
        addedTestCasePage = new AddedTestCasePage(driver);
        testCaseInfoPage = new TestCaseInfoPage(driver);
        dashboardPage = new DashboardPage(driver);
        createProjectPage = new CreateProjectPage(driver);
        administrationPage = new AdministrationPage(driver);
        testCasesTab = new TestCasesTab(driver);
        milestonesTab = new MilestonesTab(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @BeforeMethod(alwaysRun = true)
    public void navigate() {
        driver.get(BASE_URL);
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            driver.switchTo().defaultContent();
        } catch (NoAlertPresentException e) {
        }
    }

    @AfterMethod(alwaysRun = true)
    public void clearCookies() {
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript(String.format("window.localStorage.clear();"));
        ((JavascriptExecutor) driver).executeScript(String.format("window.sessionStorage.clear();"));
    }
}
