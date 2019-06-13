package qa.pom.elements;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import qa.pom.base.BaseElement;
import qa.utility.enums.ElementState;
import qa.utility.enums.LocatorType;
import qa.utility.enums.PetType;
import qa.utility.Utils;

public class PetTypeSelector extends BaseElement
{
    private WebElement webElement;

    public PetTypeSelector(String xpath)
    {
        super(LocatorType.XPATH, xpath, 10);
    }

    public void selectPeType(PetType petType)
    {
        WebElement input = webElement.findElement(By.xpath("//input[@value='" + Utils.getPetTypeName(petType) + "']"));
        clickWithJavaScript(input);
    }

    public PetTypeSelector get()
    {
        findElement(ElementState.CLICKABLE);
        return this;
    }
}
