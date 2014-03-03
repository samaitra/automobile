package com.flipkart.automobile.tests.util;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: shikha.agrawal
 * Date: 17/06/13
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataProviders {

    @DataProvider(name="getDataFromFile")
    public static Iterator<Object[]> getDataFromFile(Method testMethod) throws Exception
    {
        Map<String, String> arguments = DataProviderUtils.resolveDataProviderArguments(testMethod);
        List<String> lines = DataProviders.getRawLinesFromFile(arguments.get("filePath"));
        List<Object[]> data = new ArrayList<Object[]>();
        for (String line : lines)
        {
            data.add(new Object[]{line});
        }
        return data.iterator();
    }

    @SuppressWarnings("unchecked")
    public static List<String> getRawLinesFromFile(String filePath) throws Exception
    {
        InputStream is = new FileInputStream(new File(filePath));
        List<String> lines = IOUtils.readLines(is, "UTF-8");
        is.close();
        return lines;
    }

}
