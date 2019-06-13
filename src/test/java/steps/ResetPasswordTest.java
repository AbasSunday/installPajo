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



public class ResetPasswordTest  extends SeleniumDriver {

        private LoginPage loginSteps;
        private HomePage homePage;
        private static final Logger logger = LogManager.getLogger(LoginTest.class);

        @BeforeClass
        public void setUp() {
            loginSteps = new LoginPage();
            CommonSteps commonSteps = new CommonSteps();
            SeleniumDriver.getInstance().printTestDetails();
            commonSteps.user_visit_pet_insurance_portal_url();
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Description("Test case Description: Verify That user can reset password")
        @Epic("Login and Register")
        public void verifyUserShouldResetPassword()  {

            loginSteps.getLoginTab().click();
            String regPageTitle = loginSteps.getTitle();
            Assert.assertEquals("HUC - Portal Registration Page", regPageTitle);
            logger.warn("Home Page is displayed  ===> "+ regPageTitle  );


            String loginPageMessages = loginSteps.getLoginTitle().getText();
            Assert.assertEquals("Welcome to our Pet Portal", loginPageMessages);

            loginSteps.getLoginEmail().sendKeys(getProperties().getProperty("EMAIL"));
            loginSteps.getLoginPassword().sendKeys(getProperties().getProperty("REGISTER_PASSWORD"));

            loginSteps.getForgottenEmail().click();
            String resetMessages = loginSteps.getResetMessages().getText();
            Assert.assertEquals("Reset your password", resetMessages);
            logger.info("Message is " + "===>" + resetMessages);
            loginSteps.getResetPassword().sendKeys(getProperties().getProperty("EMAIL"));
            loginSteps.getConfirmEmail().click();
        }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @Description("Test case Description: Verify that password less than 8 chars is not accepted.")
        @Epic("Login and Register")
        public void verifyThatPasswordLessThanLimit(){


        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Description("Test case Description: verify That Failed Login Attempts is Available in DB.")
        @Epic("Login and Register")
        public void verifyThatFailedLoginAttemptsAvailableInDB(){

            //TODO: Please verify the DB manually

        }
        @AfterClass
        public void tearDown() {
            SeleniumDriver.getInstance().onTestExecutionFinished();
        }
    }




