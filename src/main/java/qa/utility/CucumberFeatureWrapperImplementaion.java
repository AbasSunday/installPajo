package qa.utility;

import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.runtime.model.CucumberFeature;

/**
 * The only purpose of this class is to provide custom {@linkplain #toString()},
 * making TestNG reports look more descriptive.
 *
 */
public class CucumberFeatureWrapperImplementaion implements CucumberFeatureWrapper {

private final CucumberFeature cucumberFeature;

public  CucumberFeatureWrapperImplementaion(CucumberFeature cucumberFeature) {
        this.cucumberFeature = cucumberFeature;
    }

    public  CucumberFeature getCucumberFeature() {
        return cucumberFeature;
    }

    @Override
    public String toString() {
        return cucumberFeature.getGherkinFeature().toString();
    }

}
