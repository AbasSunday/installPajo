package qa.pom.pages;

import io.qameta.allure.Step;
import qa.pom.base.BaseElement;
import qa.utility.enums.LocatorType;

public class LoginPage extends BaseSteps{

    private BaseElement loginEmail = new BaseElement(LocatorType.ID, "loginEmail", 10);
    private BaseElement loginPassword = new BaseElement(LocatorType.ID, "loginPassword", 10);
    private BaseElement showForgottenPasswordModal = new BaseElement("showForgottenPasswordModal");
    private BaseElement rememberUsername = new BaseElement(LocatorType.ID, "rememberUsername", 10);
    private BaseElement loginSubmitButton = new BaseElement(LocatorType.ID, "loginSubmit", 10);
    private BaseElement loginTab = new BaseElement(LocatorType.ID, "login-tab", 10);
    private BaseElement forgottenEmail = new BaseElement(LocatorType.XPATH,"//*[@id='loginForm']/div/div[3]/a",20);
    private BaseElement loginTitle = new BaseElement(LocatorType.ID, "register-login-title", 10);
    private BaseElement resetPassword = new BaseElement(LocatorType.ID,"resetPasswordEmail",10);
    private BaseElement confirmEmail = new BaseElement(LocatorType.ID,"confirmEmail",10);
    private BaseElement resetMessages = new BaseElement(LocatorType.ID,"myModalLabel",10);
    public BaseElement closeMessages = new BaseElement(LocatorType.XPATH, "//*[@class='btn btn-primary btn-block close-modal']", 10);

    @Step("getting Close confirm Messages")
    public BaseElement getCloseMessages() {
        return closeMessages.get();
    }

    @Step("Login Email field")
    public BaseElement getLoginEmail() {
        return loginEmail.get();

    }
    @Step("getting login password field")
    public BaseElement getLoginPassword() {
        return loginPassword.get();
    }

    @Step("getting forget password Tab")
    public BaseElement getShowForgottenPasswordModal() {
        return showForgottenPasswordModal.get();
    }

    @Step("getting login password field")
    public BaseElement getRememberUsername() {
        return rememberUsername.get();
    }
    @Step("getting submit button for Login")
    public BaseElement getLoginSubmitButton() {

        return loginSubmitButton.get();
    }
    @Step("getting login Tab")
    public BaseElement getLoginTab() {

        return loginTab.get();
    }
    @Step("getting forgotten Login Email link")
    public BaseElement getForgottenEmail() {
        return forgottenEmail.get();
    }
    @Step("getting reset password")
    public BaseElement getResetPassword() {
        return resetPassword.get();
    }
    @Step("getting confirm Email")
    public BaseElement getConfirmEmail() {
        return confirmEmail.get();
    }
    @Step("getting page Title")
    public String getTitle() {
        return driver.getTitle();
    }

    public BaseElement getLoginTitle() {
        return loginTitle.get();
    }

    public BaseElement getResetMessages() {
        return resetMessages.get();
    }
}


