package qa.base;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import qa.utility.enums.ElementState;
import qa.utility.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;



public class SeleniumDriver implements WebDriver {

    // ==================================================
    // VARIABLES
    // ==================================================

    private static SeleniumDriver instance;
    protected static WebDriver driver;
    private static Properties properties;
    private static final Logger logger = LogManager.getLogger(SeleniumDriver.class);
    // TODO: Update the Listener Instance
    // TODO: Add some browser Instance for parallel testing

    // ==================================================
    // CLASS LOGIC
    // ==================================================

    private void initialize() {
        logger.info("test Log4j");
        reduceChromeDriverLogging();
        loadConfigFile();


        String chromePath = System.getProperty("user.dir") + "/resources/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();


        boolean forceSize = getBooleanFromConfig("BROWSER_FORCE_WINDOW_SIZE");

        if (forceSize)
        {
            int width = getIntFromConfig("BROWSER_WINDOW_WIDTH");
            int height = getIntFromConfig("BROWSER_WINDOW_HEIGHT");
            driver.manage().window().setSize(new Dimension(width, height));
        }

        Utils.print("Browser size: " + driver.manage().window().getSize() + "[" + (forceSize ? "forced" : "not forced") + "]");

        if (getBooleanFromConfig("RUN_BROWSER_MAXIMIZED"))
        {
            driver.manage().window().maximize();
        }
        driver.manage().deleteAllCookies();
    }
    /**
     * Reduces number of ChromeDriver log output to the console,
     * makes console cleaner and easier to see important output.
     * Use with caution, it might disable important logs in case of crash etc.
     */
    private static void reduceChromeDriverLogging() {
        System.setProperty("webdriver.chrome.silentOutput", "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
    }

    private static void loadConfigFile() {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream("config.properties");
            properties.load(fis);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void printTestDetails() {
        Utils.print("Starting test execution...");
        Utils.printSeparator();
        Utils.print("| Time stamp   | " + Utils.getTimeStamp());
        Utils.print("| Java version | " + Utils.getJavaVersion());
        Utils.printSeparator();
    }
    /**
     * Draws red border around element, useful for debuging.
     *
     * @param element that will have border around it
     */
    public void drawBorder(WebElement element) {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].style.border='3px solid red'", element);
        } catch (StaleElementReferenceException ignored) {

        }
    }

    public void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void openMainPage() {
        driver.get(properties.getProperty("AUTH_ACCESS_LINK"));
        driver.navigate().to(properties.getProperty("REGISTER_LOGIN"));
    }

    public void openQuotePage() {
        driver.get(properties.getProperty("_EMAIL_LINK") + "/");
        driver.navigate().to("_EMAIL_LINK");
    }
    // ==================================================
    // GETTERS AND SETTERS
    // ==================================================

    public Properties getProperties() {
        return properties;
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    /**
     * This method is used to switch and alert pop-up screen
     */

    public void switchToAlert() {
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
    }

    /**
     * This method is used to locate element on the web page that is in a specified state
     * and also wait till the expected condition is met
     */

    public WebElement getElement(By by, ElementState elementState, int timeOut) {
        switch (elementState) {
            case CLICKABLE:
                return driverWait(timeOut).until(ExpectedConditions.elementToBeClickable(by));
            case VISIBLE:
                return driverWait(timeOut).until(ExpectedConditions.visibilityOfElementLocated(by));
            case PRESENT:
                return driverWait(timeOut).until(ExpectedConditions.presenceOfElementLocated(by));
        }
        return null;
    }

    private WebDriverWait driverWait(int timeOut) {

        return new WebDriverWait(driver, timeOut);
    }

    public static Boolean getBooleanFromConfig(String propertyKey) {
        return Boolean.parseBoolean(properties.getProperty(propertyKey));
    }

    public static int getIntFromConfig(String propertyKey) {
        return Integer.parseInt(properties.getProperty(propertyKey));
    }

    // ==================================================
    // SINGLETON HOLDER
    // ==================================================

    public static SeleniumDriver getInstance() {
        if (instance == null) {
            instance = new SeleniumDriver();

        }
        return instance;
    }

    protected SeleniumDriver() {
        initialize();
    }

    public void onTestExecutionFinished() {
        if (getBooleanFromConfig("CLOSE_SELENIUM_ON_EXECUTION_FINISHED")) {
            driver.close();
        }
    }

    // ==================================================
    // INTERFACE IMPLEMENTATION
    // ==================================================

    public void get(String url) {
        driver.get(url);

    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void close() {

    }

    public void quit() {

    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public TargetLocator switchTo() {
        return driver.switchTo();
    }
    public Navigation ReNavigate() {
        return driver.navigate();
    }

    public Navigation navigate() {
        return driver.navigate();
    }

    public Options manage() {
        return driver.manage();
    }
}
