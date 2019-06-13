package qa.pom.base;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import qa.base.SeleniumDriver;
import qa.utility.enums.ElementState;
import qa.utility.enums.LocatorType;

public class BaseElement {

    private WebElement webElement;
    private LocatorType locatorType = LocatorType.ID;
    private String locator;
    private int timeOut;
    protected static SeleniumDriver driver = SeleniumDriver.getInstance();
    private final int DEFAULT_TIMEOUT = 10; //TODO might be config?

    public BaseElement(LocatorType locatorType, String locator, int timeOut) {
        this.locatorType = locatorType;
        this.locator = locator;
        this.timeOut = timeOut;
    }

    public BaseElement(String id)
    {
        new BaseElement(LocatorType.ID, id, DEFAULT_TIMEOUT);
    }

    public String getLocatorInfo() {

        return "Found By " + locatorType + "[" + locator + "]";
    }

    public void findElement(ElementState elementState) {
        switch (locatorType) {
            case ID:
                webElement = driver.getElement(By.id(locator), elementState, timeOut);
                break;
            case XPATH:
                webElement = driver.getElement(By.xpath(locator), elementState, timeOut);
                break;
            case FORM_CONTROL_NAME:
                webElement = driver.getElement(By.xpath("//*[@formcontrolname=" + locator + "]"), elementState, timeOut);
                break;
            case DATA_VALIDATION:
                webElement = driver.getElement(By.xpath("//*[@data-validation=" + locator + "]"), elementState, timeOut);
                break;
        }


    }

    public boolean isDisplayed(){
        return webElement.isDisplayed();
    }
    /**
     *
     *
     */

    public BaseElement get(ElementState elementState) {
        findElement(elementState);
        return this;
    }

    /**
     *
     *
     */

    public BaseElement get() {
        return get(ElementState.CLICKABLE);
    }

    /**
     *
     *
     * @param
     */

    public void clickWithJavaScript(WebElement webElement)
    {
        JavascriptExecutor executor = (JavascriptExecutor) driver.getWebDriver();
        executor.executeScript("arguments[0].click();", webElement);
    }

    /**
     *
     *
     */

    public void doubleClickWithJavascript(WebElement element)
    {
        String jsCode = "var evObj = new MouseEvent('dblclick', {bubbles: true, cancelable: true, view: window});";
        jsCode += " arguments[0].dispatchEvent(evObj);";
        ((JavascriptExecutor)driver).executeScript(jsCode, element);
    }

    /**
     *
     *
     */

    public WebElement getElementWhenClickable(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     *
     *
     */
    public void selectDropDown(String visibleText){
        Select select = new Select(webElement);
        select.selectByVisibleText(visibleText);
    }

    public void click(){

        webElement.click();
    }
    public String  getText(){

        return webElement.getText();

    }

    public void submit()
    {
        webElement.submit();
    }
    public void sendKeys(String e)
    {
        webElement.sendKeys(e);
    }

}




