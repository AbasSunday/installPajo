package qa.pom.pages.register;


import qa.pom.base.BaseElement;
import qa.pom.base.BasePage;
import qa.utility.enums.LocatorType;

public class RegisterPage extends BasePage {


        private BaseElement getQuoteButton = new BaseElement(LocatorType.XPATH, "//*[text()='Get A Quote']", 10);

        private BaseElement enterEmailRadioButton = new BaseElement(LocatorType.XPATH, "//*[@id='registerForm']/div[1]/fieldset/div[1]/label", 15);

        private BaseElement enterEmail = new BaseElement(LocatorType.ID, "emailAddress", 10);

        private BaseElement enterPostcode = new BaseElement(LocatorType.ID, "postCode", 10);

        //private BaseElement enterDate0fBirth = new BaseElement(LocatorType.ID, "daySelectDob", 10);

        private BaseElement enterPassword = new BaseElement(LocatorType.ID, "createPassword", 10);

        private BaseElement verifyPassword = new BaseElement(LocatorType.ID, "verifyPassword", 10);

        private BaseElement clickNextButton = new BaseElement(LocatorType.ID, "registerSubmit", 10);

        private BaseElement daySelectDob = new BaseElement(LocatorType.ID, "daySelectDob", 10);

        private BaseElement monthSelectDob = new BaseElement(LocatorType.ID, "monthSelectDob", 10);

        private BaseElement yearSelectDob = new BaseElement(LocatorType.ID, "yearSelectDob", 10);

        private BaseElement enterPolicyNumber = new BaseElement(LocatorType.ID, "registrationPolicyNumber", 10);

        private BaseElement submitButton = new BaseElement("submitPolicyNumber");

        private PasswordStrengthIndicator passwordStrengthIndicator = new PasswordStrengthIndicator("passwordStrengthIndicator");


        public BaseElement getQuoteButton() {
            return getQuoteButton.get();
        }

        public BaseElement getGetQuoteButton() {
            return getQuoteButton.get();
        }

        public BaseElement getEnterPostcode() {
            return enterPostcode.get();
        }


        public BaseElement getEnterPassword() {
            return enterPassword.get();
        }

        public BaseElement getVerifyPassword() {
            return verifyPassword.get();
        }

        /**
         * @return
         */
        public BaseElement getClickNextButton() {
            return clickNextButton.get();
        }

        public BaseElement getEnterEmailRadioButton() {
            return enterEmailRadioButton.get();
        }

        public BaseElement getEnterPolicyNumber() {

            return enterPolicyNumber.get();
        }

        public BaseElement getEnterEmail() {
            return enterEmail.get();
        }

        public BaseElement getDaySelectDob() {
            return daySelectDob.get();
        }

        public BaseElement getMonthSelectDob() {
            return monthSelectDob.get();
        }

        public BaseElement getYearSelectDob() {
            return yearSelectDob.get();
        }

        public BaseElement getSubmitButton() {
            return submitButton.get();
        }

        public PasswordStrengthIndicator getPasswordStrengthIndicator() {
            return passwordStrengthIndicator.get();
        }



//    public boolean isValidationErrorDisplayed(){
//        utils.waitForElements(usernamevalidation, KnowsTestContext.timeout, KnowsTestContext.driver);
//        return usernamevalidation.isDisplayed();
//    }
//
//    //method for fill password to input field
//    public void inputPassword(String password) {
//        utils.waitForElements(passwordInputField, KnowsTestContext.timeout, KnowsTestContext.driver);
//        phone.sendKeys(phone);
//    }
//
//    //method for fill phone to input field
//    public void inputPhone(String phone) {
//        utils.waitForElements(phone, KnowsTestContext.timeout, KnowsTestContext.driver);
//        phone.sendKeys(phone);
//    }


}
