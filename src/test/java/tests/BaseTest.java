package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;
import utils.InvokedListener;

import java.time.Duration;

@Listeners({InvokedListener.class})
public abstract class BaseTest {
    private final static String BASE_URL = "https://ibay.testrail.io/";
    protected final static String USERNAME = "ilyab.sumo@gmail.com";
    protected final static String WRONG_USERNAME = "ayqa245@mailinator.com";
    protected final static String PASSWORD = "Aa12345@";
    protected final static String WRONG_PASSWORD = "Ayqa2414!";
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected LoginPage loginPage;
    protected AllProjectsPage allProjectsPage;
    protected AddTestCasePage addTestCasePage;
    protected ProjectPage projectPage;
    protected AddedTestCasePage addedTestCasePage;
    protected TestCasesPage testCasesPage;
    protected AddTestRunPage addTestRunPage;
    protected AddMilestonePage addMilestonePage;
    protected TestCaseInfoPage testCaseInfoPage;
    protected MilestonesPage milestonesPage;
    protected AddProjectPage addProjectPage;

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
        testCasesPage = new TestCasesPage(driver);
        addTestRunPage = new AddTestRunPage(driver);
        addMilestonePage = new AddMilestonePage(driver);
        testCaseInfoPage = new TestCaseInfoPage(driver);
        milestonesPage = new MilestonesPage(driver);
        addProjectPage = new AddProjectPage(driver);

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @BeforeMethod(alwaysRun = true)
    public void navigate() {
        driver.get(BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void clearCookies() {
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript(String.format("window.localStorage.clear();"));
        ((JavascriptExecutor) driver).executeScript(String.format("window.sessionStorage.clear();"));
    }
}
