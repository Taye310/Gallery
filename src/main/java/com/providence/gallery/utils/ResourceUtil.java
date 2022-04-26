package com.providence.gallery.utils;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class ResourceUtil {

    public static String getValue(String file, String name) {
        try{
            Properties prop = PropertiesLoaderUtils.loadAllProperties(file);
            return prop.getProperty(name);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
