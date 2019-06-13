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
import qa.pom.pages.register.PolicyPage;
import qa.pom.pages.register.RegisterPage;
import qa.utility.Utils;




public class PolicyPageTest extends SeleniumDriver {

    private PolicyPage policyPage = new PolicyPage();
    private CommonSteps commonSteps = new CommonSteps();
    private RegisterPage registerPage = new RegisterPage();
    private ErrorMessagesRegPage validationErrorMessage = new ErrorMessagesRegPage();
    private static final Logger logger = LogManager.getLogger(SeleniumDriver.class);

    @BeforeClass
    public void setUp() {
        commonSteps.user_visit_pet_insurance_portal_url();
    }

    @Test(dataProvider = "readExcelPolicyNumber", dataProviderClass = Utils.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test case Description: Verify that user can Register with policy number")
    @Epic("Login and Register")
    public void verifyUserShouldRegisterWithPolicyNum(String policyNumber, String postcode, String date_of_Birth,
                                                      String password, String verifyPassword) {
        logger.info("User successfully fill in the policy details form");
        policyPage.getPetPolicyNumber().sendKeys(policyNumber);
        registerPage.getEnterPostcode().sendKeys(postcode);
        registerPage.getDaySelectDob().sendKeys(date_of_Birth);
        registerPage.getMonthSelectDob().sendKeys(date_of_Birth);
        registerPage.getYearSelectDob().sendKeys(date_of_Birth);
        registerPage.getEnterPassword().sendKeys(password);
        registerPage.getVerifyPassword().sendKeys(verifyPassword);
        policyPage.getNextButton().click();

        //policyPage.getPetPolicyNumberRadioButton().click();
    }

    @Test(description = "Verify that when user enter incorrect data error message should be displayed",
            dependsOnMethods = "verifyUserShouldRegisterWithPolicyNum")
    @Description("Test Case Description: Verify that when user enter incorrect data error message should be displayed ")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Login and Register")
    public void verifyThatErrorMessageIsDisplayed() {

        policyPage.getPetPolicyNumber()
                .sendKeys(getProperties()
                        .getProperty("INV_POLICY_NUM"));

        registerPage.getEnterPostcode()
                .sendKeys(getProperties()
                        .getProperty("INV_POSTCODE"));

        registerPage.getDaySelectDob()
                .sendKeys(getProperties()
                        .getProperty("DATE_OF_BIRTH"));

        registerPage.getMonthSelectDob()
                .sendKeys(getProperties()
                        .getProperty("DATE_OF_BIRTH"));

        registerPage.getYearSelectDob()
                .sendKeys(getProperties()
                        .getProperty("DATE_OF_BIRTH"));

        registerPage.getEnterPassword()
                .sendKeys(getProperties()
                        .getProperty("INV_PASSWORD"));

        registerPage.getVerifyPassword()
                .sendKeys(getProperties()
                        .getProperty("INV_PASSWORD"));
        policyPage.getNextButton().click();

        String getPcyNumberErrorMsg = policyPage.getPolicyNumberError().getText();
        if (getPcyNumberErrorMsg.equals("Policy number is required.")) {
            Assert.assertEquals(getPcyNumberErrorMsg, "Error Message is Displayed");
        } else logger.error("When user registers with an invalid Policy Number error message is not displayed");

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
        Assert.assertEquals("The password fields must match", passwordError);
    }


    @AfterClass
    public void tearDown() {
        SeleniumDriver.getInstance().onTestExecutionFinished();
    }
}
