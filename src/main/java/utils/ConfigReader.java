package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * This class works with .properties file.
 */

public class ConfigReader {
    private static Properties properties = new Properties();


    /**
     * This method loads properties from a file.
     *
     * @return The loaded properties.
     */
    public static Properties loadProperties() {
        try {
            InputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    /**
     * This method returns the username property.
     *
     * @return The username property.
     */
    public static String getUsernameProperty() {
        return properties.getProperty("username");
    }

    /**
     * This method returns the password property.
     *
     * @return The password property.
     */
    public static String getPasswordProperty() {
        return properties.getProperty("password");
    }

    /**
     * This method returns the browser property.
     *
     * @return The browser property.
     */
    public static String getBrowserProperty() {
        return loadProperties().getProperty("browser");
    }

    /**
     * This method returns the URL property.
     *
     * @return The URL property.
     */
    public static String getURLProperty() {
        return properties.getProperty("url");
    }
}