package com.flipkart.automobile.tests.util;

import com.flipkart.automobile.core.Http;

/**
 * Created with IntelliJ IDEA.
 * User: shikha.agrawal
 * Date: 17/06/13
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class Common {
    public static boolean isNullOrEmpty ( Object o) {
        return (o == null) || (o.getClass().toString().equalsIgnoreCase(String.class.toString()) && ( (String)o).equalsIgnoreCase(""));
    }

}
