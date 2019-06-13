package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class CommonSteps extends BaseSteps {

    @Given("User visit pet insurance portal ")
    public void user_visit_pet_insurance_portal_url() {
        driver.openMainPage();
    }

    @And("User opens sub page {string} in the quote page")
    public void userOpensLoginPage()
    {
        driver.openQuotePage();
    }
//    @When("I should login")
//    public void userOpenLoginPage(){
//        driver.
//    }

}

