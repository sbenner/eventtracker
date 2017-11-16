package com.test.eventtracker;

import org.springframework.boot.SpringApplication;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: sbenner
 * Date: 7/19/17
 * Time: 3:27 PM
 */
public class DefaultPropertiesApplication {
    public DefaultPropertiesApplication() {
    }

    public static void run(Class cls, String[] args) {
        SpringApplication app = new SpringApplication(new Object[]{cls});
        app.setDefaultProperties(getDefaultProperties(cls));
        app.run(args);
    }

    private static Properties getDefaultProperties(Class cls) {
        Properties properties = new Properties();
        if (cls.getPackage().getImplementationVersion() == null) {
            properties.setProperty("version", "unknown");
        } else {
            properties.setProperty("version", cls.getPackage().getImplementationVersion());
        }

        return properties;
    }
}