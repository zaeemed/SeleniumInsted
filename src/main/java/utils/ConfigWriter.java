package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigWriter {

    public static void writeConfig(String key, String value) {
        Properties properties = new Properties();

        properties.setProperty(key, value);

        try (FileOutputStream outputStream = new FileOutputStream("config.properties")) {
            properties.store(outputStream, "Configuration Properties");
            System.out.println("Configuration file created/updated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to write to configuration file.");
        }
    }
}

