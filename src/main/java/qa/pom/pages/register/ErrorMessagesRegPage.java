package qa.pom.pages.register;

import qa.pom.base.BaseElement;
import qa.utility.enums.LocatorType;

public class ErrorMessagesRegPage {


    private BaseElement dataOfBirthErrorMessage = new BaseElement(LocatorType.XPATH, "//*[@id='dobInputs']/div[1]/div/p", 10);
    private BaseElement emailErrorMessage = new BaseElement(LocatorType.XPATH, "//*[@id='emailInputContainer']/div/p", 10);
    private BaseElement postErrorMessage = new BaseElement(LocatorType.XPATH, "//*[@id='registerForm']/div[2]/div[1]/p", 10);
    private BaseElement passwordErrorMessage = new BaseElement(LocatorType.XPATH, "//*[@class='form-group']/div[2]/p", 10);
    private BaseElement PasswordErrorBlank = new BaseElement(LocatorType.XPATH, "//*[@id='registerForm']/div[4]/div[2]/p", 10);
    private BaseElement verifyPasswordErrorMessage = new BaseElement(LocatorType.ID, "", 10);

    public BaseElement getDataOfBirthErrorMessage() {
        return dataOfBirthErrorMessage.get();
    }
    public BaseElement getEmailErrorMessage(){
        return emailErrorMessage.get();
    }

    public BaseElement getPostErrorMessage(){
        return postErrorMessage.get();
    }

    public BaseElement getPasswordErrorMessage(){
        return passwordErrorMessage.get();
    }

    public BaseElement getPasswordErrorBlank() {
        return PasswordErrorBlank.get();
    }

    public BaseElement getVerifyPasswordErrorMessage() {
        return verifyPasswordErrorMessage.get();
    }
}
