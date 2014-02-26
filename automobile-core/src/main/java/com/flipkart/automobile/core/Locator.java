package com.flipkart.automobile.core;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: saikat
 * Date: 04/06/13
 * Time: 12:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Locator {

    public Locator(String key, String value)
    {
        this.key = key;
        this.value = value;
    }
    public String key;
    public String value;

    static Map<String, String> paths = new HashMap<String, String>();
    static Map<String, Map<String, String>> locatorsMap = new HashMap<String, Map<String, String>>();

    public static String getlocatorsDirectory()
    {
        String srcDir =  System.getProperty("user.dir") + File.separator;
        return srcDir + "automobile-tests/" + "mobile-test/" + "src/" + "main/" + "resources/" + "locators/";
    }

    public static Map<String, String> getLocators(String pageName)
    {
        Map<String, String> locators = locatorsMap.get(pageName);
        paths.put(pageName, getlocatorsDirectory() + pageName.toString() + ".properties");

        if(locators != null)
            return locators;

        locators = new HashMap<String, String>();
        Properties props = new Properties();
        try
        {
            FileInputStream fis = new FileInputStream(paths.get(pageName));
            props.load(fis);
            fis.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Enumeration em = props.keys();
        while (em.hasMoreElements())
        {
            String key = em.nextElement().toString();
            locators.put(key, props.getProperty(key));

        }

        locatorsMap.put(pageName, locators);

        return locators;
    }


}
