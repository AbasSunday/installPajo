package steps;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import qa.base.SeleniumDriver;
import qa.pom.pages.HomePage;
import qa.pom.pages.LoginPage;


public class LoginTest extends SeleniumDriver {

    private LoginPage loginSteps;
    private CommonSteps commonSteps;
    private HomePage homePage;
    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    @BeforeClass
    public void setUp() {
        SeleniumDriver.getInstance().openMainPage();
        loginSteps = new LoginPage();
    }

    @Test(priority = 1, description = "Verify User can Login")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test case Description: Verify That user should be able to login")
    @Epic("Login and Register")
    public void verifyUserCanLogin() {
        String regPageTitle = loginSteps.getTitle();
        Assert.assertEquals("HUC - Portal Registration Page", regPageTitle);
        loginSteps.getLoginTab().click();
        String loginPageMessages = loginSteps.getLoginTitle().getText();
        Assert.assertEquals("Welcome to our Pet Portal", loginPageMessages);

        loginSteps.getLoginEmail().sendKeys(getProperties().getProperty("EMAIL_LOGIN"));
        loginSteps.getLoginPassword().sendKeys(getProperties().getProperty("PASSWORD_LOGIN"));
        loginSteps.getLoginSubmitButton().click();
    }

    @Test(priority = 1, description = "Verify User can Login")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test case Description: Verify login Welcome Page is displayed")
    @Epic("Login and Register")
    public void verifyThatHomePageIsDisplayed() {
        String welcomePage = homePage.getWelcomePage().getText();
        Assert.assertEquals(welcomePage, "Welcome to your pet portal", "Welcome Message is not displayed");
    }
    @AfterClass
    public void tearDown(){
       //SeleniumDriver.getInstance().onTestExecutionFinished();
    }

}
