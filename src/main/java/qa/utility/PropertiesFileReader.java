package qa.utility;

import qa.base.SeleniumDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

public class PropertiesFileReader extends SeleniumDriver {


    public static Properties properties;
    private static final String CONFIG_PATH = "/Users/abas/Documents/freecrm/";

    public PropertiesFileReader() throws FileNotFoundException, MalformedURLException {
        super();


        try {
            properties = new Properties();

            properties.load(new FileInputStream("config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        FileInputStream fileInputStream = new FileInputStream(CONFIG_PATH +"config.properties");
        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


