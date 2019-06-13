package qa.pom.pages;


import qa.pom.base.BaseElement;
import qa.pom.base.BasePage;
import qa.utility.enums.LocatorType;

public class HomePage extends BasePage {


    private BaseElement getWelcomePage = new BaseElement(LocatorType.XPATH, "//*[contains(text(),'Welcome to your pet portal')]", 10);

    public BaseElement getWelcomePage(){
        return getWelcomePage.get();
    }

}
