package com.app.utils.impl.properties;

import com.app.utils.Loader;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Service
public class PropertiesLoader implements Loader<Properties> {
    public Properties load(String path) {
        Properties properties = new Properties();
        try(FileInputStream fis = new FileInputStream(path)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

}
