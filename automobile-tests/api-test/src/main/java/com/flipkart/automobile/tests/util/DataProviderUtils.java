package com.flipkart.automobile.tests.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: shikha.agrawal
 * Date: 30/12/13
 * Time: 4:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataProviderUtils {

    protected static Map<String, String> resolveDataProviderArguments(Method testMethod) throws Exception
    {
        if (testMethod == null)
            throw new IllegalArgumentException("Test Method context cannot be null.");

        DataProviderArguments args = testMethod.getAnnotation(DataProviderArguments.class);
        if (args == null)
            throw new IllegalArgumentException("Test Method context has no DataProviderArguments annotation.");
        if (args.value() == null || args.value().length == 0)
            throw new IllegalArgumentException("Test Method context has a malformed DataProviderArguments annotation.");
        Map<String, String> arguments = new HashMap<String, String>();
        for (int i = 0; i < args.value().length; i++)
        {
            String[] parts = args.value()[i].split("=");
            arguments.put(parts[0], parts[1]);
        }
        return arguments;
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface DataProviderArguments
    {
        /**
         * String array of key-value pairs fed to a dynamic data provider.
         * Should be in the form of key=value, e.g., <br />
         * args={"foo=bar", "biz=baz"}
         */
        String[] value();
    }
}
