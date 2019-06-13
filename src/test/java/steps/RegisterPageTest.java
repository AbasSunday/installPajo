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
import qa.pom.pages.register.ErrorMessagesRegPage;
import qa.pom.pages.register.RegisterPage;

import qa.utility.Utils;

import java.lang.reflect.Method;


public class RegisterPageTest extends SeleniumDriver {

    private RegisterPage registerPage = new RegisterPage();
    private CommonSteps commonSteps = new CommonSteps();
    private ErrorMessagesRegPage validationErrorMessage = new ErrorMessagesRegPage();
    private static final Logger logger = LogManager.getLogger(RegisterPageTest.class);


    @BeforeClass
    public void setUp() {
        commonSteps.user_visit_pet_insurance_portal_url();
    }

    @Test(priority = 1)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test case Description: Verify That user can Register with Email")
    @Epic("Login and Register")
    public void verifyEmailRadioButton() {
        //SeleniumDriver.getInstance().openMainPage();
        registerPage.getEnterEmailRadioButton().click();
    }

    @Test(priority = 2, dataProvider = "readExcelDataRegister", dataProviderClass = Utils.class)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test case Description: Verify That user can Register with Email")
    @Epic("Login and Register")
    public void verifyThatUserCanRegister(String emailAddress, String postcode, String dateOfBirth, String password,
                                          String verifyPassword, String policyNumber, Method method)
    {
        registerPage.getEnterEmailRadioButton().click();
        registerPage.getEnterEmail().sendKeys(emailAddress);
        registerPage.getEnterPostcode().sendKeys(postcode);
        registerPage.getDaySelectDob().sendKeys(dateOfBirth);
        registerPage.getMonthSelectDob().sendKeys(dateOfBirth);
        registerPage.getYearSelectDob().sendKeys(dateOfBirth);
        registerPage.getEnterPassword().sendKeys(password);
        //AssertionWrapper.assertPasswordStrength(password, registerPage.getPasswordStrengthIndicator());
        registerPage.getVerifyPassword().sendKeys(verifyPassword);
        registerPage.getClickNextButton().click();

    }
    @Test(priority = 3, dependsOnMethods = "verifyThatUserCanRegister" )
    @Severity(SeverityLevel.NORMAL)
    @Description("Test case Description: Verify That error messages is displayed when user enters invalid details")
    @Epic("Login and Register")
    public void verifyThatWrongDoBShows_A_validationMessage(){
        commonSteps.user_visit_pet_insurance_portal_url();
        registerPage.getEnterEmailRadioButton().click();
        registerPage.getEnterEmail().sendKeys(getProperties().getProperty("INV_EMAIL_ADDRESS"));

        registerPage.getEnterPostcode().sendKeys(getProperties().getProperty("INV_POSTCODE"));
        registerPage.getDaySelectDob().sendKeys(getProperties().getProperty("DATE_OF_BIRTH"));
        registerPage.getMonthSelectDob().sendKeys(getProperties().getProperty("DATE_OF_BIRTH"));
        registerPage.getYearSelectDob().sendKeys(getProperties().getProperty("DATE_OF_BIRTH"));
        registerPage.getEnterPassword().sendKeys(getProperties().getProperty("INV_PASSWORD"));
        registerPage.getClickNextButton().click();

        String emailValidation = validationErrorMessage.getEmailErrorMessage().getText();
        Assert.assertEquals("Please enter a valid email address." ,emailValidation );

        if (emailValidation.equalsIgnoreCase("Please enter a valid email address."))
            logger.info("Email Validation displayed");
        else logger.info("Email Validation is not displayed");

        //Postcode Validation
        String postcodeError = validationErrorMessage.getPostErrorMessage().getText();
        logger.info(postcodeError);
        Assert.assertEquals("The postcode must be between 5 and 7 characters excluding spaces", postcodeError);

        //Date of Birth Validation
        String DateOfBirthErrorMessage = validationErrorMessage.getDataOfBirthErrorMessage().getText();
        if (DateOfBirthErrorMessage.equalsIgnoreCase("Date of birth (DD/MM/YYYY) is required."))
            logger.info(DateOfBirthErrorMessage);

        Assert.assertEquals("Please only use characters 0 to 9.", DateOfBirthErrorMessage);
        validationErrorMessage.getDataOfBirthErrorMessage().isDisplayed();

        //Password Validation
        String passwordError = validationErrorMessage.getPasswordErrorMessage().getText();
        logger.info(passwordError);
        Assert.assertEquals("The password fields must match",passwordError);
    }
    //@Test
//    public void iShouldSeeError(String arg0) {
//            AssertionWrapper.assertElementExists(driver, By.xpath("//a[text()='The password fields must match']"));
//            AssertionWrapper.assertElementExists(driver, By.xpath("//p[text()='The password fields must match']"));
//        }

    @Test(dependsOnMethods = "verifyEmailRadioButton")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test case Description: Verify that email address if not matches PIPPA, then policy number is requested.")
    @Epic("Login and Register")
    public void verifyThatIfInvalidEmailIsEnteredPolicyNumberShouldBeRequested(){


    }
    @AfterClass
    public void tearDown() {

        //SeleniumDriver.getInstance().onTestExecutionFinished();
    }
}
