package com.aqa.homeworks.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class QueriesPropertiesManager {
    private static final String QUERIES_PROPERTIES_PATH = "src/main/resources/queries.properties";
    private static Properties properties;
    public static Properties getProperties() {
        properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get(QUERIES_PROPERTIES_PATH)));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return properties;
    }
}
