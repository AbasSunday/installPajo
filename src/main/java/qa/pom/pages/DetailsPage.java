package qa.pom.pages;


import qa.pom.base.BaseElement;
import qa.utility.enums.LocatorType;

public class  DetailsPage{

    private BaseElement petNames = new BaseElement(LocatorType.ID, "pets.0.name", 10);
    private BaseElement petTypeDog = new BaseElement(LocatorType.XPATH, "//*[text()='Get A Quote']", 10);
    private BaseElement petTypeCat = new BaseElement(LocatorType.ID, "//*[text()='Get A Quote']", 10);
    private BaseElement petBreedType = new BaseElement(LocatorType.XPATH, "//*[text()='Get A Quote']", 10);


    public BaseElement getPetName() {
        return petNames.get();
    }

    public BaseElement getPetType() {
        return petTypeDog.get();
    }
    public BaseElement getPetTypeCat() {
        return petTypeCat.get();
    }

    public BaseElement getPetBreedType() {
        return petBreedType.get();
    }


}
