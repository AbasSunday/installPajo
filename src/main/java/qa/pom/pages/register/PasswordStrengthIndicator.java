package qa.pom.pages.register;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import qa.pom.base.BaseElement;
import qa.utility.enums.ElementState;
import qa.utility.enums.LocatorType;


public class PasswordStrengthIndicator extends BaseElement {

        private PasswordStrength passwordStrength;
        private WebElement webElement;

        public enum PasswordStrength {

            WEAK("ls-level-2"),
            OK("ls-level-3"),
            GOOD("ls-level-4"),
            GREAT("ls-level-5");

            private final String className;

            PasswordStrength(String className) {
                this.className = className;
            }

            public String getClassName() {
                return className;
            }
        }

        public PasswordStrengthIndicator(String id) {
            super(LocatorType.ID, id, 10);
        }

        public String getStrengthLabelValue() {
            return webElement.findElement(By.xpath("//span[@class='current-strength']")).getText().trim();
        }

        public WebElement getPasswordStrengthMeter() {
            return webElement.findElement(By.id("passwordStrengthMeter"));
        }

        public PasswordStrengthIndicator get() {
            findElement(ElementState.VISIBLE);
            return this;
        }
    }


