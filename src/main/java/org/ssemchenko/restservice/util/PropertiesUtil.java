package org.ssemchenko.restservice.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {

    public static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesUtil() {
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
    private static void loadProperties() {

        try {
            PROPERTIES.load(new FileReader("src/main/resources/application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
