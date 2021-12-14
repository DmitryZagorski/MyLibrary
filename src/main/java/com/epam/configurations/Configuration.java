package com.epam.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static final Logger Log = LoggerFactory.getLogger(Configuration.class);
    private static Properties properties;

    public static Properties getApplicationProperties() {
        if (properties == null) {
            try (InputStream props = Configuration.class.getClassLoader().getResourceAsStream("application.properties")) {
                properties = new Properties();
                properties.load(props);
            } catch (IOException e) {
                Log.error("Application initialization failed.");
                throw new IllegalStateException(e);
            }
        }
        return properties;
    }
}
