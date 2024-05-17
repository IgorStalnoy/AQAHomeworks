package com.aqa.homeworks.utils;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@UtilityClass
public class PropertiesManager {
    private static final String QUERIES_PROPERTIES_PATH = "src/main/resources/queries.properties";
    private final Properties properties = new Properties();

    public String getQueryProperty(String name) {
        try {
            properties.load(Files.newInputStream(Paths.get(QUERIES_PROPERTIES_PATH)));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return properties.getProperty(name);
    }
}
